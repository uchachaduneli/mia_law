package ge.economy.law.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.economy.law.misc.JsonDateSerializeSupport;

import java.util.Date;
import java.util.List;

public class AddCaseRequest {

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
    private String note;
    private Integer addUserId;
    private String addUserName;
    private Integer courtId;
    private String courtName;
    private Integer statusId;
    private String statusName;
    private Integer courtInstanceId;
    private String courtInstanceName;
    private String courtInstanceNote;
    private Double litigationPrice;
    private String groupId;
    private Integer boardId;
    private String boardName;
    private String thirdPersons;
    private Integer ministryStatus;
    private List<AddCaseDocRequest> docs;
    private String role;
    private String user;
    
    

    public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<AddCaseDocRequest> getDocs() {
        return docs;
    }

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setDocs(List<AddCaseDocRequest> docs) {
        this.docs = docs;
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

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

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
}
