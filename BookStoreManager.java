
package com.mycompany.bookstoremanage;


import java.util.ArrayList;
import java.util.Scanner;
// Represents a book in the bookstore
class Book {
    private String title;
    private String author;
    private double price;
    private  int stock;
    private String isbn;
    private String genre;
    private String description;

    //public constructor for book class
    public Book(String title, String author, double price, int stock, String isbn,String genre, String description) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.isbn=isbn;
        this.genre=genre;
        this.description=description;
    }
//getter methods
    public String getTitle() {
        return title; 
    }
    public String getAuthor() {
        return author; 
    }
    public double getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
 public String getIsbn() {
        return isbn;
    }
 public String getDescription() {
        return description;
    }
 
 //throwing an exception whenever there is an error like when the specific book is out of stock
    public void soldBook() throws Exception {
        if (stock > 0) {
            stock--;
        } else {
            throw new Exception("Out of stock!");
        }
    }

    public void displayBook() {
        System.out.println("Title: " + title + " \n Author: " + author + " \n Price: $" + price + " \n Stock: " + stock +"\n Book's description:"+description);
    }
}
// Parent class for manager and Customer
class User {
     String name;
//parameterized constructor for user class
    public User(String name) {
        this.name = name;
    }
    
//method to display user information
    public void displayUser() {
        System.out.println("User: " + name);
    }
}


// Manager class to manage books
class Manager extends User {
     ArrayList<Book> books= new ArrayList<>();
;
    public Manager(String name) {
        super(name);
    }
//parametrized  method for manager class
    public void addBook(String title, String author, double price, int stock ,String isbn, String genre, String description) {
Book b = new Book(title, author, price, stock, isbn, genre, description);

books.add(b);//adding book to the list
        System.out.println(title + " added to store.");
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            
        }
        else {
        System.out.println("Books in Store:");
        for (int i=0;i<books.size();i++) {
            books.get(i).displayBook();
        }
    }
    }
    public Book findBook(String title) {
        for (int i=0;i<books.size();i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                return books.get(i);
            } 
        }
             
                return null;
        
    }

    // Search books by title
    public void searchByTitle(String title) {
        boolean found = false;
        for (int i=0;i<books.size();i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                books.get(i).displayBook();
                found = true;
            }
        }
        if (!found) System.out.println("No book found with that title.");
    }

    //public String toString(){
      //  return ;
   // } 
    // Search books by author
    public void searchByAuthor(String author) {
        boolean found = false;
        for (int i=0;i<books.size();i++) {
            if (books.get(i).getAuthor().equalsIgnoreCase(author)) {
                books.get(i).displayBook();
                found = true;
            }
        }
        if (!found) System.out.println("No book found by that author.");
    }

// Search books within a price range
    public void searchByPriceRange(double minPrice, double maxPrice) {
        boolean found = false;
               for (int i=0;i<books.size();i++)  {
            if (books.get(i).getPrice() >= minPrice && books.get(i).getPrice() <= maxPrice) {
                books.get(i).displayBook();
                found = true;
            }
        }
        if (!found) System.out.println("No books found in that price range.");
    }
}
// Customer class that can buy books
class Customer extends User {
    private double wallet;
    private int customerId;
    public Customer(String name, double wallet,int customerId) {
        super(name);
        this.wallet = wallet;
        this.customerId=customerId;
    }

    public void buyBook(Book book) {
        int numbOfBooksToOrder=0;
        Scanner sc=new Scanner(System.in);
        System.out.println("please enter number of books to order");
        numbOfBooksToOrder=sc.nextInt();
        try {
        if (numbOfBooksToOrder <= 0) {
            System.out.println("Invalid number of books!");
            return;
        }

        if(numbOfBooksToOrder > book.getStock()) {
            System.out.println("Sorry, we're out of stock!");
            return;
        }

        double totalPrice = book.getPrice() * numbOfBooksToOrder;
        if (wallet < totalPrice) {
            System.out.println("You dont't have enough money.");
            return;
        }

        for (int i = 0; i < numbOfBooksToOrder; i++) {
            book.soldBook();
        }

        wallet = wallet-totalPrice;

        if (numbOfBooksToOrder == 1) {
            System.out.println("1 book successfully bought!");
        } else {
            System.out.println(numbOfBooksToOrder + " books successfully bought!");
        }

        System.out.println(name + " bought " + numbOfBooksToOrder + " of " + book.getTitle());
        
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
        
    }

    @Override
    public void displayUser() {
        System.out.println("Customer: " + name + " \n Wallet: $" + wallet +"\n customerID:"+ customerId);
    }
}

public class BookStoreManager {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Manager man = new Manager("Abebe Alemu");
    
// Adding some books using the addBook function one by one
        man.addBook("Pride and Prejudice", "Jane Austen", 50.0, 5,"978-1503290563","Romance","This classic novel follows Elizabeth Bennet, a spirited young woman, as she navigates issues of class, marriage, and morality in early 19th-century England. The story primarily revolves around her complex relationship with the wealthy and aloof Mr. Darcy, exploring themes of love, reputation, and the importance of personal growth.");
        man.addBook("Crime and Punishment", " Fyodor Dostoevsky", 40.0, 3 ,"978-0486415871","Psychological Fiction, Philosophical Fiction","This psychological thriller centers on Rodion Raskolnikov, a former student living in St. Petersburg who believes he is above moral law. After committing a murder, he grapples with guilt, paranoia, and existential questions about morality and redemption, ultimately leading to a profound exploration of human psychology and the nature of justice.");
        man.addBook("Dertogada", "Yismake Worku", 30.0, 4,"9994484311","Fiction,philosophy","This fiction follows the lives of Sipara and her lover from a young age Mirazh.It narrates their story in retrospect and their journey in life till they recconect along with the things they found about their country's secret treasure.");
       // adding multiple books using loop
       String[] titles={
           "To kill a mocking bird","Wuthering Heights","Norwegian Wood"
       };
       String[] authors={
           "Harper Lee","Emily Bronte ","Haruki Murakami"
       };
       double[] price={
           56,67,80
       };
      int [] stock={
           5,3,4
       };
       String[] isbn={
           "978-0061120084 ","978-1505291610 ","978-0375704024 "
       };
       String[] genre={
           "classical literature","Romance","psychology and philosophy"
       };
       String[] description={
           "Set in the racially charged American South during the 1930s, this novel follows young Scout Finch as she witnesses her father, lawyer Atticus Finch, defend a Black man accused of raping a white woman. Through Scout's eyes, the story addresses themes of racial injustice, moral growth, and the loss of innocence.","   This gothic novel tells the tumultuous love story between Heathcliff and Catherine Earnshaw on the Yorkshire moors. Their passionate and destructive relationship influences the lives of those around them, exploring themes of obsession, revenge, and the impact of environment on character.","This gothic novel tells the tumultuous love story between Heathcliff and Catherine Earnshaw on the Yorkshire moors. Their passionate and destructive relationship influences the lives of those around them, exploring themes of obsession, revenge, and the impact of environment on character.\n" +
""
       };
       for(int i=0;i<titles.length;i++)
               {
           man.addBook(titles[i], authors[i],price [i],stock [i], isbn[i], genre[i], description[i]);
       }
     
        Customer c1 = new Customer("Betelhem", 100,001);
        Customer c2 = new Customer("Netsanet", 50,002);
        Customer c3 = new Customer("Venusia", 25,003);
        Customer c4 = new Customer("Yohannes", 90,004);
        Customer c5 = new Customer("Yonathan", 65,005);

        for (;;) {  // displaying the menu for
            System.out.println("\n Bookstore Management" );
            System.out.println("1. Display Books");
            System.out.println("2. Buy a Book");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Search Book by Author");
            System.out.println("5. Search Book by Price Range");
            System.out.println("6. Display Customer Information"); 
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 7) {
                System.out.println("Exiting...");
                break;  // Exit the loop when the user selects option 6
            }

switch (choice) {
                case 1:
                    man.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter book title to buy: ");
                    String title = scanner.nextLine();
                    Book book = man.findBook(title);
                    if (book != null) {
                        c1.buyBook(book);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter book title to search: ");
                    String searchTitle = scanner.nextLine();
                    man.searchByTitle(searchTitle);
                    break;
                case 4:
                    System.out.print("Enter author name to search: ");
                    String searchAuthor = scanner.nextLine();
                    man.searchByAuthor(searchAuthor);
                    break;
                case 5:
                    System.out.print("Enter minimum price: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Enter maximum price: ");
                    double maxPrice = scanner.nextDouble();
                    man.searchByPriceRange(minPrice, maxPrice);
                    break;
                case 6:
                    c1.displayUser();
                    c2.displayUser();
                    c3.displayUser();
                    c4.displayUser();
                    c5.displayUser();
                 break;
                    default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}
