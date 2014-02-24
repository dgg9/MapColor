package com.rangesearch.tree;

import static org.junit.Assert.*;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rangesearch.inetaddress.InetAddressDecorator;
import com.rangesearch.util.Range;
import com.rangesearch.util.Region;

public class TestMapRangeToRegion {

  static Random r = new Random();

  MapRangeToRegion rangeMap;

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testMapExists() {
    rangeMap = new MapRangeToRegion();
    assertNotNull(rangeMap.rangeList);
    assertNotNull(rangeMap.arrKeys);
  }

  @Test
  public void testRangeOrdering() {
    rangeMap = new MapRangeToRegion();
    assertNotNull(rangeMap);
    Iterator<Range<InetAddressDecorator>> itr = rangeMap.arrKeys.iterator();
    assertNotNull(itr);
    while (itr.hasNext()) {
      Range<InetAddressDecorator> range = itr.next();
      assertTrue(range.from.compareTo(range.until) < 0);
    }
  }

  @Test
  public void testRangeSetOrdering() {
    rangeMap = new MapRangeToRegion();
    assertNotNull(rangeMap);
    Iterator<Range<InetAddressDecorator>> itr = rangeMap.arrKeys.iterator();
    assertNotNull(itr);
    Range<InetAddressDecorator> curr = itr.next();
    Range<InetAddressDecorator> next;
    while (itr.hasNext()) {
      next = itr.next();
      assertTrue(curr.until.compareTo(next.from) <= 0);
      curr = next;
    }
  }

  @Test
  public void testQuery() throws UnknownHostException {
    rangeMap = new MapRangeToRegion();
    Region region = rangeMap.query(new InetAddressDecorator(getRandomIp()));
    assertNotNull(region);
  }

  @Test
  public void testQueryFail() throws UnknownHostException {
    rangeMap = new MapRangeToRegion();
    Region region = rangeMap.query(new InetAddressDecorator("300.400.500.600"));
    assertEquals(region.getName(), "UNKOWN REGION");
  }

  static String getRandomIp() {
    return r.nextInt(255) + "." + r.nextInt(255) + "." + r.nextInt(255) + "."
        + r.nextInt(255) + ".";
  }

}
