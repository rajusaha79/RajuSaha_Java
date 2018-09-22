package com.transactions;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.Math;
import java.util.ArrayList;

public class TransactionProcessing {

    /*
     * The input data is stored in C:/test directory
     * The output data will be stored in C:/test directory
     */

   public static void main(String args[]){

       /*Reading the json file and getting the Transaction List*/
        final String fileName="C:/test/Input_Transactions.txt";
        ArrayList<Transaction> transactions=FileReader.readJsonFileForTransaction(fileName);
        
        /*Reading the csv File and getting the Start Position List*/
        final String csvFileName="C:/test/Input_StartOfDay_Positions.txt";
        ArrayList<Position> posList=FileReader.readCsvFileForPosition(csvFileName);

        final String outputFileName="C:/test/EndOfDay_Positions.txt";

        /*Creating new ArrayList for New Position information*/
        ArrayList<EndPosition> endPosList=new ArrayList<>();

        int i=0; // index for the endPosList

        /*parse through the Position data*/

        for(Position pos: posList){
           long oldQuantity=pos.getQuantity();
           //double newQuantity=0.0;

                //2nd for loop to traverse through the transactions and to find the matching one
                for(Transaction transaction : transactions){
                    if(transaction.getInstrument().equals(pos.getInstrument())) {
                        //Following is the business logic
                    if (transaction.getTransactionType().equals("B")) {
                        if (pos.getAccountType().equals("E"))
                            pos.setQuantity(pos.getQuantity()+transaction.getTransactionQuantity());

                        if (pos.getAccountType().equals("I"))
                            pos.setQuantity(pos.getQuantity()-transaction.getTransactionQuantity());

                    }

                    if (transaction.getTransactionType().equals("S")) {
                        if (pos.getAccountType().equals("E"))
                            pos.setQuantity(pos.getQuantity()-transaction.getTransactionQuantity());

                        if (pos.getAccountType().equals("I"))
                            pos.setQuantity(pos.getQuantity()+transaction.getTransactionQuantity());
                    }

                }//end of if
            }//end of 2nd for loop

           endPosList.add(i++,new EndPosition(pos.getInstrument(),pos.getAccount(),pos.getAccountType(),
                          pos.getQuantity(),(pos.getQuantity()-oldQuantity)));
        }//end of 1st for loop


       //print the new endPosList
       for(EndPosition pos: endPosList){
           System.out.println(pos.getInstrument()+" "+pos.getAccount()+" "+
                              pos.getAccountType()+" "+pos.getQuantity()+" "+pos.getDelta());
       }

       /*Write the data to the text file in C:/test/ directory */
       TextFileWriter.writeDataToTextFile(endPosList,outputFileName);

       /*Answering the queries*/
       List<String> listStr=findMaxAndMinTransactionInstrument(endPosList);
       String maxInstr=listStr.get(0).toString();
       String minInstr=listStr.get(1).toString();
       System.out.println("Instrument With Max Transaction Volumne : " +maxInstr);
       System.out.println("Instrument with Min Transaction Volumne :" +minInstr);


    }//end of main(String args[]) method

    
    /**
     * Method is to find the maximum and minimum transaction volume and the corresponding instrument
     */

   public static List<String> findMaxAndMinTransactionInstrument(ArrayList<EndPosition> pos){

       ArrayList<Long> listDelta=new ArrayList<Long>(); //for listing the absolute values of the delta
       HashMap<Long,String> mapDeltaVsInstrument=new HashMap<>();//creating key value pair
                                                                 //key : delta(absolute) , value: instrument

       //traverse through the input list and get the absolute values in a list
       for(EndPosition pos1: pos){
          listDelta.add(Math.abs(pos1.getDelta()));
          mapDeltaVsInstrument.put(Math.abs(pos1.getDelta()),pos1.getInstrument());
       }

      
       //listDelta.forEach(delta->{System.out.println(delta);});

       listDelta.stream().distinct().collect(Collectors.toList());

       //find the maximum and minimum transactions from the list
       Long maxTransaction=Collections.max(listDelta);
       Long minTransaction=Collections.min(listDelta);

       //find the corresponding instruments
       String instrumentMax=mapDeltaVsInstrument.get(maxTransaction);
       String instrumentMin=mapDeltaVsInstrument.get(minTransaction);

       //System.out.println("Max one is : "+maxTransaction+" max Instrument:"+instrumentMax+ "min :"+instrumentMin);
       return Arrays.asList(instrumentMax,instrumentMin);
   }


}

