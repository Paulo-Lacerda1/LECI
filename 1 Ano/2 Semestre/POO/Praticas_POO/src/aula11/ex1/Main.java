package aula11.ex1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        String str = """
                Construa um programa que leia um ficheiro de texto e que conte todos os pares de palavras
                encontrados no ficheiro e o número de ocorrências de cada par. Despreze todas as palavras
                de tamanho inferior a 3 e considere como separadores os seguintes caracteres:""";

        TreeMap<String, HashMap<String, Integer>> resultParagraph = doThing(str);

        System.out.println(parseTreeMap(resultParagraph) + "\n\n");

        String text = Files.readString(Paths.get("major.txt"));

        TreeMap<String, HashMap<String, Integer>> resultFile = doThing(text);

        System.out.println(parseTreeMap(resultFile));
    }


    public static TreeMap<String, HashMap<String, Integer>> doThing(String str) {
        TreeMap<String, HashMap<String, Integer>> result = new TreeMap<>();
        String[] s;

        s = (str.replaceAll("[^a-zA-Z0-9À-ÖØ-öø-ÿ ]", "").split("[ \n\t.,:'‘’;?!*{}=+&/()”“\"-]"));

        for (int i = 0; i < s.length; i++) {
            String word = s[i].toLowerCase();
            if (word.length() <= 3) continue;

            if (!result.containsKey(word)) {
                result.put(word, new HashMap<>());
            }

            for (int j = i + 1; j < s.length; j++) {
                String compare = s[j].toLowerCase();
                if (compare.length() <= 3) continue;

                if (result.get(word).containsKey(compare))
                    result.get(word).put(compare, result.get(word).get(compare) + 1);
                else
                    result.get(word).put(compare, 1);
                break;
            }
        }
        return result;
    }

    public static String parseTreeMap(TreeMap<String, HashMap<String, Integer>> genre) {
        String str = "";
        Set<String> keys = genre.keySet();

        for (Object key : keys) {
            str += key + "=" + genre.get(key) + "\n";
        }
        return str;
    }
}


