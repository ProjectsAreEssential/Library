import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class LibraryManagerModule {
   
   // Reads info from the "books.txt" file
   public static Book[] readBooks(String filename) throws FileNotFoundException {
      Scanner input = new Scanner(new File(filename));
      Book[] books = new Book[100]; // Max of 100 books
      
      int count = 0; // Represents the book count
      while (input.hasNextLine()) {
         String line = input.nextLine();
         Scanner lineScanner = new Scanner(line);
         
         // Splits the tokens at a comma now instead of a space
         lineScanner.useDelimiter(","); 
         
         int bookId = lineScanner.nextInt();
         String title = lineScanner.next().trim();
         String author = lineScanner.next().trim(); 
         boolean isCheckedOut = lineScanner.nextBoolean();
         
         lineScanner.close(); // Close the line scanner
         
         // With each book create a new book object while assigning it at index count while iterating
         books[count++] = new Book(bookId, title, author, isCheckedOut);
      }
      
      input.close(); // Close the input scanner
      return books; // Return the array with all the book objects
   }
   
   // Reads info from the "patron.txt" file
   public static Patron[] readPatrons(String filename) throws FileNotFoundException {
      Scanner input = new Scanner(new File(filename));
      Patron[] patrons = new Patron[100]; // Max of 100 patrons
      
      int count = 0; // Represents the patron count
      while (input.hasNextLine()) {
         String line = input.nextLine();
         Scanner lineScanner = new Scanner(line);
         
         // Splits the tokens at a comma now instead of a space
         lineScanner.useDelimiter(",");
         
         int patronId = lineScanner.nextInt();
         String name = lineScanner.next().trim();
         
         // Initialize a new patron object
         patrons[count++] = new Patron(patronId, name);
         
         // Read remaining tokens as checked out book Id's
         while (lineScanner.hasNextInt()) {
            int bookId = lineScanner.nextInt();
            
            // Store the book Id's into the checked out books array
            patrons[count - 1].checkOutBook(bookId);
         }
         lineScanner.close(); // Close the line scanner
      }
      
      input.close(); // Close the input scanner  
      return patrons; // Return the array with all the patron objects
   }
   
   // Prints a interactive menu to the console
   public static void showMenu(Book[] books, Patron[] patrons, Scanner console) {
      while (true) { // Menu showing all the options
         System.out.println("-----Library Menu-----");
         
         System.out.println(); // Blank line for formatting
         
         System.out.println("1. View all books");
         System.out.println("2. View all patrons");
         System.out.println("3. Check out a book");
         System.out.println("4. Return a book");
         System.out.println("5. Search for a book by Id");
         System.out.println("6. Search for a patron by Id");
         System.out.println("7. Save and Exit");
         
         System.out.println(); // Blank line for formatting
         
         System.out.print("Enter your choice: "); // Prompts the user to enter a number
         
         int choice = console.nextInt();
         console.nextLine(); // Consume new line
         
         switch (choice) {
            case 1:
               showAllBooks(books);
               break;
            case 2:
               showAllPatrons(patrons);
               break;
            case 3:
               checkOutBook(books, patrons, console);
               break;
            case 4:
               returnBook(books, patrons, console);
               break;
            case 5:
               searchBookById(books, console);
               break;
            case 6:
               searchPatronById(patrons, console);
               break;
            case 7:
               System.out.println("Exiting the system. Goodbye!");
               return; // Exit the method
            default:
               System.out.println("Invalid choice. Try again.");
         }
         
         System.out.println(); // Space after each action
      }
   }
   
   // Shows all the books
   public static void showAllBooks(Book[] books) {
      System.out.println("-----Books-----");
      
      System.out.println(); // Blank line for formatting
         
      for (int i = 0; i < books.length; i++) {
         if (books[i] != null) { // Checks to see if element is populated with an object
            System.out.println(books[i]);
            System.out.println();
         }
      }
   }
   
   // Show all the patrons
   public static void showAllPatrons(Patron[] patrons) {
      System.out.println("-----Patrons-----");
      
      System.out.println(); // Blank line for formatting
      
      for (int i = 0; i < patrons.length; i++) {
         if (patrons[i] != null) { // Checks to see if element is populated with an object
            System.out.println(patrons[i]);
            System.out.println();
         }
      }
   }
   
   // Checks out a book
   public static void checkOutBook(Book[] books, Patron[] patrons, Scanner console) {
      System.out.print("Enter a patron Id: "); // Ask the user to enter a patron Id
      int patronId = console.nextInt();
      
      System.out.print("Enter a book Id: "); // Ask the user to enter a book Id
      int bookId = console.nextInt();
      
      Patron tempPatron = null;
      Book tempBook = null;
      
      // Loop through the patron array
      for (int i = 0; i < patrons.length; i++) {
         if (patrons[i] != null) {
            
            if(patrons[i].getId() == patronId) {
               tempPatron = patrons[i]; // Store the matching object to a temp variable
               break; // Leave the loop once found
            }
         } 
      }
      if (tempPatron == null) { // Checks to see if the patron was found
         System.out.println("Patron id not found.");
         return; // Exit the method if patron not found
      }
      // Loop through the book array
      for (int i = 0; i < books.length; i++) {
         if (books[i] != null) {
            
            if (books[i].getId() == bookId) {
               tempBook = books[i]; // Store the matching object to a temp variable
                              
               if (tempBook.isCheckedOut()) { // Check if the books is already checked out
                  System.out.println("Book already checked out.");
                  return;
               }
               break; // Leave the method once found
            }
         } 
      }
      if (tempBook == null) { // Checks to see if the book was found
         System.out.println("Book id not found.");
         return; // Exit the method if book not found
      }
      
      tempBook.setCheckedOut(true); // Set the book to being checked out
      tempPatron.checkOutBook(bookId); // Checks out the book
      
      System.out.println("Book checked out successfully.");
   }
    
   // Return a book
   public static void returnBook(Book[] books, Patron[] patrons, Scanner console) {
      System.out.print("Enter a patron Id: "); // Ask the user to enter a patron Id
      int patronId = console.nextInt();
      
      System.out.print("Enter a book Id: "); // Ask the user to enter a book Id
      int bookId = console.nextInt();
      
      Patron tempPatron = null;
      Book tempBook = null;
      
      // Loop through the patron array
      for (int i = 0; i < patrons.length; i++) {
         if (patrons[i] != null) {
            
            if (patrons[i].getId() == patronId) {
               tempPatron = patrons[i]; // Store the matching object to a temp variable
               break; // Leave the loop once found
            }
         } 
      }
      if (tempPatron == null) { // Checks to see if the patron was found
         System.out.println("Patron id not found.");
         return; // Exit the method if patron not found
      }
      
      // Loop through the book array
      for (int i = 0; i < books.length; i++) {
         if (books[i] != null) {
            
            if (books[i].getId() == bookId) {
               tempBook = books[i]; // Store the matching object to a temp variable
               break; // Leave the loop once found
            }
         } 
      }
      if (tempBook == null) { // Checks to see if the book was found
         System.out.println("Book Id not found.");
         return; // Exit the method if book not found
      }
      
      // Try to return the book and check if patron had it
      if (!tempPatron.returnBook(bookId)) {
         System.out.println("This patron does not have this book checked out.");
         return; // Leave the method
      }
      
      tempBook.setCheckedOut(false); // Sets the book to not checked out
      
      System.out.println("Book returned successfully.");
   }
   
   // Search a book by its Id
   public static void searchBookById(Book[] books, Scanner console) {
      System.out.print("Enter a book Id: "); // Ask the user for a book Id
      int bookId = console.nextInt();
      
      // Loop through the books array to find the book with that Id
      for (int i = 0; i < books.length; i++) {
         if (books[i] != null) {
            
            if (books[i].getId() == bookId) {
               System.out.println(books[i]); // Print the info of the book
               return; // Leave the method
            }
         }
      }
      System.out.println("Book Id not found.");
   }
   
   // Search a patron by their Id
   public static void searchPatronById(Patron[] patrons, Scanner console) {
      System.out.print("Enter a patron Id: "); // Ask the user to enter a patron Id
      int patronId = console.nextInt();
      
      // Loop through the patrons array to find the patron with that Id
      for (int i = 0; i < patrons.length; i++) {
         if (patrons[i] != null) {
            
            if (patrons[i].getId() == patronId) {
               System.out.println(patrons[i]); // Print the info of the patron
               return; // Leave the method
            }
         }
      }
      System.out.println("Patron Id not found.");
   }
}