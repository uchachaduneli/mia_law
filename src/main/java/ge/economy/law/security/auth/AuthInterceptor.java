package ge.economy.law.security.auth;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ucha
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws IOException {

        Integer userId = (Integer) request.getSession().getAttribute("userId");
        String uri = request.getRequestURI();

        if (userId == null && request.getHeader("X-Requested-With") == null) {
            if (uri.startsWith(request.getContextPath())) {
                uri = uri.replace(request.getContextPath(), "");
            }
            response.sendRedirect("login");
            /*if (uri.length() > 0 && !uri.equals("/")) {
                response.sendRedirect("login");
            } else {
                
            }*/
            return false;
        } else if (userId == null) {
            response.sendError(353, "áƒ¡áƒ”áƒ¡áƒ˜áƒ�áƒ¡ áƒ’áƒ�áƒ£áƒ•áƒ˜áƒ“áƒ� áƒ•áƒ�áƒ“áƒ�, áƒ’áƒ—áƒ®áƒ�áƒ•áƒ— áƒ—áƒ�áƒ•áƒ˜áƒ“áƒ�áƒœ áƒ’áƒ�áƒ˜áƒ�áƒ áƒ�áƒ— áƒ�áƒ•áƒ¢áƒ�áƒ áƒ˜áƒ–áƒ�áƒªáƒ˜áƒ�");
            return false;
        }
        return true;
    }
}
