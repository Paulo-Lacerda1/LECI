package aula12.ex2;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SortByInitial {
    public static void main(String[] args) throws IOException {
        TreeMap<Character, TreeMap<String, Integer>> database = processFile("C:\\Users\\paulo\\OneDrive - Universidade de Aveiro\\UA\\2 Semestre\\POO\\Praticas_POO\\src\\aula12\\ex2\\texto.txt");

        System.out.println(parseTreeMap(database));

        writeToFile(database, "C:\\Users\\paulo\\OneDrive - Universidade de Aveiro\\UA\\2 Semestre\\POO\\Praticas_POO\\src\\aula12\\ex2\\out.txt");
    }

    public static TreeMap<Character, TreeMap<String, Integer>> processFile(String filePath) throws IOException {
        TreeMap<Character, TreeMap<String, Integer>> database = new TreeMap<>();
        String str = Files.readString(Paths.get(filePath)).toLowerCase();
        String[] words = wordSplitter(str);

        for (String word : words) {
            if (word.length() <= 2) continue;

            char ch = word.charAt(0);
            database.putIfAbsent(ch, new TreeMap<>());
            database.get(ch).merge(word, 1, Integer::sum);
        }

        return database;
    }

    public static String[] wordSplitter(String str) {
        List<String> list = new ArrayList<>(Arrays.asList(str.replaceAll("[^a-zA-ZÀ-ÖØ-öø-ÿ]", " ").split("[ \n\t.,:'‘’;?!*{}_=+&/()\\[\\]”“\"-]")));
        list.removeIf(String::isEmpty);
        return list.toArray(new String[0]);
    }

    public static String parseTreeMap(TreeMap<Character, TreeMap<String, Integer>> treeMap) {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<Character, TreeMap<String, Integer>> entry : treeMap.entrySet()) {
            str.append(entry.getKey()).append(": ");
            for (Map.Entry<String, Integer> innerEntry : entry.getValue().entrySet()) {
                str.append(innerEntry.getKey()).append(", ").append(innerEntry.getValue()).append("; ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    public static void writeToFile(TreeMap<Character, TreeMap<String, Integer>> treeMap, String filePath) throws IOException {
        try (FileWriter out = new FileWriter(filePath)) {
            out.write(parseTreeMap(treeMap));
        }
    }
}
