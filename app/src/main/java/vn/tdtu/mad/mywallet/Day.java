package vn.tdtu.mad.mywallet;

import java.util.ArrayList;

public class Day {
    private String dayName;
    private ArrayList<Transaction> transactions;


    public double getDayCost() {
        double dayCost=0;
        for(Transaction transaction: transactions){
            dayCost+= transaction.getAmount();
        }
        return dayCost;
    }


    public Day(String sectionName, ArrayList<Transaction> transactions){
        this.dayName = sectionName;
        this.transactions = transactions;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }


    @Override
    public String toString() {
        return "Section{" +
                "dayName='" + dayName + '\'' +
                ", transactions=" + transactions.toString() +
                '}';
    }


}
