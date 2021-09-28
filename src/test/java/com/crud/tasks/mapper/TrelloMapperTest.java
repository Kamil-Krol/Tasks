package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TrelloMapperTest {

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldMapToBoards()
    {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1","test",new ArrayList<>());
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto);

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        assertEquals(1,trelloBoards.size());
    }

    @Test
    public void shouldMapToBoardsDto()
    {
        //Given
        TrelloBoard trelloBoard = new TrelloBoard("1","test",new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(1,trelloBoardDtos.size());
    }

    @Test
    public void shouldMapToList()
    {
        TrelloListDto trelloListDto = new TrelloListDto("1","test",true);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        //Then
        assertEquals(1,trelloLists.size());
    }

    @Test
    public void shouldMapToListDto()
    {
        //Given
        TrelloList trelloList = new TrelloList("1","test",true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(1,trelloListDtos.size());
    }

    @Test
    public void shouldMapToCard()
    {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test","test","1","1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("test",trelloCard.getName());
    }

    @Test
    public void shouldMapToCardsDto()
    {
        //Given
        TrelloCard trelloCard = new TrelloCard("test","test","1","1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("test",trelloCardDto.getName());
    }

}