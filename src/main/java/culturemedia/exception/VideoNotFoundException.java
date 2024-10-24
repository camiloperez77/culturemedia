package culturemedia.exception;

import java.text.MessageFormat;

public class VideoNotFoundException extends CulturotecaException {

    public VideoNotFoundException() {super("Videos is empty");}

    public VideoNotFoundException(String title) {
        super(MessageFormat.format("Video not found: {0} ", title));
    }
}
