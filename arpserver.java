import java.io.*;
import java.net.*;

class arpserver {
    public static void main(String args[]) throws IOException {
        try {
            ServerSocket soc = new ServerSocket(2500);
            System.out.println("Server started");
            Socket client = null;
            client = soc.accept();
            String str;
            PrintStream ps = new PrintStream(client.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            Runtime r = Runtime.getRuntime();
            Process p = r.exec("ipconfig /all");
            BufferedReader pin = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String haddr = "";
            String ipaddr = br.readLine();
            int flag = 0;
            while ((str = pin.readLine()) != null) {
                System.out.println(str);
                if ((str.indexOf("HWaddr")) != -1) {
                    int tlen = str.length();
                    int hlen = tlen - 19;
                    haddr = str.substring(hlen, tlen);
                } else if ((str.indexOf(ipaddr)) != -1) {
                    flag = 1;
                }
            }
            if (flag == 1)
                ps.println(haddr);
            ps.close();
            br.close();
            pin.close();
            client.close();
            soc.close();
        } catch (IOException io) {
            System.err.println("Exception : " + io.toString());
        }
    }
}
