package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.dto.AddArticleRequest;
import com.example.demo.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    // 직렬화, 역직렬화를 위한 클래스
    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        blogRepository.deleteAll();
    }


    @DisplayName("addArticle: 블로그 글 추가를 성공한다")
    @Test
    public void addArticle() throws Exception {
        //        given

//        url
        final String url = "/api/articles";
//        title
        final String title = "myTitle";
//        content
        final String content = "myContent";
//        dto
        final AddArticleRequest articleRequest = new AddArticleRequest(title, content);
//        직렬화까지
        final String requestBody = objectMapper.writeValueAsString(articleRequest);


        //        when
//        실제 데이터를 요청한다. 즉 post로 보낸다.
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));


        //        then
//        이결과의 state 가 ok 이면
//        데이터가 잘들어갔는 지 조회. 이때 beforeEach > 기존 데이터 모두 삭제 했죠?

        result.andExpect(status().isCreated());

        List<Article> articleList = blogRepository.findAll();

//        검증하기.

//        사이즈 검증
        assertThat(articleList.size()).isEqualTo(1); //왼쪽 조건의 연산이, 오른쪽 값과 같냐

//        내가 넣은 title 이 맞냐?
        assertThat(articleList.get(0).getTitle()).isEqualTo(title); //왼쪽 조건의 연산이, 오른쪽 값과 같냐.

//        내용 검증
        assertThat(articleList.get(0).getContent()).isEqualTo(content); //왼쪽 조건의 연ㅅ나이, 오른쪽 값과 같은지.

    }


    @DisplayName("findAllArticles: 블로그 글 목록 조회에 성공")
    @Test
    public void findAllArticles() throws Exception {
        //        given
        final String url = "/api/aritcles";
        final String title = "title";
        final String content = "content";

        blogRepository.save(
                Article.builder()
                        .title(title)
                        .content(content)
                        .build()

        );

        //        when
        // 수행하는 로직
        final ResultActions resultActions = mockMvc
                .perform(
                        get(url)
                                .accept(MediaType.APPLICATION_JSON)
                );


        //        then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(title))
                .andExpect(jsonPath("$[0].content").value(content));


    }


}