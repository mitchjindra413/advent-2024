package org.example.day3;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day3 {

    static String parse() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/org/example/day3/data.txt"));
            StringBuilder sb = new StringBuilder();
            for(String line : lines) {
                sb.append(line);
            }
            return sb.toString();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static int part1() {
        String input = parse();
        int res = 0;

        int l = 0;
        while(l < input.length()) {
            if(input.startsWith("mul(", l)) {
                l = l + 4;
                int r = l;
                while(r < input.length() && Character.isDigit(input.charAt(r))) {
                    r++;
                }
                if(input.charAt(r) != ',') {
                     continue;
                }
                int digit1 = Integer.parseInt(input.substring(l, r));

                l = r + 1;
                r = l;
                while(r < input.length() && Character.isDigit(input.charAt(r))) {
                    r++;
                }
                if(input.charAt(r) != ')') {
                    continue;
                }
                int digit2 = Integer.parseInt(input.substring(l, r));;
                res += digit1 * digit2;
            } else {
                l += 1;
            }
        }

        return res;
    }

    public static int part2() {
        String input = parse();
        int res = 0;
        boolean skip = false;
        int l = 0;
        while(l < input.length()) {
            if(input.startsWith("mul(", l)) {
                l = l + 4;
                int r = l;
                while(r < input.length() && Character.isDigit(input.charAt(r))) {
                    r++;
                }
                if(input.charAt(r) != ',') {
                    continue;
                }
                int digit1 = Integer.parseInt(input.substring(l, r));

                l = r + 1;
                r = l;
                while(r < input.length() && Character.isDigit(input.charAt(r))) {
                    r++;
                }
                if(input.charAt(r) != ')') {
                    continue;
                }
                int digit2 = Integer.parseInt(input.substring(l, r));

                if(!skip) {
                    res += digit1 * digit2;
                }
            } else if(input.startsWith("do()", l)) {
                skip = false;
                l += 4;
            } else if(input.startsWith("don't()", l)) {
                skip = true;
                l += 7;
            } else {
                l += 1;
            }
        }

        return res;
    }
}
