package mytest;

public class Main_book {

	public static void main(String[] args) {
		Bookmgs_1 mgs = new Bookmgs_1();
		
		mgs.addBook(new Book_1("asdf", 1000));
		mgs.addBook(new Book_1("eeee", 1000));
		mgs.addBook(new Book_1("3333", 1000));
		mgs.addBook(new Book_1("4555", 1000));
		mgs.addBook(new Book_1("6666", 1000));
		
		
		mgs.printBookList();
		mgs.printTotalPrice();
	}

}
