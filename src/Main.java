import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 3000;
        try( ServerSocket serverSocket = new ServerSocket(port);){
            System.out.println("Server created on port "+port);
            HttpTesting httpTesting;
            Socket socket ;
            while(true){
                socket = serverSocket.accept();
                httpTesting = new HttpTesting(socket);
                httpTesting.start();
            }
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }

    }
}