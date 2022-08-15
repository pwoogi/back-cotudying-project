package com.week06.cotudying_project.dto.boardsDto;


import com.week06.cotudying_project.model.Boards;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BoardsShowResponseDto {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String title;
    private Long num;
    private String category;
    private String period;
    private String content;
    private Long participant;

    public BoardsShowResponseDto(Boards boards) {
        this.createdAt = boards.getCreatedAt();
        this.modifiedAt = boards.getModifiedAt();
        this.id = boards.getId();
        this.title = boards.getTitle();
        this.num = boards.getNum();
        this.category = boards.getCategory();
        this.period = boards.getPeriod();
        this.content = boards.getContent();
        this.participant = boards.getParticipant();
    }
}
