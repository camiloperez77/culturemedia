package culturemedia.repository.impl;

import java.util.ArrayList;
import java.util.List;

import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.VideoRepository;

public class VideoRepositoryImpl implements VideoRepository {

	private final List<Video> videos;

	public VideoRepositoryImpl() {
		videos = new ArrayList<>();
	}

	@Override
	public List<Video> findAll() {

		return videos;
	}

	@Override
	public Video save(Video video) {
		this.videos.add( video );
		return video;
	}

	@Override
	public List<Video> find(String title)  {
		List<Video> filteredVideos = new ArrayList<Video>();
		for ( Video video : videos ) {
			if(video.title().contains(title)){
				filteredVideos.add(video);
			}
		}

		return filteredVideos;
	}

	@Override
	public List<Video> find(Double fromDuration, Double toDuration) {
		List<Video> filteredVideos = new ArrayList<Video>();
		for ( Video video : videos ) {
			if(video.duration()>= fromDuration && video.duration()<= toDuration){
				filteredVideos.add(video);
			}
		}

		return filteredVideos;
	}
}
