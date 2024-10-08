import java.util.*;
//this is the option class where methods for option are called.
public class Option{
    private Carts cart;
    private Scanner sc = new Scanner(System.in);

    public Option(Carts cart){
        this.cart = cart;
    }

    public void ItemAdder(Order menu){          //method for adding items  to the cart

        for (int x = 0;x<menu.getTotalItems();x++) {
            System.out.println((x + 1) 
            + ". "
            + menu.getItem(x) 
            + " - P " 
            + menu.getPrice(x));
        }

        System.out.println("Enter the item you want to add [use index]: ");
        int n = sc.nextInt();

        if (n > 0 && n <= menu.getTotalItems()){
            Item item = new Item(menu.getItem(n-1),menu.getPrice(n-1), 1);
            cart.addItem(item);

            System.out.println("you added: " 
            + menu.getItem(n-1) 
            + " - P " 
            + menu.getPrice(n-1));

            System.out.println("you selected the following items: ");
            for (int x=0;x<cart.getItemList().size();x++) {
                System.out.println((x + 1) 
                + ". " 
                + cart.getItemList().get(x).getName() 
                + " - Quantity: " 
                +cart.getItemList().get(x).getQuantity() 
                + " - Price: " 
                + cart.getItemList().get(x).getTotalPrice());

            }
            } else    { System.out.println("invalid index"); }
            }

    public void ItemRemover(){           //method to remove items  from the cart


        System.out.println("Enter the index of the item you want to remove: ");
        int x=sc.nextInt();

        if (x>0 && x <= cart.getItemList().size()) {
            cart.removeItem(x-1);

            System.out.println("item is removed ");
        } else {
            System.out.println("Invalid index");
        }
        }

    public void ItemUpdater(){          //method to change quantity of items  in the cart


        System.out.println("Enter the index of the item you want to update/change quantity: ");
        int x = sc.nextInt();

        if (x>0 && x <= cart.getItemList().size()){
            System.out.println("type new quantity: ");
            int quantity = sc.nextInt();

            cart.updateItem(x - 1, quantity);

            System.out.println("item is updated successfully");

        } else { System.out.println("index only"); }
        }

    public void CreateNewCart(){        //method to create a new instance of cart 

        cart = new Carts();
        System.out.println("you created a new cart");
    }

    public void checkout() {
        System.out.println("Your cart:");
        for (int x = 0; x < cart.getItemList().size(); x++) {
            System.out.println((x + 1) + ". " + cart.getItemList().get(x).getName() +
                    " - Quantity: " + cart.getItemList().get(x).getQuantity() +
                    " - Price: " + cart.getItemList().get(x).getTotalPrice());
        }
    
        System.out.println("Your total bill is: " + cart.getTotalPrice());
    
        double money;
    
        while (true) {
            System.out.println("Enter your money (or type 'exit' to cancel): ");
            String input = sc.next();
    
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting checkout...");
                return; 
            }
    
            try {
                money = Double.parseDouble(input);
    
                if (money < cart.getTotalPrice()) {
                    System.out.println("You don't have enough money. Please try again.");
                } else {
                    double change = money - cart.getTotalPrice();
                    System.out.println("Your change is: " + change);
                    break; 
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number or type 'exit'.");
            }
        }
    
        printReceipt(money);
    }
    
    private void printReceipt(double money) {
        System.out.println("---------------------------------------");
        System.out.println("              Receipt:                 ");
        System.out.println("ITEMS:");
    
        for (int x = 0; x < cart.getItemList().size(); x++) {
            System.out.println((x + 1) + ". " + cart.getItemList().get(x).getName() +
                    " - Quantity: " + cart.getItemList().get(x).getQuantity() +
                    " - Price: " + cart.getItemList().get(x).getTotalPrice());
        }
    
        double totalPrice = cart.getTotalPrice();
        double change = money - totalPrice;
    
        System.out.println("\nMoney: " + money);
        System.out.println("Total: " + totalPrice);
        System.out.println("Change: " + change);
        System.out.println("---------------------------------------");
    
        System.out.println("1. Order again ");
        System.out.println("2. Exit now ");
    
        int choice;
        while (true) {
            System.out.println("Enter your choice: ");
            try {
                choice = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Number only.");
                sc.next();
            }
        }
    
        switch (choice) {
            case 1:
                orderAgain();
                break;
            case 2:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }
    }
    
    

    public void orderAgain(){ //method to order again from the start

        cart = new Carts();
        Inputter inputter = new Inputter();
        inputter.Inputs();
    }

    public void exit(){ //just method to print exit

        System.out.println("exit");
    }
    public void ItemSearchName() {
    System.out.println("Enter the name of the item to search: ");
    String name = sc.next();
    boolean found = false;

    for (Item item : cart.getItemList()) {
        if (item.getName().equalsIgnoreCase(name)) {
            System.out.println("Item found: " + item.getName() 
            + " - Quantity: " + item.getQuantity() 
            + " - Price: " + item.getTotalPrice());
            found = true;
            break;
        }
    }
    if (!found) {
        System.out.println("Item not found in the cart.");
    }
    }
    public void ItemFilterPrice() {
    System.out.println("Enter minimum price: ");
    double minPrice = sc.nextDouble();
    System.out.println("Enter maximum price: ");
    double maxPrice = sc.nextDouble();

    System.out.println("Items within the price range:");
    for (Item item : cart.getItemList()) {
        if (item.getTotalPrice() >= minPrice && item.getTotalPrice() <= maxPrice) {
            System.out.println(item.getName() 
            + " - Quantity: " + item.getQuantity() 
            + " - Price: " + item.getTotalPrice());
        }
    }
    }
    public void ItemFilterName() {
    System.out.println("Enter a name to filter: ");
    String substring = sc.next();
    boolean found = false;

    System.out.println("Filtered items:");
    for (Item item : cart.getItemList()) {
        if (item.getName().toLowerCase().contains(substring.toLowerCase())) {
            System.out.println(item.getName() 
            + " - Quantity: " + item.getQuantity()
             + " - Price: " + item.getTotalPrice());
            found = true;
        }
    }
    if (!found) {
        System.out.println("No items match the given name.");
    }
    }

    }