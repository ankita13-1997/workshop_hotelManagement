import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class HotelReservation {
static long totalDays,totalWeekDays,totalWeekEndDays;




	static Scanner input = new Scanner(System.in);
	
	public ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
	

	public void addHotel(String customerType) {
		Hotel hotel1 = null;
		Hotel hotel2 = null;
		Hotel hotel3 = null;
		try {
			
			if(customerType.equals("Regular")) {
				hotel1 = new Hotel("LakeWood", 110, 90, 4, "Regular");
				hotel2 = new Hotel("BridgeWood", 150, 50, 3, "Regular");
				hotel3 = new Hotel("RidgeWood" , 220, 150, 5, "Regular");
			}
			else if(customerType.equals("Reward")){
				hotel1 = new Hotel("LakeWood", 80, 80, 4, "Reward");
				hotel2 = new Hotel("BridgeWood", 110, 50, 3, "Reward");
				hotel3 = new Hotel("RidgeWood" , 100, 40, 5, "Reward");
			}
			else {
				throw new InvalidEntryException("Invalid Customer Type");
			}
		}
		catch(InvalidEntryException exception) {
			System.out.println(exception);
		}
		hotelList.add(hotel1);
		hotelList.add(hotel2);
		hotelList.add(hotel3);
		
		System.out.println(hotelList.toString());
	}
	/**
	 * Get the list of hotels
	 * @return
	 */
	public ArrayList<Hotel> getHotelList() {
		return hotelList;
	}
	public void setHotelList(ArrayList<Hotel> hotelList) {
		this.hotelList = hotelList;
	}
	
	
	public void getInput(String date1,String date2) {
		LocalDate[] localDate = new LocalDate[2];
		
		//Split the string to get the dates
		String[] dates = date1.split(":|,");
		String[] dates2 = date2.split(":|,");
		date1 = dates[0];
		date2= dates2[0];
		for(int iteration = 0; iteration<1 ; iteration++) {
			//Convert dates to standard format
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy", Locale.ENGLISH);
				LocalDate date = LocalDate.parse(dates[iteration], formatter);
				localDate[iteration-1] = date;
			}
			catch(DateTimeException exception) {
				System.out.println("Invalid Date Entry");
				exception.printStackTrace();
				
			}
		}
		LocalDate start = localDate[0];
		LocalDate end = localDate[1];
		totalDays = ChronoUnit.DAYS.between(start, end);
		totalDays = totalDays + 1;
		totalWeekEndDays = getTotalWeekEndDays(start, end); 
		totalWeekDays = totalDays - totalWeekEndDays;
		System.out.println(totalWeekDays);
	}
	
	public int getTotalWeekEndDays(LocalDate start, LocalDate end) {
		long weekEndDays = 0;
		LocalDate next = start.minusDays(1);
		//iterate from start date to end date
		while ((next = next.plusDays(1)).isBefore(end.plusDays(1))) {
			if(next.getDayOfWeek().toString().equals("SATURDAY") || next.getDayOfWeek().toString().equals("SUNDAY")) {
				totalWeekEndDays++;
			}
		}
		return (int)totalWeekEndDays;	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("*******************Welcome to Hotel Reservation Program*******************");
		System.out.println("enter the type of customer you are");
		String customerType=input.next();
		HotelReservation hotel = new HotelReservation();
		hotel.addHotel(customerType);
		
		String date1= "10/02/2021";
		String date2="11/03/2021";
		
		hotel.getInput(date1, date2);
		
		
		input.close();
		
	}

}
