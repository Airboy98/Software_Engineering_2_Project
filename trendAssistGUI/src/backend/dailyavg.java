package backend;

public class dailyavg {
	public float grosssales=0;
	public String day=new String();
	
}





// TODO Auto-generated method stub



//testing blah=new testing();
//boolean worked = false;
//String days[] = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
//ArrayList<dailyavg> hold = new ArrayList<dailyavg>();
//hold = blah.filllist();
//String months[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
//String monthname[]= {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","decm"};
//Runtime r=Runtime.getRuntime();
//float total = 0;
//int count = 0;
//	for (int x = 0; x < 12; x++) {
//		r.gc();
//		for (int z = 1; z <= 5; z++)
//			for (int y = 0; y <= 6; y++) {
//				float avg= blah.GetAvg(months[x],z+days[y]);
//				dailyavg temp=hold.get((z*(y+1))-1);
//				worked=blah.UpdateOneDay(monthname[x],avg,z+days[y],temp.grosssales);
//			}			}
//System.out.println(worked);

//  Scanner scanner;
//try {
//	//array1 position are 0=date 1=grosssales 2=day of week
//	String current = null;
//	int DayOfYearByWeek;
//	String DayOfMonth;
//	scanner = new Scanner(new File("C:\\Users\\cadew\\Documents\\GitHub\\Software2project\\DataImport.csv"));
//	//scanner.useDelimiter(",");
//	scanner.next();
//    while(scanner.hasNext()){
//    	current=scanner.next()+",";
//    	String[] array1 = current.split(",");
//    	String[] day=array1[0].split("/");
//    	int dayval=Integer.parseInt(day[1]);
//    	DayOfMonth=dayofmonth(dayval)+array1[2];
//    	DayOfYearByWeek=dayofmonth(dayval)*Integer.parseInt(day[0]);
//    	float grossales=Float.parseFloat(array1[1]);
//    	boolean ok = IntoDaily(array1[0],array1[2],Integer.toString(DayOfYearByWeek),DayOfMonth,grossales);
//    }
//    scanner.close();
//} catch (FileNotFoundException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
