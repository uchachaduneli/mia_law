package ge.economy.law.service;


import ge.economy.law.dao.UtilDAO;
import ge.economy.law.dto.LitigationSubjectDTO;
import ge.economy.law.model.Tables;
import ge.economy.law.model.tables.records.LitigationSubjectRecord;
import ge.economy.law.request.AddKeyValueRequest;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ucha
 */
@Service
public class LitigationSubjectService {

    @Autowired
    private UtilDAO utilDAO;

    @Autowired
    private DSLContext dslContext;

    public LitigationSubjectDTO save(AddKeyValueRequest request) {
        boolean newRecord = false;
        LitigationSubjectRecord record = null;

        if (request.getId() != null) {
            record = utilDAO.getLitigationSubjectObjectById(request.getId());
        }

        if (record == null) {
            record = dslContext.newRecord(Tables.LITIGATION_SUBJECT);
            newRecord = true;
        }
        record.setName(request.getName());
        if (newRecord) {
            record.store();
        } else {
            record.update();
        }
        return LitigationSubjectDTO.translate(record);
    }

    public List<LitigationSubjectDTO> getLitigationSubjects() {
        return LitigationSubjectDTO.translateArray(utilDAO.getLitigationSubjects());
    }


    public void deleteLitigationSubject(int id) {
        utilDAO.deleteLitigationSubject(id);
    }
}
