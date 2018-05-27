package export;

/**
 * Exports for a given Fileformat
 * Interface would be nicer...
 * @author David
 *
 */
public class exporter {

	/**
	 * Export for Enabled feature
	 * If JSON and PDF is checked it Export in both formats
	 * @param gp
	 */
	//#if export
	public static void export(planer.Gameplaner gp) {
		//#if PDF
		PdfExporter.export(gp);
		//#endif
		
		//#if JSON
		JsonExporter.export(gp);
		//#endif
	}
	
	//#endif
}
