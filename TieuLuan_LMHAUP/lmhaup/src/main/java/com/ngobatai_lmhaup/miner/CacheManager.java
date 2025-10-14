package com.ngobatai_lmhaup.miner;

import java.util.HashMap;
import java.util.Map;

import com.ngobatai_lmhaup.struct.TAList;
import com.ngobatai_lmhaup.struct.TAListEntry;

/**
 * Class quản lý cache của pattern utilities
 */
public class CacheManager {

    private final Map<String, Map<Integer, Double>> patternUtilCache;
    private final int maxCacheSize;

    public CacheManager(int maxCacheSize) {
        this.patternUtilCache = new HashMap<>();
        this.maxCacheSize = maxCacheSize;
    }

    /**
     * Cache utility của một pattern
     */
    public void cachePatternUtility(TAList pattern) {
        // Kiểm tra kích thước cache
        if (patternUtilCache.size() >= maxCacheSize) {
            System.out.printf("⚠ Cache size limit reached (%d), clearing cache...%n", maxCacheSize);
            clearCache();
        }

        // Chỉ cache patterns ngắn (≤ 5 items)
        if (pattern.len <= 5) {
            Map<Integer, Double> utilMap = new HashMap<>();
            for (TAListEntry e : pattern.entries) {
                utilMap.put(e.tid, e.util);
            }
            patternUtilCache.put(itemsetToString(pattern.itemset), utilMap);
        }
    }

    /**
     * Cache utilities của nhiều TA-Lists
     */
    public void cacheMultiple(Iterable<TAList> taLists) {
        for (TAList taList : taLists) {
            Map<Integer, Double> utilMap = new HashMap<>();
            for (TAListEntry e : taList.entries) {
                utilMap.put(e.tid, e.util);
            }
            patternUtilCache.put(itemsetToString(taList.itemset), utilMap);
        }
    }

    /**
     * Clear toàn bộ cache
     */
    public void clearCache() {
        patternUtilCache.clear();
        System.gc();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Get cache map (để set vào Joiner)
     */
    public Map<String, Map<Integer, Double>> getCache() {
        return patternUtilCache;
    }

    /**
     * Get cache size
     */
    public int size() {
        return patternUtilCache.size();
    }

    /**
     * Convert itemset thành cache key
     */
    private String itemsetToString(int[] itemset) {
        StringBuilder sb = new StringBuilder();
        for (int item : itemset) {
            sb.append(item).append(",");
        }
        return sb.toString();
    }
}
