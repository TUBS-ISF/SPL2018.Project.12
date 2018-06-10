package Main;

import java.util.List;

import interfaces.IGame;
import interfaces.appearance;
import interfaces.export;
import interfaces.importer;
import interfaces.planer;
import interfaces.calendars;
import loader.PluginLoader;

public class FootballPlaner {
	static planer gp;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<IGame> g = PluginLoader.load(IGame.class);
		List<planer> p = PluginLoader.load(planer.class);
		List<appearance> ap = PluginLoader.load(appearance.class);
		List<export> ex = PluginLoader.load(export.class);
		List<importer> im = PluginLoader.load(importer.class);
		List<calendars> cal=PluginLoader.load(calendars.class);
		
		//p.get(0).setGames(null);
		//gp=p.get(0).createPlaner(g.size(), g);..
		gp=p.get(0);
		gp.setIGame(g);
		testGames();
		if(cal !=null && cal.size()>0) {
			System.out.println(cal.get(0).createCalendar(2009, 3, gp).toString());
		}
		testImporter(im.get(0),g);
		testExporter(ex);
		
		ap.get(0).setImporter(im.get(0));
		ap.get(0).setExporter(ex);
		ap.get(0).setPlaner(gp);
		ap.get(0).setIGame(g);
		ap.get(0).run();//
	}

	/**
	 * Creates test data
	 */
	public static void testGames() {
		String s1 = "2011.11.11 13:30";
		String s2 = "2009.1.3 12:00>2:1";
		String s3 = "2009.3.3 13:00>2:5";
		String s4 = "2009.3.3 16:00>2:2";
		String s5 = "2009.3.10 13:00>2:1";
		String s6 = "2009.3.10 13:00>2:2";

		gp.createGame(s1);
		gp.createGame(s2);
		gp.createGame(s3);
		gp.createGame(s4);
		gp.createGame(s5);
		gp.createGame(s6);

		System.out.println(gp.toString());
	}

	public static void testExporter(List<export> l) {
		for (export e :l) {
			e.exportPlaner(gp);
		}
	}
	
	public static void testImporter(importer i,List<IGame> g) {
		gp=i.importfrom(gp,g);
		System.out.println(gp.toString());
	}
	/// **
	// * creates a calendar for march 2009
	// */
	// public static void testCalendar() {
	// planer.calendar march2009 = new planer.calendar(2009, 3, gp);
	// System.out.println("\n Calendar: \n" + march2009);
	// }

}
