package com.board.boardservice.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryBoardRepository implements BoardRepository{

    private static final Map<Long, Board> store = new HashMap<>();
    private static long sequence = 1L;

    @Override
    public Board save(Board board) {
        //여기서 게시물 id값 넣어주기
        board.setId(sequence++);
        store.put(board.getId(), board);
        return board;
    }

    @Override
    public Board findBoard(Long id) {
        return store.get(id);
    }

    @Override
    public String delete(Long id) {
        store.remove(id);
        return "삭제되었습니다.";
    }

    @Override
    public void update(Long id, Board updateBoard) {
        Board board = store.get(id);
        board.setContent(updateBoard.getContent());
        board.setTitle(updateBoard.getTitle());
        board.setWriter(updateBoard.getWriter());
    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clear() {
        store.clear();
    }
}
