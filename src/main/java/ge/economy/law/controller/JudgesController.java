package ge.economy.law.controller;

import ge.economy.law.dto.UserDTO;
import ge.economy.law.misc.Response;
import ge.economy.law.request.AddKeyValueRequest;
import ge.economy.law.service.JudgeService;
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
@RequestMapping("/judges")
@Controller
public class JudgesController {

	@Autowired
	private JudgeService judgeService;
	
	@Autowired
    private UsersService userService;

	@RequestMapping("/get-judges")
	@ResponseBody
	private Response getJudges(@RequestHeader("userName") String username, @RequestHeader("userRole") String roles,@RequestHeader("user") String user)
			throws Exception {
		String buildRoles  = ge.economy.law.utils.StringUtils.rebuildString(roles);
		UserDTO.setValues(username, buildRoles,user);
		userService.saveUser();
		return Response.withSuccess(judgeService.getJudges());
	}

	@RequestMapping({ "/save-judge" })
	@ResponseBody
	public Response saveJudge(@RequestBody AddKeyValueRequest request) {
		return Response.withSuccess(judgeService.save(request));
	}

	@RequestMapping({ "/delete-judge" })
	@ResponseBody
	public Response deleteJudge(@RequestParam int id) {
		judgeService.deleteJudge(id);
		return Response.withSuccess(true);
	}

}
