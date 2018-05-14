package ge.economy.law.dao;

import ge.economy.law.model.Tables;
import ge.economy.law.model.tables.records.*;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ME.
 */

@Repository
public class UtilDAO extends AbstractDAO {

    public List<Record> getCourts() {
        return dslContext.
                select().
                from(Tables.COURT).
                fetch();
    }

    public void deleteCourt(int itemId) {
        dslContext.deleteFrom(Tables.COURT).where(Tables.COURT.COURT_ID.eq(itemId)).execute();
    }

    public CourtRecord getCourtObjectById(int id) {
        return dslContext.fetchOne(Tables.COURT, Tables.COURT.COURT_ID.eq(id));
    }

    public BoardRecord getBoardObjectById(int id) {
        return dslContext.fetchOne(Tables.BOARD, Tables.BOARD.BOARD_ID.eq(id));
    }

    public List<Record> getBoards() {
        return dslContext.
                select().
                from(Tables.BOARD).
                fetch();
    }

    public void deleteBoard(int itemId) {
        dslContext.deleteFrom(Tables.BOARD).where(Tables.BOARD.BOARD_ID.eq(itemId)).execute();
    }

    public List<Record> getCaseDocs(int caseId) {
        return dslContext.
                select().
                from(Tables.CASE_DOC).where(Tables.CASE_DOC.CASE_ID.eq(caseId)).
                fetch();
    }

    public void deleteCaseDoc(int caseId) {
        dslContext.deleteFrom(Tables.CASE_DOC).where(Tables.CASE_DOC.CASE_ID.eq(caseId)).execute();
    }

    public List<Record> getCourtInstances() {
        return dslContext.
                select().
                from(Tables.COURT_INSTANCE).
                fetch();
    }

    public void deleteCourtInstance(int itemId) {
        dslContext.deleteFrom(Tables.COURT_INSTANCE).where(Tables.COURT_INSTANCE.INSTANCE_ID.eq(itemId)).execute();
    }

    public CourtInstanceRecord getCourtInstanceObjectById(int id) {
        return dslContext.fetchOne(Tables.COURT_INSTANCE, Tables.COURT_INSTANCE.INSTANCE_ID.eq(id));
    }

    public List<Record> getEndResults() {
        return dslContext.
                select().
                from(Tables.END_RESULT).
                fetch();
    }

    public void deleteEndResult(int itemId) {
        dslContext.deleteFrom(Tables.END_RESULT).where(Tables.END_RESULT.END_RESULT_ID.eq(itemId)).execute();
    }

    public EndResultRecord getEndResultObjectById(int id) {
        return dslContext.fetchOne(Tables.END_RESULT, Tables.END_RESULT.END_RESULT_ID.eq(id));
    }

    public List<Record> getJudges() {
        return dslContext.
                select().
                from(Tables.JUDGE).
                fetch();
    }

    public void deleteJudge(int itemId) {
        dslContext.deleteFrom(Tables.JUDGE).where(Tables.JUDGE.JUDGE_ID.eq(itemId)).execute();
    }

    public JudgeRecord getJudgeObjectById(int id) {
        return dslContext.fetchOne(Tables.JUDGE, Tables.JUDGE.JUDGE_ID.eq(id));
    }

    public List<Record> getLitigationSubjects() {
        return dslContext.
                select().
                from(Tables.LITIGATION_SUBJECT).
                fetch();
    }

    public void deleteLitigationSubject(int itemId) {
        dslContext.deleteFrom(Tables.LITIGATION_SUBJECT).where(Tables.LITIGATION_SUBJECT.LITIGATION_SUBJECT_ID.eq(itemId)).execute();
    }

    public LitigationSubjectRecord getLitigationSubjectObjectById(int id) {
        return dslContext.fetchOne(Tables.LITIGATION_SUBJECT, Tables.LITIGATION_SUBJECT.LITIGATION_SUBJECT_ID.eq(id));
    }

}
