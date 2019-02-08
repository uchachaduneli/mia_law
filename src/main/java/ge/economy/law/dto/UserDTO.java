package ge.economy.law.dto;

import java.util.ArrayList;
import java.util.List;

import org.jooq.Record;

import ge.economy.law.dao.USER_ROLE;
import ge.economy.law.model.Tables;

public class UserDTO {

	private Integer userId;
	private String name;
	private String username;

	public static String userName = "";
	public static String userRole = "";
	public static String user = "";
	public static boolean isAdmin = false;

	public static void setValues(String username, String roles, String user) {
		UserDTO.userRole = roles;
		UserDTO.userName = username;
		UserDTO.user = user;
		if (roles.contains(USER_ROLE.JUSTICE_ADMIN.getValue()) || roles.contains(USER_ROLE.JUSTICE_SIP_ADMIN.getValue()) ) {
			UserDTO.isAdmin = true;
		}else {
			UserDTO.isAdmin = false;
		}
	}

	public static UserDTO translate(Record record) {
		UserDTO dto = new UserDTO();
		dto.setUserId(record.getValue(Tables.USER.USER_ID));
		dto.setName(record.getValue(Tables.USER.NAME));
		dto.setUsername(record.getValue(Tables.USER.USERNAME));
		return dto;
	}

	public static List<UserDTO> translateArray(List<Record> records) {
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		for (Record record : records) {
			list.add(UserDTO.translate(record));
		}
		return list;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
