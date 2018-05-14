package ge.economy.law.service;


import ge.economy.law.dao.UtilDAO;
import ge.economy.law.dto.CourtInstanceDTO;
import ge.economy.law.model.Tables;
import ge.economy.law.model.tables.records.CourtInstanceRecord;
import ge.economy.law.request.AddKeyValueRequest;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ucha
 */
@Service
public class CourtInstanceService {

    @Autowired
    private UtilDAO utilDAO;

    @Autowired
    private DSLContext dslContext;

    public CourtInstanceDTO save(AddKeyValueRequest request) {
        boolean newRecord = false;
        CourtInstanceRecord record = null;

        if (request.getId() != null) {
            record = utilDAO.getCourtInstanceObjectById(request.getId());
        }

        if (record == null) {
            record = dslContext.newRecord(Tables.COURT_INSTANCE);
            newRecord = true;
        }
        record.setName(request.getName());
        if (newRecord) {
            record.store();
        } else {
            record.update();
        }
        return CourtInstanceDTO.translate(record);
    }

    public List<CourtInstanceDTO> getCourtInstances() {
        return CourtInstanceDTO.translateArray(utilDAO.getCourtInstances());
    }


    public void deleteCourtInstance(int id) {
        utilDAO.deleteCourtInstance(id);
    }
}
