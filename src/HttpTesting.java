import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpTesting extends Thread {
    Socket socket;
    public HttpTesting(Socket socket) {this.socket = socket;}
    @Override
    public void run() {

            try{
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = bufferedReader.readLine();
                System.out.println(line);
                String requestMethod = line.split(" ")[0];
                String header = line.split(" ")[1];
                if (requestMethod.equals("GET")){
                    if(header.equals("/")){
                        String page = filePath("./src/file.html");
                        socket.getOutputStream().write((("HTTP/1.1 200 OK" +" \r\n\r\n" + page).getBytes()));
                    } else if (header.equals("/json")) {
                        String jsonFile = filePath("/Users/decagon/Downloads/TestingServer/src/json");
                        socket.getOutputStream().write(("HTTP/1.1 200 OK \r\nContent-Type: application/json\r\n\r\n".getBytes()));
                        socket.getOutputStream().write((jsonFile).getBytes());

                    }else socket.getOutputStream().write(("HTTP/1.1 200 OK\r\n\r\n"+"<html>Page failed to load...</html>").getBytes());
                }
            }catch (IOException e){
                throw new RuntimeException();
            }
        }

    public static String filePath(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line ;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line =bufferedReader.readLine()) !=null){
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }
}
