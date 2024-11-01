package culturemedia.service;

import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;

import culturemedia.service.impl.CulturotecaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

class CulturotecaServiceTest {


    @Mock
    private VideoRepository videoRepository;

    @InjectMocks
    private CulturotecaServiceImpl culturotecaService;

    List<Video> videos = List.of(new Video("01", "Título 1", "----", 4.5),
            new Video("02", "Título 2", "----", 5.5),
            new Video("03", "Título 3", "----", 4.4),
            new Video("04", "Título 4", "----", 3.5),
            new Video("05", "Clic 5", "----", 5.7),
            new Video("06", "Clic 6", "----", 5.1));


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException{
        doReturn(videos).when(videoRepository).findAll();
        assertEquals(6, videos.size());
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() throws VideoNotFoundException {
        assertThrows(VideoNotFoundException.class, () -> {culturotecaService.findAll();});
    }

     @Test
    void when_Find_a_video_and_should_be_returned_successfully() throws VideoNotFoundException {
         List<Video> videosEncontrados = List.of(new Video("06", "Clic 6", "----", 5.1));
         doReturn(videosEncontrados).when(videoRepository).find("Clic 6");
        assertEquals(1, videosEncontrados.size());
    }

        @Test
    void when_Find_does_not_find_any_video_with_title_an_VideoNotFoundException_should_be_thrown_successfully() throws VideoNotFoundException {
        assertThrows(VideoNotFoundException.class, () -> {culturotecaService.find("Proof 2");});
    }

    @Test
    void when_Find_a_video_on_range_of_duration_should_be_returned_successfully() throws DurationNotValidException {
        List<Video> videosEncontrados = List.of(new Video("02", "Título 2", "----", 5.5),
                new Video("05", "Clic 5", "----", 5.7));
        doReturn(videosEncontrados).when(videoRepository).find(5.2, 5.8);
        assertEquals(2, videosEncontrados.size());
    }

    @Test
    void when_Find_does_not_find_any_video_with_range_of_duration_and_DurationNotFoundException_should_be_thrown_successfully() throws DurationNotValidException{
        assertThrows(DurationNotValidException.class, () -> {culturotecaService.find(7.0, 8.0);});
    }
}
