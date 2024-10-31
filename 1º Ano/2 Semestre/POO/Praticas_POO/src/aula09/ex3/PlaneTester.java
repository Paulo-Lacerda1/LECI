package aula09.ex3;

import java.util.Scanner;

public class PlaneTester {
    public static void main(String[] args) {
        PlaneManager planeManager = new PlaneManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            showMenu();
            choice = getChoice(scanner);

            switch (choice) {
                case 1:
                    addPlane(planeManager, scanner);
                    break;
                case 2:
                    removePlane(planeManager, scanner);
                    break;
                case 3:
                    searchPlane(planeManager, scanner);
                    break;
                case 4:
                    printAllPlanes(planeManager);
                    break;
                case 5:
                    printCommercialPlanes(planeManager);
                    break;
                case 6:
                    printMilitaryPlanes(planeManager);
                    break;
                case 7:
                    printFastestPlane(planeManager);
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nPlane Fleet Menu:");
        System.out.println("1. Add a plane to the fleet");
        System.out.println("2. Remove a plane from the fleet");
        System.out.println("3. Search for a plane");
        System.out.println("4. Print summary of all planes in the fleet");
        System.out.println("5. Print list of all commercial planes in the fleet");
        System.out.println("6. Print list of all military planes in the fleet");
        System.out.println("7. Print the fastest plane in the fleet");
        System.out.println("0. Exit");
    }

    private static int getChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static void addPlane(PlaneManager planeManager, Scanner scanner) {
        System.out.print("Enter the plane's ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter the plane's manufacturer: ");
        String manufacturer = scanner.nextLine();
        System.out.print("Enter the plane's model: ");
        String model = scanner.nextLine();
        System.out.print("Enter the plane's year of manufacture: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the plane's passenger count: ");
        int passengerCount = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the plane's maximum speed: ");
        int maxSpeed = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the plane's type (commercial/military): ");
        String type = scanner.nextLine();
        if ("commercial".equals(type)) {
            System.out.print("Enter the plane's crew members count: ");
            int crewMembersCount = Integer.parseInt(scanner.nextLine());
            planeManager.addPlane(new CommercialPlane(id, manufacturer, model, year, passengerCount, maxSpeed, crewMembersCount));
        } else if ("military".equals(type)) {
            System.out.print("Enter the plane's missile count: ");
            int missileCount = Integer.parseInt(scanner.nextLine());
            planeManager.addPlane(new MilitaryPlane(id, manufacturer, model, year, passengerCount, maxSpeed, missileCount));
        }
    }

    private static void removePlane(PlaneManager planeManager, Scanner scanner) {
        System.out.print("Enter the plane's ID: ");
        String id = scanner.nextLine();
        if (planeManager.searchPlane(id) == null) {
            System.out.println("Plane not found.");
            return;
        }
        planeManager.removePlane(id);
    }

    private static void searchPlane(PlaneManager planeManager, Scanner scanner) {
        System.out.print("Enter the plane's ID: ");
        String id = scanner.nextLine();
        Plane plane = planeManager.searchPlane(id);
        if (plane == null) {
            System.out.println("Plane not found.");
            return;
        }
        System.out.println(plane);
    }

    private static void printAllPlanes(PlaneManager planeManager) {
        planeManager.printAllPlanes();
    }

    private static void printCommercialPlanes(PlaneManager planeManager) {
        planeManager.printAllPlanes("commercial");
    }

    private static void printMilitaryPlanes(PlaneManager planeManager) {
        planeManager.printAllPlanes("military");
    }

    private static void printFastestPlane(PlaneManager planeManager) {
        System.out.println(planeManager.getFastestPlane());
    }
}
