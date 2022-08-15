package com.week06.cotudying_project.dto.boardsDto;


import com.week06.cotudying_project.model.Boards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardsDetailResponseDto {
    private Long id;
    private String title;
    private String category;
    private Long num;
    private String period;
    private String content;
    private Long participant;

    public static BoardsDetailResponseDto toEntity(Boards boards) {
        return new BoardsDetailResponseDto(
                boards.getId(),
                boards.getTitle(),
                boards.getCategory(),
                boards.getNum(),
                boards.getContent(),
                boards.getPeriod(),
                boards.getParticipant()
        );
    }
}
