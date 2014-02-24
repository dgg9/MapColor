package com.rangesearch.tree;

import java.util.LinkedHashMap;
import java.util.Set;

import com.rangesearch.util.Range;
import com.rangesearch.util.Region;

public interface RangeTree<T extends Comparable<T>> {

	public static String INPUT_VALUE_SEPARATOR = ",";
	public static String SET_VALUE_SEPARATOR = ":";


	public LinkedHashMap<Range<T>, Region> buildMap(Set<String> boundarySet);

	
	public void printRangeMap();
	
	public Region query(T input);

}
