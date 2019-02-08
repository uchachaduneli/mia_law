package ge.economy.law.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ge.economy.law.dto.UserDTO;
import ge.economy.law.misc.Response;
import ge.economy.law.request.AddCaseRequest;
import ge.economy.law.request.SearchCaseRequest;
import ge.economy.law.service.CaseService;
import ge.economy.law.service.FileService;
import ge.economy.law.service.UsersService;


/**
 * @author ucha
 */
@RequestMapping("/cases")
@Controller
public class CaseController {

	@Autowired
	private CaseService caseService;
	
	@Autowired
	private UsersService userService;

	@Autowired
	private FileService fileService;

	@ResponseBody
	@RequestMapping({ "/get-cases" })
	public Response getCases(@RequestParam("start") int start, @RequestParam("limit") int limit,
			@RequestBody SearchCaseRequest srchCase, HttpServletRequest servletRequest,
			@RequestHeader("userName") String username, @RequestHeader("userRole") String roles,
			@RequestHeader("user") String user) {
		String buildRoles  = ge.economy.law.utils.StringUtils.rebuildString(roles);
		UserDTO.setValues(username, buildRoles, user);
		userService.saveUser();
		return Response.withSuccess(caseService.getCases(start, limit, srchCase, buildRoles/*.split(",")*/, username));
	}

	@ResponseBody
	@RequestMapping({ "/get-statistics" })
	public Response getStatistics(@RequestBody SearchCaseRequest srchCase, @RequestHeader("userName") String username,
			@RequestHeader("userRole") String roles, @RequestHeader("user") String user) {
		String buildRoles  = ge.economy.law.utils.StringUtils.rebuildString(roles);
		UserDTO.setValues(username, buildRoles, user);
		userService.saveUser();
		return Response.withSuccess(caseService.getReport(srchCase, buildRoles/*.split(",")*/, username));
	}

	@ResponseBody
	@RequestMapping({ "/get-status" })
	public Response getStatuses() {
		return Response.withSuccess(caseService.getStatus());
	}

	@ResponseBody
	@RequestMapping({ "/get-instance-history" })
	public Response getInstanceHistory(@RequestParam int id, @RequestParam String number) {
		return Response.withSuccess(caseService.getInstanceHistory(id, number));
	}

	@ResponseBody
	@RequestMapping({ "/save-case" })
	public Response saveCase(@RequestBody AddCaseRequest request, @RequestHeader("userName") String username,
			@RequestHeader("userRole") String roles, @RequestHeader("user") String user) {

		if ((request.getCaseId() != null && (UserDTO.isAdmin || username.equalsIgnoreCase(request.getAddUserName())))
				|| request.getCaseId() == null) {

			request.setAddUserName(username);
			UserDTO userDto = userService.getUser(username);
			if(request.getAddUserId() == null) {
				request.setAddUserId(userDto.getUserId());
			}
			String buildRoles  = ge.economy.law.utils.StringUtils.rebuildString(roles);
			request.setRole(buildRoles);
			request.setUser(user);
			return Response.withSuccess(caseService.save(request));
		}
		return Response.withSuccess(null);
	}

	@RequestMapping({ "/delete-case" })
	@ResponseBody
	public Response deleteCase(@RequestParam int id, @RequestHeader("userName") String username,
			@RequestHeader("userRole") String roles, @RequestHeader("user") String user) {
		UserDTO.setValues(username, roles, user);
		caseService.deleteCase(id, username);
		return Response.withSuccess(true);
	}

	@RequestMapping("/add-doc")
	@ResponseBody
	private Response addImage(@RequestParam("file") MultipartFile file) throws IOException {
		return Response.withSuccess(fileService.addFile(file));
	}

	@RequestMapping("/get-doc")
	@ResponseBody
	private void getImage(HttpServletResponse response, @RequestParam String name) throws IOException {
		response.getOutputStream().write(fileService.readFile(name));
	}

	@RequestMapping("/get-doc-names")
	@ResponseBody
	private Response getImage(@RequestParam Integer caseId) throws IOException {
		return Response.withSuccess(caseService.getCaseDocs(caseId));
	}

}
