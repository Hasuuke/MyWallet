package vn.tdtu.mad.mywallet;

import java.util.Date;

public class Transaction
{
    private Date date;
    private double amount;
    private TransactionTypes transactionTypes;

    public Transaction(Date date, double amount, TransactionTypes transactionTypes) {
        this.date = date;
        this.amount = amount;
        this.transactionTypes = transactionTypes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionTypes() {
        switch(this.transactionTypes){
            case GENERAL:
                return "General";
            case CLOTHES:
                return "Clothes";
            case  FOOD:
                return "Food";
            case INSURANCE:
                return "Insurance";
            default:
                return "General";
        }
    }

    public void setTransactionTypes(TransactionTypes transactionTypes) {
        this.transactionTypes = transactionTypes;
    }
}
