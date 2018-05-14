package ge.economy.law.dto;

import ge.economy.law.model.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

public class StatusDTO {

    private int statusId;
    private String name;


    public static StatusDTO translate(Record record) {
        StatusDTO dto = new StatusDTO();
        dto.setStatusId(record.getValue(Tables.USER_STATUS.STATUS_ID));
        dto.setName(record.getValue(Tables.USER_TYPE.NAME));
        return dto;
    }


    public static List<StatusDTO> translateArray(List<Record> records) {
        ArrayList<StatusDTO> list = new ArrayList<StatusDTO>();
        for (Record record : records) {
            list.add(StatusDTO.translate(record));
        }
        return list;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
