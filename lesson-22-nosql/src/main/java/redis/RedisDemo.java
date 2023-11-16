package redis;

import java.util.Map;

/*
 * 1. docker pull redis/redis-stack-server
 * 2. docker run -d --name redis -p 6379:6379 redis/redis-stack-server:latest
 */
public class RedisDemo {

    private static final String HOST_NAME = "localhost";
    private static final int PORT = 6379;

    public static void main(String... args) throws Exception {
        try (RedisClient client = RedisClient.create(HOST_NAME, PORT)) {
            foo(client);
        }
    }

    private static void foo(RedisClient client) {
        dataTypeString_KeyValue(client);
        dataTypeLists_Queue(client);
        dataTypeSets(client);
        dataTypeHashes(client);
        dataTypeSortedSets(client);
    }

    private static void dataTypeString_KeyValue(RedisClient client) {
        System.out.println("--- Data type 'String' ---");
        String key = "events/city/rome";
        String value = "32,15,223,828";

        client.setString(key, value);
        String response = client.getString(key);
        System.out.format("Set/get: key='%s', value='%s', response='%s'\n", key, value, response);
    }

    private static void dataTypeLists_Queue(RedisClient client) {
        System.out.println("\n--- Data type 'Lists' ---");

        String key = "queue#tasks";
        long listSize = client.pushListItems(key, "firstTask", "secondTask");
        System.out.format("list size: %s\n", listSize);

        listSize = client.pushListItems(key, "thirdTask");
        System.out.format("list size: %s\n", listSize);

        System.out.println(client.popListItems(key));
        System.out.println(client.popListItems(key));
        System.out.println(client.popListItems(key));
        System.out.println(client.popListItems(key));   // null
    }

    private static void dataTypeSets(RedisClient client) {
        System.out.println("\n--- Data type 'Sets' ---");

        String key = "nicknames";
        String member2 = "nickname#2";

        client.addMembers(key, "nickname#1", member2);
        client.addMembers(key, "nickname#1"); // duplication (will not be added)

        System.out.format("Members: %s\n", client.getMembers(key));
        System.out.format("Member '%s' exists: %s\n", member2, client.isMemberExists(key, member2));
    }

    private static void dataTypeHashes(RedisClient client) {
        System.out.println("\n--- Data type 'Hashes' ---");

        String key = "user#1";
        String field = "name";

        client.setKeyFieldValue(key, field, "Peter");
        client.setKeyFieldValue(key, "job", "politician");

        System.out.format("key='%s', field='%s', value='%s'\n",
                          key, field, client.getValueByKeyField(key, field));
        System.out.format("key '%s': %s\n", key, client.getFieldValueByKey(key));
    }

    private static void dataTypeSortedSets(RedisClient client) {
        System.out.println("\n--- Data type 'SortedSets' ---");

        String key = "ranking";
        String playerOne = "PlayerOne";
        Map<String, Double> scores = Map.of(playerOne, 3000.0,
                                            "PlayerTwo", 1500.0,
                                            "PlayerThree", 8200.0);

        scores.forEach((name, score) -> client.addScoreMember(key, score, name));
        System.out.println("Top 1 member: " + client.getTopOneMember(key));
        // PlayerOne's rank is 1, because he is 2nd in the ranking list
        System.out.format("Member '%s' rank is %d\n", playerOne, client.getMemberRank(key, playerOne));
    }
}
