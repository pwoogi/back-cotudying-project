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
    private String category;
    private String content;

    public BoardsShowResponseDto(Boards boards) {
        this.createdAt = boards.getCreatedAt();
        this.modifiedAt = boards.getModifiedAt();
        this.id = boards.getId();
        this.title = boards.getTitle();
        this.category = boards.getCategory();
        this.content = boards.getContent();
    }
}
