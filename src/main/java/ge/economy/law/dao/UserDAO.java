package ge.economy.law.dao;

import static org.jooq.impl.DSL.val;

import java.util.List;

import org.jooq.Record;
import org.springframework.stereotype.Repository;

import ge.economy.law.model.Tables;
import ge.economy.law.model.tables.records.UserRecord;

/**
 * Created by ME.
 */

@Repository
public class UserDAO extends AbstractDAO {

	public List<Record> getUsers(String roles) {
		return dslContext.select().from(Tables.USER)
				.where(dslContext.select(val(roles)).fetchOne().field1().contains(Tables.USER.ROLE.cast(String.class)))
				.fetch();
	}

	public List<Record> getUserTypes() {
		return dslContext.select().from(Tables.USER_TYPE).fetch();
	}

	public List<Record> getUserStatus() {
		return dslContext.select().from(Tables.USER_STATUS).fetch();
	}

	/*
	 * public void deleteUser(int itemId) {
	 * dslContext.deleteFrom(Tables.USER).where(Tables.USER.USER_ID.eq(itemId)).
	 * execute(); }
	 */

	public UserRecord getUserObjectById(int id) {
		return dslContext.fetchOne(Tables.USER, Tables.USER.USER_ID.eq(id));
	}

	public UserRecord getUserObjectByUserName(String userName) {
		return dslContext.fetchOne(Tables.USER, Tables.USER.USERNAME.eq(userName));
	}

	public Record getUser(String username) {
		return dslContext.select().from(Tables.USER).where(Tables.USER.USERNAME.eq(username)).fetchOne();
	}

	public Record getUserById(int id) {
		return dslContext.select().from(Tables.USER).where(Tables.USER.USER_ID.eq(id)).fetchAny();
	}

	public Record getUserByUserName(String userName) {
		return dslContext.select().from(Tables.USER).where(Tables.USER.USERNAME.eq(userName)).fetchAny();
	}

}
