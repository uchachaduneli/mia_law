package ge.economy.law.service;

import ge.economy.law.dao.CaseDAO;
import ge.economy.law.dao.UtilDAO;
import ge.economy.law.dto.CaseDTO;
import ge.economy.law.dto.CaseDocDTO;
import ge.economy.law.dto.StatusDTO;
import ge.economy.law.dto.UserReportDTO;
import ge.economy.law.model.Tables;
import ge.economy.law.model.tables.records.CaseDocRecord;
import ge.economy.law.model.tables.records.CaseRecord;
import ge.economy.law.request.AddCaseDocRequest;
import ge.economy.law.request.AddCaseRequest;
import ge.economy.law.request.SearchCaseRequest;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * @author ucha
 */
@Service
public class CaseService {

	@Autowired
	private CaseDAO caseDAO;

	@Autowired
	private UtilDAO utilDAO;

	@Autowired
	private DSLContext dslContext;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HashMap<String, Object> getCases(int start, int limit, SearchCaseRequest srchCase, String userRoles,
			String username) {

		HashMap<String, Object> resultMap = new HashMap<>();
		HashMap<String, Object> map = caseDAO.getCases(start, limit, srchCase, userRoles);
		List<CaseDTO> items = CaseDTO.translateArray((List) map.get("list"), username);
		resultMap.put("list", items);
		resultMap.put("size", map.get("size"));
		return resultMap;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HashMap<String, Object> getReport(SearchCaseRequest srchCase, String userRoles, String username) {
		HashMap<String, Object> resultMap = new HashMap<>();
		HashMap<String, Object> map = caseDAO.getReport(srchCase, userRoles);
		List<UserReportDTO> items = UserReportDTO.translateArray((List) map.get("list"));
		resultMap.put("list", items);
		resultMap.put("sum", map.get("sum"));
		return resultMap;
	}

	public CaseDTO save(AddCaseRequest request) {
		boolean newRecord = false;
		boolean newInstanceHidtoryRecord = false;
		CaseRecord record = null;
		if (request.getCaseId() != null) {
			record = caseDAO.getCaseObjectById(request.getCaseId());
		}

		if (record == null) {
			record = dslContext.newRecord(Tables.CASE);
			newRecord = true;
			record.setAddUserId(request.getAddUserId());
			record.setGroupId(new SimpleDateFormat("yyMMddhhmmssMs").format(new java.util.Date()));
		}

		if (!newRecord && request.getCourtInstanceId() != record.getCourtInstanceId()) {// Ã¡Æ’ËœÃ¡Æ’Å“Ã¡Æ’Â¡Ã¡Æ’Â¢Ã¡Æ’ï¿½Ã¡Æ’Å“Ã¡Æ’ÂªÃ¡Æ’ËœÃ¡Æ’ËœÃ¡Æ’Â¡
																						// Ã¡Æ’ËœÃ¡Æ’Â¡Ã¡Æ’Â¢Ã¡Æ’ï¿½Ã¡Æ’Â Ã¡Æ’ËœÃ¡Æ’ï¿½Ã¡Æ’Â¨Ã¡Æ’Ëœ
																						// Ã¡Æ’â€™Ã¡Æ’ï¿½Ã¡Æ’â€œÃ¡Æ’ï¿½Ã¡Æ’Â§Ã¡Æ’Â Ã¡Æ’ï¿½
			newInstanceHidtoryRecord = true;
			record = dslContext.newRecord(Tables.CASE);
		}
		record.setAddUserId(request.getAddUserId());
		if (!newRecord) {
			record.setGroupId(request.getGroupId());
		}
		record.setName(request.getName());
		record.setNumber(request.getNumber());
		record.setLitigationPrice(request.getLitigationPrice());
		record.setJudgeId(request.getJudgeId());
		record.setCaseStartDate(new Date(request.getCaseStartDate().getTime()));
		if (request.getCaseEndDate() != null) {
			record.setCaseEndDate(new Date(request.getCaseEndDate().getTime()));
		}
		record.setLitigationSubjectId(request.getLitigationSubjectId());
		record.setLitigationDescription(request.getLitigationDescription());
		record.setEndResultId(request.getEndResultId());
		record.setNote(request.getNote());
		record.setCourtId(request.getCourtId());
		record.setStatusId(request.getStatusId());
		record.setMinistryStatus(request.getMinistryStatus());
		record.setBoardId(request.getBoardId());
		record.setThirdPersons(request.getThirdPersons());
		record.setCourtInstanceId(request.getCourtInstanceId());

		if (newRecord || newInstanceHidtoryRecord) {// Ã¡Æ’â€”Ã¡Æ’Â£ Ã¡Æ’ï¿½Ã¡Æ’Â®Ã¡Æ’ï¿½Ã¡Æ’Å¡
													// Ã¡Æ’ËœÃ¡Æ’Å“Ã¡Æ’Â¡Ã¡Æ’Â¢Ã¡Æ’ï¿½Ã¡Æ’Å“Ã¡Æ’ÂªÃ¡Æ’ËœÃ¡Æ’ï¿½Ã¡Æ’â€“Ã¡Æ’â€�
													// Ã¡Æ’â€™Ã¡Æ’ï¿½Ã¡Æ’â€œÃ¡Æ’ï¿½Ã¡Æ’â€¢Ã¡Æ’ËœÃ¡Æ’â€œÃ¡Æ’ï¿½
													// Ã¡Æ’ï¿½Ã¡Æ’Â®Ã¡Æ’ï¿½Ã¡Æ’Å¡Ã¡Æ’Ëœ
													// Ã¡Æ’Â©Ã¡Æ’ï¿½Ã¡Æ’Å“Ã¡Æ’ï¿½Ã¡Æ’Â¬Ã¡Æ’â€�Ã¡Æ’Â Ã¡Æ’Ëœ
													// Ã¡Æ’â„¢Ã¡Æ’â€�Ã¡Æ’â€”Ã¡Æ’â€œÃ¡Æ’â€�Ã¡Æ’â€˜Ã¡Æ’ï¿½
													// Ã¡Æ’ÂªÃ¡Æ’Â®Ã¡Æ’Â Ã¡Æ’ËœÃ¡Æ’Å¡Ã¡Æ’Â¨Ã¡Æ’Ëœ
													// Ã¡Æ’â€œÃ¡Æ’ï¿½Ã¡Æ’Â¢Ã¡Æ’ï¿½Ã¡Æ’Âª
													// Ã¡Æ’â€ºÃ¡Æ’ËœÃ¡Æ’Â§Ã¡Æ’â€¢Ã¡Æ’â€�Ã¡Æ’â€˜Ã¡Æ’ï¿½
													// Ã¡Æ’Â¨Ã¡Æ’â€�Ã¡Æ’ËœÃ¡Æ’Â«Ã¡Æ’Å¡Ã¡Æ’â€�Ã¡Æ’â€˜Ã¡Æ’ï¿½
													// Ã¡Æ’Â¨Ã¡Æ’â€�Ã¡Æ’ÂªÃ¡Æ’â€¢Ã¡Æ’Å¡Ã¡Æ’ËœÃ¡Æ’Å¡Ã¡Æ’Ëœ
			try {
				record.store();
			} catch (RuntimeException ex) {
				ex.printStackTrace();
				System.out.println(ex.getMessage());
			}

		} else {
			record.update();
		}

		if (!request.getDocs().isEmpty() && record.getCaseId() != null) {
			utilDAO.deleteCaseDoc(record.getCaseId());
			CaseDocRecord docRecord;
			for (AddCaseDocRequest doc : request.getDocs()) {
				docRecord = dslContext.newRecord(Tables.CASE_DOC);
				docRecord.setName(doc.getName());
				docRecord.setCaseId(record.getCaseId());
				docRecord.store();
			}

		}

		return CaseDTO.translate(caseDAO.getWholeCaseObjectById(record.getCaseId()), request.getAddUserName());
	}

	public List<StatusDTO> getStatus() {
		return StatusDTO.translateArray(caseDAO.getCaseStatuses());
	}

	public void deleteCase(int id, String userName) {
		caseDAO.deleteCase(id, userName);
	}

	public List<CaseDTO> getInstanceHistory(int id, String number) {
		return CaseDTO.translateArray(caseDAO.getInstanceHistory(id, number), "");
	}

	public List<CaseDocDTO> getCaseDocs(Integer caseId) {
		return CaseDocDTO.translateArray(utilDAO.getCaseDocs(caseId));
	}
}
