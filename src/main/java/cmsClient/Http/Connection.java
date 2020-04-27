package cmsClient.Http;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;

    public void startConnection(String ip, int port) {
        try
        {
            Socket socket = new Socket(ip, port);
            System.out.println("Connected");

            // takes input from terminal 
            input  = new DataInputStream(System.in);

            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        // string to read message from input 
        String line = "";
    }


}
