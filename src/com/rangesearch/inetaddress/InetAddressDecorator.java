package com.rangesearch.inetaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Decorator class to perform manipulations on ipv4 address to perform
 * comparisons
 * 
 * @author Reference: http://www.hawkee.com/snippet/9731/
 * 
 */
public class InetAddressDecorator implements Comparable<InetAddressDecorator> {

  private InetAddress iNetAddress;
  private Long iNetValue;
  private String ip;

  /**
   * Returns the long format of the provided IP address.
   * 
   * @param ipAddress
   *          the IP address
   * @return the long format of <code>ipAddress</code>
   * @throws IllegalArgumentException
   *           if <code>ipAddress</code> is invalid
   */
  private static Long toNumeric(String ipAddress) {
    if (ipAddress == null || ipAddress.isEmpty()) {
      throw new IllegalArgumentException("ip address cannot be null or empty");
    }
    String[] octets = ipAddress.split(java.util.regex.Pattern.quote("."));
    if (octets.length != 4) {
      throw new IllegalArgumentException("invalid ip address");
    }
    long ip = 0;
    for (int i = 3; i >= 0; i--) {
      long octet = Long.parseLong(octets[3 - i]);
      if (octet > 255 || octet < 0) {
        throw new IllegalArgumentException("invalid ip address");
      }
      ip |= octet << (i * 8);
    }
    return ip;
  }

  /**
   * Returns the 32bit dotted format of the provided long ip.
   * 
   * @param ip
   *          the long ip
   * @return the 32bit dotted format of <code>ip</code>
   * @throws IllegalArgumentException
   *           if <code>ip</code> is invalid
   */
  public static String toString(long ip) {
    // if ip is bigger than 255.255.255.255 or smaller than 0.0.0.0
    if (ip > 4294967295l || ip < 0) {
      throw new IllegalArgumentException("invalid ip");
    }
    StringBuilder ipAddress = new StringBuilder();
    for (int i = 3; i >= 0; i--) {
      int shift = i * 8;
      ipAddress.append((ip & (0xff << shift)) >> shift);
      if (i > 0) {
        ipAddress.append(".");
      }
    }
    return ipAddress.toString();
  }

  public InetAddressDecorator(String ip) throws UnknownHostException {
    this.ip = ip;
    this.iNetAddress = InetAddress.getByName(ip);
    this.iNetValue = toNumeric(ip);
  }

  public InetAddressDecorator(Long ipValue) throws UnknownHostException {
    this.iNetValue = ipValue;
    this.ip = toString(ipValue);
    this.iNetAddress = InetAddress.getByName(ip);
  }

  /**
   * Compares current object for given object
   * 
   * @param value
   * 
   * @return -1 if current object is less than given object
   * @return 0 if current object equals given object
   * @return 1 if current object is greater than given object
   */
  @Override
  public int compareTo(InetAddressDecorator value) {
    if (this.iNetValue.equals(value.iNetValue)) {
      return 0;
    } else if (this.iNetValue < value.iNetValue) {
      return -1;
    }
    return 1;
  }

  public String getName() {
    return this.ip;
  }

  public Long getLong() {
    return this.iNetValue;
  }

  public String toString() {
    return this.iNetAddress.getHostAddress();
  }

}
