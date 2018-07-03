package data_transformation;

/**
 * Class to manage String which represents booleans
 * @author avonva
 *
 */
public class BooleanConverter {

	/**
	 * Return true if the string value is either true or 1, otherwise false.
	 * @param value
	 * @return
	 */
	public static boolean getBoolean( String value ) {
		if ( value.equals("true") || value.equals("1") || value.equals("YES") )
			return true;
		else return false;
	}
	
	/**
	 * Convert a numeric boolean (1/0) into a textual boolean (true/false).
	 * @param value
	 * @return
	 */
	public static String toTextBoolean ( String value ) {
		
		String booleanValue = null;
		
		switch ( value ) {
		case "1":
			booleanValue = "true"; break;
		case "0":
			booleanValue = "false"; break;
		default:
			booleanValue = value; break;
		}
		
		return booleanValue;
	}
	
	/**
	 * Convert a textual boolean (true/false) into a numerical boolean (1/0).
	 * @param value
	 * @return
	 */
	public static String toNumericBoolean ( String value ) {

		String booleanValue = null;
		
		switch ( value ) {
		case "true":
			booleanValue = "1"; break;
		case "false":
			booleanValue = "0"; break;
		default:
			booleanValue = value; break;
		}
		
		return booleanValue;
	}
	
	/**
	 * Check if the value is equal to the target value. Return a numerical boolean
	 * @param value
	 * @param target
	 * @return
	 */
	public static String equals( String value, String target ) {
		
		if ( value.equalsIgnoreCase( target ) )
			return "1";
		else
			return "0";
	}
}
