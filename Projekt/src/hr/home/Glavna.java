package hr.home;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Glavna {

	public static void main(String[] args) {
		ArrayList<Plovilo> plovila = new ArrayList<>();
		String csvFile = "cjenik.csv";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String csvRegex = ";";
        Long regexCount;
        String line = "";
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	line = br.readLine();
        	regexCount = line.chars().filter(c -> c == csvRegex.charAt(0)).count();
        	String[] intervaliDatuma = line.split(csvRegex);
        	
        	for(String s : intervaliDatuma) {
        		if(!s.isEmpty()) {
        			IntervalDatuma id = new IntervalDatuma(LocalDate.parse(s.split("-")[0],dtf), LocalDate.parse(s.split("-")[1], dtf));
        			Plovilo.addInterval(id);
        		}
        	}
            while ((line = br.readLine()) != null) {
            	if(line.chars().filter(c -> c == csvRegex.charAt(0)).count() == regexCount)
            		plovila.add(new Plovilo(line));
            }

        } catch (IOException e) {
        	System.out.println("Unable to read " +csvFile +" , make sure it's in same directory as .jar");
            e.printStackTrace();
        }
        try {
        	int brojDana = Integer.parseInt(args[2]);
        	LocalDate datum = LocalDate.parse(args[1], dtf);
    		Double ukupnaCijena = 0.0;
    		
        	for(; brojDana>0; brojDana--){
        		ukupnaCijena += plovila.stream().filter(p -> p.getId().equals(Long.parseLong(args[0]))).findFirst().get().getCijena(datum)/7;
        		datum = datum.plusDays(1);
        	}
			System.out.println(String.format("%.2f", ukupnaCijena));
		} catch (Exception e) {
			System.out.println("Invalid argument or date is outside of acceptable interval");
			e.printStackTrace();
		}
	}

}
