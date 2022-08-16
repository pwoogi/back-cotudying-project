package com.week06.cotudying_project.repository;


import com.week06.cotudying_project.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByCreatedAtDesc();  // 업데이트 시간 기준 내림차순 정렬
    List<Board> findAllByCategoryOrderByCreatedAtDesc(String category);

}
