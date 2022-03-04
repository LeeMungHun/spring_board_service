package com.board.boardservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository {

    Board save(Board board);
    Board findBoard(Long id);
    String delete(Long id);
    void update(Long id, Board board);
    List<Board> findAll();
    void clear();

}
