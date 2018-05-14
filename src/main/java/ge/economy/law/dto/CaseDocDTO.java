package ge.economy.law.dto;

import ge.economy.law.model.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

public class CaseDocDTO {

    private int docId;
    private int caseId;
    private String name;


    public static CaseDocDTO translate(Record record) {
        CaseDocDTO dto = new CaseDocDTO();
        dto.setDocId(record.getValue(Tables.CASE_DOC.DOC_ID));
        dto.setCaseId(record.getValue(Tables.CASE_DOC.CASE_ID));
        dto.setName(record.getValue(Tables.CASE_DOC.NAME));
        return dto;
    }


    public static List<CaseDocDTO> translateArray(List<Record> records) {
        ArrayList<CaseDocDTO> list = new ArrayList<CaseDocDTO>();
        for (Record record : records) {
            list.add(CaseDocDTO.translate(record));
        }
        return list;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
