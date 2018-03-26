import java.util.ArrayList;

public class threadcontrol extends Thread{
	
	public void run() {
		testing blah=new testing();
		boolean worked = false;
		String days[] = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
		ArrayList<dailyavg> hold = new ArrayList<dailyavg>();
		hold = blah.filllist();
		String months[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		String monthname[]= {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","decm"};
		Runtime r=Runtime.getRuntime();
		float total = 0;
		int count = 0;
			for (int x = 0; x < 12; x++) {
				r.gc();
				for (int z = 1; z <= 5; z++)
					for (int y = 0; y <= 6; y++) {
						float avg= blah.GetAvg(months[x],z+days[y]);
						dailyavg temp=hold.get((z*(y+1))-1);
						worked=blah.UpdateOneDay(monthname[x],avg,z+days[y],temp.grosssales);
					}			}	
}
	
}