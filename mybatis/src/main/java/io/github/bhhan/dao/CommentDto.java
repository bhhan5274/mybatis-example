package io.github.bhhan.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long commentId;
    private String description;
    private String subject;
    private LocalDateTime issuedAt;
}
