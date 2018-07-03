package utilities;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;

import catalogue.Catalogue;
import catalogue_browser_dao.DatabaseManager;
import messages.Messages;
import soap.DetailedSOAPException;
import soap.SOAPError;


/**
 * This class contains static functions and static variables that can be used everywhere in the application
 * @author Valentino
 *
 */

public class GlobalUtil {
	
	private static final String TEMP_DIR_NAME = "temp";
	
	private static String workDir = "";
	
	// directory for the user files as the settings 
	final static public String CONFIG_FILES_DIR_NAME = "config";
	final static public String CONFIG_FILES_DIR_PATH = getConfigDir();
	final static public String CONFIG_FILE = getConfigDir() + "appConfig.xml";
	
	final static public String RESERVE_SCHEMA = getConfigDir() + "reserveSchema.xml";
	final static public String PUBLISH_SCHEMA = getConfigDir() + "publishSchema.xml";
	
	// directory which contains all the pick lists
	final static public String PICKLISTS_DIR_NAME = "picklists";
	final static public String PICKLISTS_DIR_PATH = getPicklistDir();
	
	final static public String PREF_DIR_NAME = "preferences";
	final static public String PREF_DIR_PATH = getPrefDir();

	// directory which contains all the pick lists
	final static public String BUSINESS_RULES_DIR_NAME = "business-rules";
	final static public String BUSINESS_RULES_DIR_PATH = getBusinessRulesDir();

	// the filename of the warning messages and colors
	final static public String WARNING_MESSAGES_FILE = "warningMessages.txt";
	final static public String WARNING_COLORS_FILE = "warningColors.txt";
	
	// the filename of the file which contains the business rule for the describe 
	final static public String BUSINESS_RULES_FILE = "BR_Data.csv";
	
	// the filename of the file which contains the business rule EXCEPTIONS for the describe 
	final static public String BUSINESS_RULES_EX_FILE = "BR_Exceptions.csv";
	
	//AlbyDev: path of the changelog file
	final static public String CHANGELOG_PATH = "config/changelog.txt";
	final static public String FLAG_PATH = "config/flag.txt";

	/**
	 * AlbyDev: Path for the changelog file and the flag which inform the program 
	 * that the release note was read
	 */
	public static String getChangelogPath() {
		return CHANGELOG_PATH;
	}
	
	public static String getFlagPath() {
		return FLAG_PATH;
	}
	
	/**
	 * Set the working directory where the directories should
	 * be searched.
	 * @param path
	 */
	public static void setWorkingDirectory( String path ) {
		workDir = path + System.getProperty( "file.separator" );
		System.setProperty( "user.dir", workDir );
	}
	
	public static String getWorkingDir() {
		return workDir;
	}
	
	public static String getPrefDir() {
		return ( workDir + PREF_DIR_NAME + 
				System.getProperty( "file.separator" ) );
	}
	
	/**
	 * Get the user file directory path
	 * @return
	 */
	public static String getConfigDir() {
		return ( workDir + CONFIG_FILES_DIR_NAME + 
				System.getProperty( "file.separator" ) );
	}
	
	/**
	 * get the business rules directory path
	 * @return
	 */
	public static String getBusinessRulesDir() {
		return ( workDir + BUSINESS_RULES_DIR_NAME + 
				System.getProperty( "file.separator" ) );
	}
	
	/**
	 * Get the business rules filename
	 * @return
	 */
	public static String getBRData() {
		return getBusinessRulesDir() + BUSINESS_RULES_FILE;
	}
	
	/**
	 * Get the business rules exception filename
	 * @return
	 */
	public static String getBRExceptions() {
		return getBusinessRulesDir() + BUSINESS_RULES_EX_FILE;
	}
	
	/**
	 * Get the warning messages filename
	 * @return
	 */
	public static String getBRMessages() {
		return getBusinessRulesDir() + WARNING_MESSAGES_FILE;
	}
	
	/**
	 * Get the warning colors filename
	 * @return
	 */
	public static String getBRColors() {
		return getBusinessRulesDir() + WARNING_COLORS_FILE;
	}
	
	/**
	 * get the picklists directory path
	 * @return
	 */
	public static String getPicklistDir() {
		return ( workDir + PICKLISTS_DIR_NAME + 
				System.getProperty( "file.separator" ) );
	}
	
	/**
	 * get the temporary files directory path
	 * @return
	 */
	public static String getTempDir() {
		return ( workDir + TEMP_DIR_NAME + 
				System.getProperty( "file.separator" ) );
	}
	
	/**
	 * Delete all the temporary files in the {@link #getTempDir()}
	 */
	public static void clearTempDir() {
		File directory = new File( getTempDir() );
		for ( File file : directory.listFiles() ) {
			file.delete();
		}
	}

	
	/**
	 * Check if a file exists or not
	 * @param filename
	 * @return
	 */
	public static boolean fileExists ( String filename ) {
		File check = new File ( filename );
		return ( check.exists() );
	}
	
	/**
	 * Create the application folders if they do not exist
	 */
	public static void createApplicationFolders() {
		
		// create the user files directory
		if ( !fileExists( CONFIG_FILES_DIR_PATH ) ) {
			new File( CONFIG_FILES_DIR_PATH ).mkdir();
		}

		// create the business rules directory
		if ( !fileExists( BUSINESS_RULES_DIR_PATH ) ) {
			new File( BUSINESS_RULES_DIR_PATH ).mkdir();
		}
		
		// create preferences directory
		if ( !fileExists( PREF_DIR_PATH ) ) {
			new File( PREF_DIR_PATH ).mkdir();
		}
		
		// create the temp directory 
		if ( !fileExists( getTempDir() ) ) {
			new File( getTempDir() ).mkdir();
		}
	}
	
	/**
	 * Create a directory in the absolute path
	 * return true is everything went well
	 * @param path
	 */
	public static boolean createDirectory ( String path ) {
		
		File file = new File( path );
		
		// create the directory if it does not exist
		if ( !file.exists() )
			file.mkdir();
		
		return !file.exists();
	}
	
	
	/**
	 * Convert a java.util.date in a java.sql.timestamp, in order to store the information
	 * in a jdbc database
	 * @param date
	 * @return
	 */
	public static java.sql.Timestamp toSQLTimestamp ( Date date ) {
		
		// get the timestamp from the date
		java.sql.Timestamp timestamp = new java.sql.Timestamp( date.getTime() );
		
		// return the timestamp
		return timestamp; 
	}
	
	/**
	 * Convert a java.sql.timestamp into a java.util.date
	 * @param ts
	 * @return
	 */
	public static Date SQLTimestampToDate ( java.sql.Timestamp ts ) {
		Date date = new Date( ts.getTime() );
		return date;
	}
	
	/**
	 * Convert the timestamp to a string (as the DCF date string)
	 * @param ts
	 * @return
	 */
	public static String DCFDateFormat ( java.sql.Timestamp ts ) {
		
		Date date = SQLTimestampToDate ( ts );
		
		// convert the time stamp to string
		DateFormat sdf = new SimpleDateFormat ( 
				Catalogue.ISO_8601_24H_FULL_FORMAT );
		
		return sdf.format( date );
	}
	
	/**
	 * Trasform a date string into a timestamp
	 * @param dateString
	 * @param dateFormat
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp getTimestampFromString ( String dateString, 
			String dateFormat ) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat( dateFormat );
	    Date parsedDate = format.parse( dateString );
	    Timestamp ts = new Timestamp( parsedDate.getTime() );
	    return ts;
	}
	
	
	/**
	 * Open a soap connection
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws SOAPException
	 */
	public static SOAPConnection openSOAPConnection () 
			throws UnsupportedOperationException, SOAPException {
		
		// Connect to the DCF, given username and password
		SOAPConnectionFactory connectionFactory = 
				SOAPConnectionFactory.newInstance();

		// create the soap connection
		return connectionFactory.createConnection();
	}
	
	/**
	 * Copy the source file into the target file
	 * @param source
	 * @param target
	 * @return
	 * @throws IOException 
	 */
	public static void copyFile ( File source, File target ) throws IOException {
		// copy the .start file into the sas folder
		DatabaseManager.copyFolder( source, target );
	}
	
	/**
	 * Delete a folder with all the sub files.
	 * @param directory
	 * @throws IOException
	 */
	public static void deleteFileCascade( String directory ) throws IOException {
		deleteFileCascade( new File( directory ) );
	}
	
	/**
	 * Delete a folder with all the sub files.
	 * @param directory
	 * @throws IOException
	 */
	public static void deleteFileCascade( File directory ) throws IOException {
		
		// delete all the sub files recursively
		if ( directory.isDirectory() ) {
			
			File[] files = directory.listFiles();
			
			// some JVM return null for listfiles
			if ( files == null )
				return;
			
			for ( File file : files ) {
				file.setWritable( true );
				deleteFileCascade( file );
			}
		}

		Files.delete( Paths.get( directory.getAbsolutePath() ) );
		
		// delete the directory
		//if ( !directory.delete() )
		//	throw new FileNotFoundException( "Failed to delete file: " + directory );
	}
	
	public static String[] getSOAPWarning(DetailedSOAPException e) {
		
		String title = null;
		String message = null;
		SOAPError error = e.getError();
		switch(error) {
		case NO_CONNECTION:
			title = Messages.getString("error.title");
			message = Messages.getString("no.connection");
			break;
		case UNAUTHORIZED:
		case FORBIDDEN:
			title = Messages.getString("error.title");
			message = Messages.getString("wrong.credentials");
			break;
		case MESSAGE_SEND_FAILED:
			title = Messages.getString("error.title");
			message = Messages.getString("send.message.failed");
			break;
		}
		
		return new String[] {title, message};
	}
}