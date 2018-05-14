package ge.economy.law.controller;

import ge.economy.law.misc.Response;
import ge.economy.law.request.AddKeyValueRequest;
import ge.economy.law.service.EndResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping("/get-endresults")
    @ResponseBody
    private Response getEndResults() throws Exception {
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
