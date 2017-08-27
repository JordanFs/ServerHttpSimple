/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author jordanferreirasaran
 */
public class Port {
    protected String host;
    protected int port;
    protected ServerSocket serverSocket;
    protected Socket socket;
    protected InputStream input;
    protected OutputStream output;
    
    protected Port(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    protected void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    protected void setPort(int port) {
        this.port = port;
    }   
    
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    protected void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    
    public Socket getSocket() {
        return socket;
    }

    protected void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    public InputStream getInput() {
        return input;
    }

    protected void setInput(InputStream input) {
        this.input = input;
    }

    public OutputStream getOutput() {
        return output;
    }

    protected void setOutput(OutputStream output) {
        this.output = output;
    }
}
