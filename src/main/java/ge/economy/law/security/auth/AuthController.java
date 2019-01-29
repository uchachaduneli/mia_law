package ge.economy.law.security.auth;

import ge.economy.law.dto.UserDTO;
import ge.economy.law.security.config.SecurityConfig;
import ge.economy.law.service.UsersService;

import org.keycloak.AuthorizationContext;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessToken.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

/**
 * @author ucha
 */
@Controller
@RequestMapping
public class AuthController {

    @Autowired
    private UsersService usersService;
    
    @Autowired
    private AccessToken accessToken;
    

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
    private String endpoint;

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String verify(@RequestParam(value = "uri", required = false) String originalUri,@RequestHeader("Authorization") String encoding) throws Exception {
        /*String username = request.getParameter("username");
        String password = request.getParameter("password");*/
        try {
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	KeycloakAuthenticationToken authToken = (KeycloakAuthenticationToken) authentication;
        	String token = authToken.getAccount().getKeycloakSecurityContext().getTokenString();
        			
        	AccessToken tttt = accessToken;
        	String aaaa = accessToken.getAccessTokenHash();
        }catch (Exception e) {
			System.out.println(e.getMessage());
		}
        //Authentication auth =SecurityContextHolder.getContext().getAuthentication();
        
        ServletRequestAttributes att = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes());
        HttpServletRequest reqatt = att.getRequest();
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
        
        try {
        	KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        	    AuthorizationContext authzContext = keycloakSecurityContext.getAuthorizationContext();
        	
        	Principal bbb = request.getUserPrincipal();
        	KeycloakAuthenticationToken aaa  = (KeycloakAuthenticationToken)request.getUserPrincipal();
        	
        	AccessToken aaaa =  ((KeycloakAuthenticationToken) request.getUserPrincipal()).getAccount().getKeycloakSecurityContext().getToken();
        	String ttttt;
        }catch (Exception e) {
        	System.out.println(e.getMessage());
		}
		return "aaa";
        
        /*UserDTO foundedUser = usersService.login(username, password);
        if (foundedUser != null) {
            request.getSession().setAttribute("userId", foundedUser.getUserId());
            request.getSession().setAttribute("firstname", foundedUser.getFirstname());
            request.getSession().setAttribute("lastname", foundedUser.getLastname());
            request.getSession().setAttribute("typeId", foundedUser.getTypeId());
            request.getSession().setAttribute("userToken", "JUSTICE_OPERATOR_1");
            request.getSession().setAttribute("userRole", "JUSTICE_OPERATOR");
            switch (foundedUser.getTypeId()) {
                case UserDTO.USER_ADMIN:
                    request.getSession().setAttribute("typeName", "Ã¡Æ’ï¿½Ã¡Æ’â€œÃ¡Æ’â€ºÃ¡Æ’ËœÃ¡Æ’Å“Ã¡Æ’ËœÃ¡Æ’Â¡Ã¡Æ’Â¢Ã¡Æ’Â Ã¡Æ’ï¿½Ã¡Æ’Â¢Ã¡Æ’ï¿½Ã¡Æ’Â Ã¡Æ’Ëœ");
                    break;
                case UserDTO.USER_OPERATOR:
                    request.getSession().setAttribute("typeName", "Ã¡Æ’ï¿½Ã¡Æ’Å¾Ã¡Æ’â€�Ã¡Æ’Â Ã¡Æ’ï¿½Ã¡Æ’Â¢Ã¡Æ’ï¿½Ã¡Æ’Â Ã¡Æ’Ëœ");
                    break;
                default:
                    break;
            }
            response.sendRedirect("cases");
//            return "redirect:cases";
            return null;
        } else {
            response.sendError(400, "Ã¡Æ’â€ºÃ¡Æ’ï¿½Ã¡Æ’â€ºÃ¡Æ’Â®Ã¡Æ’â€ºÃ¡Æ’ï¿½Ã¡Æ’Â Ã¡Æ’â€�Ã¡Æ’â€˜Ã¡Æ’â€�Ã¡Æ’Å¡Ã¡Æ’Ëœ Ã¡Æ’ï¿½Ã¡Æ’Å“ Ã¡Æ’Å¾Ã¡Æ’ï¿½Ã¡Æ’Â Ã¡Æ’ï¿½Ã¡Æ’Å¡Ã¡Æ’Ëœ Ã¡Æ’ï¿½Ã¡Æ’Â Ã¡Æ’ï¿½Ã¡Æ’Â¡Ã¡Æ’Â¬Ã¡Æ’ï¿½Ã¡Æ’Â Ã¡Æ’ËœÃ¡Æ’ï¿½");
            return null;
        }*/
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(HttpSession session) {
        session.removeAttribute("userId");
        session.removeAttribute("firstname");
        session.removeAttribute("lastname");
        session.removeAttribute("typeId");
        session.removeAttribute("typeName");
        session.invalidate();
        return "redirect:login";
    }
}
