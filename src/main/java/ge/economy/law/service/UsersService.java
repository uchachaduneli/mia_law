package ge.economy.law.service;


import ge.economy.law.dao.UserDAO;
import ge.economy.law.dto.UserDTO;
import ge.economy.law.dto.UserStatusDTO;
import ge.economy.law.dto.UserTypeDTO;
import ge.economy.law.model.Tables;
import ge.economy.law.model.tables.records.UserRecord;
import ge.economy.law.request.AddUserRequest;
import ge.economy.law.utils.MD5Provider;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ucha
 */
@Service
public class UsersService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private DSLContext dslContext;

    public UserDTO saveUser(AddUserRequest request) {
        boolean newRecord = false;
        UserRecord record = null;

        if (request.getUserId() != null) {
            record = userDAO.getUserObjectById(request.getUserId());
        }

        if (record == null) {
            record = dslContext.newRecord(Tables.USER);
            newRecord = true;
        }

        record.setFirstname(request.getFirstname());
        record.setLastname(request.getLastname());
        record.setUsername(request.getUsername());
        record.setTypeId(request.getTypeId());
        record.setStatusId(request.getStatusId());

        if (request.getPassword() != null && !request.getPassword().equals(record.getPassword())) {
            record.setPassword(MD5Provider.doubleMd5(request.getPassword()));
        }
        if (newRecord) {
            record.store();
        } else {
            record.update();
        }
        return UserDTO.translate(record);
    }

    public List<UserDTO> getUsers() {
        return UserDTO.translateArray(userDAO.getUsers());
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

    public UserDTO getUser(String username, String password) {

        Record record = userDAO.getUser(username, MD5Provider.doubleMd5(password));

        if (record == null) {
            return null;
        }
        return UserDTO.translate(record);
    }

    public UserDTO login(String username, String password) throws Exception {
        Record record = userDAO.getUser(username, MD5Provider.doubleMd5(password));
        UserDTO user = null;
        if (record != null) {
            user = UserDTO.translate(record);
        } else {
            throw new Exception("ამ მონაცემებით აქტიური მომხმარებელი არ იძებნება");
        }
        return user;
    }

    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    public UserDTO changePassword(Integer userId, String pass, String newpass) throws Exception {
        UserDTO user = null;
        UserRecord record = (UserRecord) dslContext
                .select()
                .from(Tables.USER)
                .where(Tables.USER.USER_ID.eq(userId))
                .and(Tables.USER.PASSWORD.eq(MD5Provider.doubleMd5(pass)))
                .fetchOne();
        if (record != null) {
            record.setPassword(MD5Provider.doubleMd5(newpass));
            record.update();
            user = UserDTO.translate(record);
        } else {
            throw new Exception("ამ მონაცემებით აქტიური მომხმარებელი არ იძებნება");
        }
        return user;
    }
}
