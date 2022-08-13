package com.week06.cotudying_project.repository;


import com.week06.cotudying_project.model.Boards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardsRepository extends JpaRepository<Boards, Long> {
    List<Boards> findAllByOrderByModifiedAtDesc();  // 업데이트 시간 기준 내림차순 정렬
}
