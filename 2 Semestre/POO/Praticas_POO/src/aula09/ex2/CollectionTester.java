package aula09.ex2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

public class CollectionTester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual a dimensão?");
        int DIM = scanner.nextInt();
        scanner.close();    
        Collection<Integer> col = new ArrayList<>();
        checkPerformance(col, DIM);
    }

    private static void checkPerformance(Collection<Integer> col, int DIM) {
        testAdd(col, DIM);
        testSearch(col, DIM);
        testRemove(col);
    }

    private static void testAdd(Collection<Integer> col, int DIM) {
        double start = System.nanoTime(); 
        for (int i = 0; i < DIM; i++) {
            col.add(i);
        }
        double stop = System.nanoTime(); 
        double delta = (stop - start) / 1e6; 
        System.out.println(col.size() + ": Add to " + col.getClass().getSimpleName() + " took " + delta + "ms");
    }

    private static void testSearch(Collection<Integer> col, int DIM) {
        double start = System.nanoTime(); 
        for (int i = 0; i < DIM; i++) {
            int n = (int) (Math.random() * DIM);
            if (!col.contains(n)) {
                System.out.println("Not found???" + n);
            }
        }
        double stop = System.nanoTime(); 
        double delta = (stop - start) / 1e6; 
        System.out.println(col.size() + ": Search in " + col.getClass().getSimpleName() + " took " + delta + "ms");
    }

    private static void testRemove(Collection<Integer> col) {
        double start = System.nanoTime(); 
        Iterator<Integer> iterator = col.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        double stop = System.nanoTime(); 
        double delta = (stop - start) / 1e6; 
        System.out.println(col.size() + ": Remove from " + col.getClass().getSimpleName() + " took " + delta + "ms");
    }
}
