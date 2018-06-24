public class Appearance{
	
	private void commandImport() {
		original();
		gp=Importer.importfromJson();
		System.out.println("Loaded:");
		System.out.println(gp);
	}
	
}