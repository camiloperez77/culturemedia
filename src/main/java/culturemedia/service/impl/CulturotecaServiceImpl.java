package culturemedia.service.impl;

import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.service.CulturotecaService;

import java.util.List;

public class CulturotecaServiceImpl implements CulturotecaService {
    @Override
    public List<Video> findAll() {
        return List.of();
    }

    @Override
    public Video add(Video save) {
        return null;
    }

    @Override
    public View add(View view) {
        return view;
    }
}
