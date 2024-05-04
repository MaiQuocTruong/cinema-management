package execute_sever;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import handlerClient.ClientHandler;

public class Run {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(6789);
			ExecutorService execute = Executors.newCachedThreadPool();
			
			System.out.println("Sever Is Running");
			
			while(true) {
				Socket clientSocket = server.accept();
				System.out.println(clientSocket.getInetAddress());
				ClientHandler clientHandler = new ClientHandler(clientSocket);
				execute.execute(clientHandler);
			
			}		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
