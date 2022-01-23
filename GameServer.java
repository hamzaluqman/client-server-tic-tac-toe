import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {
	
	private ServerSocket serverSocket;
	private Socket gameSocket;
	private ExecutorService pool;
	
	public GameServer(int portNumber) {
		try {
			serverSocket = new ServerSocket(portNumber);
			pool = Executors.newCachedThreadPool();
			//pool = Executors.newFixedThreadPool(4);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void communicateWithClient() {
		// this is just for communication
		// we need to communicate with client
		// The objects of various classes that were created in Game.java main will be created by the server
		// server is not reading and writing to socket. 
		try {
			while (true) {
				Player xPlayer = new Player(serverSocket.accept(), 'X'); 
				Player oPlayer = new Player(serverSocket.accept(), 'O');
				Referee theRef = new Referee(); 
				theRef.setoPlayer(oPlayer);  
				theRef.setxPlayer(xPlayer); 
				Game theGame = new Game();
				theGame.appointReferee(theRef);
				
				System.out.println("The game has started!");
				
				pool.execute(theGame);
			}
		} catch (IOException e) {
			e.printStackTrace();
			pool.shutdown();
		}
		
	}

	public static void main(String[] args) {
		GameServer theServer = new GameServer(8099);
		System.out.println("Server is now running");
		theServer.communicateWithClient();
		// TODO Auto-generated method stub

	}

}
