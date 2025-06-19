package org.example.services;

import org.example.dtos.Request.ArtWorkRequest;
import org.example.dtos.Response.ArtWorkResponse;
import org.example.model.ArtWork;
import org.example.repository.ArtWorkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArtWorkServiceImplTest {

    @Mock
    private ArtWorkRepository artWorkRepository;

    @InjectMocks
    private ArtWorkServiceImpl artWorkService;

    @Test
    public void testCreateArtWork() {
        ArtWorkRequest artWorkRequest = new ArtWorkRequest();
        artWorkRequest.setTitle("Title");
        artWorkRequest.setDescription("Sunshine");
        artWorkRequest.setPrice(300.00);

        ArtWork savedArt = new ArtWork();
        savedArt.setId("123");
        savedArt.setTitle("Title");
        savedArt.setDescription("Sunshine");
        savedArt.setPrice(300.00);
        savedArt.setAvailable(true);

        when(artWorkRepository.save(any(ArtWork.class))).thenReturn(savedArt);


        ArtWorkResponse response = artWorkService.createArtWork(artWorkRequest, savedArt.getId());

        assertNotNull(response);
        assertEquals("123", response.getId());
        assertEquals("Title", response.getTitle());
    }


    @Test
    public void testGetArtworkById() {
        String id = "123";
        ArtWork mockArt = new ArtWork();
        mockArt.setId(id);
        mockArt.setTitle("Test Art");

        when(artWorkRepository.findById(id)).thenReturn(Optional.of(mockArt));

        ArtWorkResponse response = artWorkService.getArtWorkById(id);
        assertNotNull(response);
        assertEquals("Test Art", response.getTitle());
        verify(artWorkRepository, times(1)).findById(id);
    }
}
