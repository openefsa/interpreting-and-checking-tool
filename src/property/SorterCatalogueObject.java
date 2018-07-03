package property;
import java.util.Comparator;

import catalogue_object.SortableCatalogueObject;

/**
 * Simply class Sorter for sorting SimpleProperties, used in
 * ComboViewer(selectionCombo).
 * 
 * @author
 * 
 */
public class SorterCatalogueObject  implements Comparator<SortableCatalogueObject> {

	@Override
	public int compare(SortableCatalogueObject t1, SortableCatalogueObject t2) {
		
		int cmps = 0;
		
		if ( t1.getOrder() < t2.getOrder() )
			cmps = -1;
		else if ( t1.getOrder() == t2.getOrder() )
			cmps = 0;
		else if ( t1.getOrder() > t2.getOrder() )
			cmps = 1;
		return cmps;
	}
}
