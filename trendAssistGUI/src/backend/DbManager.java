package backend;

import frontend.DBconnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class DbManager {

	static private Statement st = DBconnection.getstsa();
	static private Connection con=DBconnection.getconsa();

	// updates one day in the months database with name month ex jan
	// high order is most recent data where as loworder is previous
	// if the month table is empty then call filllist in order to populate an
	// average for the scenario, should only need to be used on first run
	private static boolean UpdateOneDay(String month, float highorder, String day, float loworder) {
		try {
			String query1 = "INSERT INTO " + month
					+ " SET DayOfMonth=?,AvgGrossSales=? ON DUPLICATE KEY UPDATE AvgGrossSales = VALUES(AvgGrossSales)";
			PreparedStatement ps = con.prepareStatement(query1);
			ps.setString(1, day);
			ps.setFloat(2, ((highorder) * 3 + loworder) / 4);
			System.out.println(month + " " + day + " " + ((highorder) * 3 + loworder) / 4);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	// Retrieves avg for that day of the particular month
	//month is the first three letters of the month name in lowercase
	//day is the first three letters of the name of the day with which occurrence in the month it is at the beginning
	private static float GetAvg(String month, String day) {
		float total = 0;
		int count = 0;
		String query = "SELECT * FROM " + month + " WHERE DayOfMonth LIKE '" + day + "'";
		try {
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				total += rs.getFloat("AvgGrossSales");
				count++;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		if (count == 0)
			return 0;
		//System.out.println(total/count);
		return total / count;

	}

	//finds and formats the day properly 
	//ex returns 1fri
	public static String getNumDay(String date, String day1){
		String day = formatday(day1);
		String[] dayof = date.split("/");
		day = dayofmonth(Integer.parseInt(dayof[1]))+ day;
		return day;
	}

	//calls the getavg function with the strings formatted the proper way
	//date is the date in format MM/DD/YYYY
	//day1 is the name of the day being requested.This is not case sensitive the day does need to be spelled correctly though
	public static float frontGetAvg(String date, String day1) {
		String month= WhatMonth(date);
		String day = getNumDay(date, day1);
		//System.out.println(day + " " + month);
		//System.out.println(GetAvg(month,day));
		return GetAvg(month,day);
	}

	//finds the month in which the date is in
	//pass date in format MM/DD/YYYY
	public static String WhatMonth(String date) {
		String monthname[]= {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","decm"};
		String[] day = date.split("/");
		return monthname[Integer.parseInt(day[0])-1];
	}

	//formats day properly
	//pass the name of the day being requested properly
	private static String formatday(String day) {
		String correct=day.toLowerCase();
		return correct.substring(0,3);
	}

	// fills arraylist for local use
	// fills the arralist with the unweighed average of the day of the year
	private static ArrayList<dailyavg> filllist() {
		ArrayList<dailyavg> hold = new ArrayList<dailyavg>();
		for (int x = 1; x <= 5; x++)
			for (int y = 1; y <= 7; y++) {
				String days[] = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
				float total = 0;
				float fst=0;
				int count = 0;
				int first = 0;
				String query = "SELECT * FROM dailyinformation WHERE DayOfMonth = '" + x + days[y - 1] + "'";
				System.out.println(x+days[y-1]);
				try {
					ResultSet rs = st.executeQuery(query);
//					if (first == 0){
//						rs.next();
//						fst=rs.getFloat("GrossSales");
//						first++;
//					}
//					else
					while (rs.next()) {
						total = rs.getFloat("GrossSales");
						System.out.println(total);
						count++;
					}
					dailyavg temp = new dailyavg();
					if (count != 0) {
						temp.day = x + days[y - 1];
						temp.grosssales = (total / count);
						//temp.grosssales = (temp.grosssales + fst * 3)/4;
					} else {
						temp.day = x + days[y - 1];
						temp.grosssales = 0;
					}
					
					//System.out.println(temp.grosssales);
					hold.add(temp);
					//System.out.println(temp.grosssales);
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		return hold;
	}

	// find which day of the month it is ie: 1mon 2mon
	// pass the day in format DD must be parsed from format MM/DD/YYYY
	public static int dayofmonth(int day) {
		if (day <= 7)
			return 1;
		if (day <= 14)
			return 2;
		if (day <= 22)
			return 3;
		if (day <= 29)
			return 4;
		return 5;
	}

	// inserts into dailyinformation table
	//date is formated as MM/DD/YYYY
	public static boolean IntoDaily(String date, String dayofweek, float GrossSales) {
		String dayofmonth= getNumDay(date,dayofweek) + formatday(dayofweek);
		String array[]=date.split("/");
		int temp = Integer.parseInt(array[0]) * dayofmonth(Integer.parseInt(array[1]));
		String dayofyear= Integer.toString(temp);
		
		if (GrossSales == 0) return false;
		try {
			String query = "INSERT INTO dailyinformation SET Date=?,DayOfWeek=?,DayOfYearByWeek=?,DayOfMonth=?,GrossSales=? ON DUPLICATE KEY UPDATE DayOfWeek=VALUES(DayOfWeek),DayOfYearByWeek=VALUES(DayOfYearByWeek),DayOfMonth=VALUES(DayOfMonth),GrossSales=VALUES(GrossSales)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, date);
			ps.setString(2, formatday(dayofweek));
			ps.setString(3, dayofyear);
			ps.setString(4, dayofmonth.substring(0, 4));
			ps.setDouble(5, GrossSales);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			//System.out.println("Hello");
			return false;
		}
	}

	// reads the csv file with given pathway and updates daily information table
	// pass the pathway to the csv file in a string format
	public static boolean CSVupdate(String path) {
		Scanner scanner;
		try {
			String current = null;
			int DayOfYearByWeek;
			int count=1;
			String DayOfMonth;
			scanner = new Scanner(new File(path));
			// scanner.useDelimiter(",");
			scanner.next();
			while (scanner.hasNext()) {
				current = scanner.next() + ",";
				System.out.println(current);
				String[] array1 = current.split(",");
				String[] day = array1[0].split("/");
				int dayval = Integer.parseInt(day[1]);
				DayOfMonth = dayofmonth(dayval) + array1[2];
				DayOfYearByWeek = dayofmonth(dayval) * Integer.parseInt(day[0]);
				System.out.println(array1[1]);
				float grossales = Float.parseFloat(array1[1]);
				boolean ok = IntoDaily(array1[0], array1[2], grossales);
			}
			scanner.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	//updates an entire month for the new data input
	public static boolean MonthUpdate(String date) {
		String month=WhatMonth(date);
		String days[] = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
		ArrayList<dailyavg> hold = new ArrayList<dailyavg>();
		hold = DbManager.filllist();
		//String months[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		float total = 0;
		int count = 0;
		float checker = 0;
		
		for (int z = 1; z <= 5; z++)
			for (int y = 0; y <= 6; y++) {
				float avg= DbManager.GetAvg(month,z+days[y]);
				String query = "SELECT * FROM dailyinformation WHERE Date LIKE '"+month + "%' AND DayOfMonth = '" + z+days[y]+"'";
				try{
					avg= DbManager.GetAvg(month,z+days[y]);
					ResultSet rs=st.executeQuery(query);
					if (rs.next())
					checker=rs.getFloat("GrossSales");
					if (checker > 0)
						avg = checker;
				}catch (SQLException e){
					System.out.println(e);
					return false;
					
				}
				dailyavg temp=hold.get((z*(y+1))-1);
				System.out.println(temp.grosssales);
				DbManager.UpdateOneDay(month,avg,z+days[y],avg);
	}
	return true;	
	}
	
	//todo: update every year
	
}

