package com.map.color;

import java.net.Inet4Address;
import java.util.Map;

/**
 * A controller which decides which regions of the map should light up when.
 */
public class MapController {
  /**
   * Concurrent data structure which has a map between range objects and Regions
   * This data structure will get updated by multiple agents via the
   * {@link #hit(String, long) hit}
   */
  Map<Range<Inet4Address>, Region> regions;

  /**
   * Maps UserExperiences to different time durations. This is the rules based
   * on which we provide results.
   */
  Map<Range<Long>, UserExperience> userExperiences;

  /**
   * Reference to a MapView object providing the {@link com.map.color.MapView}
   */
  MapView view;

  /**
   * Represents a range of some ordered type.
   */
  public static class Range<T> {
    public final T from;
    public final T until;

    public Range(T from, T until) {
      this.from = from;
      this.until = until;
    }
  }

  /**
   * @param regions
   *          mapping of IP address ranges to regions on the map. The ranges
   *          include both the from and until IP addresses.
   * 
   * @param userExperiences
   *          mapping of response time (in milliseconds) ranges to user
   *          experience categories. The ranges include the the time, but not
   *          the until time.
   * 
   * @param view
   *          the MapView which needs to be updated
   */
  public MapController(RangeBalancedTree tree, 
                       Map<Range<Long>, UserExperience> userExperiences, 
                       MapView view) 
  {
    throw new UnsupportedOperationException("Needs to be implemented");
  }

  /**
   * Update the view to record a hit.
   * 
   * @param ipAddress
   *          an IP address String
   * @param responseTime
   *          the response time, in milli second, experienced by the user with
   *          the given IP address
   */
  public void hit(String ipAddress, long responseTime) {
    throw new UnsupportedOperationException("Needs to be implemented");
  }
}
