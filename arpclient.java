import java.io.*;
import java.net.*;

class arpclient {
    public static void main(String args[]) {
        try {
            Socket client = new Socket("localhost", 2500);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintStream ps = new PrintStream(client.getOutputStream());
            String ipaddr, haddr = null;
            BufferedReader sin = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.print("Enter the IP address : ");
            ipaddr = br.readLine();
            ps.println(ipaddr);
            haddr = sin.readLine();
            if (haddr == null)
                System.out.println("Host does not exist");
            else
                System.out.println("Physical Address " + haddr);
            ps.close();
            br.close();
            client.close();
        } catch (IOException io) {
            System.err.println(io.toString());
        }
    }
}
