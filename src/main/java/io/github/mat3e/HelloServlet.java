package io.github.mat3e;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Hello", urlPatterns = {"/api/*"})
public class HelloServlet extends HttpServlet {
    private static final Object NAME_PARAM = "name";
    private static final Object LANG_PARAM = "lang";
    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    /**
     * *Serblet container needs it
     */

    @SuppressWarnings("unused")
    public HelloServlet(){
        this(new HelloService());
    }

    private final HelloService service;

    HelloServlet(HelloService service)  {
        this.service = service;
    } 

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       logger.info("Request got with parameters " + req.getParameterMap());
       String name = req.getParameter ((String) NAME_PARAM);
       String lang = req.getParameter ((String) LANG_PARAM);
        resp.getWriter().write(service.prepareGreeting(name,lang));

    }
}
