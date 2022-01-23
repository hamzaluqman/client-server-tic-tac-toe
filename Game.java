
import java.io.*;


public class Game implements Constants, Runnable {

	private Board theBoard; // theBoard variable of type Board
	private Referee theRef; // theRef variable of type Referee
	
    public Game( ) {
        theBoard  = new Board(); // creating a Board object
	}
    
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
        theRef.setBoard(theBoard);
		theRef.getxPlayer().setBoard(theBoard);
		theRef.getoPlayer().setBoard(theBoard);
    }

	@Override
	public void run() {
		try {
			theRef.runTheGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
