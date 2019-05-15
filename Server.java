package connect;

import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class Server {
    private String recive;//receiving data store in this variable
    private static ServerSocket serverSocket;
    public static Socket connection = null;
    private Scanner received;
    public static Formatter send;

    //************************************************************************************
    //method for connecting to client & listening
    public static void connectServer() {
        try {
            serverSocket = new ServerSocket(15000);
            connection = serverSocket.accept();
        } catch (Exception e) {

        }
    }//end of method connectServer

    //************************************************************************************
    //method for receiving data from client
    public String recievedServer() {
        try {
            received = new Scanner(connection.getInputStream());

            //check if client is connected else close the socket & show message
            if (received.hasNextLine()) {
                recive = received.nextLine();
            } else {
                recive = "EXIT";
                connClose();
                JOptionPane.showMessageDialog(null, "Client disconnected!!!", "Client error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
        }
        return recive;
    }//end of method recievedServer

    //*********************************************************************************************************
    //method for sending data to client
    public void sendServer(String text) {
        try {
            connectServer();
            send = new Formatter(connection.getOutputStream());
            send.format(text + "\n");
            send.flush();
        } catch (Exception e) {

        }

    }//end of method sendServer

    //********************************************************************************************************
    //method for closing socket
    public void connClose() {
        try {
            connection.close();
        } catch (Exception e) {

        }
    }//end of method connClose

    //**********************************************************************************************************
    //method for waiting till one client is connected & show message
    public String connWating() {
        String status = "";
        if (connection != null) {
            status = "Client is connected!!!\n===========================================\n";
        }
        return status;

    }

}//end of method connWating
