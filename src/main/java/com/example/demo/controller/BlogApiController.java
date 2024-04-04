package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.dto.AddArticleRequest;
import com.example.demo.dto.ArticleResponse;
import com.example.demo.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/aritcles")
    public ResponseEntity<List<ArticleResponse>> findAllAritcles(){
        List<ArticleResponse> articles = blogService.finaAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok().body(articles);
    }

}
