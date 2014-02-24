package com.rangesearch.tree;

import static com.rangesearch.exception.ExceptionMessages.INVALID_INPUT_TYPE;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.rangesearch.exception.ExceptionMessages;
import com.rangesearch.inetaddress.InetAddressDecorator;
import com.rangesearch.util.Range;
import com.rangesearch.util.Region;
import com.rangesearch.util.RegionImpl;

public class InetAddressTree implements RangeTree<InetAddressDecorator> {

  private static String INPUT_FILE_PATH = "IP";

  private LinkedHashMap<Range<InetAddressDecorator>, Region> rangeList = new LinkedHashMap<Range<InetAddressDecorator>, Region>();
  private List<Range<InetAddressDecorator>> arrKeys;

  public InetAddressTree() {
    Set<String> boundarySet = parseInput(INPUT_FILE_PATH);
    this.rangeList = buildMap(boundarySet);
    this.arrKeys = new ArrayList<Range<InetAddressDecorator>>(
        this.rangeList.keySet());
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
   * The given input is of the form: [low],[high],[data]. This indicates that
   * [data] is bounded by [low] (inclusive) and [high] (exclusive)
   * 
   * The output is a Set of containing elements of the form [ipAddr]:[data]
   * 
   * @return
   */
  public Set<String> parseInput(String inputFile) {
    Set<String> regions = null;
    Set<String> boundaries = null;
    Scanner sc = null;
    try {
      sc = new Scanner(new File(inputFile));
      regions = new TreeSet<String>();
      boundaries = new TreeSet<String>();
      while (sc.hasNext()) {
        /*
         * Each line in the input is of the format low,high,data. This indicates
         * that [data] is bounded by [low] and [high] inclusive
         */
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
          boundaries.add(low.getLong() + SET_VALUE_SEPARATOR + value);
          boundaries.add(high.getLong() + SET_VALUE_SEPARATOR + value);
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

  public LinkedHashMap<Range<InetAddressDecorator>, Region> buildMap(Set<String> boundarySet) {
    /*
     * We create a mapping of range nodes to the regions
     */
    LinkedHashMap<Range<InetAddressDecorator>, Region> rangeList = new LinkedHashMap<Range<InetAddressDecorator>, Region>();

    Object[] array = boundarySet.toArray();

    /*
     * The list [elementsInRange] has the list of all elements that can occur in
     * the current range being processed.
     */
    List<String> elementsInRange = new ArrayList<String>();
    /*
     * The list [processedElements] contains the list of all elements that have
     * been processed completely. These values should be removed from the
     * working set.
     */
    Set<String> processedElements = new HashSet<String>();

    /*
     * We process the set 2 elements at a time to create sub-ranges. So we start
     * at the beginning and process till the penultimate element
     * 
     * Each element in the set of the form [boundary]:[value].
     */
    String[] currElement, nextElement;

    String currElementValue, nextElementValue;
    Long lowerBound, upperBound;

    // Initializing the current element value and the lower bound
    currElement = ((String) array[0]).split(SET_VALUE_SEPARATOR);
    lowerBound = Long.parseLong(currElement[0]);
    currElementValue = currElement[1];

    for (int i = 1; i < array.length; i++) {
      // Initializing the next element value and the upper bound
      nextElement = ((String) array[i]).split(SET_VALUE_SEPARATOR);
      upperBound = Long.parseLong(nextElement[0]);
      nextElementValue = nextElement[1];

      /*
       * Add the current element to the list of elements in range only if it
       * does not exist and is not processed. This signifies that the element
       * has not yet been encountered for the current range and hence, it's
       * lower bound will be set.
       */
      if (!elementsInRange.contains(currElementValue)
          && !processedElements.contains(currElementValue)) {
        elementsInRange.add(currElementValue);
      }

      /*
       * Add the next element to the processed elements list if it has already
       * occurred once in the elements in range. This indicates that for the
       * given element - we have already encountered both the lower bound and
       * the upper bound - so it should be considered as processed.
       */
      if (elementsInRange.contains(nextElementValue)) {
        processedElements.add(nextElementValue);
      }

      /*
       * Update the data structure with the newly created range and the elements
       * in the range.
       */
      try {
        InetAddressDecorator lowerLimit = new InetAddressDecorator(lowerBound);

        InetAddressDecorator upperLimit = new InetAddressDecorator(upperBound);

        rangeList.put(new Range<InetAddressDecorator>(lowerLimit, upperLimit),
            new RegionImpl(elementsInRange));
      } catch (UnknownHostException e) {
        System.out.println(e.getMessage() + " : "
            + ExceptionMessages.INVALID_ADDRESS_TYPE);
      }
      /*
       * After updating the datastructure, we should remove all processed
       * elements from the list of elements in current range.
       */
      elementsInRange.removeAll(processedElements);

      /*
       * We continue the iteration by updating the current element value and the
       * lower bound
       */
      currElementValue = nextElementValue;
      lowerBound = upperBound;
    }

    return rangeList;
  }

  public void printRangeMap() {
    for (Map.Entry<Range<InetAddressDecorator>, Region> entry : this.rangeList
        .entrySet()) {
      System.out.println("From " + entry.getKey().from.getHost() + " To "
          + entry.getKey().until.getHost() + " has regions : "
          + entry.getValue().getName());
    }
  }

  /**
   * Searches through the keys for a specific value.
   * 
   * @param key
   *          the search key
   * @return ; null if not present
   */
  public Region query(InetAddressDecorator ip) {
    int lo = 0;
    int hi = rangeList.size() - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (arrKeys.get(mid).inRange(ip) < 0)
        lo = mid + 1;
      else if (arrKeys.get(mid).inRange(ip) > 0)
        hi = mid - 1;
      else
        return rangeList.get(arrKeys.get(mid));
    }
    System.out.println(ExceptionMessages.INVALID_IP_INPUT);
    return new RegionImpl("");
  }
}
