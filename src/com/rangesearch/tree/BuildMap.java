package com.rangesearch.tree;

public interface BuildMap<K extends Comparable<K>, V> {
  public static String INPUT_VALUE_SEPARATOR = ",";

  /** 
   * Builds the map after traversing input file.
   * The File is stored at the project level and 
   * is consumed internally by the BuildMap implementations.
   */
  public void buildMap();

  /**
   * Builds the printRangeMap, is a helper code for 
   * debugging purposes. Currently prints to the standard out
   * could be moved to a logger file.  
   */
  public void printRangeMap();

  /**
   * Searches through the keys for a specific value.
   * 
   * @param key
   *          the search key
   * @return ; null if not present
   */
  public V query(K ip) ;
}
