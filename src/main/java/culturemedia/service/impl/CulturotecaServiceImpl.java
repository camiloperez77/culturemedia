package culturemedia.service.impl;

import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.repository.impl.VideoRepositoryImpl;
import culturemedia.repository.impl.ViewsRepositoryImpl;
import culturemedia.service.CulturotecaService;

import java.util.ArrayList;
import java.util.List;

public class CulturotecaServiceImpl implements CulturotecaService {

    private List<Video> videos = new ArrayList<>();
    VideoRepository videoRepository;
    ViewsRepository viewRepository;

    public CulturotecaServiceImpl(VideoRepository videoRepository, ViewsRepository viewRepository ) {
        this.videoRepository = new VideoRepositoryImpl();
        this.viewRepository = new ViewsRepositoryImpl();
    }

    @Override
    public List<Video> findAll() throws VideoNotFoundException {
        return  videoRepository.findAll();
    }

    @Override
    public Video save(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public View save(View view) {
        return viewRepository.save(view);
    }

    @Override
    public List<Video> find(String title) throws VideoNotFoundException{
        return videoRepository.find(title);
    }

    @Override
    public List<Video> find(Double fromDuration, Double toDuration) throws DurationNotValidException {
        return videoRepository.find(fromDuration, toDuration);
    }
}
