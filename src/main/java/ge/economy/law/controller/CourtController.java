package ge.economy.law.controller;

import ge.economy.law.dto.UserDTO;
import ge.economy.law.misc.Response;
import ge.economy.law.request.AddKeyValueRequest;
import ge.economy.law.service.CourtService;
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
@RequestMapping("/courts")
@Controller
public class CourtController {

    @Autowired
    private CourtService courtService;
    
    @Autowired
    private UsersService userService;

    @RequestMapping("/get-courts")
    @ResponseBody
    private Response getCourts(@RequestHeader("userName") String username, @RequestHeader("userRole") String roles,@RequestHeader("user") String user) throws Exception {
    	String buildRoles  = ge.economy.law.utils.StringUtils.rebuildString(roles);
    	UserDTO.setValues(username, buildRoles,user);
    	userService.saveUser();
        return Response.withSuccess(courtService.getCourts());
    }

    @RequestMapping({"/save-court"})
    @ResponseBody
    public Response saveCourt(@RequestBody AddKeyValueRequest request) {
        return Response.withSuccess(courtService.save(request));
    }

    @RequestMapping({"/delete-court"})
    @ResponseBody
    public Response deleteCourt(@RequestParam int id) {
        courtService.deleteCourt(id);
        return Response.withSuccess(true);
    }

}
