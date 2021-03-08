package lab3.locations;

import java.time.Duration;
import java.time.LocalTime;

public interface Visitable {
    default LocalTime getOpeningTime() {
        return LocalTime.of(9, 30);
    }

    default LocalTime getClosingTime() {
        return LocalTime.of(20, 0);
    }

    static int compareByOpeningHour(Visitable left, Visitable right) {
        return left.getClosingTime().compareTo(right.getClosingTime());
    }

    static Duration getVisitingDuration(Visitable visitable) {
        return Duration.between(visitable.getOpeningTime(), visitable.getClosingTime());
    }
}
