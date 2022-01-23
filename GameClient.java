import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
	
	private Socket gameSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;
	private PrintWriter socketOut;
	
	public GameClient(String serverName, int portNumber) {
		try {
			gameSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(gameSocket.getInputStream()));
			socketOut = new PrintWriter(gameSocket.getOutputStream(), true);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void communicateWithServer() { // will recieve X, O, board and display to user
		System.out.println("Connected to server, waiting on other player to join....");
		try {
			while(true) {
				String line = "";
				
				while(true) {
					line = socketIn.readLine();  // reading from server
					if(line.contains("\0")) {
						line = line.replace("\0", "");
						System.out.println(line);
						break;
					}
					
					if(line.equals("QUIT")) {
						return;
					}
					System.out.println(line);
				} 
		
				line = stdIn.readLine();
				socketOut.println(line);
				socketOut.flush();
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		} 
		
		try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			System.out.println("Closing error: " + e.getMessage());
		}
		
	}
	

	public static void main(String[] args) {
		GameClient theClient = new GameClient("localhost", 8099);
		System.out.println("Client is now running");
		theClient.communicateWithServer();
		// TODO Auto-generated method stub

	}

}
