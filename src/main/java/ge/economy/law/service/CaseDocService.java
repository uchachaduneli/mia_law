package ge.economy.law.service;


import ge.economy.law.dao.UtilDAO;
import ge.economy.law.dto.CaseDocDTO;
import ge.economy.law.model.Tables;
import ge.economy.law.model.tables.records.CaseDocRecord;
import ge.economy.law.request.AddCaseDocRequest;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ucha
 */
@Service
public class CaseDocService {

    @Autowired
    private UtilDAO utilDAO;

    @Autowired
    private DSLContext dslContext;

    public CaseDocDTO save(AddCaseDocRequest request) {
        boolean newRecord = false;
        CaseDocRecord record = dslContext.newRecord(Tables.CASE_DOC);
        record.setName(request.getName());
        record.store();
        return CaseDocDTO.translate(record);
    }

    public List<CaseDocDTO> getCaseDocs(Integer caseId) {
        return CaseDocDTO.translateArray(utilDAO.getCaseDocs(caseId));
    }

    public void deleteCaseDoc(int id) {
        utilDAO.deleteCaseDoc(id);
    }
}
