package Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AutoComplete {

	public static List<String> getData(String query) {

		List<String> matched = new ArrayList<String>();
		Collection<String> prefixColl = CreateTST.trieObj.autoComplete(query.toLowerCase());

		matched.addAll(prefixColl);

		return matched;
	}

}
