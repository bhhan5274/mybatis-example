package io.github.bhhan.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "contents")
@NoArgsConstructor
@Getter
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    private String description;
    private String subject;
    private LocalDateTime issuedAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "content")
    private List<Comment> comments = new ArrayList<>();

    public Content(String description, String subject, List<Comment> comments) {
        this(null, description, subject, Content.nullAndEmptyListCheck(comments));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return Objects.equals(id, content.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private Content(Long id, String description, String subject, List<Comment> comments) {
        this.id = id;
        this.description = description;
        this.subject = subject;
        this.issuedAt = LocalDateTime.now();

        for (Comment comment : comments) {
            comment.setContent(this);
            this.comments.add(comment);
        }
    }

    private static <T> List<T> nullAndEmptyListCheck(List<T> list){
        if(Objects.isNull(list) || list.isEmpty()) {
            return Collections.emptyList();
        }
        return list;
    }
}
