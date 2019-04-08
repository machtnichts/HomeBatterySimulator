package org.machtnichts.csv;

import java.io.*;
import java.util.*;


public class CsvReader implements Iterable<String[]>{

  private BufferedReader reader;
  private CsvAdapter csvAdapter;
  public CsvReader(InputStream is){
    csvAdapter = new CsvAdapter(",");
    try{
      reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
    }catch(UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  public Iterator<String[]> iterator() {
    return new MyIterator();
  }


  class MyIterator implements Iterator<String[]> {

    String line;

    public boolean hasNext() {
      try{
        line = reader.readLine();
      } catch(IOException e) {
        e.printStackTrace();
      }
      return line!=null;
    }

    public String[] next() {
      if(line==null){
        try{
          line = reader.readLine();
        } catch(IOException e) {
          e.printStackTrace();
        }
      }

      if(line==null)
        throw new NoSuchElementException();

      return csvAdapter.convertToTokens(line);
    }

    public void remove() {
        //implement... if supported.
    }
  }

}