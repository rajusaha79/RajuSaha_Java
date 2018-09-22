package com.transactions;


/*
* Class for endPosition after transactions
* This is the Child class of Position
*
* */
class EndPosition extends  Position{

    long delta;

    public  EndPosition(String instr,int acc,String accountType,long quantity,long delta){
        super(instr,acc,accountType,quantity);
        this.delta=delta;

    }

    public long getDelta() {
        return delta;
    }

    public void setDelta(long delta) {
        this.delta = delta;
    }
}


