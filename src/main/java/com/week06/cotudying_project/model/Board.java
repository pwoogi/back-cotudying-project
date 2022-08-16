package com.week06.cotudying_project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.week06.cotudying_project.dto.boardsDto.BoardCreateRequest;
import com.week06.cotudying_project.dto.boardsDto.BoardRegisterDto;
import com.week06.cotudying_project.dto.boardsDto.BoardUpdateDto;
import com.week06.cotudying_project.dto.member.MemberDto;
import com.week06.cotudying_project.model.audit.AuditingFields;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Board extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 300)
    @Lob
    private String content;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String category;


    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String endDate;

    @Column(nullable = false)
    private Long num;

    @Column(nullable = false)
    private Long participant;

    @Column(nullable = false)
    private String registerStatus;

    public Board(BoardCreateRequest registerDto,  String username) {
        this.name = registerDto.getName();
        this.title = registerDto.getTitle();
        this.username = username;
        this.num = registerDto.getNum();
        this.category = registerDto.getCategory();
        this.startDate = registerDto.getStartDate();
        this.endDate = registerDto.getEndDate();
        this.content = registerDto.getContent();
        this.participant = 1L;
        this.registerStatus = "모집중";
    }

    public void updateBoard(BoardUpdateDto boardUpdateDto){
        this.category = boardUpdateDto.getCategory();
        this.name = boardUpdateDto.getName();
        this.content = boardUpdateDto.getContent();
        this.category = boardUpdateDto.getCategory();
        this.num = boardUpdateDto.getNum();
        this.startDate = boardUpdateDto.getStartDate();
        this.endDate = boardUpdateDto.getEndDate();
        this.registerStatus = boardUpdateDto.getRegisterStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Board board = (Board) o;
        return id != null && Objects.equals(id, board.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
