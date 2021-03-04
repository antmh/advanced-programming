package lab3;

import java.time.LocalTime;

public interface Visitable {
    LocalTime getOpeningTime();
    LocalTime getClosingTime();
    void setOpeningTime(LocalTime openingTime);
    void setClosingTime(LocalTime closingTime);
}
