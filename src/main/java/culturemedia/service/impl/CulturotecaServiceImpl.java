package culturemedia.service.impl;

import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.service.CulturotecaService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CulturotecaServiceImpl implements CulturotecaService {

    VideoRepository videoRepository;
    ViewsRepository viewRepository;

    public CulturotecaServiceImpl(VideoRepository videoRepository, ViewsRepository viewRepository ) {
        this.videoRepository = videoRepository;
        this.viewRepository = viewRepository;
    }

    @Override
    public List<Video> findAll() throws VideoNotFoundException {
        List<Video> videos = videoRepository.findAll();
        	if (videos.isEmpty()) {
			throw new VideoNotFoundException();
		}
        return  videos;
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
        List<Video> videos = videoRepository.find(title);
        if(videos.isEmpty()){
					throw new VideoNotFoundException(title);
				}
        return videos;
    }

    @Override
    public List<Video> find(Double fromDuration, Double toDuration) throws DurationNotValidException {
        List<Video> videos = videoRepository.find(fromDuration, toDuration);
                if(videos.isEmpty()){
					throw new DurationNotValidException(fromDuration, toDuration);
				}
        return videos;
    }
}
