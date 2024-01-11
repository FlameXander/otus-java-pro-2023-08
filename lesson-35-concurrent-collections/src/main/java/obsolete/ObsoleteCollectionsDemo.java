package obsolete;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

/**
 * @author Oleg Cherednik
 * @since 11.01.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ObsoleteCollectionsDemo {

    public static void main(String... args) {
        hashtable();
        vector();
    }

    public static void hashtable() {
        Map<String, Integer> map = new Hashtable<>();
        map.put("one", 1);
        map.put("two", 2);
    }

    public static void vector() {
        List<String> list = new Vector<>();
        list.add("one");
        list.add("two");
    }
}
