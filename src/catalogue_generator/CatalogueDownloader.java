package catalogue_generator;

import catalogue.Catalogue;

/**
 * Thread used to download a catalogue in background. If needed,
 * a progress bar can be set using {@link #setProgressBar(FormProgressBar)}.
 * If you need to perform actions when the download is finished, specify them
 * in the {@link ThreadFinishedListener} using the
 * {@link #setDoneListener(ThreadFinishedListener)} method.
 * @author avonva
 *
 */
public class CatalogueDownloader extends Thread {

	private Catalogue catalogue;
	private boolean finished;
	
	/**
	 * Download and import in the application database 
	 * the selected {@code catalogue}
	 * @param catalogue
	 */
	public CatalogueDownloader( Catalogue catalogue ) {
		this.catalogue = catalogue;
		finished = false;
	}
	
	/**
	 * Listener called when the thread finishes its work
	 * @param doneListener
	 */
	public void setDoneListener(ThreadFinishedListener doneListener) {
	}
	
	/**
	 * Check if the thread has finished its work or not
	 * @return
	 */
	public boolean isFinished() {
		return finished;
	}
	
	/**
	 * Get the catalogue which is being downloaded
	 * @return
	 */
	public Catalogue getCatalogue() {
		return catalogue;
	};
}
