package org.machtnichts.csv;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Ignore;

import java.io.*;
import java.nio.file.*;

public class CsvReaderTest{

  @Test
  @Ignore
  public void readerTest() {
    File testFile = Paths.get("src","test","resources","testData.csv").toFile();
    try{
      CsvReader reader = new CsvReader(new FileInputStream(testFile));
      for(String[] tokens: reader){
        assertEquals(6,tokens.length);
        System.out.println("-> "+tokens[0]);
      }
    } catch(IOException ioe )    {
      ioe.printStackTrace();
    }
  }

}