package ge.economy.law.controller;

import ge.economy.law.misc.Response;
import ge.economy.law.request.AddKeyValueRequest;
import ge.economy.law.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author ucha
 */
@RequestMapping("/boards")
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping("/get-boards")
    @ResponseBody
    private Response getBoards() throws Exception {
        return Response.withSuccess(boardService.getBoards());
    }

    @RequestMapping({"/save-board"})
    @ResponseBody
    public Response saveBoard(@RequestBody AddKeyValueRequest request) {
        return Response.withSuccess(boardService.save(request));
    }

    @RequestMapping({"/delete-board"})
    @ResponseBody
    public Response deleteBoard(@RequestParam int id) {
        boardService.deleteBoard(id);
        return Response.withSuccess(true);
    }

}
