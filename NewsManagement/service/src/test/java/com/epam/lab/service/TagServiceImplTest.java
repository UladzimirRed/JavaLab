package com.epam.lab.service;

import com.epam.lab.dto.TagDto;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagDao;
import com.epam.lab.service.impl.TagServiceImpl;
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

public class TagServiceImplTest {
    TagService tagService;
    @Mock
    TagDao tagDao;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        modelMapper = new ModelMapper();
        tagService = new TagServiceImpl(tagDao, modelMapper);
    }

    @Test
    void showTagById() {
        String tagName = "world";
        when(tagDao.getEntityById(1L)).thenReturn(new Tag(tagName));
        TagDto tagDto = tagService.showTagById(1L);
        assertEquals(tagName, tagDto.getTagName());
    }

    @Test
    void showAllTags() {
        List<Tag> tags = new ArrayList<>();
        Tag tag = new Tag("politics");
        Tag tag2 = new Tag("world");
        Tag tag3 = new Tag("lightning");
        tags.add(tag);
        tags.add(tag2);
        tags.add(tag3);
        Integer expectedListSize = 3;

        when(tagDao.getAllEntities()).thenReturn(tags);
        List<TagDto> tagDtos = tagService.showAllTags();

        assertEquals(expectedListSize, tagDtos.size());
    }

    @Test
    void saveTag() {
        String tagName = "space";
        TagDto tagDto = new TagDto();
        tagDto.setTagName(tagName);

        Tag tag = new Tag();
        tag.setTagName(tagName);

        when(tagDao.createEntity(tag)).thenReturn(true);
        assertTrue(tagService.saveTag(tagDto));
    }

    @Test
    void editTag() {
        String tagName = "editedTag";
        TagDto tagDto = new TagDto();
        tagDto.setTagName(tagName);

        when(tagDao.updateEntity(new Tag(tagName))).thenReturn(true);
        assertTrue(tagService.editTag(tagDto));
    }

    @Test
    void removeTag() {
        Long tagId = 1L;
        when(tagDao.deleteEntity(tagId)).thenReturn(true);
        assertTrue(tagService.removeTag(tagId));
    }
}
