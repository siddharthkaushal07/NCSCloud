import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class Test {
	
	private static final String DATE_FORMATTER= "dd-MM-yyyy";

    
public static void main(String[] args) {
//	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//	Calendar c = Calendar.getInstance();
//	System.out.println(c.DATE);
//	c.setTime(new Date()); // Using today's date
//	System.out.println("todays date"+new Date());
//	c.add(Calendar.DATE, 7); // Adding 5 days
//	String output = sdf.format(c.getTime());
//	System.out.println(output);
//	//System.out.println("localdate= "+LocalDate.now());
//	//System.out.println("after 5 days= "+LocalDate.now().plusDays(5));
//	LocalDate today = LocalDate.now().plusDays(5);
//	
//	String formattedDate = today.format(DateTimeFormatter
//		    .ofLocalizedDate(FormatStyle.SHORT));
//		System.out.println("SHORT format: " + formattedDate);
	
//	 Date date= new Date();
//     //getTime() returns current time in milliseconds
// Long time =  date.getTime();
//     //Passed the milliseconds to constructor of Timestamp class 
// Timestamp ts = new Timestamp(time);
// System.out.println("Current Time Stamp: "+ts);
	
	
	
//	LocalDateTime dtm = LocalDateTime.now();  
//	// Getting the LocalDate representation of the LocalDateTime  
//	// using the toLocalDate() method  
//	System.out.println("The date is: " + dtm.toLocalDate() + " "+ dtm.toLocalTime());  
	
//	String pattern = "hh:mm:ss a";
////	//1. LocalTime
//    LocalTime now = LocalTime.now();
//    System.out.println(now.format(DateTimeFormatter.ofPattern(pattern)));
//
//    //2. LocalDateTime
//    LocalDateTime nowTime = LocalDateTime.now();
//    System.out.println(nowTime);
//    System.out.println(nowTime.format(DateTimeFormatter.ofPattern(pattern)));
//	
	LocalDateTime localDateTime = LocalDateTime.now().plusDays(5); //get current date time
    System.out.println("Current Time " + localDateTime);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
    String formatDateTime = localDateTime.format(formatter);

    System.out.println("Formatted Time :" +formatDateTime);
    
     
}
}
