package lab3;

import java.time.LocalTime;

public class Park extends Location implements Visitable {
    private LocalTime openingTime;
    private LocalTime closingTime;

    @Override
    public LocalTime getOpeningTime() {
        return openingTime;
    }

    @Override
    public LocalTime getClosingTime() {
        return closingTime;
    }

    @Override
    public void setOpeningTime(LocalTime openingTime) {
        if (openingTime == null) {
            throw new IllegalArgumentException("Opening time cannot be null");
        }
        this.openingTime = openingTime;
    }

    @Override
    public void setClosingTime(LocalTime closingTime) {
        if (closingTime == null) {
            throw new IllegalArgumentException("Closing time cannot be null");
        }
        this.closingTime = closingTime;
    }

    @Override
    public String toString() {
        return "Park [name=" + getName() + "]";
    }
}
