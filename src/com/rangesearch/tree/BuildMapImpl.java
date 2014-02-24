package com.rangesearch.tree;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.rangesearch.util.Range;

public abstract class BuildMapImpl<K extends Comparable<K>, V> implements BuildMap<K, V> {
  
  void printRangeMap(LinkedHashMap<Range<K>, V> rangeList) {
    for (Map.Entry<Range<K>, V> entry : rangeList.entrySet()) {
      System.out.println("From " + entry.getKey().from.toString() + " To "
          + entry.getKey().until.toString() + " has regions : "
          + entry.getValue());
    }
  }
	
  V query(K key, LinkedHashMap<Range<K>, V> rangeList, List<Range<K>> arrKeys) {
    int lo = 0;
    int hi = rangeList.size() - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (arrKeys.get(mid).inRange(key) < 0)
        lo = mid + 1;
      else if (arrKeys.get(mid).inRange(key) > 0)
        hi = mid - 1;
      else
        return rangeList.get(arrKeys.get(mid));
    }
    return null;
  }
}
