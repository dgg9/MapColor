package com.rangesearch.tree;

import static com.rangesearch.exception.ExceptionMessages.INVALID_INPUT_TYPE;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.rangesearch.exception.ExceptionMessages;
import com.rangesearch.inetaddress.InetAddressDecorator;
import com.rangesearch.util.Range;
import com.rangesearch.util.Region;
import com.rangesearch.util.RegionImpl;

public class MapRangeToRegion extends
    BuildMapImpl<InetAddressDecorator, Region> {

  private static String INPUT_FILE_PATH = "IP_25";

  LinkedHashMap<Range<InetAddressDecorator>, Region> rangeList = new LinkedHashMap<Range<InetAddressDecorator>, Region>();
  List<Range<InetAddressDecorator>> arrKeys = null;

  public MapRangeToRegion() {
    this.buildMap();
    this.arrKeys = new ArrayList<Range<InetAddressDecorator>>(
        this.rangeList.keySet());
  }

  public void buildMap() {
    Set<Data> boundarySet = parseInput(INPUT_FILE_PATH);
    this.rangeList = buildMap(boundarySet);
  }

  /**
   * This method does the following:
   * 
   * 1) Parse input given in text format to valid the input.
   * 
   * 2) Form the set of boundary ip addresses for each location.
   * 
   * Validation of input ensures:
   * 
   * 1) The given file exists.
   * 
   * 2) The given file has the entries in the expected format.
   * 
   * 3) The given file does NOT have duplicating entries.
   * 
   * Each line of the given input is of the form: [low],[high],[data]. This
   * indicates that [data] is bounded by [low] (inclusive) and [high]
   * (exclusive)
   * 
   * 
   * The output is a Set of containing elements of the form
   * [boundaryValue]:[data]
   * 
   * @return
   */
  Set<Data> parseInput(String inputFile) {
    Set<String> regions = null;
    Set<Data> boundaries = null;
    Scanner sc = null;
    try {
      sc = new Scanner(new File(inputFile));
      regions = new HashSet<String>();
      boundaries = new TreeSet<Data>();
      while (sc.hasNext()) {

        String line = sc.nextLine();
        if (!line.startsWith("#")) {

          String[] args = line.split(INPUT_VALUE_SEPARATOR);
          if (args.length != 3) {
            throw new Exception(ExceptionMessages.INVALID_INPUT_FORMAT);
          }

          String value = args[2];
          if (regions.contains(value)) {
            throw new Exception(ExceptionMessages.DUPLICATE_ENTRY);
          }
          regions.add(value);

          InetAddressDecorator low = new InetAddressDecorator(args[0]);
          InetAddressDecorator high = new InetAddressDecorator(args[1]);
          if (low.toString().isEmpty() || high.toString().isEmpty()) {
            throw new Exception(ExceptionMessages.INVALID_INPUT_TYPE);
          }
          if (low.compareTo(high) > 0) {
            throw new Exception(ExceptionMessages.INVALID_INPUT_RANGE);
          }
          boundaries.add(new Data(low.getLong(),value));
          boundaries.add(new Data(high.getLong(), value));
        }
      }

    } catch (UnknownHostException e) {
      System.out.println("Exception encountered: " + INVALID_INPUT_TYPE);
    } catch (Exception e) {
      System.out.println("Exception encountered: " + e.getMessage());
    } finally {
      if (sc != null)
        sc.close();
    }

    return boundaries;
  }
  
  private class Data implements Comparable<Data> {
    Long ip;
    String value;
    
    Data(Long bigInteger, String value) {
      this.ip = bigInteger;
      this.value = value;
    }
    
    @Override
    public int compareTo(Data o) {
      return ip.compareTo(o.ip);
    }
  }

  /**
   * This method parses through the boundary set and creates ranges based on
   * adjacent values. Since the input to this method is always a sorted set, the
   * ranges created will be ordered.
   * 
   * @param boundarySet
   * @return
   */
  LinkedHashMap<Range<InetAddressDecorator>, Region> buildMap(Set<Data> boundarySet) {

    // We create a mapping of range nodes to the regions
    LinkedHashMap<Range<InetAddressDecorator>, Region> rangeList = new LinkedHashMap<Range<InetAddressDecorator>, Region>();

    //Object[] array = boundarySet.toArray();

    // The list [elementsInRange] has the list of all elements that can occur in
    // the current range being processed.

    List<String> elementsInRange = new ArrayList<String>();

    // The list [processedElements] contains the list of all elements that have
    // been processed completely. These values should be removed from the
    // working set.

    Set<String> processedElements = new HashSet<String>();

    Iterator<Data> iter = boundarySet.iterator();
    Data curr,next;
    if((iter != null) && iter.hasNext()){
      curr = iter.next();
      while(iter.hasNext()) {
          next = iter.next();
    
          if (!elementsInRange.contains(curr.value)
              && !processedElements.contains(curr.value)) {
            elementsInRange.add(curr.value);
          }
        
          // Add the next element to the processed elements list if it has already
          // occurred once in the elements in range. This indicates that for the
          // given element - we have already encountered both the lower bound and
          // the upper bound - so it should be considered as processed.
          if (elementsInRange.contains(next.value)) {
            processedElements.add(next.value);
          }
          
          // Update the data structure with the newly created range and the elements
          // in the range.
          try {
            InetAddressDecorator lowerLimit = new InetAddressDecorator(curr.ip);
            InetAddressDecorator upperLimit = new InetAddressDecorator(next.ip);
    
            rangeList.put(new Range<InetAddressDecorator>(lowerLimit, upperLimit),
                new RegionImpl(elementsInRange));
          } catch (UnknownHostException e) {
            System.out.println(ExceptionMessages.INVALID_ADDRESS_TYPE);
          }
    
          // After updating the datastructure, we should remove all processed
          // elements from the list of elements in current range.
          elementsInRange.removeAll(processedElements);

          // We continue the iteration by updating the current element value and the
          // lower bound
          curr = next;
      }
    }
    return rangeList;
  }


  /**
   * Helper method to print the data structure - can be extended to use logger
   * in an enterprise setting.
   */
  public void printRangeMap() {
    super.printRangeMap(this.rangeList);
  }

  /**
   * Helper method to identify the region associated with a given data value
   */
  public Region query(InetAddressDecorator ip) {
    return query(ip, this.rangeList, this.arrKeys);
  }

  @Override
  Region query(InetAddressDecorator ip,
      LinkedHashMap<Range<InetAddressDecorator>, Region> rangeList,
      List<Range<InetAddressDecorator>> arrKeys) {
    Region region = super.query(ip, rangeList, arrKeys);
    if (region == null) {
      System.out.println(ExceptionMessages.INVALID_IP_INPUT);
      return new RegionImpl("UNKOWN REGION");
    }
    return region;
  }
}
