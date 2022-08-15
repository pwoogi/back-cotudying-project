package com.week06.cotudying_project.model;

import com.week06.cotudying_project.dto.boardsDto.BoardsDetailRequestDto;
import com.week06.cotudying_project.repository.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Boards extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long num;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String period;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long participant;

    public Boards(String title, Long num, String category, String period, String content, Long participant) {
        this.title = title;
        this.num = num;
        this.category = category;
        this.period = period;
        this.content = content;
        this.participant = participant;
    }

    public Boards(BoardsDetailRequestDto boardsDetailRequestDto) {
        this.title = boardsDetailRequestDto.getTitle();
        this.num = boardsDetailRequestDto.getNum();
        this.category = boardsDetailRequestDto.getCategory();
        this.period = boardsDetailRequestDto.getPeriod();
        this.content = boardsDetailRequestDto.getContent();
        this.participant = boardsDetailRequestDto.getParticipant();
    }

}
