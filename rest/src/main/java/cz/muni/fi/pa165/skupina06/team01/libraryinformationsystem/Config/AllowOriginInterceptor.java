/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * We need to rewrite headers to allow for cross-origin access for some
 * operations e.g. DELETE. Without this, you might get messages such as "HTTP
 * Status 405 - Request method 'DELETE' not supported" when trying to DELETE,
 * PUT or POST Here we are allowing all origins "Access-Control-Allow-Origin",
 * "*" and all operations "Access-Control-Allow-Methods", "GET, POST, PUT,
 * DELETE, OPTIONS"
 *
 * 
 */
public class AllowOriginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Headers", "x-http-password, x-http-login");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        return true;
    }

}
