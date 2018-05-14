package ge.economy.law.dto;

import ge.economy.law.model.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

public class BoardDTO {

    private int boardId;
    private String name;


    public static BoardDTO translate(Record record) {
        BoardDTO dto = new BoardDTO();
        dto.setBoardId(record.getValue(Tables.BOARD.BOARD_ID));
        dto.setName(record.getValue(Tables.BOARD.NAME));
        return dto;
    }


    public static List<BoardDTO> translateArray(List<Record> records) {
        ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
        for (Record record : records) {
            list.add(BoardDTO.translate(record));
        }
        return list;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
