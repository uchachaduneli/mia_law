package ge.economy.law.security.auth;

import ge.economy.law.dto.UserDTO;
import ge.economy.law.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ucha
 */
@Controller
@RequestMapping
public class AuthController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login(HttpServletRequest request) {
        try {
            Integer loginedUserId = (Integer) request.getSession().getAttribute("userId");
            if (loginedUserId == null) {
                return "login";
            } else {
                return "redirect:cases";
            }
        } catch (Exception ex) {
            return "login";
        }
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String verify(@RequestParam(value = "uri", required = false) String originalUri, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDTO foundedUser = usersService.login(username, password);
        if (foundedUser != null) {
            request.getSession().setAttribute("userId", foundedUser.getUserId());
            request.getSession().setAttribute("firstname", foundedUser.getFirstname());
            request.getSession().setAttribute("lastname", foundedUser.getLastname());
            request.getSession().setAttribute("typeId", foundedUser.getTypeId());
            switch (foundedUser.getTypeId()) {
                case UserDTO.USER_ADMIN:
                    request.getSession().setAttribute("typeName", "ადმინისტრატორი");
                    break;
                case UserDTO.USER_OPERATOR:
                    request.getSession().setAttribute("typeName", "ოპერატორი");
                    break;
                default:
                    break;
            }
            response.sendRedirect("cases");
//            return "redirect:cases";
            return null;
        } else {
            response.sendError(400, "მომხმარებელი ან პაროლი არასწორია");
            return null;
        }
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(HttpSession session) {
        session.removeAttribute("userId");
        session.removeAttribute("firstname");
        session.removeAttribute("lastname");
        session.removeAttribute("typeId");
        session.invalidate();
        return "redirect:login";
    }
}
