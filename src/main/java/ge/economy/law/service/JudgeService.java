package ge.economy.law.service;


import ge.economy.law.dao.UtilDAO;
import ge.economy.law.dto.JudgeDTO;
import ge.economy.law.model.Tables;
import ge.economy.law.model.tables.records.JudgeRecord;
import ge.economy.law.request.AddKeyValueRequest;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ucha
 */
@Service
public class JudgeService {

    @Autowired
    private UtilDAO utilDAO;

    @Autowired
    private DSLContext dslContext;

    public JudgeDTO save(AddKeyValueRequest request) {
        boolean newRecord = false;
        JudgeRecord record = null;

        if (request.getId() != null) {
            record = utilDAO.getJudgeObjectById(request.getId());
        }

        if (record == null) {
            record = dslContext.newRecord(Tables.JUDGE);
            newRecord = true;
        }
        record.setName(request.getName());
        record.setAssistant(request.getAssistant());
        record.setAssistantPhone(request.getAssistantPhone());
        if (newRecord) {
            record.store();
        } else {
            record.update();
        }
        return JudgeDTO.translate(record);
    }

    public List<JudgeDTO> getJudges() {
        return JudgeDTO.translateArray(utilDAO.getJudges());
    }


    public void deleteJudge(int id) {
        utilDAO.deleteJudge(id);
    }
}
