package com.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
class MapperTests {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testOfInsert() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 게시글 제목");
		params.setContent("1번 게시글 내용");
		params.setWriter("테스터");
		
		int result = boardMapper.insertBoard(params);
		System.out.println("결과는 " + result + "입니다.");
	}
	
	@Test
	 public void insertTasks() {
		BoardDTO params = new BoardDTO();
		for(int i=0; i<50; i++) {
			params.setTitle((i + 1) + "번 게시글 제목");
			params.setContent((i + 1) + "번 게시글 내용");
			params.setWriter((i + 1) + "번 게시글 작성자");
			boardMapper.insertBoard(params);
		}
	 }
	 
	@Test
	public void testOfSelectDetail() {
		BoardDTO board = boardMapper.selectBoardDetail(1L); 
		try {
			String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
			System.out.println("=======================");
			System.out.println(boardJson); 
			System.out.println("=======================");
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOfUpdate() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 게시글 제목을 수정합니다."); 
		params.setContent("1번 게시글 내용을 수정합니다.");
		params.setWriter("홀길동"); 
		params.setIdx(1L);
	
		int result = boardMapper.updateBoard(params);
		if (result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail(1L);
			try {
				String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
	
				System.out.println("=======================");
				System.out.println(boardJson);
				System.out.println("=======================");
				System.out.println(">>>업데이트 완료");
			
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testOfDelete() {
		int result = boardMapper.deleteBoard(1L);
		if (result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail(1L);
			try {
				String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
				
				System.out.println("========================");
				System.out.println(boardJson);
				System.out.println("========================");
			
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
//	@Test
//	public void testSelectList() {
//		int boardTotalCount = boardMapper.selectBoardTotalCount(); 
//		if (boardTotalCount > 0) {
//			List<BoardDTO> boardList = boardMapper.selectBoardList(); 
//			if (boardList != null && !boardList.isEmpty()) {
//				for (BoardDTO board : boardList) {
//					System.out.println("========================");
//					System.out.println(board.getTitle()); 
//					System.out.println(board.getContent());
//					System.out.println(board.getWriter());
//					System.out.println("========================");
//				}
//			}
//		}
//	}
}