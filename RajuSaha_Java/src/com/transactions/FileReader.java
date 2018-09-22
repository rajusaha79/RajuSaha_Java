package com.transactions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileReader {
	
	/** Method to read the csvFile of Position data and create one List of Positions**/
    public static ArrayList<Position> readCsvFileForPosition(String fileName) {

        //create one blank List of Positions
        ArrayList<Position> posList=new ArrayList<Position>();
        
        Scanner scanner=null;
        
        try {
            scanner = new Scanner(new File(fileName));
            scanner.useDelimiter("\n");

            int i=0;//index for the list

            scanner.next(); //to skip the header

            //Traverse through the text line by line
            while (scanner.hasNext()){
                    String nextLine=scanner.next(); //reading the next line

                    String arrStr[] = nextLine.split(","); // reading the data into one array after splitting

                    String instr = arrStr[0];
                    int account = Integer.valueOf(arrStr[1]);
                    String accountType = arrStr[2];
                    long quantity=Double.valueOf(arrStr[3]).longValue();

                    //inserting the object of position into the Position List
                    posList.add(i++,new Position(instr,account,accountType,quantity));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
        	scanner.close();
        }
        
        //return the list of Position
        return posList;
    }
    
    
    /***
     * Method to read the json file and to create one List of transactions
     * ***/
    
    public  static ArrayList<Transaction> readJsonFileForTransaction(String fileName){
       /*** example:
      "TransactionId": 1,
      "Instrument": "IBM",
      "TransactionType": "B",
      "TransactionQuantity": 1000
        ***/

       ArrayList<Transaction> transactionList=new ArrayList<>();

       try {
           Scanner scanner = new Scanner(new File(fileName));
           String jsonString = scanner.useDelimiter("\\A").next();
           scanner.close();

           //adding one key : "Transactions" to the json String
           String jsonStringWithKey = "{Transactions:" + jsonString + "}";
           //System.out.println("jsonString =" + jsonString1);

           JSONObject transactionObj = new JSONObject();

           JSONObject jsonObject = new JSONObject(jsonStringWithKey);

           //Create one Array of Json elements where each element will contain one transaction data
           JSONArray jsonArray = jsonObject.getJSONArray("Transactions");

           //traverse through the elements of the JsonArray
           for (int j = 0; j < jsonArray.length(); j++) {

               transactionObj = jsonArray.getJSONObject(j);

               int transactionId = transactionObj.getInt("TransactionId");
               String transactionInstr = (String) transactionObj.get("Instrument");
               String transactionType = (String) transactionObj.get("TransactionType");
               long transactionQty = transactionObj.getLong("TransactionQuantity");

               //insert the transaction element to the transactionList
               transactionList.add(j,new Transaction(transactionId, transactionInstr, transactionType, transactionQty));

           }

       }
       catch (FileNotFoundException e) {
        e.printStackTrace();
       }
       catch(JSONException e) {
        e.printStackTrace();
       }
       //return the list of transactions
       return  transactionList;
   }

}
