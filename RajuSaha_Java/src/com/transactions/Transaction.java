package com.transactions;


/*Class for Transaction data*/

class Transaction{
      private int transactionId;
      private String instrument;
      private String transactionType;
      private long transactionQuantity;

      public Transaction(int trId,String trInstr,String trType,long trQty){
          this.transactionId=trId;
          this.instrument=trInstr;
          this.transactionType=trType;
          this.transactionQuantity=trQty;
      }

      public int getTransactionId(){
          return  this.transactionId;
      }

      public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
      }

      public String getInstrument() {
        return instrument;
      }

      public void setInstrument(String instrument) {
        this.instrument = instrument;
      }

      public String getTransactionType() {
        return transactionType;
      }

      public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
      }

      public long getTransactionQuantity() {
        return transactionQuantity;
      }

      public void setTransactionQuantity(long transactionQuantity) {
        this.transactionQuantity = transactionQuantity;
      }
}

