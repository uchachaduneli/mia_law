package ge.economy.law.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.economy.law.misc.JsonDateSerializeSupport;
import ge.economy.law.model.Tables;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaseDTO {
    private Integer caseId;
    private String name;
    private String number;
    private Integer judgeId;
    private String judgeName;
    private String judgeAssistant;
    private String judgeAssistantPhone;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date caseStartDate;
    private Integer litigationSubjectId;
    private String litigationSubjectName;
    private String litigationDescription;
    private Integer endResultId;
    private String endResultName;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date caseEndDate;
    private Date expireDate;
    private String note;
    private Integer addUserId;
    private String addUserName;
    private Integer courtId;
    private String courtName;
    private Integer statusId;
    private String statusName;
    private Integer courtInstanceId;
    private String groupId;
    private String courtInstanceName;
    private String courtInstanceNote;
    private Double litigationPrice;
    private Integer boardId;
    private Integer ministryStatus;
    private String boardName;
    private String thirdPersons;
    private String role;
    private boolean ownsUser;
    private boolean alert;
    private String user;
    private String resolution;
    private String expireNote;

    public static CaseDTO translate(Record record, String username) {
        CaseDTO dto = new CaseDTO();
        dto.setOwnsUser(false);
        dto.setCaseId(record.getValue(Tables.CASE.CASE_ID));
        dto.setName(record.getValue(Tables.CASE.NAME));
        dto.setNumber(record.getValue(Tables.CASE.NUMBER));
        dto.setJudgeId(record.getValue(Tables.CASE.JUDGE_ID));
        if (dto.getJudgeId() != null) {
            dto.setJudgeName(record.getValue(Tables.JUDGE.NAME));
            dto.setJudgeAssistant(record.getValue(Tables.JUDGE.ASSISTANT));
            dto.setJudgeAssistantPhone(record.getValue(Tables.JUDGE.ASSISTANT_PHONE));
        }
        dto.setCaseStartDate(record.getValue(Tables.CASE.CASE_START_DATE));
        dto.setLitigationSubjectId(record.getValue(Tables.CASE.LITIGATION_SUBJECT_ID));
        if (dto.getLitigationSubjectId() != null && record.field(Tables.LITIGATION_SUBJECT.NAME) != null) {
            dto.setLitigationSubjectName(record.getValue(Tables.LITIGATION_SUBJECT.NAME));
        }
        dto.setLitigationDescription(record.getValue(Tables.CASE.LITIGATION_DESCRIPTION));
        dto.setEndResultId(record.getValue(Tables.CASE.END_RESULT_ID));
        if (dto.getEndResultId() != null && record.field(Tables.END_RESULT.NAME) != null) {
            dto.setEndResultName(record.getValue(Tables.END_RESULT.NAME));
        }
        dto.setCaseEndDate(record.getValue(Tables.CASE.CASE_END_DATE));
        dto.setNote(record.getValue(Tables.CASE.NOTE));
        dto.setAddUserId(record.getValue(Tables.CASE.ADD_USER_ID));
        if (dto.getAddUserId() != null) {
            dto.setUser(record.getValue(Tables.USER.NAME));
            dto.setAddUserName(record.getValue(Tables.USER.USERNAME));
        }
        dto.setCourtId(record.getValue(Tables.CASE.COURT_ID));
        if (dto.getCourtId() != null && record.field(Tables.COURT.NAME) != null) {
            dto.setCourtName(record.getValue(Tables.COURT.NAME));
        }
        dto.setStatusId(record.getValue(Tables.CASE.STATUS_ID));
        if (dto.getStatusId() != null && record.field(Tables.STATUS.NAME) != null) {
            dto.setStatusName(record.getValue(Tables.STATUS.NAME));
        }
        dto.setCourtInstanceId(record.getValue(Tables.CASE.COURT_INSTANCE_ID));
        dto.setLitigationPrice(record.getValue(Tables.CASE.LITIGATION_PRICE));
        dto.setGroupId(record.getValue(Tables.CASE.GROUP_ID));
        if (dto.getCourtInstanceId() != null && record.field(Tables.COURT_INSTANCE.NAME) != null) {
            dto.setCourtInstanceName(record.getValue(Tables.COURT_INSTANCE.NAME));
        }
        dto.setThirdPersons(record.getValue(Tables.CASE.THIRD_PERSONS));
        dto.setMinistryStatus(record.getValue(Tables.CASE.MINISTRY_STATUS));
        dto.setBoardId(record.getValue(Tables.CASE.BOARD_ID));
        if (dto.getBoardId() != null && record.field(Tables.BOARD.NAME) != null) {
            dto.setBoardName(record.getValue(Tables.BOARD.NAME));
        }
        if (username.equals(dto.getAddUserName()) || UserDTO.isAdmin) {
            dto.setOwnsUser(true);
        }
        dto.setAlert(daysBetween(record.getValue(Tables.CASE.EXPIRE_DATE)));
        dto.setExpireDate(record.getValue(Tables.CASE.EXPIRE_DATE));
        dto.setResolution(record.getValue(Tables.CASE.RESOLUTION));
        dto.setExpireNote(record.getValue(Tables.CASE.EXPIRE_NOTE));
        return dto;
    }

    private static boolean daysBetween(java.util.Date d1) {
        if (d1 == null) return false;
        return Days.daysBetween(new LocalDate(d1), new LocalDate(new java.util.Date())).getDays() > -2;// erti dgit adre antebs alerts
    }

    public static List<CaseDTO> translateArray(List<Record> records, String username) {
        ArrayList<CaseDTO> list = new ArrayList<CaseDTO>();
        for (Record record : records) {
            list.add(CaseDTO.translate(record, username));
        }
        return list;
    }

    public String getExpireNote() {
        return expireNote;
    }

    public void setExpireNote(String expireNote) {
        this.expireNote = expireNote;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getMinistryStatus() {
        return ministryStatus;
    }

    public void setMinistryStatus(Integer ministryStatus) {
        this.ministryStatus = ministryStatus;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getThirdPersons() {
        return thirdPersons;
    }

    public void setThirdPersons(String thirdPersons) {
        this.thirdPersons = thirdPersons;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Double getLitigationPrice() {
        return litigationPrice;
    }

    public void setLitigationPrice(Double litigationPrice) {
        this.litigationPrice = litigationPrice;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(Integer judgeId) {
        this.judgeId = judgeId;
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

    public String getJudgeAssistant() {
        return judgeAssistant;
    }

    public void setJudgeAssistant(String judgeAssistant) {
        this.judgeAssistant = judgeAssistant;
    }

    public String getJudgeAssistantPhone() {
        return judgeAssistantPhone;
    }

    public void setJudgeAssistantPhone(String judgeAssistantPhone) {
        this.judgeAssistantPhone = judgeAssistantPhone;
    }

    public Date getCaseStartDate() {
        return caseStartDate;
    }

    public void setCaseStartDate(Date caseStartDate) {
        this.caseStartDate = caseStartDate;
    }

    public Integer getLitigationSubjectId() {
        return litigationSubjectId;
    }

    public void setLitigationSubjectId(Integer litigationSubjectId) {
        this.litigationSubjectId = litigationSubjectId;
    }

    public String getLitigationSubjectName() {
        return litigationSubjectName;
    }

    public void setLitigationSubjectName(String litigationSubjectName) {
        this.litigationSubjectName = litigationSubjectName;
    }

    public String getLitigationDescription() {
        return litigationDescription;
    }

    public void setLitigationDescription(String litigationDescription) {
        this.litigationDescription = litigationDescription;
    }

    public Integer getEndResultId() {
        return endResultId;
    }

    public void setEndResultId(Integer endResultId) {
        this.endResultId = endResultId;
    }

    public String getEndResultName() {
        return endResultName;
    }

    public void setEndResultName(String endResultName) {
        this.endResultName = endResultName;
    }

    public Date getCaseEndDate() {
        return caseEndDate;
    }

    public void setCaseEndDate(Date caseEndDate) {
        this.caseEndDate = caseEndDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    /*
     * public Integer getAddUserId() { return addUserId; }
     *
     * public void setAddUserId(Integer addUserId) { this.addUserId = addUserId; }
     */

    public String getAddUserName() {
        return addUserName;
    }

    public void setAddUserName(String addUserName) {
        this.addUserName = addUserName;
    }

    public Integer getCourtId() {
        return courtId;
    }

    public void setCourtId(Integer courtId) {
        this.courtId = courtId;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getCourtInstanceId() {
        return courtInstanceId;
    }

    public void setCourtInstanceId(Integer courtInstanceId) {
        this.courtInstanceId = courtInstanceId;
    }

    public String getCourtInstanceName() {
        return courtInstanceName;
    }

    public void setCourtInstanceName(String courtInstanceName) {
        this.courtInstanceName = courtInstanceName;
    }

    public String getCourtInstanceNote() {
        return courtInstanceNote;
    }

    public void setCourtInstanceNote(String courtInstanceNote) {
        this.courtInstanceNote = courtInstanceNote;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isOwnsUser() {
        return ownsUser;
    }

    public void setOwnsUser(boolean ownsUser) {
        this.ownsUser = ownsUser;
    }

}
