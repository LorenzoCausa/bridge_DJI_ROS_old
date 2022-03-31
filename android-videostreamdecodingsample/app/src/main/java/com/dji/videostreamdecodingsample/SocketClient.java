package com.dji.videostreamdecodingsample;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class SocketClient extends AsyncTask<byte[], Void, Void> {
    @Override
    protected Void doInBackground(byte[]... voids) {
        byte[] buf = voids[0];
        byte[] ip = voids[1];
        byte[] port = voids[2];

        DatagramSocket socket;
        InetAddress address;
        String ip_address = new String(ip, StandardCharsets.UTF_8); // for UTF-8 encoding
        String portStr = new String(port, StandardCharsets.UTF_8); // for UTF-8 encoding
        Integer my_port = Integer.valueOf(portStr);

        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
            socket = null;
        }
        try {
            address = InetAddress.getByName(ip_address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            address = null;
        }
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, my_port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}