package culturemedia.service;

import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;

import culturemedia.repository.ViewsRepository;
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

    @Mock
    private ViewsRepository viewRepository;

    @InjectMocks
    private CulturotecaServiceImpl culturotecaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException{
        List<Video> videos = (List.of(new Video("01", "Título 1", "----", 4.5),
                new Video("02", "Título 2", "----", 5.5),
                new Video("03", "Título 3", "----", 4.4)));
        mock_find_all(videos);
        assertEquals(3, videos.size());
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() throws VideoNotFoundException {
        mock_find_all(List.of());
        assertThrows(VideoNotFoundException.class, () -> {culturotecaService.findAll();});
    }

    private void mock_find_all(List<Video> videos) {
        doReturn(videos).when(videoRepository).findAll();
    }

    @Test
    void when_Find_a_video_and_should_be_returned_successfully() throws VideoNotFoundException {
        List<Video> expectedvideos = List.of(new Video("06", "Clic 6", "----", 5.1));
        mock_find_by_title("Clic 6", expectedvideos);
        List<Video> videos = videoRepository.find("Clic 6");
        assertEquals(1, videos.size());
    }

    private void mock_find_by_title(String title, List<Video> videos) throws VideoNotFoundException {
        doReturn(videos).when(videoRepository).find(title);
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
