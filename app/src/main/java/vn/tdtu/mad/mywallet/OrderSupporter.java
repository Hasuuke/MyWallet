package vn.tdtu.mad.mywallet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderSupporter {

    public static HashMap<String,ArrayList<Transaction>> update(ArrayList<Transaction> list){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
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
}
