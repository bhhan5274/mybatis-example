package io.github.bhhan.web;


import com.github.javafaker.Faker;
import io.github.bhhan.App;
import io.github.bhhan.dao.ContentDto;
import io.github.bhhan.dao.ContentMapper;
import io.github.bhhan.domain.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContentController {
    private final ContentMapper contentMapper;
    private final Faker faker;
    private final ContentRepository contentRepository;

    @GetMapping("/api/v1/contents")
    public List<ContentDto> contents(){
        return contentMapper.getContents();
    }

    @GetMapping("/api/v1/contents/{contentId}")
    public ContentDto contentById(@PathVariable Long contentId) {
        return contentMapper.getContentById(contentId);
    }

    @PostMapping("/api/v1/contents")
    @ResponseStatus(HttpStatus.CREATED)
    public void addContent(){
        contentRepository.save(App.makeContent(faker, 1).get(0));
    }
}
