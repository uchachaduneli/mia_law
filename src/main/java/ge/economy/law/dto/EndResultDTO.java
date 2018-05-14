package ge.economy.law.dto;

import ge.economy.law.model.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

public class EndResultDTO {

    private int endResultId;
    private String name;


    public static EndResultDTO translate(Record record) {
        EndResultDTO dto = new EndResultDTO();
        dto.setEndResultId(record.getValue(Tables.END_RESULT.END_RESULT_ID));
        dto.setName(record.getValue(Tables.JUDGE.NAME));
        return dto;
    }


    public static List<EndResultDTO> translateArray(List<Record> records) {
        ArrayList<EndResultDTO> list = new ArrayList<EndResultDTO>();
        for (Record record : records) {
            list.add(EndResultDTO.translate(record));
        }
        return list;
    }

    public int getEndResultId() {
        return endResultId;
    }

    public void setEndResultId(int endResultId) {
        this.endResultId = endResultId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
