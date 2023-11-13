package cache.ehcache;

import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class SquaredCalculator {

    private final CacheHelper cache;

    public int getSquareValueOfNumber(int input) {
        if (cache.getSquareNumberCache().containsKey(input))
            return cache.getSquareNumberCache().get(input);

        System.out.println("Calculating square value of "
                                   + input + " and caching result.");

        int squaredValue = (int) Math.pow(input, 2);
        cache.getSquareNumberCache().put(input, squaredValue);

        return squaredValue;
    }

    // standard getters and setters

    public static void main(String... args) {
        SquaredCalculator squaredCalculator = new SquaredCalculator(new CacheHelper());
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            int input = random.nextInt(20);
            int squaredValue = squaredCalculator.getSquareValueOfNumber(input);
            System.out.format("%d ^ 2 = %d\n", input, squaredValue);
        }
    }

}
