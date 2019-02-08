package ge.economy.law.dto;

import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

public class UserReportDTO {

    private String userDesc;
    private Integer count;

    public static UserReportDTO translate(Record record) {
        UserReportDTO dto = new UserReportDTO();
        dto.setUserDesc(record.getValue(0).toString());
        dto.setCount((Integer) record.getValue(1));
        return dto;
    }


    public static List<UserReportDTO> translateArray(List<Record> records) {
        ArrayList<UserReportDTO> list = new ArrayList<UserReportDTO>();
        for (Record record : records) {
            list.add(UserReportDTO.translate(record));
        }
        return list;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
