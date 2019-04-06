package org.machtnichts.csv;

import java.io.*;
import java.util.*;


public class CsvReader implements Iterable<String[]>{

  private BufferedReader reader;
  public CsvReader(InputStream is){
    try{
      reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
    }catch(UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  public Iterator<String[]> iterator() {
    return new MyIterator();
  }

  public static String[] convertToTokens(String line){
    String tokens[] = line.split(",");
    for(int i=0;i<tokens.length;i++){
      tokens[i]=tokens[i].trim();
      if(tokens[i].startsWith("\"") && tokens[i].endsWith("\""))
        tokens[i]=tokens[i].substring(1,tokens[i].length()-1).trim();
    }
    return tokens;
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

      return convertToTokens(line);
    }

    public void remove() {
        //implement... if supported.
    }
  }

}