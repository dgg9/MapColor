package com.map.color;

import java.net.UnknownHostException;
import java.util.Map;

import com.rangesearch.exception.ExceptionMessages;
import com.rangesearch.inetaddress.InetAddressDecorator;
import com.rangesearch.tree.InetAddressTree;
import com.rangesearch.tree.RangeTree;
import com.rangesearch.util.Region;

/**
 * A controller which decides which regions of the map should light up when.
 */
public class MapController {
  RangeTree<InetAddressDecorator> rangeTree;
  //RangeTree<UserExperience> userExp;
  MapView mapView;
  
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
  public MapController(MapView view) {
    this.rangeTree = new InetAddressTree();
    //Create user experience tree
    this.mapView = view;
  }

  /**
   * Update the view to record a hit.
   * 
   * @param ipAddress
   *          an IP address String
   * @param responseTime
   *          the response time, in milli second, experienced by the user with
   *          the given IP address
   * @throws UnknownHostException
   */
  public void hit(String ipAddress, long responseTime) {
    try {
      Region region = rangeTree.query(new InetAddressDecorator(ipAddress));
      this.mapView.lightUp(region, UserExperience.NORMAL);
      
    } catch (UnknownHostException e) {
      System.out.println(ExceptionMessages.INVALID_ADDRESS_TYPE);
      e.printStackTrace();
    }
    
  }
}
