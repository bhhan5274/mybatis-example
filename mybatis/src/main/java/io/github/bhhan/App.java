package io.github.bhhan;

import com.github.javafaker.Faker;
import io.github.bhhan.domain.Comment;
import io.github.bhhan.domain.Content;
import io.github.bhhan.domain.ContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@Import({MybatisConfig.class, DatasourceConfig.class, JpaConfig.class})
public class App {
    private final ContentRepository contentRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public Faker faker() {
        return Faker.instance();
    }

    @Bean
    public ApplicationRunner applicationRunner(Faker faker) {
        return args -> contentRepository.saveAll(makeContent(faker, 3));
    }

    public static List<Content> makeContent(Faker faker, int count) {
        List<Content> contents = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String campus = faker.educator().campus();

            contents.add(new Content(
                    faker.educator().course(),
                    campus,
                    makeComments(faker, campus)));
        }

        return contents;
    }

    private static List<Comment> makeComments(Faker faker, String campus) {
        return List.of(
                new Comment(campus + faker.book().title(), campus + faker.book().author()),
                new Comment(campus + faker.book().title(), campus + faker.book().author()),
                new Comment(campus + faker.book().title(), campus + faker.book().author())
        );
    }
}
