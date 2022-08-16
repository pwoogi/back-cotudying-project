package com.week06.cotudying_project.repository;

import com.week06.cotudying_project.model.Board;
import com.week06.cotudying_project.model.BoardInfo;
import com.week06.cotudying_project.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardInfoRepository extends JpaRepository<BoardInfo, Long> {
    List<BoardInfo> findAllByMember(Member member);
    List<BoardInfo> findAllByBoard(Board board);
    Optional<BoardInfo> findByMemberAndBoard(Member member, Board board);
}
