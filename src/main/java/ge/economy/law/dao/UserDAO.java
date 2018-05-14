package ge.economy.law.dao;

import ge.economy.law.dto.UserDTO;
import ge.economy.law.model.Tables;
import ge.economy.law.model.tables.records.UserRecord;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ME.
 */

@Repository
public class UserDAO extends AbstractDAO {

    public List<Record> getUsers() {
        return dslContext.
                select().
                from(Tables.USER).
                fetch();
    }

    public List<Record> getUserTypes() {
        return dslContext.
                select().
                from(Tables.USER_TYPE).
                fetch();
    }

    public List<Record> getUserStatus() {
        return dslContext.
                select().
                from(Tables.USER_STATUS).
                fetch();
    }

    public void deleteUser(int itemId) {
        dslContext.deleteFrom(Tables.USER).where(Tables.USER.USER_ID.eq(itemId)).execute();
    }

    public UserRecord getUserObjectById(int id) {
        return dslContext.fetchOne(Tables.USER, Tables.USER.USER_ID.eq(id));
    }

    public Record getUser(String username, String password) {
        return dslContext
                .select()
                .from(Tables.USER)
                .where(Tables.USER.USERNAME.eq(username))
                .and(Tables.USER.PASSWORD.eq(password))
                .and(Tables.USER.STATUS_ID.eq(UserDTO.USER_STATUS_ACTIVE))
                .fetchOne();
    }

    public Record getUserById(int id) {
        return dslContext.select().
                from(Tables.USER).
                where(Tables.USER.USER_ID.eq(id)).fetchAny();
    }

}
