package culturemedia.service;

import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.repository.impl.VideoRepositoryImpl;
import culturemedia.repository.impl.ViewsRepositoryImpl;
import culturemedia.service.impl.CulturotecaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class CulturotecaServiceTest {

    private VideoRepository videoRepository;
    private ViewsRepository viewRepository;
    private CulturotecaService culturotecaTest;
    private CulturotecaService culturotecaTestVoid;

    @BeforeEach
    void init() {
        culturotecaTest = new CulturotecaServiceImpl(videoRepository, viewRepository);
        culturotecaTestVoid = new CulturotecaServiceImpl(videoRepository, viewRepository);

        List<Video> videos = List.of(new Video("01", "Título 1", "----", 4.5),
                new Video("02", "Título 2", "----", 5.5),
                new Video("03", "Título 3", "----", 4.4),
                new Video("04", "Título 4", "----", 3.5),
                new Video("05", "Clic 5", "----", 5.7),
                new Video("06", "Clic 6", "----", 5.1));

        for ( Video video : videos ) {
			culturotecaTest.save( video );
		}


    }


    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException{
        List<Video> videos = culturotecaTest.findAll();
        assertEquals(6, videos.size());

    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() throws VideoNotFoundException {
        List<Video> videos = culturotecaTestVoid.findAll();
        assertThrows(VideoNotFoundException.class, () -> {culturotecaTestVoid.findAll().isEmpty();});
    }

     @Test
    void when_Find_a_video_and_should_be_returned_successfully() throws VideoNotFoundException {
        List<Video> videos = culturotecaTest.find("Clic 6");
        assertEquals(1, videos.size());
    }

        @Test
    void when_Find_does_not_find_any_video_with_title_an_VideoNotFoundException_should_be_thrown_successfully() throws VideoNotFoundException {
        List<Video> videos = culturotecaTest.find("Proof 2");
        assertThrows(VideoNotFoundException.class, () -> {culturotecaTest.find("Proof 2").isEmpty();});
    }

    @Test
    void when_Find_a_video_on_range_of_duration_should_be_returned_successfully() throws DurationNotValidException {
        List<Video> videos = culturotecaTest.find(5.2, 5.8);
        assertEquals(2, videos.size());
    }

    @Test
    void when_Find_does_not_find_any_video_with_range_of_duration_and_DurationNotFoundException_should_be_thrown_successfully() throws DurationNotValidException{
        List<Video> videos = culturotecaTest.find(7.0, 8.0);
        assertThrows(VideoNotFoundException.class, () -> {culturotecaTest.find(7.0, 8.0).isEmpty();});
    }
}
