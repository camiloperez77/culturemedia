package culturemedia.repository;

import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;

import java.util.List;

public interface VideoRepository {
    List<Video> findAll() throws VideoNotFoundException;
    Video save(Video save);
    List<Video> find(String title) throws VideoNotFoundException;
    List<Video> find(Double fromDuration, Double toDuration) throws DurationNotValidException;
}
