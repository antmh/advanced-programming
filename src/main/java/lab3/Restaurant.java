package lab3;

import java.time.LocalTime;

public class Restaurant extends Location implements Visitable, Payable, Classifiable {
	private LocalTime openingTime;
	private LocalTime closingTime;
	private double price;
	private int rank;

	@Override
	public LocalTime getOpeningTime() {
		return openingTime;
	}

	@Override
	public LocalTime getClosingTime() {
		return closingTime;
	}

	@Override
	public int getRank() {
		return rank;
	}

	@Override
	public double getPrice() {
		return price;
	}

	public void setOpeningTime(LocalTime openingTime) {
		if (openingTime == null) {
			throw new IllegalArgumentException("Opening time cannot be null");
		}
		this.openingTime = openingTime;
	}

	public void setClosingTime(LocalTime closingTime) {
		if (closingTime == null) {
			throw new IllegalArgumentException("Closing time cannot be null");
		}
		this.closingTime = closingTime;
	}

	public void setPrice(double price) {
		if (price < 0) {
			throw new IllegalArgumentException("Price cannot be negative");
		}
		this.price = price;
	}

	public void setRank(int rank) {
		if (rank < 0) {
			throw new IllegalArgumentException("Rank cannot be negative");
		}
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Restaurant [name=" + getName() + "]";
	}
}
