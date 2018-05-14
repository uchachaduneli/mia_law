package ge.economy.law.service;


import ge.economy.law.dao.UtilDAO;
import ge.economy.law.dto.BoardDTO;
import ge.economy.law.model.Tables;
import ge.economy.law.model.tables.records.BoardRecord;
import ge.economy.law.request.AddKeyValueRequest;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ucha
 */
@Service
public class BoardService {

    @Autowired
    private UtilDAO utilDAO;

    @Autowired
    private DSLContext dslContext;

    public BoardDTO save(AddKeyValueRequest request) {
        boolean newRecord = false;
        BoardRecord record = null;

        if (request.getId() != null) {
            record = utilDAO.getBoardObjectById(request.getId());
        }

        if (record == null) {
            record = dslContext.newRecord(Tables.BOARD);
            newRecord = true;
        }
        record.setName(request.getName());
        if (newRecord) {
            record.store();
        } else {
            record.update();
        }
        return BoardDTO.translate(record);
    }

    public List<BoardDTO> getBoards() {
        return BoardDTO.translateArray(utilDAO.getBoards());
    }


    public void deleteBoard(int id) {
        utilDAO.deleteBoard(id);
    }
}
