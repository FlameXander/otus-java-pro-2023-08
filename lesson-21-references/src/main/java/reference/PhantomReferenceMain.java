package reference;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PhantomReferenceMain {

    public static void main(String... args) {
        // create large objects
        List<LargeArray> largeArrays = IntStream.range(0, 10)
                                                .mapToObj(LargeArray::new)
                                                .collect(Collectors.toList());

        // create phantom references
        ReferenceQueue<LargeArray> refQueue = new ReferenceQueue<>();
        List<LargeArrayFinalizer> phantomReferences =
                largeArrays.stream()
                           .map(largeArray -> new LargeArrayFinalizer(largeArray, refQueue))
                           .collect(Collectors.toList());

        // clear strong references
        largeArrays = null;
        System.gc();

        // check that phantom references are enqued
        for (LargeArrayFinalizer phantomReference : phantomReferences) {
            System.out.println(phantomReference.getId() + " - " + phantomReference.isEnqueued());
        }

        Reference<?> ref;

        // get all available phantom references from the ReferenceQueue and clear it
        while ((ref = refQueue.poll()) != null) {
            ((LargeArrayFinalizer)ref).finalizeResources();
            ref.clear();
        }
    }

    @RequiredArgsConstructor
    public static class LargeArray {

        @Getter
        private final int id;
        private final int[] arr = new int[1_000_000];
    }

    public static class LargeArrayFinalizer extends PhantomReference<LargeArray> {

        @Getter
        private final int id;

        public LargeArrayFinalizer(LargeArray largeArray, ReferenceQueue<LargeArray> refQueue) {
            super(largeArray, refQueue);
            id = largeArray.getId();
        }

        public void finalizeResources() {
            // free resources
            System.out.println(id + " - clearing ...");
        }

    }
}



