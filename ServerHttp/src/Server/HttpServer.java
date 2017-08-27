/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Entity.Port;
import Server.Request.Request;
import Server.Response.Response;
import Server.Response.ResponseFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jordanferreirasaran
 */
public class HttpServer extends Port {
    
    private final static Logger logger = Logger.getLogger(HttpServer.class
			.toString());
    
    /**
    * Construtor do servidor de HTTP
    * 
    * @param host
    *            host do servidor
    * @param port
    *            porta do servidor
    */
    public HttpServer(String host, int port) {
        super(host, port);
    }
    
    public HttpServer() {
        super("localhost", 8080);
    }
    
    /**
    * Inicia o servidor e fica escutando no endereço e porta especificada no
    */
    public void serve() {
        
        logger.log(Level.INFO, "Iniciando servidor no endere\u00e7o: {0}:{1}", 
                new Object[]{this.getHost(), this.getPort()});		

        try {
            // Cria a conexão servidora
            this.setServerSocket(new ServerSocket(this.getPort(), 1,
                            InetAddress.getByName(this.getHost())));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao iniciar servidor!", e);
            return;
        }
        logger.log(Level.INFO, "Conex\u00e3o com o servidor aberta no "
                + "endere\u00e7o: {0}:{1}", new Object[]{this.getHost(), 
                                                        this.getPort()});

        // Fica esperando pela conexão cliente
        while (true) {
            logger.info("Aguardando conexões...");
            try {
                this.setSocket(this.getServerSocket().accept());
                this.setInput(this.getSocket().getInputStream());
                this.setOutput(this.getSocket().getOutputStream());

                // Realiza o parse da requisição recebida
                String requestString = convertStreamToString(this.getInput());
                logger.log(Level.INFO, "Conex\u00e3o recebida. Conte\u00fado:\n{0}", requestString);
                Request request = new Request();
                request.parse(requestString);

                // recupera a resposta de acordo com a requisicao
                Response response = ResponseFactory.createResponse(request);
                String responseString = response.respond();
                logger.log(Level.INFO, "Resposta enviada. Conte\u00fado:\n{0}", 
                        responseString);
                this.getOutput().write(responseString.getBytes());
                // Fecha a conexão
        this.getSocket().close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Erro ao executar servidor!", e);
            } 
        }
        
   }

   private String convertStreamToString(InputStream is) {
        if (is != null) {
            Writer writer = new StringWriter();
            char[] buffer = new char[2048];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is));
                int i = reader.read(buffer);
                writer.write(buffer, 0, i);
            } catch (IOException e) {
                logger.log(Level.SEVERE, 
                        "Erro ao converter stream para string", e);
                return "";
            }
            return writer.toString();
        } else return "";
   }
   
   public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.serve();
    }
}