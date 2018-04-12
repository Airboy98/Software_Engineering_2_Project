package backend;

import java.io.File;

import junit.framework.TestCase;

public class BackendTest extends TestCase {

	public void testAvg(){
		String ans = "299.375";
		float Answer = DbManager.frontGetAvg("8/26/2017", "sat");
		String str = String.valueOf(Answer); 
		assertEquals(str,ans);
		//Tests whatMonth, getNumDay, frontGetAvg and GetAvg at the same time
	}
	
	public void testIntoDaily() {
		float value = (float) (1300.45);
		assertTrue(DbManager.IntoDaily("8/25/2017", "fri", value));
	} //tests IntoDaily
	
	public void testDayOfMonth() {
		assertEquals(DbManager.dayofmonth(20),3);
	}//tests dayofmonth
	
	public void testCSVupdate(){
		//Path is unique to each computer
		assertTrue(DbManager.CSVupdate("C:\\Users\\andre\\Documents\\GitHub\\Software2project\\documents\\Deliverable_3\\gross_sale.csv"));
	}//Tests CSVupdate
	
	public void testMonthUpdate() {
		assertTrue(DbManager.MonthUpdate("12/29/2017"));
	}//tests MonthUpdate
	
}
