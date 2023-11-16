package redis;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RedisClient implements AutoCloseable {

    private final Jedis jedis;

    public static RedisClient create(String hostname, int port) {
        Jedis jedis = new Jedis(hostname, port);
        return new RedisClient(jedis);
    }

    public String setString(String key, String value) {
        return jedis.set(key, value);
    }

    public String getString(String key) {
        return jedis.get(key);
    }

    public long pushListItems(String key, String... strings) {
        return jedis.lpush(key, strings);
    }

    public String popListItems(String key) {
        return jedis.rpop(key);
    }

    public long addMembers(String key, String... members) {
        return jedis.sadd(key, members);
    }

    public Set<String> getMembers(String key) {
        return jedis.smembers(key);
    }

    public boolean isMemberExists(String key, String member) {
        return jedis.sismember(key, member);
    }

    public long setKeyFieldValue(String key, String field, String value) {
        return jedis.hset(key, field, value);
    }

    public String getValueByKeyField(String key, String field) {
        return jedis.hget(key, field);
    }

    public Map<String, String> getFieldValueByKey(String key) {
        return jedis.hgetAll(key);
    }

    public long addScoreMember(String key, double score, String member) {
        return jedis.zadd(key, score, member);
    }

    public String getTopOneMember(String key) {
        return jedis.zrevrange(key, 0, 1).iterator().next();
    }

    public long getMemberRank(String key, String member) {
        return jedis.zrevrank("ranking", "PlayerOne");
    }

    // ---------- AutoCloseable ----------

    @Override
    public void close() throws Exception {
        jedis.close();
    }
}
