package culturemedia.controllers;

import java.util.*;

import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.CulturotecaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CultureMediaController {

	private final CulturotecaService cultureMediaService;

	public CultureMediaController(CulturotecaService cultureMediaService) {
		this.cultureMediaService = cultureMediaService;
	}

	@GetMapping("/videos")
	public List<Video> findAll() throws VideoNotFoundException {
		List<Video> videos = cultureMediaService.findAll();
		if (videos.isEmpty()) {
			throw new VideoNotFoundException();
		}
		return videos;
	}

	@PostMapping("/videos")
	public Video save(@RequestBody Video video)throws DurationNotValidException {
		return cultureMediaService.save(video);
	}
}
