package ge.economy.law.dao;

import ge.economy.law.misc.CustomException;
import ge.economy.law.model.Tables;
import ge.economy.law.model.tables.Case;
import ge.economy.law.model.tables.records.CaseRecord;
import ge.economy.law.request.SearchCaseRequest;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.jooq.impl.DSL.*;

/**
 * Created by ME.
 */

@Repository
public class CaseDAO extends AbstractDAO {

	public HashMap<String, Object> getCases(int start, int limit, SearchCaseRequest srchCase, String userRoles) {
		Case c = Tables.CASE.as("c");
		List<Condition> condition = new ArrayList<>();
		if (srchCase.getCaseId() != null && srchCase.getCaseId() > 0) {
			condition.add(c.CASE_ID.eq(srchCase.getCaseId()));
		}
		/*if (!Arrays.asList(userRoles).contains("JUSTICE_ADMIN")) {
			if (Arrays.asList(userRoles).contains("JUSTICE_OPERATOR")) {
				condition.add(Tables.USER_TYPE.NAME.contains("JUSTICE_OPERATOR"));
			} else {
				if (Arrays.asList(userRoles).contains("JUSTICE_SIP_ADMIN")) {
					condition.add(Tables.USER_TYPE.NAME.contains("JUSTICE_SIP_ADMIN")
							.or(Tables.USER_TYPE.NAME.contains("JUSTICE_SIP_OPERATOR")));
				} else {
					if (Arrays.asList(userRoles).contains("JUSTICE_SIP_OPERATOR")) {
						condition.add(Tables.USER_TYPE.NAME.contains("JUSTICE_SIP_OPERATOR"));
					}
				}
			}
		}*/
		condition.add(dslContext.select(val(userRoles)).fetchOne().field1().contains(Tables.USER.ROLE.cast(String.class)));
		if (srchCase.getName() != null) {
			condition.add(c.NAME.like("%" + srchCase.getName() + "%"));
		}
		if (srchCase.getNumber() != null) {
			condition.add(c.NUMBER.like("%" + srchCase.getNumber() + "%"));
		}
		if (srchCase.getJudgeId() != null && srchCase.getJudgeId() > 0) {
			condition.add(c.JUDGE_ID.eq(srchCase.getJudgeId()));
		}
		if (srchCase.getJudgeAssistant() != null) {
			condition.add(Tables.JUDGE.ASSISTANT.like("%" + srchCase.getJudgeAssistant() + "%"));
		}
		if (srchCase.getJudgeAssistantPhone() != null) {
			condition.add(Tables.JUDGE.ASSISTANT_PHONE.like("%" + srchCase.getJudgeAssistantPhone() + "%"));
		}
		if (srchCase.getCaseStartDateFrom() != null && srchCase.getCaseStartDateTo() != null) {
			condition.add(c.CASE_START_DATE.between(new java.sql.Date(srchCase.getCaseStartDateFrom().getTime()))
					.and(new java.sql.Date(srchCase.getCaseStartDateTo().getTime())));
		}
		if (srchCase.getCaseEndDateFrom() != null && srchCase.getCaseEndDateTo() != null) {
			condition.add(c.CASE_START_DATE.between(new java.sql.Date(srchCase.getCaseEndDateFrom().getTime()))
					.and(new java.sql.Date(srchCase.getCaseEndDateTo().getTime())));
		}
		if (srchCase.getLitigationSubjectId() != null && srchCase.getLitigationSubjectId() > 0) {
			condition.add(c.LITIGATION_SUBJECT_ID.eq(srchCase.getLitigationSubjectId()));
		}
		if (srchCase.getEndResultId() != null && srchCase.getEndResultId() > 0) {
			condition.add(c.END_RESULT_ID.eq(srchCase.getEndResultId()));
		}
		if (srchCase.getAddUserId() != null && srchCase.getAddUserId() > 0) {
			condition.add(c.ADD_USER_ID.eq(srchCase.getAddUserId()));
		}
		if (srchCase.getCourtId() != null && srchCase.getCourtId() > 0) {
			condition.add(c.COURT_ID.eq(srchCase.getCourtId()));
		}
		if (srchCase.getStatusId() != null && srchCase.getStatusId() > 0) {
			condition.add(c.STATUS_ID.eq(srchCase.getStatusId()));
		}
		if (srchCase.getBoardId() != null && srchCase.getBoardId() > 0) {
			condition.add(c.BOARD_ID.eq(srchCase.getBoardId()));
		}
		if (srchCase.getMinistryStatus() != null && srchCase.getMinistryStatus() > 0) {
			condition.add(c.MINISTRY_STATUS.eq(srchCase.getMinistryStatus()));
		}
		if (srchCase.getCourtInstanceId() != null && srchCase.getCourtInstanceId() > 0) {
			condition.add(c.COURT_INSTANCE_ID.eq(srchCase.getCourtInstanceId()));
		} else {
			condition.add(c.CASE_ID
					.eq(dslContext.select(max(Tables.CASE.CASE_ID)).from(Tables.CASE).where(Tables.CASE.GROUP_ID.eq(c.GROUP_ID))));
		}

		SelectConditionStep<Record> selectConditionStep = dslContext.select().from(c).leftJoin(Tables.LITIGATION_SUBJECT)
				.on(c.LITIGATION_SUBJECT_ID.eq(Tables.LITIGATION_SUBJECT.LITIGATION_SUBJECT_ID)).leftJoin(Tables.END_RESULT)
				.on(c.END_RESULT_ID.eq(Tables.END_RESULT.END_RESULT_ID)).leftJoin(Tables.COURT).on(c.COURT_ID.eq(Tables.COURT.COURT_ID))
				.leftJoin(Tables.JUDGE).on(c.JUDGE_ID.eq(Tables.JUDGE.JUDGE_ID)).leftJoin(Tables.STATUS)
				.on(c.STATUS_ID.eq(Tables.STATUS.STATUS_ID)).leftJoin(Tables.USER).on(c.ADD_USER_ID.eq(Tables.USER.USER_ID)).where(DSL.and(condition));

		SelectConditionStep<Record> selectConditionStepSize = selectConditionStep;
		int recordSize = selectConditionStepSize.fetch().size();

		selectConditionStep.orderBy(c.EXPIRE_DATE.desc().nullsLast(), c.CASE_ID.desc()).limit(limit).offset(start);

		HashMap<String, Object> map = new HashMap<>();
		map.put("list", selectConditionStep.fetch());
		map.put("size", recordSize);
		return map;
	}

	public List<Record> getInstanceHistory(int itemId, String number) {
		return dslContext.select().from(Tables.CASE).join(Tables.LITIGATION_SUBJECT)
				.on(Tables.CASE.LITIGATION_SUBJECT_ID.eq(Tables.LITIGATION_SUBJECT.LITIGATION_SUBJECT_ID)).join(Tables.END_RESULT)
				.on(Tables.CASE.END_RESULT_ID.eq(Tables.END_RESULT.END_RESULT_ID)).join(Tables.COURT)
				.on(Tables.CASE.COURT_ID.eq(Tables.COURT.COURT_ID)).join(Tables.USER).on(Tables.CASE.ADD_USER_ID.eq(Tables.USER.USER_ID))
				.join(Tables.JUDGE).on(Tables.CASE.JUDGE_ID.eq(Tables.JUDGE.JUDGE_ID)).join(Tables.STATUS)
				.on(Tables.CASE.STATUS_ID.eq(Tables.STATUS.STATUS_ID)).join(Tables.COURT_INSTANCE)
				.on(Tables.CASE.COURT_INSTANCE_ID.eq(Tables.COURT_INSTANCE.INSTANCE_ID)).where(Tables.CASE.GROUP_ID.eq(number))
				.orderBy(Tables.CASE.CASE_ID.asc())
				//                .and(Tables.CASE.CASE_ID.ne(itemId))
				.fetch();
	}

	public List<Record> getCaseStatuses() {
		return dslContext.select().from(Tables.STATUS).fetch();
	}

	public int deleteCase(int itemId) throws CustomException {
		CaseRecord cs = dslContext.fetchOne(Tables.CASE, Tables.CASE.CASE_ID.eq(itemId));
		if (cs == null) {
			throw new CustomException("ჩანაწერი ვერ მოინიშნა !!!");
		}
		return dslContext.executeDelete(cs);

		//From(Tables.CASE).where(Tables.CASE.CASE_ID.eq(itemId)).execute();
		//						.and(Tables.CASE.ADD_USER_ID.in(dslContext.select().from(Tables.USER)
		//								.where(Tables.USER.USERNAME.eq(userName)).fetchOne().getValue(Tables.USER.USER_ID))))
		//				.execute();
	}

	public CaseRecord getCaseObjectById(int id) {
		return dslContext.fetchOne(Tables.CASE, Tables.CASE.CASE_ID.eq(id));
	}

	public Record getWholeCaseObjectById(int id) {

		return dslContext.select().from(Tables.CASE).join(Tables.JUDGE).on(Tables.CASE.JUDGE_ID.eq(Tables.JUDGE.JUDGE_ID))
				.join(Tables.LITIGATION_SUBJECT).on(Tables.CASE.LITIGATION_SUBJECT_ID.eq(Tables.LITIGATION_SUBJECT.LITIGATION_SUBJECT_ID))
				.join(Tables.END_RESULT).on(Tables.CASE.END_RESULT_ID.eq(Tables.END_RESULT.END_RESULT_ID)).join(Tables.COURT)
				.on(Tables.CASE.COURT_ID.eq(Tables.COURT.COURT_ID)).join(Tables.USER).on(Tables.CASE.ADD_USER_ID.eq(Tables.USER.USER_ID))
				.join(Tables.STATUS).on(Tables.CASE.STATUS_ID.eq(Tables.STATUS.STATUS_ID)).where(Tables.CASE.CASE_ID.eq(id)).fetchOne();
	}

	public HashMap<String, Object> getReport(SearchCaseRequest srchCase, String userRoles) {
		Case c = Tables.CASE.as("c");
		List<Condition> condition = new ArrayList<>();
		if (srchCase.getCaseId() != null && srchCase.getCaseId() > 0) {
			condition.add(c.CASE_ID.eq(srchCase.getCaseId()));
		}
		if (srchCase.getName() != null) {
			condition.add(c.NAME.like("%" + srchCase.getName() + "%"));
		}
		if (srchCase.getNumber() != null) {
			condition.add(c.NUMBER.like("%" + srchCase.getNumber() + "%"));
		}
		/*if (!Arrays.asList(userRoles).contains("JUSTICE_ADMIN")) {
			if (Arrays.asList(userRoles).contains("JUSTICE_OPERATOR")) {
				condition.add(Tables.USER_TYPE.NAME.contains("JUSTICE_OPERATOR"));
			} else {
				if (Arrays.asList(userRoles).contains("JUSTICE_SIP_ADMIN")) {
					condition.add(Tables.USER_TYPE.NAME.contains("JUSTICE_SIP_ADMIN")
							.or(Tables.USER_TYPE.NAME.contains("JUSTICE_SIP_OPERATOR")));
				} else {
					if (Arrays.asList(userRoles).contains("JUSTICE_SIP_OPERATOR")) {
						condition.add(Tables.USER_TYPE.NAME.contains("JUSTICE_SIP_OPERATOR"));
					}
				}
			}
		}*/
		condition.add(dslContext.select(val(userRoles)).fetchOne().field1().contains(Tables.USER.ROLE.cast(String.class)));
		if (srchCase.getJudgeId() != null && srchCase.getJudgeId() > 0) {
			condition.add(c.JUDGE_ID.eq(srchCase.getJudgeId()));
		}
		if (srchCase.getJudgeAssistant() != null) {
			condition.add(Tables.JUDGE.ASSISTANT.like("%" + srchCase.getJudgeAssistant() + "%"));
		}
		if (srchCase.getJudgeAssistantPhone() != null) {
			condition.add(Tables.JUDGE.ASSISTANT_PHONE.like("%" + srchCase.getJudgeAssistantPhone() + "%"));
		}
		if (srchCase.getCaseStartDateFrom() != null && srchCase.getCaseStartDateTo() != null) {
			condition.add(c.CASE_START_DATE.between(new java.sql.Date(srchCase.getCaseStartDateFrom().getTime()))
					.and(new java.sql.Date(srchCase.getCaseStartDateTo().getTime())));
		}
		if (srchCase.getCaseEndDateFrom() != null && srchCase.getCaseEndDateTo() != null) {
			condition.add(c.CASE_START_DATE.between(new java.sql.Date(srchCase.getCaseEndDateFrom().getTime()))
					.and(new java.sql.Date(srchCase.getCaseEndDateTo().getTime())));
		}
		if (srchCase.getLitigationSubjectId() != null && srchCase.getLitigationSubjectId() > 0) {
			condition.add(c.LITIGATION_SUBJECT_ID.eq(srchCase.getLitigationSubjectId()));
		}
		if (srchCase.getEndResultId() != null && srchCase.getEndResultId() > 0) {
			condition.add(c.END_RESULT_ID.eq(srchCase.getEndResultId()));
		}
		if (srchCase.getAddUserId() != null && srchCase.getAddUserId() > 0) {
			condition.add(c.ADD_USER_ID.eq(srchCase.getAddUserId()));
		}
		if (srchCase.getCourtId() != null && srchCase.getCourtId() > 0) {
			condition.add(c.COURT_ID.eq(srchCase.getCourtId()));
		}
		if (srchCase.getBoardId() != null && srchCase.getBoardId() > 0) {
			condition.add(c.BOARD_ID.eq(srchCase.getBoardId()));
		}
		if (srchCase.getMinistryStatus() != null && srchCase.getMinistryStatus() > 0) {
			condition.add(c.MINISTRY_STATUS.eq(srchCase.getMinistryStatus()));
		}
		if (srchCase.getStatusId() != null && srchCase.getStatusId() > 0) {
			condition.add(c.STATUS_ID.eq(srchCase.getStatusId()));
		}
		if (srchCase.getCourtInstanceId() != null && srchCase.getCourtInstanceId() > 0) {
			condition.add(c.COURT_INSTANCE_ID.eq(srchCase.getCourtInstanceId()));
		}

		SelectConditionStep<Record2<String, Integer>> selectConditionStep = dslContext.select(Tables.USER.NAME, count()).from(c)
				.join(Tables.LITIGATION_SUBJECT).on(c.LITIGATION_SUBJECT_ID.eq(Tables.LITIGATION_SUBJECT.LITIGATION_SUBJECT_ID))
				.join(Tables.END_RESULT).on(c.END_RESULT_ID.eq(Tables.END_RESULT.END_RESULT_ID)).join(Tables.COURT)
				.on(c.COURT_ID.eq(Tables.COURT.COURT_ID)).leftJoin(Tables.USER).on(c.ADD_USER_ID.eq(Tables.USER.USER_ID))/*.leftJoin
				(Tables.USER_TYPE)
				.on(Tables.USER.TYPE_ID.eq(Tables.USER_TYPE.TYPE_ID))*/.join(Tables.JUDGE).on(c.JUDGE_ID.eq(Tables.JUDGE.JUDGE_ID))
				.join(Tables.STATUS).on(c.STATUS_ID.eq(Tables.STATUS.STATUS_ID)).where(DSL.and(condition));
		selectConditionStep.groupBy(c.ADD_USER_ID, Tables.USER.NAME);

		SelectConditionStep<Record1<BigDecimal>> sumCondition = dslContext.select(sum(c.LITIGATION_PRICE)).from(c)
				.join(Tables.LITIGATION_SUBJECT).on(c.LITIGATION_SUBJECT_ID.eq(Tables.LITIGATION_SUBJECT.LITIGATION_SUBJECT_ID))
				.join(Tables.END_RESULT).on(c.END_RESULT_ID.eq(Tables.END_RESULT.END_RESULT_ID)).join(Tables.COURT)
				.on(c.COURT_ID.eq(Tables.COURT.COURT_ID)).leftJoin(Tables.USER).on(c.ADD_USER_ID.eq(Tables.USER.USER_ID))/*.leftJoin
				(Tables.USER_TYPE)
				.on(Tables.USER.TYPE_ID.eq(Tables.USER_TYPE.TYPE_ID))*/.join(Tables.JUDGE).on(c.JUDGE_ID.eq(Tables.JUDGE.JUDGE_ID))
				.join(Tables.STATUS).on(c.STATUS_ID.eq(Tables.STATUS.STATUS_ID)).where(DSL.and(condition));

		HashMap<String, Object> map = new HashMap<>();
		map.put("list", selectConditionStep.fetch());
		map.put("sum", sumCondition.fetch().size() > 0 ? sumCondition.fetch().get(0).getValue(0, Double.class) : 0.0);
		return map;
	}
}
