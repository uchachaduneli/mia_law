package ge.economy.law.controller;

import ge.economy.law.dto.UserDTO;
import ge.economy.law.misc.Response;
import ge.economy.law.request.AddCaseRequest;
import ge.economy.law.request.SearchCaseRequest;
import ge.economy.law.service.CaseService;
import ge.economy.law.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author ucha
 */
@RequestMapping("/cases")
@Controller
public class CaseController {

    @Autowired
    private CaseService caseService;

    @Autowired
    private FileService fileService;

    @ResponseBody
    @RequestMapping({"/get-cases"})
    public Response getCases(@RequestParam("start") int start, @RequestParam("limit") int limit, @RequestBody SearchCaseRequest srchCase, HttpServletRequest servletRequest) {
        if ((Integer) servletRequest.getSession().getAttribute("typeId") == UserDTO.USER_OPERATOR) {
            srchCase.setAddUserId((Integer) servletRequest.getSession().getAttribute("userId"));
        }
        return Response.withSuccess(caseService.getCases(start, limit, srchCase));
    }

    @ResponseBody
    @RequestMapping({"/get-statistics"})
    public Response getStatistics(@RequestBody SearchCaseRequest srchCase) {
        return Response.withSuccess(caseService.getReport(srchCase));
    }

    @ResponseBody
    @RequestMapping({"/get-status"})
    public Response getStatuses() {
        return Response.withSuccess(caseService.getStatus());
    }

    @ResponseBody
    @RequestMapping({"/get-instance-history"})
    public Response getInstanceHistory(@RequestParam int id, @RequestParam String number) {
        return Response.withSuccess(caseService.getInstanceHistory(id, number));
    }

    @ResponseBody
    @RequestMapping({"/save-case"})
    public Response saveCase(@RequestBody AddCaseRequest request, HttpServletRequest servletRequest) {
        request.setAddUserId((Integer) servletRequest.getSession().getAttribute("userId"));
        return Response.withSuccess(caseService.save(request));
    }

    @RequestMapping({"/delete-case"})
    @ResponseBody
    public Response deleteCase(@RequestParam int id) {
        caseService.deleteCase(id);
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
