import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class ConcurrentDataStructuresDemoTest {
    @RepeatedTest(10)
    void helloTest() throws InterruptedException {
        ConcurrentDataStructuresDemo.main(new String[0]);
    }
}