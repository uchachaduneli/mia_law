package ge.economy.law.service;


import ge.economy.law.dao.UtilDAO;
import ge.economy.law.dto.EndResultDTO;
import ge.economy.law.model.Tables;
import ge.economy.law.model.tables.records.EndResultRecord;
import ge.economy.law.request.AddKeyValueRequest;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ucha
 */
@Service
public class EndResultService {

    @Autowired
    private UtilDAO utilDAO;

    @Autowired
    private DSLContext dslContext;

    public EndResultDTO save(AddKeyValueRequest request) {
        boolean newRecord = false;
        EndResultRecord record = null;

        if (request.getId() != null) {
            record = utilDAO.getEndResultObjectById(request.getId());
        }

        if (record == null) {
            record = dslContext.newRecord(Tables.END_RESULT);
            newRecord = true;
        }
        record.setName(request.getName());
        if (newRecord) {
            record.store();
        } else {
            record.update();
        }
        return EndResultDTO.translate(record);
    }

    public List<EndResultDTO> getEndResults() {
        return EndResultDTO.translateArray(utilDAO.getEndResults());
    }


    public void deleteEndResult(int id) {
        utilDAO.deleteEndResult(id);
    }
}
