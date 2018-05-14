package ge.economy.law.controller;

import ge.economy.law.misc.Response;
import ge.economy.law.request.AddKeyValueRequest;
import ge.economy.law.service.CourtInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping("/get-courtinstances")
    @ResponseBody
    private Response getCourtInstances() throws Exception {
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
