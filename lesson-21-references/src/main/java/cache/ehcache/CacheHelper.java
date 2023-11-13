package cache.ehcache;

import lombok.Getter;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

public class CacheHelper {

    private static final String CACHE_SQUARED_NUMBER = "squaredNumber";

    private CacheManager cacheManager;
    @Getter
    private Cache<Integer, Integer> squareNumberCache;

    public CacheHelper() {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        squareNumberCache = cacheManager
                .createCache(CACHE_SQUARED_NUMBER, CacheConfigurationBuilder
                        .newCacheConfigurationBuilder(
                                Integer.class, Integer.class,
                                ResourcePoolsBuilder.heap(10)));
    }

    // standard getters and setters
}
