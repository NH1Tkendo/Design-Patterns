package com.ngobatai_lmhaup.miner;

import java.util.ArrayList;
import java.util.List;

import com.ngobatai_lmhaup.struct.TAList;

// Class để generate patterns (2-itemsets, extensions)
public class PatternGenerator {

    private final Joiner joiner;
    private final CacheManager cacheManager;
    private int[] itemOrder; // Maps item ID to order position

    public PatternGenerator(Joiner joiner, CacheManager cacheManager, int[] itemOrder) {
        this.joiner = joiner;
        this.cacheManager = cacheManager;
        this.itemOrder = itemOrder;
    }

    /**
     * Set item order array (called after order is built)
     */
    public void setItemOrder(int[] itemOrder) {
        this.itemOrder = itemOrder;
    }

    // Generate tất cả 2-itemsets từ 1-itemsets
    public List<TAList> generateTwoItemsets(List<TAList> seeds) {
        List<TAList> twoItemsets = new ArrayList<>();

        for (int i = 0; i < seeds.size(); i++) {
            for (int j = i + 1; j < seeds.size(); j++) {
                TAList first = seeds.get(i);
                TAList second = seeds.get(j);

                TAList joined = joiner.join(first, second, 0);
                if (joined.entries.isEmpty())
                    continue;

                cacheManager.cachePatternUtility(joined);
                twoItemsets.add(joined);
            }
        }

        return twoItemsets;
    }

    /**
     * Generate extensions từ pattern hiện tại
     */
    public List<TAList> generateExtensions(List<TAList> patterns, int currentIndex, int prefixLen) {
        List<TAList> extensions = new ArrayList<>();
        TAList pattern = patterns.get(currentIndex);

        for (int j = currentIndex + 1; j < patterns.size(); j++) {
            TAList next = patterns.get(j);

            if (!canJoin(pattern, next, prefixLen))
                continue;

            TAList joined = joiner.join(pattern, next, prefixLen);
            if (joined.entries.isEmpty())
                continue;

            cacheManager.cachePatternUtility(joined);
            extensions.add(joined);
        }

        return extensions;
    }

    /**
     * Kiểm tra xem hai pattern có thể join với nhau không
     * So sánh theo order position, không phải item ID
     */
    private boolean canJoin(TAList p1, TAList p2, int prefixLen) {
        if (p1.len != p2.len) {
            return false;
        }

        // Kiểm tra prefix giống nhau
        for (int i = 0; i < prefixLen; i++) {
            if (p1.itemset[i] != p2.itemset[i]) {
                return false;
            }
        }

        // So sánh order position của item cuối cùng, KHÔNG phải item ID
        int lastItem1 = p1.itemset[p1.len - 1];
        int lastItem2 = p2.itemset[p2.len - 1];

        // Get order positions from itemOrder array
        int order1 = itemOrder[lastItem1];
        int order2 = itemOrder[lastItem2];

        return order1 < order2;
    }
}
