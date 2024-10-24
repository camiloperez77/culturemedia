package culturemedia.service;

import culturemedia.model.Video;
import culturemedia.model.View;

import java.util.List;

public interface CulturotecaService {
    List<Video> findAll();
    Video add(Video save);
    View add(View view);
}
