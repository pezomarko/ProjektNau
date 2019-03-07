package hr.home;

import java.time.LocalDate;

public class IntervalDatuma {
	private LocalDate startDate;
	private LocalDate endDate;
		
	public IntervalDatuma(LocalDate startDate, LocalDate endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;

	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public boolean isInInterval(LocalDate date) {
		if(date.isAfter(startDate.minusDays(1)) && date.isBefore(endDate.plusDays(1))) {
			return true;
		}
		return false;
	}
}
