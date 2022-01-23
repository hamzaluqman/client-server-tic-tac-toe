
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;




public class Player {
	
	private String name;
	private char mark;
	private Player opponent;
	private Board board;
	private Socket gameSocket;
	private BufferedReader socketIn;
	private PrintWriter socketOut;
	
	/**
	 * Constructor for Player class.
	 * @param name The Players name
	 * @param mark The Players mark ('X' or 'O')
	 */
	public Player(Socket socket, char mark) {
		
		this.gameSocket = socket;
		setMark(mark);
		try {
			socketIn = new BufferedReader(new InputStreamReader(gameSocket.getInputStream()));
			socketOut = new PrintWriter(gameSocket.getOutputStream(), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	/**
	 * Allows the players to play the game and checks for win/tie conditions
	 * @throws IOException 
	 * 
	 */
	public void play() throws IOException {
		while (!board.xWins() || !board.oWins() || !board.isFull()) { // checks if Player X or Player O has won the game or if its a tie
			
			this.makeMove(); // calls the makeMove method
			socketOut.println(board.display()); // displays the board after the move has been made
			opponent.socketOut.println(board.display());
			
			if (board.xWins() == true) {
				socketOut.println("GAME OVER! "+ name + " is the winner. ");
				opponent.socketOut.println("GAME OVER! "+ name + " is the winner. ");
				break;
			}
			System.out.println("HERE 1");
			this.opponent.makeMove(); // Passing the turn to the opponent
			System.out.println("HERE 2");
			
			socketOut.println(board.display()); // displays the board after the move has been made
			opponent.socketOut.println(board.display());
			//board.display();
			if (board.oWins() == true) {
				socketOut.println("GAME OVER! "+ opponent.getName() + " is the winner.");
				opponent.socketOut.println("GAME OVER! "+ opponent.getName() + " is the winner. ");
				break;
			} 
			if (board.isFull() == true) {
				socketOut.println("It is a tie game! ");
				opponent.socketOut.println("It is a tie game! ");
				break;
			}
		}
	}
	
	/**
	 * Allows the user to input the row and column on the board where the Player would like to add the mark
	 * @throws IOException 
	 */
	public void makeMove() throws IOException {

		System.out.println("Inside make move 1");
		sendString(name + ", Enter row for your next "+ mark+ " be placed in:\0");
		System.out.println("Inside make move 2");
		int selectedRow = Integer.parseInt(socketIn.readLine());
		
		sendString(name + ", Enter column should your next "+ mark+ " be placed in:\0 ");
		int selectedColumn = Integer.parseInt(socketIn.readLine());
	
		board.addMark(selectedRow, selectedColumn, mark);	
	}
	
	
	private void sendString (String toSend) {
		//socketOut.println(toSend + "\0");
		socketOut.println(toSend);
		socketOut.flush();
	}
	public void getPlayerName() {
		try {
			sendString("Please enter the name of " + mark + " player:\0");
			name = socketIn.readLine();
			while (name == null) {
				sendString("Please enter a name:\0");
				name = socketIn.readLine();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Getter for name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for name
	 * @param name Player name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for mark
	 * @return mark
	 */
	public char getMark() {
		return mark;
	}
	
	/**
	 * Setter for mark
	 * @param mark Player mark
	 */
	public void setMark(char mark) {
		this.mark = mark;
	}
	
	/**
	 * Getter for opponent
	 * @return opponent
	 */
	public Player getOpponent() {
		return opponent;
	}
	
	/**
	 * Setter for opponent
	 * @param opponent opponent Player
	 */
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}
	
	/**
	 * Getter for board
	 * @return board
	 */
	public Board getBoard() {
		return board;
	}
	
	public PrintWriter getSocketOut() {
		return socketOut;
	}
	
	/**
	 * Setter for board
	 * @param board board for the game
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	
}