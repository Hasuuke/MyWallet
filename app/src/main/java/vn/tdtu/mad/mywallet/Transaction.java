package vn.tdtu.mad.mywallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class Transaction
{
    private Date date;
    private double amount;
    private TransactionTypes transactionTypes;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");


    public Transaction(Date date, double amount, TransactionTypes transactionTypes) {
        this.date = date;
        this.amount = amount;
        this.transactionTypes = transactionTypes;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date.toString() +
                ", amount=" + amount +
                ", transactionTypes=" + transactionTypes.toString() +
                '}';
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
