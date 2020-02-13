package com.epam.lab.controller;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.dto.NewsSearchCriteria;
import com.epam.lab.exception.ServiceException;
import com.epam.lab.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService){
        this.newsService = newsService;
    }

    @PostMapping
    public boolean createNews(@RequestBody NewsDto newsDto){
        return newsService.saveDto(newsDto);
    }

    @GetMapping
    public List<NewsDto> getAllNews(){
        return newsService.showAllDto();
    }

    @GetMapping("/search")
    public List<NewsDto> getNewsByCriteria(@ModelAttribute NewsSearchCriteria newsSearchCriteria){
        return newsService.searchByCriteria(newsSearchCriteria);
    }

    @PutMapping(value = "{id}")
    public NewsDto updateNews(@RequestBody NewsDto newsDto) throws ServiceException {
        return newsService.editDto(newsDto);
    }

    @GetMapping(value = "/{id}")
    public NewsDto getNews(@PathVariable("id") Long id){
        return newsService.showDtoById(id);
    }

    @DeleteMapping(value = "{id}")
    public boolean deleteNews(@PathVariable("id") Long id){
        return newsService.removeDto(id);
    }
}
