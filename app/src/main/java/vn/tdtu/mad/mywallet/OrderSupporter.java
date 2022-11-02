package vn.tdtu.mad.mywallet;

import android.util.Log;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class OrderSupporter {
    public static HashMap<String,ArrayList<Transaction>> update(ArrayList<Transaction> list){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        HashMap<String, ArrayList<Transaction>> multiValueMap = new HashMap<String, ArrayList<Transaction>>();
        String currentDate;
        for(Transaction transaction:list)
        {
            currentDate = simpleDateFormat.format(transaction.getDate());
            if(!multiValueMap.containsKey(currentDate))
            {
                multiValueMap.put(currentDate, new ArrayList<Transaction>());
                multiValueMap.get(currentDate).add(transaction);
            }
            else
            {
                multiValueMap.get(currentDate).add(transaction);
            }
        }
        return multiValueMap;
    }

    public static HashMap<String,ArrayList<Transaction>> updateMonth(ArrayList<Transaction> list){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
        HashMap<String, ArrayList<Transaction>> multiValueMap = new HashMap<String, ArrayList<Transaction>>();
        String currentDate;
        for(Transaction transaction:list)
        {
            currentDate = simpleDateFormat.format(transaction.getDate());
            if(!multiValueMap.containsKey(currentDate))
            {
                multiValueMap.put(currentDate, new ArrayList<Transaction>());
                multiValueMap.get(currentDate).add(transaction);
            }
            else
            {
                multiValueMap.get(currentDate).add(transaction);
            }
        }
        return multiValueMap;
    }

    public static ArrayList<SectionDay> update(ArrayList<Transaction> transactionList, String systemTime) {

        HashMap<String, ArrayList<Transaction>> sortedList = OrderSupporter.update(transactionList);
        ArrayList<SectionDay> newList = new ArrayList<>();

        for (HashMap.Entry<String, ArrayList<Transaction>> set : sortedList.entrySet()) {
            newList.add(new SectionDay(set.getKey(), set.getValue()));

        }
        newList.sort(new SectionDayComperator());

        Log.e(TAG,"Transaction list:" + transactionList.toString());
        if(newList.isEmpty()){
            Log.e(TAG,"Section list is empty");

        }
        Log.e(TAG,"Section list:" + newList.toString());

        Date dateToday, datePrevious, datePrevious2;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd/MM/yyyy");

        try {
            dateToday= new SimpleDateFormat("dd/MM/yyyy").parse(systemTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        calendar.setTime(dateToday);

        calendar.add(Calendar.DATE,-1);
        datePrevious = calendar.getTime();

        calendar.add(Calendar.DATE,-1);
        datePrevious2 = calendar.getTime();

        String previousTime, previousTime2;
        previousTime = simpleDateFormat.format(datePrevious);
        previousTime2 = simpleDateFormat.format(datePrevious2);

        Log.e(TAG,"Times:"+ systemTime + ", "+ previousTime +", "+ previousTime2);

        if(sortedList.containsKey(systemTime)){
            for(int i = 0; i<newList.size();i++){
                if(newList.get(i).getDayName().equals(systemTime)){
                    newList.get(i).setDayName("Today");
                }
            }
        }
        if (sortedList.containsKey(previousTime)) {
            for(int i = 0; i<newList.size();i++){
                if(newList.get(i).getDayName().equals(previousTime)){
                    newList.get(i).setDayName("Previous Day");
                }
            }
        }
        if (sortedList.containsKey(previousTime2)) {
            for(int i = 0; i<newList.size();i++){
                if(newList.get(i).getDayName().equals(previousTime2)){
                    newList.get(i).setDayName("Day Before Yesterday");
                }
            }
        }

        Log.e(TAG,"Updated Section list:" + newList.toString());
        String monthFilter =  systemTime.substring(3,10);
        Log.e(TAG, "Filter Parameter"+ monthFilter);

        String tmp, tmp2;
        for(int i =0;i<newList.size();i++){
            tmp = newList.get(i).getDayName();
            if(tmp.equals("Today")||tmp.equals("Previous Day")||tmp.equals("Day Before Yesterday")){
                continue;
            }
            else {
                tmp2 =tmp.substring(3,10);
                Log.e(TAG, "tmp"+ tmp2);

                if(!tmp2.equals(monthFilter)){
                    newList.remove(i);
                    break;
                }
            }
        }
        return newList;
    }

    public static ArrayList<SectionDay> updateMonth(ArrayList<Transaction> transactionList, String systemTime) {

        HashMap<String, ArrayList<Transaction>> sortedList = OrderSupporter.updateMonth(transactionList);
        ArrayList<SectionDay> newList = new ArrayList<>();

        for (HashMap.Entry<String, ArrayList<Transaction>> set : sortedList.entrySet()) {
            newList.add(new SectionDay(set.getKey(), set.getValue()));

        }
        newList.sort(new SectionMonthComperator());

        Log.e(TAG,"Transaction list:" + transactionList.toString());
        if(newList.isEmpty()){
            Log.e(TAG,"Section list is empty");

        }
        Log.e(TAG,"Section list:" + newList.toString());

        Date dateToday, datePrevious, datePrevious2;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("MM/yyyy");

        try {
            dateToday= new SimpleDateFormat("dd/MM/yyyy").parse(systemTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        calendar.setTime(dateToday);

        calendar.add(Calendar.MONTH,-1);
        datePrevious = calendar.getTime();

        calendar.add(Calendar.MONTH,-1);
        datePrevious2 = calendar.getTime();

        String previousTime, previousTime2;
        previousTime = simpleDateFormat.format(datePrevious);
        previousTime2 = simpleDateFormat.format(datePrevious2);

        Log.e(TAG,"Times:"+ systemTime.substring(3,10) + ", "+ previousTime +", "+ previousTime2);

        if(sortedList.containsKey(systemTime.substring(3,10))){
            for(int i = 0; i<newList.size();i++){
                if(newList.get(i).getDayName().equals(systemTime.substring(3,10))){
                    newList.get(i).setDayName("This month");
                }
            }
        }
        if (sortedList.containsKey(previousTime)) {
            for(int i = 0; i<newList.size();i++){
                if(newList.get(i).getDayName().equals(previousTime)){
                    newList.get(i).setDayName("Previous Month");
                }
            }
        }

        Log.e(TAG,"Updated Section list:" + newList.toString());

        return newList;
    }

    public static double getMonthCost(ArrayList<Transaction> list, String systemTime){
        double monthCost=0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
        String tmp;
        for(Transaction transaction: list){
            tmp = simpleDateFormat.format(transaction.getDate());
            if(tmp.equals(systemTime.substring(3,10))){
                Log.e(TAG,"String result: "+tmp);
                monthCost += transaction.getAmount();
            }
        }
        return monthCost;
    }


}
