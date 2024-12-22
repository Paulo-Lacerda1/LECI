package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private static Map<Integer, String> items = new HashMap<>();
    private static int itemId = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. Search item by ID");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    addItem(scanner);
                    break;
                case 2:
                    removeItem(scanner);
                    break;
                case 3:
                    searchItem(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addItem(Scanner scanner) {
        System.out.print("Enter item name: ");
        String itemName = scanner.next();
        items.put(itemId, itemName);
        System.out.println("Item added: " + itemName + " (ID: " + itemId + ")");
        itemId++;
    }

    private static void removeItem(Scanner scanner) {
        System.out.print("Enter item ID to remove: ");
        int id = scanner.nextInt();
        if (items.containsKey(id)) {
            items.remove(id);
            System.out.println("Item removed: " + id);
        } else {
            System.out.println("Item not found: " + id);
        }
    }

    private static void searchItem(Scanner scanner) {
        System.out.print("Enter item ID to search: ");
        int id = scanner.nextInt();
        if (items.containsKey(id)) {
            System.out.println("Item found: " + items.get(id) + " (ID: " + id + ")");
        } else {
            System.out.println("Item not found: " + id);
        }
    }
}