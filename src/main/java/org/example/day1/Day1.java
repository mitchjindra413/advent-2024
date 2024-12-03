package org.example.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day1 {
    public static int day1() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/org/example/day1/part1.txt"));

            for (String line : lines) {
                String[] columns = line.trim().split("\\s+");
                if (columns.length == 2) {
                    list1.add(Integer.parseInt(columns[0]));
                    list2.add(Integer.parseInt(columns[1]));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return _part2(list1, list2);
    }

    static int _part1(List<Integer> list1, List<Integer> list2) {
        list1.sort(Comparator.naturalOrder());
        list2.sort(Comparator.naturalOrder());

        int res = 0;
        for(int i = 0; i < list1.size(); i++) {
            res += Math.abs(list1.get(i) - list2.get(i));
        }

        return res;
    }

    static int _part2(List<Integer> list1, List<Integer> list2) {
        Map<Integer, Integer> countOf2 = new HashMap<>();

        for(Integer i : list2) {
            countOf2.put(i, 1 + countOf2.getOrDefault(i, 0));
        }

        int res = 0;
        for(Integer i : list1) {
            res += i * countOf2.getOrDefault(i, 0);
        }

        return res;
    }
}
