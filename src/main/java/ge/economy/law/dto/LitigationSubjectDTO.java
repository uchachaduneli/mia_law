package ge.economy.law.dto;

import ge.economy.law.model.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

public class LitigationSubjectDTO {

    private int litigationSubjectId;
    private String name;


    public static LitigationSubjectDTO translate(Record record) {
        LitigationSubjectDTO dto = new LitigationSubjectDTO();
        dto.setLitigationSubjectId(record.getValue(Tables.LITIGATION_SUBJECT.LITIGATION_SUBJECT_ID));
        dto.setName(record.getValue(Tables.JUDGE.NAME));
        return dto;
    }


    public static List<LitigationSubjectDTO> translateArray(List<Record> records) {
        ArrayList<LitigationSubjectDTO> list = new ArrayList<LitigationSubjectDTO>();
        for (Record record : records) {
            list.add(LitigationSubjectDTO.translate(record));
        }
        return list;
    }

    public int getLitigationSubjectId() {
        return litigationSubjectId;
    }

    public void setLitigationSubjectId(int litigationSubjectId) {
        this.litigationSubjectId = litigationSubjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
