package vn.tdtu.mad.mywallet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SectionDay  {
    private String dayName;
    private ArrayList<Transaction> transactions;

    public SectionDay(String sectionName, ArrayList<Transaction> transactions){
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

    public int getSize(){
        return this.transactions.size();
    }

    public double[] getTypeStatistic(){
        double general=0, clothes=0, food=0, insurance=0;

        for(Transaction transaction: transactions){
            if(transaction.getTransactionTypes().equals("General")){
                general+= transaction.getAmount();
            }
            else if(transaction.getTransactionTypes().equals("Clothes")){
                clothes+= transaction.getAmount();;
            }
            else if(transaction.getTransactionTypes().equals("Food")){
                food+= transaction.getAmount();
            }
            else if(transaction.getTransactionTypes().equals("Insurance")){
                insurance+= transaction.getAmount();
            }
        }
        return new double[]{general,clothes,food,insurance};
    }

    public int getYear(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        return Integer.parseInt(simpleDateFormat.format(transactions.get(0).getDate()));
    }

    public double getDayCost() {
        double dayCost=0;
        for(Transaction transaction: transactions){
            dayCost+= transaction.getAmount();
        }
        return dayCost;
    }

    @Override
    public String toString() {
        return "Section{" +
                "dayName='" + dayName + '\'' +
                ", transactions=" + transactions.toString() +
                '}';
    }
}
