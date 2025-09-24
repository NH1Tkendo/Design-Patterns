package com.ngobatai_lab4.Bai4;

import java.util.*;

public class Score {
    private List<Symbol> score = new ArrayList<>();

    public void add(Symbol s) {
        score.add(s);
    }

    public void printScore() {
        if (score.isEmpty()) {
            System.out.println("Bản nhạc rỗng.");
            return;
        }
        System.out.println("--- BẢN NHẠC ---");
        int i = 1;
        for (Symbol s : score) {
            System.out.printf("%2d. %s%n", i++, s.toString());
        }
    }

    public int countQuarterRests() {
        int cnt = 0;
        for (Symbol s : score) {
            if (s.isRest() && s.getDuration() == Duration.QUARTER)
                cnt++;
        }
        return cnt;
    }

    public Optional<Pitch> highestPitch() {
        Pitch best = null;
        for (Symbol s : score) {
            if (!s.isRest()) {
                Note n = (Note) s;
                if (best == null || n.getPitch().ordinal() > best.ordinal()) {
                    best = n.getPitch();
                }
            }
        }
        return Optional.ofNullable(best);
    }

    public boolean removeAt(int index1Based) {
        if (index1Based < 1 || index1Based > score.size())
            return false;
        score.remove(index1Based - 1);
        return true;
    }
}
