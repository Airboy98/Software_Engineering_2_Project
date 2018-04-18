//import java.io.File;
//import junit.framework.TestCase;
//
//public class FrontendTest extends TestCase {
//
//	passQL pass = new passQL();
//	PasswordENC passenc = new PasswordENC();
//
//	public void testlogin(){
//		String uname = "bhakta";
//		String passw = "mayur";
//		String[] check = pass.CheckPass(uname,passw);
//		assertEquals(check[0],"True");
//		//Tests a user logging in.
//	}
//	public void testloginfail(){
//		String uname = "carolyn";
//		String passw = "wow_wu";
//		String[] check = pass.CheckPass(uname,passw);
//		assertEquals(check[0],null);
//		//Fails test of a user logging in.
//	}
//
//	public void testnewuser(){
//		String uname = "carolyn";
//		String passw = "wu";
//		boolean check = pass.AddUser(uname, passw, "Employee");
//		assertEquals(check,false);
//		//test user creation of user that already exist
//	}
//
//	public void testencryptPass(){
//		String finish = "66 -123 -75 117 -79 -42 -72 13 38 72 -52 -114 113 72 -127 84 ";
//		String before = "wu";
//		byte[] test = passenc.encryptPass(before);
//		String testfin = passenc.byteToString(test);
//		assertEquals(finish, testfin);
//		//test encryption of a password and conversion of a byte array to string
//	}
//
//	public void testdecrypt(){
//		String before = "66 -123 -75 117 -79 -42 -72 13 38 72 -52 -114 113 72 -127 84 ";
//		String finish = "wu";
//		byte[] test = passenc.stringToByte(before);
//		String dec = passenc.decrypt(test);
//		assertEquals(finish,dec);
//	}
//
//
//}
