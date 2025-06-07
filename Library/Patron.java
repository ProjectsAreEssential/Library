import java.util.Arrays;

public class Patron {
   
   // Instance variables
   private int patronId;
   private String name;
   private int[] checkedOutBooks = new int[3]; // Holds up to 3 book Ids the patron has checked out
   private int bookCount; // Keeps track of how many books the patron has checked out
   
   // Constructor
   public Patron(int patronId, String name) {
      this.patronId = patronId;
      this.name = name;
   }
   
   // Gets the patron id
   public int getId() {
      return patronId;
   }
   
   // Gets the patron name
   public String getName() {
      return name;
   }
   
   // Gets the checked out books
   public int[] getBooks() {
      return checkedOutBooks;
   }
   
   // Gets the book count
   public int getCount() {
      return bookCount;
   }
   
   // Sets the patron id
   public void setId(int newId) {
      patronId = newId;
   }
   
   // Sets the patron name
   public void setName(String newName) {
      name = newName;
   }
   
   // Sets the checked out books
   public void setBooks(int[] newBooks) {
      checkedOutBooks = newBooks;
   }
   
   // Sets the book count
   public void setCount(int newCount) {
      bookCount = newCount;
   }
   
   // Adds the book id to "checkedOutBooks" if theres space, and it's not already checked out
   public boolean checkOutBook(int bookId) {
      if (bookCount >= 3) return false; // Already at max books
      
      for (int i = 0; i < checkedOutBooks.length; i++) {
         if (checkedOutBooks[i] == bookId) return false; // Already has this book
      } 
      
      // Add the book to the first available slot
      for (int i = 0; i < checkedOutBooks.length; i++) {
         if (checkedOutBooks[i] == 0) {
            checkedOutBooks[i] = bookId;
            bookCount++; // Increment their book count
            return true;
         }
      } 
      return false;
   }
   
   // Removes the book id from "checkedOutBooks" if found
   public boolean returnBook(int bookId) {
      for (int i = 0; i < checkedOutBooks.length; i++) {
         if (checkedOutBooks[i] == bookId) {
            checkedOutBooks[i] = 0;
            bookCount--; // Decrement their book count
            return true;
         }
      }
      return false;
   }
   
   // Prints a string representation
   public String toString() {
      String temp = ""; // Initialize a temp variable
      for (int i = 0; i < checkedOutBooks.length; i++) {
         if (checkedOutBooks[i] != 0) { // If a element doesnt equal 0, then its a book id
            temp += checkedOutBooks[i] + " "; // Add the book id's to temp with spaces for readability
         }
      }
      
      return "Patron Id: " + patronId + "\n"
           + "Name: " + name + "\n"
           + "Checked Out Books: " + temp.trim() + "\n"
           + "Book Count: " + bookCount;
   }
}