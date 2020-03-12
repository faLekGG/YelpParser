package com.varteq.constants;

import lombok.experimental.UtilityClass;

/**
 * INITIAL_STEP here begins from 980 because yelp give an access max to 50th page
 * further the pages don't exist.
 * LAST_STEP is the first page from yelp for parsing (because we begin from the 50th page)
 * STEP is 20 because specific url that signals to yelp how to make paging
 */
@UtilityClass
public class OtherConfigurationsOfApp {
  public static final int NUMBER_OF_THREADS = 1;
  public static final int INITIAL_STEP = 980;
  public static final int LAST_STEP = 0;
  public static final int STEP = 20;
}
