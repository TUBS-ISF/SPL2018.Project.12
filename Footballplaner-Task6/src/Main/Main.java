package Main;

import planer.*;

public class Main {

	static Gameplaner gp;
	
	public static void main(String[] args) {
		System.out.println("Task6");
		gp=new Gameplaner();
		test();
	}
	
	public static void test() {
		String s1 = "2011.11.11 13:30";
		String s2 = "2009.1.3 12:00>2:1";
		String s3 = "2009.3.3 13:00>2:5";
		String s4 = "2009.3.3 16:00>2:2";
		String s5 = "2009.3.10 13:00>2:1";

		gp.createGame(s1);
		gp.createGame(s2);
		gp.createGame(s3);
		gp.createGame(s4);
		gp.createGame(s5);

		System.out.println(gp.toString());
	}

	public static Gameplaner getGp() {
		return gp;
	}

	public static void setGp(Gameplaner gp) {
		Main.gp = gp;
	}
	
	
}
