package com.board.boardservice.controller;

import com.board.boardservice.domain.Board;
import com.board.boardservice.domain.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardRepository boardRepository;

    @GetMapping
    public String boards(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/boards";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId,Model model){
        Board board = boardRepository.findBoard(boardId);
        model.addAttribute("board", board);
        return "board/board";
    }

    @GetMapping("/write")
    public String addForm() {
        return "board/addForm";
    }

    @PostMapping("/write")
    public String writeBoard(@ModelAttribute Board board, RedirectAttributes redirectAttributes) {
        Board saveBoard = boardRepository.save(board);
        redirectAttributes.addAttribute("boardId", saveBoard.getId());
        redirectAttributes.addAttribute("add_status", true);
        return "redirect:/boards/{boardId}";
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId,Model model) {
        Board board = boardRepository.findBoard(boardId);
        model.addAttribute("board", board);
        return "board/editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String edit(@PathVariable Long boardId,@ModelAttribute Board board,
                       RedirectAttributes redirectAttributes) {
        boardRepository.update(boardId, board);
        redirectAttributes.addAttribute("edit_status", true);
        return "redirect:/boards/{boardId}";
    }

    @PostConstruct
    public void init(){
        boardRepository.save(new Board("title1", "content1", "작성자1"));
        boardRepository.save(new Board("title2", "content2", "작성자2"));
    }


}
