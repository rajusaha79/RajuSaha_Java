package com.transactions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TextFileWriter {
	
	/*Method to write Arraylist data into a text file*/
    public static void writeDataToTextFile(ArrayList<EndPosition> endPosList,String fileName){

       BufferedWriter bw=null;

       String fileHeader = "Instrument,Account,AccountType,Quantity,Delta";
       try {
           bw = new BufferedWriter(new FileWriter(fileName));
           bw.write(fileHeader);

           for(EndPosition endPos: endPosList){
               bw.newLine();
               String line=endPos.getInstrument()+","+endPos.getAccount()+","+endPos.getAccountType()+","+
                           endPos.getQuantity()+ ","+endPos.getDelta();
               bw.write(line);
           }
       }
       catch (IOException e){
           e.printStackTrace();
       }
       finally {
           try{
              bw.flush();
              bw.close();
           }
           catch (IOException e){
               e.printStackTrace();
           }
       }

   }
}
