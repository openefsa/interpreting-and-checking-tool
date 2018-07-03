package data_transformation;

/**
 * Used to model the catalogue/hierarchy groups field of the catalogue xml
 * And in general to save multiple values into the same excel cell in a 
 * dollar separated way.
 * @author avonva
 *
 */
public class ValuesGrouper {

	private StringBuilder values;  // all the groups dollar separated
	int valuesCount;               // groups counter
	Object data;                   // data for whatever uses
	
	public ValuesGrouper() {
		// initialize the string builder and item count
		values = new StringBuilder();
		valuesCount = 0;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public void reset() {
		// reset content of string builder
		values.setLength(0);
		valuesCount = 0;
		data = null;
	}
	
	/**
	 * Add a new group to the groups in a dollar separated way
	 * @param group
	 */
	public void addValue ( String value ) {
		
		// only if more than one group is present add a dollar separation
		if ( valuesCount > 0 )
			values.append ( "$" );

		// make the groups dollar separated
		values.append ( value );

		// count the groups
		valuesCount++;
	}
	
	/**
	 * Get all the groups as a string. Each group is separated by a $
	 * if multiple groups are present.
	 * @return
	 */
	public String getCompactValues () {
		return values.toString();
	}
	
	/**
	 * Get the number of groups
	 * @return
	 */
	public int getValuesCount() {
		return valuesCount;
	}
	
	public Object getData() {
		return data;
	}
}
