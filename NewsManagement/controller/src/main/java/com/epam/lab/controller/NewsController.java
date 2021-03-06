package com.epam.lab.controller;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.model.NewsSearchCriteria;
import com.epam.lab.service.impl.NewsServiceJpaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/news")
public class NewsController {
    private NewsServiceJpaImpl newsService;

    @Autowired
    public NewsController(NewsServiceJpaImpl newsService) {
        this.newsService = newsService;
    }

    @PostMapping
    public boolean createNews(@RequestBody NewsDto newsDto) {
        return newsService.saveDto(newsDto);
    }

    @GetMapping(value = "/{id}")
    public NewsDto getNews(@PathVariable("id") Long id) {
        return newsService.showDtoById(id);
    }

    @GetMapping
    public Set<NewsDto> getAllNews() {
        return newsService.showAllDto();
    }

    @GetMapping("/search")
    public Collection<NewsDto> getNewsByCriteria(@ModelAttribute NewsSearchCriteria newsSearchCriteria) {
        return newsService.searchByCriteria(newsSearchCriteria);
    }

    @PutMapping(value = "{id}")
    public NewsDto updateNews(@RequestBody NewsDto newsDto) {
        return newsService.editDto(newsDto);
    }

    @DeleteMapping(value = "{id}")
    public boolean deleteNews(@PathVariable("id") Long id) {
        return newsService.removeDto(id);
    }
}
