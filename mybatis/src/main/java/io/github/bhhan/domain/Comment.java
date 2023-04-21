package io.github.bhhan.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String description;
    private String subject;
    private LocalDateTime issuedAt;

    @ManyToOne
    @JoinColumn(name = "content_id", nullable = false)
    private Content content;

    void setContent(Content content) {
        this.content = content;
    }

    public Comment(String description, String subject){
        this.description = description;
        this.subject = subject;
        this.issuedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
