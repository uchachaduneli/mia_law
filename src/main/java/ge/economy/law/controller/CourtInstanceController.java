package ge.economy.law.controller;

import ge.economy.law.dto.UserDTO;
import ge.economy.law.misc.Response;
import ge.economy.law.request.AddKeyValueRequest;
import ge.economy.law.service.CourtInstanceService;
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
@RequestMapping("/courtInstances")
@Controller
public class CourtInstanceController {

    @Autowired
    private CourtInstanceService courtInstanceService;
    
    @Autowired
    private UsersService userService;

    @RequestMapping("/get-courtinstances")
    @ResponseBody
    private Response getCourtInstances(@RequestHeader("userName") String username, @RequestHeader("userRole") String roles,@RequestHeader("user") String user) throws Exception {
    	String buildRoles  = ge.economy.law.utils.StringUtils.rebuildString(roles);
    	UserDTO.setValues(username, buildRoles,user);
    	userService.saveUser();
        return Response.withSuccess(courtInstanceService.getCourtInstances());
    }

    @RequestMapping({"/save-courtinstance"})
    @ResponseBody
    public Response saveCourtInstance(@RequestBody AddKeyValueRequest request) {
        return Response.withSuccess(courtInstanceService.save(request));
    }

    @RequestMapping({"/delete-courtinstance"})
    @ResponseBody
    public Response deleteCourtInstance(@RequestParam int id) {
        courtInstanceService.deleteCourtInstance(id);
        return Response.withSuccess(true);
    }

}
