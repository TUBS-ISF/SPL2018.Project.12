package interfaces;

import java.util.List;

public interface appearance {

	public void run();
	public void setPlaner(planer p);
	
	public void setImporter(importer im);
	public void setExporter(List<export> ex);
	public void setIGame(List<IGame> g);
	public void setCalendars(List<calendars> c);
}
