package io.github.bhhan.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ContentDto {
    private Long contentId;
    private String description;
    private String subject;
    private LocalDateTime issuedAt;
    private List<CommentDto> comments;
}
