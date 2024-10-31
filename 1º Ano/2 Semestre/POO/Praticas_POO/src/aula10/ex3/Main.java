package aula10.ex3;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        String str = "Hello World";

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!map.containsKey(ch)) {
                ArrayList<Integer> lst = new ArrayList<>();
                lst.add(i);
                map.put(ch, lst);
            } else {
                map.get(ch).add(i);
            }
        }

        System.out.println(map);
    }
}
