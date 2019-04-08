package org.machtnichts.csv;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class CsvAdapter {
  private String separator;
  public String[] convertToTokens(String line){
    String tokens[] = line.split(separator);
    for(int i=0;i<tokens.length;i++){
      tokens[i]=tokens[i].trim();
      if(tokens[i].startsWith("\"") && tokens[i].endsWith("\""))
        tokens[i]=tokens[i].substring(1,tokens[i].length()-1).trim();
    }
    return tokens;
  }
}