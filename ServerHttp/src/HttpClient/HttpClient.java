/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HttpClient;

import Entity.Port;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

/**
 *
 * @author jordanferreirasaran
 */
public class HttpClient extends Port {
    
   public final static Logger logger = Logger.getLogger(HttpClient.class
			.toString());
    
   /**
   * Versão do protocolo utilizada
   */
   private final static String HTTP_VERSION = "HTTP/1.1";
   private StringBuffer sb;
   
   /**
    * Construtor do cliente HTTP
    * @param host host para o cliente acessar
    * @param port porta de acesso
    */
   public HttpClient(String host, int port) {
       super(host, port);
   }
   
   /**
    * Realiza uma requisição HTTP e devolve uma resposta
    * @param path caminho a ser feita a requisição
    * @return resposta do protocolo HTTP
    * @throws UnknownHostException quando não encontra o host
    * @throws IOException quando há algum erro de comunicação
    */
   public String getURIRawContent(String path) throws UnknownHostException,
            IOException {
        try (Socket socket = new Socket(this.getHost(), this.getPort())) {
            // Abre a conexão
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));

            // Envia a requisição
            out.println("GET " + path + " " + HTTP_VERSION);
            out.println("Host: " + this.getHost());
            out.println("Connection: Close");
            out.println();

            boolean loop = true;
            sb = new StringBuffer();

            // recupera a resposta quando ela estiver disponível
            while (loop) {
                if (in.ready()) {
                    int i = 0;
                    while ((i = in.read()) != -1) {
                            sb.append((char) i);
                    }
                    loop = false;
                }
            }
            return sb.toString();
        }
   }
}
