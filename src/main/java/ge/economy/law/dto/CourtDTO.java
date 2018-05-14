package ge.economy.law.dto;

import ge.economy.law.model.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

public class CourtDTO {

    private int courtId;
    private String name;


    public static CourtDTO translate(Record record) {
        CourtDTO dto = new CourtDTO();
        dto.setCourtId(record.getValue(Tables.COURT.COURT_ID));
        dto.setName(record.getValue(Tables.JUDGE.NAME));
        return dto;
    }


    public static List<CourtDTO> translateArray(List<Record> records) {
        ArrayList<CourtDTO> list = new ArrayList<CourtDTO>();
        for (Record record : records) {
            list.add(CourtDTO.translate(record));
        }
        return list;
    }

    public int getCourtId() {
        return courtId;
    }

    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
