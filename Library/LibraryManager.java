import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class LibraryManager {
   
   public static void main(String[] args) {
      try {
         Scanner console = new Scanner(System.in); // Scanner for the menu
         
         Book[] books = LibraryManagerModule.readBooks("books.txt"); // Initialize books
         Patron[] patrons = LibraryManagerModule.readPatrons("patrons.txt"); // Initialize patrons

         LibraryManagerModule.showMenu(books, patrons, console); // Prints and uses the menu in the console
      } catch (FileNotFoundException e) {
         System.out.println("Error loading file " + e);
      }
   }
}