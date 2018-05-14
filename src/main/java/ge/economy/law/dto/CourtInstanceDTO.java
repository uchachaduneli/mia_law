package ge.economy.law.dto;

import ge.economy.law.model.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

public class CourtInstanceDTO {

    private int instanceId;
    private String name;


    public static CourtInstanceDTO translate(Record record) {
        CourtInstanceDTO dto = new CourtInstanceDTO();
        dto.setInstanceId(record.getValue(Tables.COURT_INSTANCE.INSTANCE_ID));
        dto.setName(record.getValue(Tables.COURT_INSTANCE.NAME));
        return dto;
    }


    public static List<CourtInstanceDTO> translateArray(List<Record> records) {
        ArrayList<CourtInstanceDTO> list = new ArrayList<CourtInstanceDTO>();
        for (Record record : records) {
            list.add(CourtInstanceDTO.translate(record));
        }
        return list;
    }

    public int getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(int instanceId) {
        this.instanceId = instanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
