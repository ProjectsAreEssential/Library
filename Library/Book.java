public class Book {
   
   // Instance variables
   private int bookId;
   private String title;
   private String author;
   private boolean isCheckedOut;
   
   // Constructor
   public Book(int bookId, String title, String author, boolean isCheckedOut) {
      this.bookId = bookId;
      this.title = title;
      this.author = author;
      this.isCheckedOut = isCheckedOut;
   }
   
   // Gets the book id
   public int getId() {
      return bookId;
   }
   
   // Gets the book title
   public String getTitle() {
      return title;
   }
   
   // Gets the book author
   public String getAuthor() {
      return author;
   }
   
   // Gets the book status
   public boolean isCheckedOut() {
      return isCheckedOut;
   }
   
   // Sets the book id
   public void setId(int newId) {
      bookId = newId;
   }
   
   // Sets the book title
   public void setTitle(String newTitle) {
      title = newTitle;
   }
   
   // Sets the book author
   public void setBookAuthor(String newAuthor) {
      author = newAuthor;
   }
   
   // Sets the book status
   public void setCheckedOut(boolean newIsCheckedOut) {
      isCheckedOut = newIsCheckedOut;
   }
   
   // Checks if the book is available
   public boolean isAvailable() {
      return !isCheckedOut;
   }
   
   // Prints a string representation
   public String toString() {
      return "Id: " + bookId + "\n"
           + "Title: " + title + "\n"
           + "Author: " + author + "\n"
           + "Checked Out? " + isCheckedOut;
   }
}