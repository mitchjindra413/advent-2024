package org.example.day5;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day5 {
    static Map<Integer, List<Integer>> parseRules() {
        String path = "src/main/java/org/example/day5/rules.txt";
        String test = "src/main/java/org/example/day5/testrules.txt";

        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            Map<Integer, List<Integer>> prereqs = new HashMap<>();
            for(String line : lines) {
                String[] parts = line.split("\\|");
                int a = Integer.parseInt(parts[0]);
                int b = Integer.parseInt(parts[1]);

                if(!prereqs.containsKey(a)) {
                    prereqs.put(a, new ArrayList<>());
                }
                prereqs.get(a).add(b);
            }
            return prereqs;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    static List<List<Integer>> parseOperations() {
        String path = "src/main/java/org/example/day5/data.txt";
        String test = "src/main/java/org/example/day5/testdata.txt";
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            List<List<Integer>> operations = new ArrayList<>();
            for(String line:lines) {
                String[] parts = line.split(",");
                List<Integer> operation = new ArrayList<>();
                for(String s : parts) {
                    operation.add(Integer.parseInt(s));
                }
                operations.add(operation);
            }

            return operations;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static int part1() {
        Map<Integer, List<Integer>> prereqs = parseRules();
        List<List<Integer>> operations = parseOperations();

        int sum = 0;
        for(List<Integer> operation : operations) {
            if(validOperation(operation, prereqs)) {
                sum += operation.get(operation.size() / 2);
            }
        }

        return sum;
    }

    static boolean validOperation(List<Integer> operation, Map<Integer, List<Integer>> prereqs) {
        Set<Integer> invalid = new HashSet<>();

        for(int i = operation.size() - 1; i >= 0; i--) {
            if(invalid.contains(operation.get(i))) {
                return false;
            }
            invalid.addAll(prereqs.getOrDefault(operation.get(i), List.of()));
        }

        return true;
    }
}
