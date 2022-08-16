package com.week06.cotudying_project.repository;

import com.week06.cotudying_project.model.Board;
import com.week06.cotudying_project.model.BoardInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByUsername(String username);
}
