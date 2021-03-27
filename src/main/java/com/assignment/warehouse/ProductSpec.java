package com.assignment.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ProductSpec {
    @JsonProperty
    private String name;
    @JsonProperty("contain_articles")
    private List<Article> articleList;
    @JsonProperty("prod_id")
    private Integer id;

    public ProductSpec() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    static class Article {
        @JsonProperty("art_id")
        private Integer id;
        @JsonProperty("amount_of")
        private Integer amount;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }
    }
}
