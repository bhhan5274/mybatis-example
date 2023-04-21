package io.github.bhhan.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentMapper {
    List<ContentDto> getContents();
    ContentDto getContentById(Long id);
}
