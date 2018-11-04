import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SequentialServerMain {

    private static final String OUTPUT_HEADERS = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/plain\r\n" +
            "Content-Length: ";
    private static final String OUTPUT_END_OF_HEADERS = "\r\n\r\n";

    static final Integer REQUEST_NUM = 10;
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            int count = 0;
            ServerSocket serverSocket = new ServerSocket(4444);

            while (true) {
                count++;
                System.out.println("Waiting for request");
                Socket socket = serverSocket.accept();

                System.out.println("Responding");

                PrintWriter out = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream())),
                        true);

                String date = "The Date/Time is " + new SimpleDateFormat("MMMM dd, yyyy HH:mm").format(new Date()) + "\n";
                out.write(OUTPUT_HEADERS + date.length() + OUTPUT_END_OF_HEADERS + date);
//                out.write("The Date/Time is " + new SimpleDateFormat("MMMM dd, yyyy HH:mm").format(new Date()) + "\n");

                //close resources
                out.close();
                socket.close();

                Thread.sleep(10000);
                if(REQUEST_NUM == count) {
                    break;
                }
            }

            serverSocket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
