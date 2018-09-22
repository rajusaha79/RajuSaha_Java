package com.transactions;
class Position{
    private String instr ;
    private int account ;
    private String accountType ;
    private long quantity;

    public  Position(String instr,int acc,String accountType,long quantity){
        this.instr=instr;
        this.account=acc;
        this.accountType=accountType;
        this.quantity=quantity;

    }
    public String getInstrument(){
       return  this.instr;
    }

    public void setInstrument(String instr) {
        this.instr = instr;
    }

    public  int getAccount(){
        return  this.account;
    }
    public void setAccount(int account){
        this.account=account;
    }

    public String getAccountType(){
        return  this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public long getQuantity(){
        return  this.quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
