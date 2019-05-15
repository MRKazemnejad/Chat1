package connect;


import form.ClientForm;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class Client {

    public static Socket socket = null;
    public static Scanner received;
    public static Formatter send;
    private String receive;


    //**************************** connectClient ******************************************
    //making a new socket

    public void connectClient() {
        try {
            socket = new Socket("localhost", 15000);

            //for text area being ready.if we don't use this interrupt we will get null pointer exception
            Thread.sleep(300);

            //setting text area when connected to server
            ClientForm.jTextArea1.append("Connected to Server!\n===========================================\n");

        } catch (Exception e) {

            //setting text area when not connected to server
            ClientForm.jTextArea1.append("Not connected to Server!\n===========================================\n");
            int input = JOptionPane.showOptionDialog(null, "Server not responding !!!\nDo you want to try later ?", "Server error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
            if (input == JOptionPane.OK_OPTION) {
                System.exit(0);
            } else {
                connectClient();
            }
        }

    }//end of connectClient method

    //**************************** sendData *************************************************
    //method for sending data
    public void sendData(String text) {
        try {
            send = new Formatter(socket.getOutputStream());

            send.format(text + "\n");
            send.flush();

        } catch (Exception e) {

        }
    }

    //***************************** receivedData ********************************************
    //method for getting data
    public String receivedData() {
        String data = "";
        try {
            received = new Scanner(new BufferedInputStream(socket.getInputStream()));
            if (received.hasNextLine()) {
                receive = received.nextLine();
                data = receive;
            } else {
                data = "EXIT";
                connClose();
                JOptionPane.showMessageDialog(null, "Server not responding!!!", "Server error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {

        }
        return data;
    }//end of receiveddata method

    //******************************************* connClose **********************************
    //method for closing socket

    public void connClose() {
        try {
            socket.close();
        } catch (Exception e) {

        }
    }//end of connClose method

    //**************************************** connStatus **********************************
    //method for indicating that client is connected to server or not

    public String connStatus() {
        String status = "";
        if (socket != null) {
            status = "Connected to Server!\n===========================================\n";
        }
        return status;
    }//end of connStatus method

}
