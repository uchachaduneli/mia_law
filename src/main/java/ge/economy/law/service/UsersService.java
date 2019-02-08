package ge.economy.law.service;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ge.economy.law.dao.UserDAO;
import ge.economy.law.dto.UserDTO;
import ge.economy.law.dto.UserStatusDTO;
import ge.economy.law.dto.UserTypeDTO;
import ge.economy.law.model.Tables;
import ge.economy.law.model.tables.records.UserRecord;

/**
 * @author ucha
 */
@Service
public class UsersService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private DSLContext dslContext;

	public UserDTO saveUser() {
		boolean newRecord = false;
		boolean updateRecord = false;
		UserRecord record = null;

		record = userDAO.getUserObjectByUserName(UserDTO.userName);

		if (record == null) {
			record = dslContext.newRecord(Tables.USER);
			newRecord = true;
		}

		record.setName(UserDTO.user);
		record.setUsername(UserDTO.userName);

		if (!newRecord && !UserDTO.userRole.equalsIgnoreCase(record.getRole())) {
			updateRecord = true;
		}
		record.setRole(UserDTO.userRole);
		if (newRecord) {
			record.store();
		}
		if (updateRecord) {
			record.update();
		}
		return UserDTO.translate(record);
	}

	public List<UserDTO> getUsers(String roles) {
		return UserDTO.translateArray(userDAO.getUsers(roles));
	}

	public List<UserTypeDTO> getUserTypes() {
		return UserTypeDTO.translateArray(userDAO.getUserTypes());
	}

	public List<UserStatusDTO> getUserStatuses() {
		return UserStatusDTO.translateArray(userDAO.getUserStatus());
	}

	public UserDTO getUserById(int id) {
		return UserDTO.translate(userDAO.getUserById(id));
	}

	public UserDTO getUser(String username) {

		Record record = userDAO.getUser(username);

		if (record == null) {
			return null;
		}
		return UserDTO.translate(record);
	}
}
