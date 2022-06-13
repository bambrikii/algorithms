package org.bambrikii.examples.matching;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;

import static java.text.MessageFormat.format;

/**
 * https://brilliant.org/wiki/hungarian-matching/
 * <p>
 * The Hungarian Method
 * <p>
 * Subtract the smallest entry in each row from all the other entries in the row. This will make the smallest entry in the row now equal to 0.
 * Subtract the smallest entry in each column from all the other entries in the column. This will make the smallest entry in the column now equal to 0.
 * Draw lines through the row and columns that have the 0 entries such that the fewest lines possible are drawn.
 * If there are nn lines drawn, an optimal assignment of zeros is possible and the algorithm is finished. If the number of lines is less than nn, then the optimal number of zeroes is not yet reached. Go to the next step.
 * Find the smallest entry not covered by any line. Subtract this entry from each row that isn’t crossed out, and then add it to each column that is crossed out. Then, go back to Step 3.
 */
public class HungarianAlgo {
    private List<LinkedHashMap<Integer, Integer>> allAssigned;

    public static void main(String[] args) {
        HungarianAlgo algo = new HungarianAlgo();
        algo
                .let(1, 1, 108).let(2, 1, 150).let(3, 1, 122)
                .let(1, 2, 125).let(2, 2, 135).let(3, 2, 148)
                .let(1, 3, 150).let(2, 3, 175).let(3, 3, 250)
                .print()
                .calc()
                .print();
    }

    private final Map<Integer, Map<Integer, Integer>> curr = new HashMap<>();
    private final Map<Integer, Set<Integer>> currReverted = new HashMap<>();
    private final Map<Integer, Map<Integer, Integer>> orig = new HashMap<>();

    public HungarianAlgo let(int who, int what, int cost) {
        Function<Integer, Map<Integer, Integer>> ifAbsent = integer -> new HashMap<>();
        orig.computeIfAbsent(who, ifAbsent).put(what, cost);
        curr.computeIfAbsent(who, ifAbsent).put(what, cost);
        currReverted.computeIfAbsent(what, integer -> new HashSet<>()).add(who);

        return this;
    }

    private boolean log = false;

    private void log(String message, Object... args) {
        if (!log) {
            return;
        }
        System.out.println(format(message, args));
    }

    private HungarianAlgo print() {
        if (allAssigned == null) {
            printDefault();
        } else {
            allAssigned.forEach(assigned -> printAssigned(assigned));
        }
        return this;
    }

    private HungarianAlgo printAssigned(LinkedHashMap<Integer, Integer> assigned) {
        print((who, what, cost) -> {
            char mark = assigned != null && Objects.equals(assigned.get(who), what) ? '*' : ' ';
            System.out.print(format("{0}x{1}={2}{3} ", who, what, orig.get(who).get(what), mark));
        });
        return this;
    }

    private HungarianAlgo printDefault() {
        print((who, what, cost) -> System.out.print(format("{0}x{1}={2}  ", who, what, orig.get(who).get(what))));
        return this;
    }

    private void print(PrintRow printRow) {
        for (Map.Entry<Integer, Map<Integer, Integer>> who : curr.entrySet()) {
            Integer whoKey = who.getKey();
            for (Map.Entry<Integer, Integer> what : who.getValue().entrySet()) {
                Integer whatKey = what.getKey();
                Integer whatValue = what.getValue();
                printRow.doPrint(whoKey, whatKey, whatValue);
            }
            System.out.println();
        }
        System.out.println();
    }

    private HungarianAlgo calc() {
        logPrint();
        if (allAssigned()) {
            return this;
        }
        while (true) {
            balanceRows();
//            balanceColumns();
            logPrint();

            if (allAssigned()) {
                break;
            }

            lowerMin();
            logPrint();

            balanceColumns();
            logPrint();

            if (allAssigned()) {
                break;
            }
        }
        return this;
    }

    private void logPrint() {
        if (!log) {
            return;
        }
        print();
    }

    private Boolean allAssigned() {
        // each row has distinct assignment
        Integer[] whos = curr.keySet().toArray(new Integer[0]);
        List<LinkedHashMap<Integer, Integer>> allAssigned = new ArrayList<>();
        LinkedHashMap<Integer, Integer> assigned = new LinkedHashMap<>();
        whoAssigned(0, 0, whos, assigned, new HashSet<>(), allAssigned);
        boolean yes = allAssigned.size() > 0;
        if (yes) {
            this.allAssigned = allAssigned;
        }
        return yes;
    }

    private void whoAssigned(int whoPos, int whatPos, Integer[] whos, Map<Integer, Integer> assigned, Set<Integer> whatsAssigned, List<LinkedHashMap<Integer, Integer>> allAssigned) {
        for (int i = whoPos; i < whos.length; i++) {
            Integer who = whos[i];
            Set<Integer> whatKeys = curr.get(who).keySet();
            Integer[] whats = whatKeys.toArray(new Integer[whatKeys.size()]);
            whatAssigned(i, whatPos, whos, whats, assigned, whatsAssigned, allAssigned);
        }
    }

    private void whatAssigned(int whoPos, int whatPos, Integer[] whos, Integer[] whats, Map<Integer, Integer> assigned, Set<Integer> whatsAssigned, List<LinkedHashMap<Integer, Integer>> allAssigned) {
        Integer who = whos[whoPos];
        for (int j = whatPos; j < whats.length; j++) {
            Integer what = whats[j];
            Integer cost = curr.get(who).get(what);
            if (cost == 0) {
                boolean has = whatsAssigned.contains(what);
                if (!has) {
                    log("new zero cost: {0}x{1}={2}", who, what, cost);
                    assigned.put(who, what);
                    whatsAssigned.add(what);
                }
                // check all assigned
                if (assigned.size() == whos.length && whatsAssigned.size() == whats.length) {
                    log("all assigned: {0}x{1}={2}", who, what, cost);
                    allAssigned.add(new LinkedHashMap<>(assigned));
                } else {
                    whoAssigned(whoPos + 1, 0, whos, assigned, whatsAssigned, allAssigned);
                }
                if (!has) {
                    whatsAssigned.remove(what);
                    assigned.remove(who, what);
                }
            } else {
                whoAssigned(whoPos + 1, 0, whos, assigned, whatsAssigned, allAssigned);
            }
        }
    }

    private void lowerMin() {
        TreeMap<Integer, List<Integer>> nonZerosFirst = new TreeMap<>();
        Map<Integer, Integer> minNonZeros = new HashMap<>();
        Map<Integer, Integer> minColumns = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, Integer>> who : curr.entrySet()) {
            Integer whoKey = who.getKey();
            Map<Integer, Integer> whats = who.getValue();

            Integer numberOfZeros = Math.toIntExact(whats.values().stream().filter(val -> val == 0).count());
            Integer minNonZero = whats.values().stream().filter(val -> val != 0).min(Comparator.comparingInt(o -> o)).get();

            minNonZeros.put(whoKey, minNonZero);

            minColumns.put(whoKey, whats.entrySet().stream().filter(e -> e.getValue() == 0).findFirst().get().getKey());
            nonZerosFirst.computeIfAbsent(numberOfZeros, (entry) -> new ArrayList<>()).add(whoKey);
        }
        List<Integer> nonZeroWhoKeys = nonZerosFirst.entrySet().stream().findFirst().get().getValue();
        var ref = new Object() {
            Integer minKey = null;
            Integer minVal = null;
            Integer minWhat = null;
        };
        nonZeroWhoKeys.forEach(key -> {
            if (ref.minVal == null) {
                Integer val = minNonZeros.get(key);
                ref.minKey = key;
                ref.minVal = val;
                ref.minWhat = minColumns.get(key);
            } else {
                Integer val = minNonZeros.get(key);
                if (ref.minVal > val) {
                    ref.minKey = key;
                    ref.minVal = val;
                    ref.minWhat = minColumns.get(key);
                }
            }
        });
        if (ref.minKey == null) {
            return;
        }
        if (ref.minKey != null) {
            nonZeroWhoKeys.forEach(who -> {
                Map<Integer, Integer> value = curr.get(who);
                value.entrySet().forEach(what -> {
                    what.setValue(what.getValue() - ref.minVal);
                });
            });
        }
    }

    private void balanceRows() {
        curr.entrySet().stream().forEach(entry -> balanceRow(entry.getKey()));
    }

    private void balanceColumns() {
        Map<Integer, Integer> mins = findColumnMins();
        mins.entrySet().forEach(this::balanceColumn);
    }

    private void balanceRow(Integer who) {
        Map<Integer, Integer> what = curr.get(who);
        Optional<Integer> minOptional = what.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getValue)).map(entry -> entry.getValue());
        if (minOptional.isEmpty()) {
            return;
        }
        Integer min = minOptional.get();
        if (min == 0) {
            return;
        }
        what.entrySet().forEach(entry -> entry.setValue(entry.getValue() - min));
    }

    private void balanceColumn(Map.Entry<Integer, Integer> min) {
        Integer what = min.getKey();
        Integer cost = min.getValue();
        if (cost == 0) {
            return;
        }
        for (Map.Entry<Integer, Map<Integer, Integer>> who : curr.entrySet()) {
            Map<Integer, Integer> whoValue = who.getValue();
            whoValue.put(what, whoValue.get(what) - cost);
        }
    }

    private Map<Integer, Integer> findColumnMins() {
        Map<Integer, Integer> mins = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, Integer>> who : curr.entrySet()) {
            for (Map.Entry<Integer, Integer> what : who.getValue().entrySet()) {
                Integer whatKey = what.getKey();
                Integer whatValue = what.getValue();
                if (!mins.containsKey(whatKey) || (mins.containsKey(whatKey) && mins.get(whatKey) > whatValue)) {
                    mins.put(whatKey, whatValue);
                }
            }
        }
        return mins;
    }
}
