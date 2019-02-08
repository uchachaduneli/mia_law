package ge.economy.law.controller;

import ge.economy.law.dto.UserDTO;
import ge.economy.law.misc.Response;
import ge.economy.law.request.AddKeyValueRequest;
import ge.economy.law.service.EndResultService;
import ge.economy.law.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author ucha
 */
@RequestMapping("/endresults")
@Controller
public class EndResultController {

    @Autowired
    private EndResultService endresultService;
    
    @Autowired
    private UsersService userService;

    @RequestMapping("/get-endresults")
    @ResponseBody
    private Response getEndResults(@RequestHeader("userName") String username, @RequestHeader("userRole") String roles,@RequestHeader("user") String user) throws Exception {
    	String buildRoles  = ge.economy.law.utils.StringUtils.rebuildString(roles);
    	UserDTO.setValues(username, buildRoles,user);
    	userService.saveUser();
        return Response.withSuccess(endresultService.getEndResults());
    }

    @RequestMapping({"/save-endresult"})
    @ResponseBody
    public Response saveEndResult(@RequestBody AddKeyValueRequest request) {
        return Response.withSuccess(endresultService.save(request));
    }

    @RequestMapping({"/delete-endresult"})
    @ResponseBody
    public Response deleteEndResult(@RequestParam int id) {
        endresultService.deleteEndResult(id);
        return Response.withSuccess(true);
    }

}
