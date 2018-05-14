package ge.economy.law.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.economy.law.misc.JsonDateSerializeSupport;

import java.util.Date;

public class SearchCaseRequest {

    private Integer caseId;
    private String name;
    private String number;
    private Integer judgeId;
    private String judgeAssistant;
    private String judgeAssistantPhone;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date caseStartDateFrom;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date caseStartDateTo;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date caseEndDateFrom;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date caseEndDateTo;
    private Integer litigationSubjectId;
    private Integer endResultId;
    private Integer addUserId;
    private Integer courtId;
    private Integer statusId;
    private Integer courtInstanceId;
    private Integer boardId;
    private Integer ministryStatus;

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Integer getMinistryStatus() {
        return ministryStatus;
    }

    public void setMinistryStatus(Integer ministryStatus) {
        this.ministryStatus = ministryStatus;
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

    public Date getCaseStartDateFrom() {
        return caseStartDateFrom;
    }

    public void setCaseStartDateFrom(Date caseStartDateFrom) {
        this.caseStartDateFrom = caseStartDateFrom;
    }

    public Date getCaseStartDateTo() {
        return caseStartDateTo;
    }

    public void setCaseStartDateTo(Date caseStartDateTo) {
        this.caseStartDateTo = caseStartDateTo;
    }

    public Date getCaseEndDateFrom() {
        return caseEndDateFrom;
    }

    public void setCaseEndDateFrom(Date caseEndDateFrom) {
        this.caseEndDateFrom = caseEndDateFrom;
    }

    public Date getCaseEndDateTo() {
        return caseEndDateTo;
    }

    public void setCaseEndDateTo(Date caseEndDateTo) {
        this.caseEndDateTo = caseEndDateTo;
    }

    public Integer getLitigationSubjectId() {
        return litigationSubjectId;
    }

    public void setLitigationSubjectId(Integer litigationSubjectId) {
        this.litigationSubjectId = litigationSubjectId;
    }

    public Integer getEndResultId() {
        return endResultId;
    }

    public void setEndResultId(Integer endResultId) {
        this.endResultId = endResultId;
    }

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public Integer getCourtId() {
        return courtId;
    }

    public void setCourtId(Integer courtId) {
        this.courtId = courtId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getCourtInstanceId() {
        return courtInstanceId;
    }

    public void setCourtInstanceId(Integer courtInstanceId) {
        this.courtInstanceId = courtInstanceId;
    }
}
