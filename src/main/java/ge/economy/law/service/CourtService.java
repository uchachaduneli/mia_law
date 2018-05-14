package ge.economy.law.service;


import ge.economy.law.dao.UtilDAO;
import ge.economy.law.dto.CourtDTO;
import ge.economy.law.model.Tables;
import ge.economy.law.model.tables.records.CourtRecord;
import ge.economy.law.request.AddKeyValueRequest;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ucha
 */
@Service
public class CourtService {

    @Autowired
    private UtilDAO utilDAO;

    @Autowired
    private DSLContext dslContext;

    public CourtDTO save(AddKeyValueRequest request) {
        boolean newRecord = false;
        CourtRecord record = null;

        if (request.getId() != null) {
            record = utilDAO.getCourtObjectById(request.getId());
        }

        if (record == null) {
            record = dslContext.newRecord(Tables.COURT);
            newRecord = true;
        }
        record.setName(request.getName());
        if (newRecord) {
            record.store();
        } else {
            record.update();
        }
        return CourtDTO.translate(record);
    }

    public List<CourtDTO> getCourts() {
        return CourtDTO.translateArray(utilDAO.getCourts());
    }


    public void deleteCourt(int id) {
        utilDAO.deleteCourt(id);
    }
}
