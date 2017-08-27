/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverhttp;

import HttpClient.HttpClient;
import static HttpClient.HttpClient.logger;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;

/**
 *
 * @author jordanferreirasaran
 */
public class ServerHttp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HttpClient client = new HttpClient("localhost", 8080);
        try {
                System.out.println(client.getURIRawContent(""));
        } catch (UnknownHostException e) {
                logger.log(Level.SEVERE, "Host desconhecido!", e);
        } catch (IOException e) {
                logger.log(Level.SEVERE, "Erro de entrada e saída!", e);
        }
    }
    
}
