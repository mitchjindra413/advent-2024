package org.example.day4;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day4 {
    static List<char[]> parse() {
        String path = "src/main/java/org/example/day4/data.txt";
//        String test = "src/main/java/org/example/day4/test.txt";
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            List<char[]> puzzle = new ArrayList<>();
            for (String line : lines) {
                puzzle.add(line.toCharArray());
            }

            return puzzle;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return List.of();
    }

    static List<List<Integer>> moves = List.of(
            List.of(0,1),
            List.of(1,0),
            List.of(0,-1),
            List.of(-1,0),
            List.of(1,1),
            List.of(1,-1),
            List.of(-1,1),
            List.of(-1,-1)
    );
    public static int part1() {
        List<char[]> puzzle = parse();

        int count = 0;
        for (int r = 0; r < puzzle.size(); r++) {
            for (int c = 0; c < puzzle.get(r).length; c++) {
                if (puzzle.get(r)[c] == 'X') {
                    for(List<Integer> move : moves) {
                        if(explore(r, c, puzzle, move, new ArrayDeque<>(List.of('X', 'M', 'A', 'S')))) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    static boolean explore(int r, int c, List<char[]> puzzle, List<Integer> move, Deque<Character> needed) {
        if(needed.isEmpty()) {
            return true;
        }
        if(r < 0 || c < 0 || r >= puzzle.size() || c >= puzzle.get(r).length) {
            return false;
        }
        if(puzzle.get(r)[c] != needed.getFirst()) {
            return false;
        }

        needed.pop();
        return explore(r + move.get(0), c + move.get(1), puzzle, move, needed);
    }

    public static int part2() {
        List<char[]> puzzle = parse();
        Set<Character> key = new HashSet<>(List.of('M','S'));

        int count = 0;
        for(int r = 0; r < puzzle.size(); r++) {
            for(int c = 0; c < puzzle.get(r).length; c++) {
                if(puzzle.get(r)[c] == 'A' && checker(r, c, puzzle, key)) {
                    count++;
                }
            }
        }

        return count;
    }

    static boolean checker(int r, int c, List<char[]> puzzle, Set<Character> key) {
        if(r - 1 < 0 || c - 1 < 0 || r - 1 >= puzzle.size() || c - 1 >= puzzle.get(r - 1).length) {
            return false;
        }
        if(r + 1 < 0 || c + 1 < 0 || r + 1 >= puzzle.size() || c + 1 >= puzzle.get(r + 1).length) {
            return false;
        }

        Set<Character> left = new HashSet<>();
        left.add(puzzle.get(r-1)[c-1]);
        left.add(puzzle.get(r+1)[c+1]);
        Set<Character> right = new HashSet<>();
        right.add(puzzle.get(r-1)[c+1]);
        right.add(puzzle.get(r+1)[c-1]);

        return left.equals(key) && right.equals(key);
    }
}
