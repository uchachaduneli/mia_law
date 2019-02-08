package ge.economy.law.controller;

import ge.economy.law.dto.UserDTO;
import ge.economy.law.misc.Response;
import ge.economy.law.request.AddKeyValueRequest;
import ge.economy.law.service.LitigationSubjectService;
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
@RequestMapping("/litigationsubjects")
@Controller
public class LitigationSubjectController {

    @Autowired
    private LitigationSubjectService litigationsubjectService;
    
    @Autowired
    private UsersService userService;

    @RequestMapping("/get-litigationsubjects")
    @ResponseBody
    private Response getLitigationSubjects(@RequestHeader("userName") String username, @RequestHeader("userRole") String roles,@RequestHeader("user") String user) throws Exception {
    	String buildRoles  = ge.economy.law.utils.StringUtils.rebuildString(roles);
    	UserDTO.setValues(username, buildRoles,user);
    	userService.saveUser();
        return Response.withSuccess(litigationsubjectService.getLitigationSubjects());
    }

    @RequestMapping({"/save-litigationsubject"})
    @ResponseBody
    public Response saveLitigationSubject(@RequestBody AddKeyValueRequest request) {
        return Response.withSuccess(litigationsubjectService.save(request));
    }

    @RequestMapping({"/delete-litigationsubject"})
    @ResponseBody
    public Response deleteLitigationSubject(@RequestParam int id) {
        litigationsubjectService.deleteLitigationSubject(id);
        return Response.withSuccess(true);
    }

}
