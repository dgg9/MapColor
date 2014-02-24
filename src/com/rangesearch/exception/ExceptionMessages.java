package com.rangesearch.exception;

/**
 * This class is a placeholder for ExceptionHandling mechanisms. This can be
 * replaced with a custom exception handler that will take specified action when
 * a particular type of exception happens.
 * 
 * @author gayash
 * 
 */
public final class ExceptionMessages {

  public static final String FILE_NOT_FOUND = "Input file not found in the given path. Verify file exists and is readable.";
  public static final String INVALID_INPUT_FORMAT = "Ensure that each line is of the format: [low],[high],[data]";
  public static final String INVALID_INPUT_TYPE = "Ensure that [low] and [high] correspond to valid ip address";
  public static final String INVALID_INPUT_RANGE = "Ensure that [low] is always lesser than or equal to [high]";
  public static final String DUPLICATE_ENTRY = "Ensure that each value of [data] has a unique range [low],[high]";
  public static final String INVALID_ADDRESS_TYPE = "Error retrieving IP address from given input: ";
  public static final String INVALID_IP_INPUT = "Given IP is not found in the IP address list";
};
