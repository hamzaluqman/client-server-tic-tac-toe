

public class Board implements Constants {
	private char theBoard[][]; // 2D char array
	private int markCount; // mark count integer

	/**
	 * Method to create the board layout of the game.
	 * creates a board with the 3 rows and 3 columns initially having a space char
	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR; // adding space char to every row every column in the board
		}
	}
	
	/**
	 * Returning the mark at a specific row and column on the board
	 * @param row row index of theBoard
	 * @param col column index of theBoard
	 * @return mark at row, col of theBoard
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}
	
	/**
	 * Checking to see if board is full.
	 * @return true of board is full, otherwise false
	 */
	public boolean isFull() {
		return markCount == 9; // board is full if markCount is 9
	}
	
	/**
	 * Checking if xPlayer win condition is true
	 * @return true if Player with mark X wins, false otherwise.
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1) // calling checkWinner method to determine boolean value
			return true;
		else
			return false;
	}
	
	/**
	 * Checking if oPlayer win condition is true
	 * @return true if Player with mark O wins, false otherwise.
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1) // calling checkWinner method to determine boolean value
			return true;
		else
			return false;
	}
	
	/**
	 * Adding row column labels and creating grid view of 3 row 3 columns to display
	 * board like a tic-tac-toe game
	 */
	public String display() {
		String theBoard = "";
		theBoard += displayColumnHeaders(); // displaying column headers
		theBoard += addHyphens(); // adding hyphens to display to separate row/columns
		for (int row = 0; row < 3; row++) {
			theBoard += addSpaces(); // adding spaces between row/column grids
			theBoard += "    row " + row + ' ';
			for (int col = 0; col < 3; col++)
				theBoard += "|  " + getMark(row, col) + "  ";
			theBoard += "|\n";
			theBoard += addSpaces();
			theBoard += addHyphens();
			
		}
		
		return theBoard;  // need "\0" here
	}
	
	/**
	 * Adding Player mark on the board
	 * @param row Row index on board
	 * @param col Column index on board
	 * @param mark Player mark 
	 */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++; // incrementing markCOunt to keep track if board gets full
	}
	
	/**
	 * Clearing the board of all marks and replacing with space char ' '
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0; // setting markCount to 0
	}
	
	/**
	 * Checking to see if Player with mark has won
	 * @param mark Player mark
	 * @return result = 1 if there is a winner
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;
		
		// For every row, checking if the same mark exists across the columns
		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		// For every column, checking if the same mark exists across all rows
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}
		
		// Checking the diagonal (row 0 column 0), (row 1, column 1), (row 2, column 2)
		// to check if same mark exists across the diagonal
		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		
		// Checking the diagonal (row 0 column 2), (row 1, column 1), (row 2, column 0)
		// to check if same mark exists across the diagonal
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}
	
	/**
	 * method to display column headers (column number)
	 */
	String displayColumnHeaders() {
		String columnHead = "";
		columnHead += "          ";
		for (int j = 0; j < 3; j++)
			columnHead +="|col " + j;
		columnHead +="\n";
		return columnHead;
	}
	
	/**
	 * method to print hyphens
	 */
	String addHyphens() {
		String hyphen = "";
		
		hyphen += "          ";
		for (int j = 0; j < 3; j++)
			hyphen +="+-----";
		hyphen +="+\n";
		return hyphen;
	}
	/**
	 * method to add spaces 
	 */
	String addSpaces() {
		String space = "";
		space += "          ";
		for (int j = 0; j < 3; j++)
			space +="|     ";
		space += "|\n";
		
		return space;
	}
}
