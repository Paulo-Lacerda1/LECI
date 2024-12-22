package aula10.ex4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> words = loadWords("src/aula10/ex4/words.txt");
            List<String> wordsBiggerThan2 = getWordsBiggerThan2(words);
            List<String> wordsEndWithS = getWordsEndWithS(words);

            System.out.println(wordsBiggerThan2);
            System.out.println(wordsEndWithS);
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    private static List<String> loadWords(String filePath) throws FileNotFoundException {
        List<String> words = new ArrayList<>();
        Scanner input = new Scanner(new FileReader(filePath));

        while (input.hasNext()) {
            words.add(input.next());
        }

        input.close();
        return words;
    }

    private static List<String> getWordsBiggerThan2(List<String> words) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (word.length() > 2 && word.matches("[a-zA-Z]+")) {
                result.add(word);
            }
        }
        return result;
    }

    private static List<String> getWordsEndWithS(List<String> words) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (word.endsWith("s")) {
                result.add(word);
            }
        }
        return result;
    }
}
