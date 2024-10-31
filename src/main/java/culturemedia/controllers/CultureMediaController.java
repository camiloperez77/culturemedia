package culturemedia.controllers;

import java.util.*;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.impl.CulturotecaServiceImpl;

public class CultureMediaController {

	private final CulturotecaServiceImpl cultureMediaService;

	public CultureMediaController(CulturotecaServiceImpl cultureMediaService) {
		this.cultureMediaService = cultureMediaService;
	}

	public List<Video> findAll() throws VideoNotFoundException {
		List<Video> videos = cultureMediaService.findAll();
		if (videos.isEmpty()) {
			throw new VideoNotFoundException();

		}
		return videos;
	}

	public Video save(Video video) {
		return cultureMediaService.save(video);
	}
}
