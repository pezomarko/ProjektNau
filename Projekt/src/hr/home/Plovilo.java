package hr.home;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Plovilo {
	private String csvRegex = ";";
	private static List<IntervalDatuma> intervali = new ArrayList<>();
	private Long id;
	private String name;
	private Long year;
	private List<Double> cjenik = new ArrayList<Double>();
	
	
	
	public Plovilo(Long id, String name, Long year) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
	}
	
	public Plovilo(String line) {
		String[] elementi = line.split(csvRegex);
		this.id = Long.valueOf(elementi[0]);
		this.name = elementi[1];
		this.year = Long.valueOf(elementi[2]);
		for(int i = 3; i<elementi.length;i++) {
			cjenik.add(Double.parseDouble(elementi[i]));
		}
	}

	public Double getCijena(LocalDate date) throws Exception {
		return cjenik.get(Plovilo.getIntervalId(date));
	}
	
	public static int getIntervalId(LocalDate date) throws Exception {
		for(IntervalDatuma interval : intervali) {
			if(interval.isInInterval(date)) {
				return intervali.indexOf(interval);
			}
		}
		throw new Exception();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public List<Double> getCjenik() {
		return cjenik;
	}
	public void setCjenik(List<Double> cjenik) {
		this.cjenik = cjenik;
	}

	public static List<IntervalDatuma> getIntervali() {
		return intervali;
	}

	public static void setIntervali(List<IntervalDatuma> intervali) {
		Plovilo.intervali = intervali;
	}
	public static void addInterval(IntervalDatuma interval){
		intervali.add(interval);
	}
	
}
