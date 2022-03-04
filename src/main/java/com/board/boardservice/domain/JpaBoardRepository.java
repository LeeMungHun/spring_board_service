package com.board.boardservice.domain;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

//@Repository
@Transactional
public class JpaBoardRepository implements BoardRepository{

    private final EntityManager em;

    public JpaBoardRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Board save(Board board) {
        em.persist(board);
        return board;
    }

    @Override
    public Board findBoard(Long id) {
        Board board = em.find(Board.class, id);
        return board;
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    @Override
    public void update(Long id, Board board) {

    }

    @Override
    public List<Board> findAll() {
        return null;
    }

    @Override
    public void clear() {

    }
}
