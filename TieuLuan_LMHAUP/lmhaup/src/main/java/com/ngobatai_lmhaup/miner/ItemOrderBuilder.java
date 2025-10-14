package com.ngobatai_lmhaup.miner;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.ngobatai_lmhaup.bounds.TMAUBCaculator;
import com.ngobatai_lmhaup.model.UtilityDatabase;

/**
 * Class để build item order theo support
 */
public class ItemOrderBuilder {

    private final UtilityDatabase db;
    private final double minutil;

    public ItemOrderBuilder(UtilityDatabase db, double minutil) {
        this.db = db;
        this.minutil = minutil;
    }

    /**
     * Filter promising items (TMAUB >= minutil)
     */
    public Set<Integer> filterPromisingItems(TMAUBCaculator.TmaubStats st) {
        Set<Integer> promising = st.tmaubPerItem.entrySet().stream()
                .filter(e -> e.getValue() >= minutil)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        System.out.println("Promising items after TMAUB filter: " + promising);
        return promising;
    }

    /**
     * Build item order theo ascending support
     */
    public List<Integer> buildOrder(TMAUBCaculator.TmaubStats st, Set<Integer> promising) {
        List<Integer> orderList = promising.stream()
                .sorted(Comparator.comparingInt(i -> st.support.getOrDefault(i, 0)))
                .collect(Collectors.toList());

        System.out.println("Item order (ascending support): " + orderList);
        System.out.println();

        setDatabaseOrder(orderList);
        return orderList;
    }

    /**
     * Set order arrays trong database
     */
    private void setDatabaseOrder(List<Integer> orderList) {
        int maxItem = db.items.keySet().stream().mapToInt(i -> i).max().orElse(0);
        int[] order = new int[maxItem + 1];
        int[] rev = new int[orderList.size()];

        for (int r = 0; r < orderList.size(); r++) {
            int item = orderList.get(r);
            order[item] = r;
            rev[r] = item;
        }

        db.itemOrder = order;
        db.orderToItem = rev;
    }
}
