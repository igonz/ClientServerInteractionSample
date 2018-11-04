import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServerMain {

    static final Integer REQUEST_NUM = 10;

    public static void main(String[] args) {
        try {
            Integer count = 0;
            DatagramSocket datagramSocket = new DatagramSocket(4444);

            while(true) {
                count++;

                byte[] buf = new byte[256];

                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                datagramSocket.receive(packet);

                System.out.println("Datagram received");
                String response = "Yes, I am up. You have " + count + " requests left before I shutdown";

                  InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(response.getBytes(), response.getBytes().length, address, port);
                datagramSocket.send(packet);

                System.out.println("Datagram Response sent");
                if(REQUEST_NUM.equals(count)) {
                    break;
                }
            }
            datagramSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
