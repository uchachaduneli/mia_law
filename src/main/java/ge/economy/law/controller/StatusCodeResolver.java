package ge.economy.law.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class StatusCodeResolver { // ეს კლასი იჭერს ექსეფშენებს და სტატუსს ცვლის http რესფონსის ჰედერში
    @ExceptionHandler(Exception.class)
    public void handleException(Exception exception, HttpServletResponse response) throws IOException {
//        try {
//            if (exception instanceof AbaraException) {
//                response.setStatus(((AbaraException) exception).getErrorCode());
////                response.setContentType("text/html;charset=UTF-8");
////                response.getWriter().write(((AbaraException) exception).getMessage());
//            } else if (exception instanceof javax.persistence.PersistenceException) {
//                response.setStatus(ErrorCodesDTO.DATABASE_ERROR);
//                response.setContentType("text/html;charset=UTF-8");
////                response.getWriter().write(exception.getMessage());
//            } else {
//                response.setStatus(ErrorCodesDTO.UNDEFINED_ERRROR);
////                response.getWriter().write(exception.getMessage());
//            }
//        } catch (IOException ex) {
//            response.setStatus(ErrorCodesDTO.UNDEFINED_ERRROR);
//        }
    }
}
