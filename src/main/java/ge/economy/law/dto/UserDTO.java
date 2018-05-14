package ge.economy.law.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.economy.law.misc.JsonDateSerializeSupport;
import ge.economy.law.model.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDTO {

    private Integer userId;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Integer typeId;
    private Integer statusId;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date insertDate;

    public final static int USER_STATUS_ACTIVE = 1;
    public final static int USER_STATUS_INACTIVE = 2;

    public static final int USER_ADMIN = 2;
    public static final int USER_OPERATOR = 1;


    public static UserDTO translate(Record record) {
        UserDTO dto = new UserDTO();
        dto.setUserId(record.getValue(Tables.USER.USER_ID));
        dto.setFirstname(record.getValue(Tables.USER.FIRSTNAME));
        dto.setLastname(record.getValue(Tables.USER.LASTNAME));
        dto.setUsername(record.getValue(Tables.USER.USERNAME));
        dto.setPassword(record.getValue(Tables.USER.PASSWORD));
        dto.setTypeId(record.getValue(Tables.USER.TYPE_ID));
        dto.setStatusId(record.getValue(Tables.USER.STATUS_ID));
        dto.setInsertDate(record.getValue(Tables.USER.INSERT_DATE));
        return dto;
    }


    public static List<UserDTO> translateArray(List<Record> records) {
        ArrayList<UserDTO> list = new ArrayList<UserDTO>();
        for (Record record : records) {
            list.add(UserDTO.translate(record));
        }
        return list;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }
}
