package com.epam.lab.service;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorDao;
import com.epam.lab.service.impl.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class AuthorServiceImplTest {
    AuthorService authorService;
    @Mock
    AuthorDao authorDao;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        modelMapper = new ModelMapper();
        authorService = new AuthorServiceImpl(authorDao, modelMapper);
    }

    @Test
    void showAllDto() {
        List<Author> authors = new ArrayList<>();
        Author author = new Author("Lukas", "Scarsitto");
        Author author2 = new Author("Albert", "Oneill");
        Author author3 = new Author("Wayne", "Wall");
        authors.add(author);
        authors.add(author2);
        authors.add(author3);
        Integer expectedListSize = 3;

        when(authorDao.getAllEntities()).thenReturn(authors);
        List<AuthorDto> authorDtos = authorService.showAllDto();

        assertEquals(expectedListSize, authorDtos.size());
    }

    @Test
    void showDtoById() {
        String authorName = "Ben";
        String authorSurname = "Glen";
        when(authorDao.getEntityById(1L)).thenReturn(new Author(authorName, authorSurname));
        AuthorDto authorDto = authorService.showDtoById(1L);
        assertEquals(authorName, authorDto.getAuthorName());
        assertEquals(authorSurname, authorDto.getAuthorSurname());
    }

    @Test
    void saveDto() {
        String authorName = "Stacy";
        String authorSurname = "Mcmahon";
        AuthorDto authorDto = new AuthorDto();
        authorDto.setAuthorName(authorName);
        authorDto.setAuthorSurname(authorSurname);

        Author author = new Author();
        author.setAuthorName(authorName);
        author.setAuthorSurname(authorSurname);

        when(authorDao.createEntity(author)).thenReturn(true);
        assertTrue(authorService.saveDto(authorDto));
    }

    @Test
    void removeDto() {
        Long authorId = 1L;
        when(authorDao.deleteEntity(authorId)).thenReturn(true);
        assertTrue(authorService.removeDto(authorId));
    }
}
