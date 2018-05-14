package ge.economy.law.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ucha
 */
@Controller
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
