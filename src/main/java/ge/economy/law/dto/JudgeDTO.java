package ge.economy.law.dto;

import ge.economy.law.model.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

public class JudgeDTO {

    private int judgeId;
    private String name;
    private String assistant;
    private String assistantPhone;


    public static JudgeDTO translate(Record record) {
        JudgeDTO dto = new JudgeDTO();
        dto.setJudgeId(record.getValue(Tables.JUDGE.JUDGE_ID));
        dto.setName(record.getValue(Tables.JUDGE.NAME));
        dto.setAssistant(record.getValue(Tables.JUDGE.ASSISTANT));
        dto.setAssistantPhone(record.getValue(Tables.JUDGE.ASSISTANT_PHONE));
        return dto;
    }


    public static List<JudgeDTO> translateArray(List<Record> records) {
        ArrayList<JudgeDTO> list = new ArrayList<JudgeDTO>();
        for (Record record : records) {
            list.add(JudgeDTO.translate(record));
        }
        return list;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getAssistantPhone() {
        return assistantPhone;
    }

    public void setAssistantPhone(String assistantPhone) {
        this.assistantPhone = assistantPhone;
    }

    public int getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(int judgeId) {
        this.judgeId = judgeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
