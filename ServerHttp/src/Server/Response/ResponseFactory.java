/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Response;

import Server.Request.Request;

/**
 *
 * @author jordanferreirasaran
 */
public class ResponseFactory {
    /**
    * Retorna a resposta adequada ao request
    * @param request request
    * @return resposta de acordo com o request
    */
   public static Response createResponse(Request request) {
        // TODO: Colocar outros tipos de response. Ex: FileResponse, DBResponse,
        // CacheResponse

        return new DummyResponse(request);
   }
}
