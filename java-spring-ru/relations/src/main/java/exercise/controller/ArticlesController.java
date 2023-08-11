package exercise.controller;

import exercise.dto.ArticleDto;
import exercise.model.Article;
import exercise.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticlesController {

    private final ArticleRepository articleRepository;

    @GetMapping(path = "")
    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteArticle(@PathVariable long id) {
        articleRepository.deleteById(id);
    }

    // BEGIN
    @PostMapping(path = "")
    public Article createArticle(@RequestBody ArticleDto dto) {
        Article article = new Article();
        article.setName(dto.getName());
        article.setBody(dto.getBody());
        article.setCategory(dto.getCategory());

        return articleRepository.save(article);
    }

    @GetMapping(path = "/{id}")
    public Article getArticle(@PathVariable long id) {
        return articleRepository.findById(id);
    }

    @PatchMapping(path = "/{id}")
    public Article updateArticle(@PathVariable long id, @RequestBody ArticleDto dto) {
        Article article = articleRepository.findById(id);
        article.setCategory(dto.getCategory());
        article.setName(dto.getName());
        article.setBody(dto.getBody());

        return articleRepository.save(article);
    }
    // END
}
