/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailsender;

/**
 *
 * @author haophan
 */
import java.io.*;
import java.net.*;

public class EmailSender {

    final static int port = 25;
    final static String hostname = "gmail-smtp-in.l.google.com";
    final static String from = "abc@wit.edu";
    final static String to = "abcm@gmail.com";

    public static void main(String[] args) throws Exception {
        
        // Establish a TCP connection with the Gmail server. 
        Socket socket = new Socket(hostname, port);

        // Create a BufferedReader to read a line at a time.
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("220")) {
            throw new Exception("220 reply not received from server.");
        }

        // Read greeting from the server.
        // Get a reference to the socket's output stream.
        OutputStream os = socket.getOutputStream();

        // Send HELO command and get server response.
        String command = "HELO GOOGLE\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }

        // Send MAIL FROM command.
        //Code Goes Here
        String MAILFROM = "mail from:<" + from + ">\r\n";
        os.write(MAILFROM.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(MAILFROM + "    " + response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }

        // Send RCPT TO command.
        //Code Goes Here
        String RCPTTO = "rcpt to:<" + to + ">\r\n";
        os.write(RCPTTO.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(RCPTTO + "    " + response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }

        // Send DATA command.
        //Code Goes Here
        String DATA = "DATA\r\n";
        os.write(DATA.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(DATA + "    " + response);
        if (!response.startsWith("354")) {
            throw new Exception("354 reply not received from server.");
        }

        // Send message data. 
        // (Do not forget to include header lines, e.g., To: From: Subject:
// Please send me your section number (01, 09, or 13), YOUR NAME, and your Project Topic or 
// Title.
        //Code Goes Here
        String mess = "From:<" + from + ">\r\n";
        os.write(mess.getBytes("US-ASCII"));
        // response = br.readLine();
        System.out.println(mess + "    " + response);

        //Code Goes Here
        String mess1 = "To:<" + to + ">\r\n";
        os.write(mess1.getBytes("US-ASCII"));
        // response = br.readLine();
        System.out.println(mess1 + "    " + response);

        //Code Goes Here
        String mess2 = "Subject:COMP-2100 05 Hao Phan\r\n";
        os.write(mess2.getBytes("US-ASCII"));
        // response = br.readLine();
        System.out.println(mess2 + "    " + response);

        //Code Goes Here
        String mess3 = "My name is Hao phan, and my partner is Nhu Nguyen.\r\n"
                + "And We are going to do chess game\r\n"
                + "Happy Thanksgiving\r\n\n"
                + "Best regards,\r\n"
                + "Hao Phan\r\n";
        os.write(mess3.getBytes("US-ASCII"));
        // response = br.readLine();
        System.out.println(mess3 + "    " + response);

        // End with line with a single period.
        //Code Goes Here
        String EndSending = ".\r\n";
        os.write(EndSending.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(EndSending + "    " + response);
        if (!response.startsWith("250")) {
            throw new Exception("354 reply not received from server.");
        }

        // Send QUIT command.
        //Code Goes Here
        String quit = "quit\r\n";
        os.write(quit.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(quit + "    " + response);
        if (!response.startsWith("221")) {
            throw new Exception("354 reply not received from server.");
        }

    }
}
