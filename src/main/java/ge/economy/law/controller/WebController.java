package ge.economy.law.controller;

import java.security.Principal;

import javax.validation.constraints.NotNull;

import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ucha
 */
@Controller
@Validated
@RequestMapping
public class WebController {

    @RequestMapping("/cases")
    public String cases() {
        return "cases";
    }

    @RequestMapping("/users")
    public String users() {
        return "users";
    }

    @RequestMapping("/courts")
    public String courts() {
        return "courts";
    }

    @RequestMapping("/litigsubjects")
    public String litigsubjects() {
        return "litigsubjects";
    }

    @RequestMapping("/instances")
    public String instances() {
        return "instances";
    }

    @RequestMapping("/judges")
    public String judges() {
        return "judges";
    }

    @RequestMapping("/caseresults")
    public String caseresults() {
        return "caseresults";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    
    
    @RequestMapping(value ="/greetings", method = {RequestMethod.GET})
    public String greeting(Principal principal,@NotNull Model model, @NotNull Authentication auth) {
        String email = ((SimpleKeycloakAccount) auth.getDetails())
                .getKeycloakSecurityContext()
                .getToken()
                .getEmail();
 
        model.addAttribute("name", email);
        return "greetings";
    }

    @RequestMapping("/statistics")
    public String statistics() {
        return "statistics";
    }

    @RequestMapping("/boards")
    public String boards() {
        return "boards";
    }

    @RequestMapping("/")
    public String defaultFnc() {
        return "";
    }

}
