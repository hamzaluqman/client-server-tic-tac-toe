import java.io.IOException;



public class Referee {
	
	private Player xPlayer; // Player with mark X
	private Player oPlayer; // Player with mark O
	private Board board; // board of the game
	
	/**
	 * Referee constructor
	 */
	public Referee() {
	}
	
	/**
	 * Method to set the opponents, display the board and run the game
	 * @throws IOException 
	 */
	public void runTheGame() throws IOException {
		
		xPlayer.setOpponent(oPlayer); // opponent of xPlayer is oPlayer
		//System.out.println("check 1");
		oPlayer.setOpponent(xPlayer); // opponent of oPlayer is xPlayer
		//System.out.println("check 2");
		
		xPlayer.getPlayerName();
		//System.out.println("check 3");
		
		oPlayer.getPlayerName();
		//System.out.println("check 4");
		
		System.out.println("\nReferee has started the game.");
		board.display();
		xPlayer.play();
	}
	
	/**
	 * Getter for xPlayer
	 * @return xPlayer
	 */
	public Player getxPlayer() {
		return xPlayer;
	}
	
	/**
	 * Setter for xPlayer
	 * @param xPlayer xPlayer of the game
	 */
	public void setxPlayer(Player xPlayer) {
		this.xPlayer = xPlayer;
	}
	
	/**
	 * Getter for oPlayer
	 * @return oPlayer
	 */
	public Player getoPlayer() {
		return oPlayer;
	}
	
	/**
	 * Setter for oPlayer
	 * @param oPlayer oPlayer of the game
	 */
	public void setoPlayer(Player oPlayer) {
		this.oPlayer = oPlayer;
	}
	
	/**
	 * Getter for board
	 * @return board
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Setter for board
	 * @param board board for the game
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

}
