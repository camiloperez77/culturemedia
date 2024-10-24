package culturemedia.exception;

import java.text.MessageFormat;

public class DurationNotValidException extends CulturotecaException {
    public DurationNotValidException(Double fromDuration, Double toDuration) {
        super(MessageFormat.format("Invalid duration for video: {0}. Duration: {1}", fromDuration, toDuration));
    }
}
