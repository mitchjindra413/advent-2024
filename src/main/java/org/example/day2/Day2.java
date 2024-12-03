package org.example.day2;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day2 {
    public static int day2() {
        List<List<Integer>> input = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/org/example/day2/data.txt"));
            for(String line : lines) {
                String[] stringInts = line.split(" ");
                List<Integer> ints = new ArrayList<>();

                for(String s : stringInts) {
                    ints.add(Integer.parseInt(s));
                }

                input.add(ints);
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return part2(input);
    }

    static int part1(List<List<Integer>> input) {
        int res = 0;
        for(List<Integer> report : input) {
            if(validReport(report)) {
                res += 1;
            }
        }
        return res;
    }

    static boolean validReport(List<Integer> report) {
        if(report.size() == 1) {
            return true;
        }

        boolean increasing = report.get(0) < report.get(1);
        for(int i = 1; i < report.size(); i++) {
            int first = report.get(i - 1);
            int second = report.get(i);
            int dif = second - first;

            if(dif == 0) {
                return false;
            }
            if (dif < 0 && increasing) {
                return false;
            }
            if(dif > 0 && !increasing) {
                return false;
            }
            if(dif > 3 || dif < -3) {
                return false;
            }
        }

        return true;
    }

    static boolean validReportRemove(List<Integer> record) {

        if(validReport(record)) {
            return true;
        }

        for(int i = 0; i < record.size(); i++) {
            List<Integer> newRecord = new ArrayList<>(record);
            newRecord.remove(i);

            if(validReport(newRecord)) {
                return true;
            }
        }

        return false;
    }

    static int part2(List<List<Integer>> input) {
        int res = 0;
        for(List<Integer> report : input) {
            if(validReportRemove(report)) {
                res += 1;
            }
        }
        return res;
    }
}
