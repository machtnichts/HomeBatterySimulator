package org.machtnichts.csv;

import static org.junit.Assert.*;
import org.junit.Test;


public class CsvAdapterTest{
  @Test
  public void convertToTokensTest() {
    CsvAdapter csvAdapter = new CsvAdapter(",");
    String line = "05.04.2019 13:30,\"0\",\"131.9577\",\"896.5451\",\"1028.5028\",\"896.5452\"";
    String[] tokens = csvAdapter.convertToTokens(line);
    assertEquals(tokens[0],"05.04.2019 13:30");
    assertEquals(tokens[1],"0");
    assertEquals(tokens[2],"131.9577");
    assertEquals(tokens[3],"896.5451");
    assertEquals(tokens[4],"1028.5028");
    assertEquals(tokens[5],"896.5452");
  }
}