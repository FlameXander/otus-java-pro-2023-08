package proxy;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProxyDemo {

    public static void main(String... args) {
        /*
         При помощи прокси есть возможность полностью контролировать работу
         с реальным объектом ExpensiveObject.
         */
        ExpensiveObject object = new ExpensiveObjectProxy();
        object.process();
        object.process();
    }

}
