package data_collection;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import catalogue.Catalogue;
import catalogue_browser_dao.CatalogueDAO;
import dcf_manager.Dcf;
import utilities.GlobalUtil;

/**
 * Object which models a data collection
 * @author avonva
 *
 */
public class DataCollection implements IDcfDataCollection {

	private static final Logger LOGGER = LogManager.getLogger(DataCollection.class);
	
	public static final String DATE_FORMAT = "yyyy-MM-ddX";

	private int id = -1;
	private String code;
	private String description;
	private String category;
	private Timestamp activeFrom;
	private Timestamp activeTo;
	private String resourceId;

	public DataCollection() {}
	
	/**
	 * Initialize a data collection (i.e. dc) object
	 * @param code the dc code
	 * @param description the dc description
	 * @param category
	 * @param activeFrom when the dc was started
	 * @param activeTo when the dc will end
	 * @param resourceId the resource linked to the dc (like STX)
	 */
	public DataCollection( String code, String description, 
			String category, Timestamp activeFrom, Timestamp activeTo,
			String resourceId ) {

		this.code = code;
		this.description = description;
		this.category = category;
		this.activeFrom = activeFrom;
		this.activeTo = activeTo;
		this.resourceId = resourceId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	public String getCode() {
		return code;
	}
	public String getDescription() {
		return description;
	}
	public String getCategory() {
		return category;
	}
	public Timestamp getActiveFrom() {
		return activeFrom;
	}
	public Timestamp getActiveTo() {
		return activeTo;
	}
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * Check if the data collection is valid or not
	 * in terms of time validity
	 * @return
	 */
	public boolean isActive() {

		Timestamp today = new Timestamp( System.currentTimeMillis() );

		boolean started = activeFrom.before( today );
		boolean notOver = activeTo.after( today );

		return started && notOver;
	}

	/**
	 * Check if the data collection was already downloaded
	 * or not
	 * @return
	 */
	public boolean alreadyImported() {
		DCDAO dcDao = new DCDAO();
		return dcDao.contains( this );
	}

	/**
	 * Get a timestamp from a timestamp string using
	 * the data collection date format {@value #DATE_FORMAT}
	 * @param value
	 * @return
	 */
	public static Timestamp getTimestampFromString( String value ) {

		Timestamp ts = null;

		// convert the string to timestamp
		try {

			ts = GlobalUtil.getTimestampFromString( 
					value, DATE_FORMAT );
		}
		catch ( ParseException e ) {
			e.printStackTrace();
			LOGGER.error("Cannot parse timestamp=" + value + " using format=" + DATE_FORMAT, e);
		}

		return ts;
	}

	/**
	 * Get all the {@link DCTableConfig} related to this
	 * data collection.
	 * @return
	 */
	public Collection<DCTableConfig> getTableConfigs() {
		DCTableConfigDAO tConfDao = new DCTableConfigDAO();
		return tConfDao.getByDataCollection ( this );
	}

	/**
	 * Get all the catalogues related to this data collection
	 * @return
	 */
	public Collection<Catalogue> getCatalogues() {

		// get all the configurations related to this
		// data collection
		DCTableConfigDAO configDao = new DCTableConfigDAO();
		Collection<DCTableConfig> configs = configDao.getByDataCollection( this );

		Collection<Catalogue> catalogues = new ArrayList<>();

		// check which catalogues need to be downloaded
		for ( DCTableConfig config : configs ) {

			// get the catalogue code related to the config
			String catCode = config.getConfig().getCatalogueCode();

			// if no code skip
			if ( catCode == null )
				continue;

			// get the official catalogue using the
			// catalogue code
			Catalogue catalogue = Dcf.getCatalogueByCode ( catCode );

			// if not found skip
			if ( catalogue == null )
				continue;

			// if already inserted go on (avoid duplicates)
			if ( catalogues.contains( catalogue ) )
				continue;

			// add the catalogue to the out list
			catalogues.add( catalogue );
		}

		return catalogues;
	}

	/**
	 * Get all the catalogues related to this data collection
	 * which were not downloaded yet by the user (i.e. the missing
	 * catalogues)
	 * @return
	 */
	public Collection<Catalogue> getNewCatalogues() {

		Collection<Catalogue> catToDownload = new ArrayList<>();

		// get all the catalogues related to the dc
		Collection<Catalogue> catalogues = getCatalogues();

		// check which catalogues need to be downloaded
		for ( Catalogue cat : catalogues ) {

			// check if the catalogue was already downloaded
			// or not
			CatalogueDAO catDao = new CatalogueDAO();
			boolean present = catDao.contains( cat );

			// if already present go on
			if ( present )
				continue;

			// arrived here? then the catalogue should
			// be downloaded
			catToDownload.add( cat );
		}

		return catToDownload;
	}

	@Override
	public String toString() {
		return "DATA COLLECTION: id=" + (id == -1 ? "not defined yet" : id )
				+ ";code=" + code 
				+ ";description=" + description 
				+ ";category=" + category 
				+ ";activeFrom=" + activeFrom
				+ ";activeTo=" + activeTo
				+ ";resourceId=" + resourceId;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public void setActiveFrom(Timestamp activeFrom) {
		this.activeFrom = activeFrom;
	}

	@Override
	public void setActiveTo(Timestamp activeTo) {
		this.activeTo = activeTo;
	}

	@Override
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
}
