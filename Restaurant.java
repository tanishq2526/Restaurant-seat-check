import java.util.*;

class SeatBooking {
    private int rows;
    private int columns;
    private boolean[][] seats;

    public SeatBooking(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = new boolean[rows][columns];
    }

    public void displaySeats() {
        System.out.println("Seat Layout:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (seats[i][j]) {
                    System.out.print("[B] "); // B for Booked
                } else {
                    System.out.printf("[%02d] ", (i * columns + j + 1)); // Table number
                }
            }
            System.out.println();
        }
    }

    public boolean bookSeat(int tableNumber) {
        int row = (tableNumber - 1) / columns;
        int column = (tableNumber - 1) % columns;
        if (tableNumber < 1 || tableNumber > rows * columns) {
            System.out.println("Invalid table number. Please try again.");
            return false;
        }
        if (seats[row][column]) {
            System.out.println("Seat is already booked. Please choose another seat.");
            return false;
        } else {
            seats[row][column] = true;
            return true;
        }
    }

    public boolean checkBookedSeat(int tableNumber) {
        int row = (tableNumber - 1) / columns;
        int column = (tableNumber - 1) % columns;
        if (tableNumber < 1 || tableNumber > rows * columns) {
            System.out.println("Invalid table number.");
            return false;
        }
        return seats[row][column];
    }
}

class FoodOrder {
    private String[] menuItems = {"Pizza", "Burger", "Pasta", "Salad", "Sushi", "Steak", "Tacos", "Curry", "Sandwich", "Ice Cream"};
    private double[] menuPrices = {300, 200, 250, 150, 400, 500, 180, 220, 100, 120};
    private int[] orderQuantities = new int[menuItems.length];
    private Map<Integer, String> specialInstructions = new HashMap<>();
    private Map<Integer, String> menuIds = new HashMap<>();

    public FoodOrder() {
        for (int i = 0; i < menuItems.length; i++) {
            menuIds.put(i + 1, menuItems[i]);
        }
    }

    public void displayMenu() {
        System.out.println("Menu:");
        System.out.println("____________________");
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println("| ID: " + (i + 1) + " | " + menuItems[i] + " | Rs." + menuPrices[i] + " |");
        }
    }

    public void addToOrder(int itemId, int quantity) {
        if (menuIds.containsKey(itemId)) {
            int index = itemId - 1;
            orderQuantities[index] += quantity;
            System.out.println(quantity + " x " + menuItems[index] + " added to your order.");
        } else {
            System.out.println("Invalid item ID.");
        }
    }

    public void updateOrder(int itemId, int newQuantity) {
        if (menuIds.containsKey(itemId)) {
            int index = itemId - 1;
            orderQuantities[index] = newQuantity;
            System.out.println("Updated " + menuItems[index] + " quantity to " + newQuantity);
        } else {
            System.out.println("Invalid item ID.");
        }
    }

    public void addInstruction(int itemId, String instruction) {
        if (menuIds.containsKey(itemId)) {
            specialInstructions.put(itemId, instruction);
            System.out.println("Added special instruction for " + menuIds.get(itemId) + ": " + instruction);
        } else {
            System.out.println("Invalid item ID.");
        }
    }

    public void displayOrder() {
        System.out.println("Your Order:");
        double total = 0;
        for (int i = 0; i < menuItems.length; i++) {
            if (orderQuantities[i] > 0) {
                double itemTotal = menuPrices[i] * orderQuantities[i];
                System.out.println(menuItems[i] + " x " + orderQuantities[i] + ": ₹" + itemTotal);
                if (specialInstructions.containsKey(i + 1)) {
                    System.out.println("  Special Instruction: " + specialInstructions.get(i + 1));
                }
                total += itemTotal;
            }
        }
        System.out.println("Total: ₹" + total);
    }
}

public class Restaurant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SeatBooking seatBooking = new SeatBooking(5, 5); // 5x5 seat layout
        FoodOrder foodOrder = new FoodOrder();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Restaurant Booking System ---");
            System.out.println("1. View vacant seats");
            System.out.println("2. Book a seat");
            System.out.println("3. Order food");
            System.out.println("4. View order");
            System.out.println("5. Update food order");
            System.out.println("6. Add special instruction");
            System.out.println("7. Check booked seat");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    seatBooking.displaySeats();
                    break;
                case 2:
                    System.out.print("Enter table number to book: ");
                    int tableNumber = scanner.nextInt();
                    if (seatBooking.bookSeat(tableNumber)) {
                        System.out.println("Seat booked successfully!");
                    }
                    break;
                case 3:
                    foodOrder.displayMenu();
                    System.out.print("Enter food item ID to order: ");
                    int itemId = scanner.nextInt();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    foodOrder.addToOrder(itemId, quantity);
                    break;
                case 4:
                    foodOrder.displayOrder();
                    break;
                case 5:
                    System.out.print("Enter food item ID to update: ");
                    int updateItemId = scanner.nextInt();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    foodOrder.updateOrder(updateItemId, newQuantity);
                    break;
                case 6:
                    System.out.print("Enter food item ID for special instruction: ");
                    int instructionItemId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter special instruction: ");
                    String instruction = scanner.nextLine();
                    foodOrder.addInstruction(instructionItemId, instruction);
                    break;
                case 7:
                    System.out.print("Enter table number to check: ");
                    tableNumber = scanner.nextInt();
                    if (seatBooking.checkBookedSeat(tableNumber)) {
                        System.out.println("Seat is booked.");
                    } else {
                        System.out.println("Seat is available.");
                    }
                    break;
                case 8:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
