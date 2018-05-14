package ge.economy.law.dto;

import ge.economy.law.model.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

public class UserTypeDTO {

    private int typeId;
    private String name;


    public static UserTypeDTO translate(Record record) {
        UserTypeDTO dto = new UserTypeDTO();
        dto.setTypeId(record.getValue(Tables.USER_TYPE.TYPE_ID));
        dto.setName(record.getValue(Tables.USER_TYPE.NAME));
        return dto;
    }


    public static List<UserTypeDTO> translateArray(List<Record> records) {
        ArrayList<UserTypeDTO> list = new ArrayList<UserTypeDTO>();
        for (Record record : records) {
            list.add(UserTypeDTO.translate(record));
        }
        return list;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
