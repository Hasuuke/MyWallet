package vn.tdtu.mad.mywallet;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {
    Button btnAdd, btnMinus, btnStatistic;
    TextView tvCurrentDate, tvMonthCost;
    RecyclerView mainRecyclerView;
    MainRecyclerAdapter mainRecyclerAdapter;

    ArrayList<SectionDay> sectionDayList = new ArrayList<>();
    static ArrayList<Transaction> transactionList = new ArrayList<>();
    String systemTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnPlus);
        btnStatistic = (Button) findViewById(R.id.btbStatistic);

        tvMonthCost = (TextView) findViewById(R.id.tvMonthCost);
        tvCurrentDate = (TextView) findViewById(R.id.tvCurrentDate);

        mainRecyclerView = (RecyclerView) findViewById(R.id.MainRecyclerView);
        //Time Attributes
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        systemTime = simpleDateFormat.format(new Date());
        Log.e(TAG,"System Time: "+ systemTime);
        tvCurrentDate.setText(systemTime.substring(0,5));

        mainRecyclerAdapter = new MainRecyclerAdapter(sectionDayList, this);
        mainRecyclerView.setAdapter(mainRecyclerAdapter);
        mainRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            //Adding new Transaction Case
            if (resultCode == RESULT_OK) {
                String amount = data.getStringExtra("Amount");
                String type = data.getStringExtra("Spinner");
                String timeData = data.getStringExtra("TimeDate");
                TransactionTypes transactionTypes = TransactionTypes.GENERAL;
                switch (type) {
                    case "General":
                        transactionTypes = TransactionTypes.GENERAL;
                        break;
                    case "Clothes":
                        transactionTypes = TransactionTypes.CLOTHES;
                        break;
                    case "Food":
                        transactionTypes = TransactionTypes.FOOD;

                        break;
                    case "Insurance":
                        transactionTypes = TransactionTypes.INSURANCE;
                        break;
                }

                try {
                    //Adds the new Transaction to the list
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    Date date = simpleDateFormat.parse(timeData);
                    transactionList.add(0, new Transaction(date, Double.parseDouble(amount), transactionTypes));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                sectionDayList = OrderSupporter.update(transactionList,systemTime);
                mainRecyclerAdapter.sectionDayList = sectionDayList;
                mainRecyclerAdapter.notifyDataSetChanged();
                update(transactionList,systemTime);
                Log.e(TAG,"Updated Section list:" + sectionDayList.toString());
                Log.e(TAG,"Transaction list:" + transactionList.toString());

            }
        }else if (requestCode == 2) {
            //Edit Case
            if (resultCode == RESULT_OK) {
                String amount = data.getStringExtra("Amount");
                String type = data.getStringExtra("Spinner");
                String timeData = data.getStringExtra("TimeDate");
                String position = data.getStringExtra("Position");
                TransactionTypes transactionTypes = TransactionTypes.GENERAL;

                switch (type) {
                    case "General":
                        transactionTypes = TransactionTypes.GENERAL;
                        break;
                    case "Clothes":
                        transactionTypes = TransactionTypes.CLOTHES;
                        break;
                    case "Food":
                        transactionTypes = TransactionTypes.FOOD;

                        break;
                    case "Insurance":
                        transactionTypes = TransactionTypes.INSURANCE;
                        break;
                }

                try {
                    //Edits a Transaction in the list
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    Date date = simpleDateFormat.parse(timeData);
                    transactionList.get(Integer.parseInt(position)).setDate(date);
                    transactionList.get(Integer.parseInt(position)).setAmount(Double.parseDouble(amount));
                    transactionList.get(Integer.parseInt(position)).setTransactionTypes(transactionTypes);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                sectionDayList = OrderSupporter.update(transactionList,systemTime);
                mainRecyclerAdapter.sectionDayList = sectionDayList;
                mainRecyclerAdapter.notifyDataSetChanged();
                update(transactionList,systemTime);
                Log.e(TAG,"Updated Section list:" + sectionDayList.toString());
                Log.e(TAG,"Transaction list:" + transactionList.toString());

            }
        }
    }

    public  void update(ArrayList<Transaction> list, String systemTime){
        tvMonthCost.setText(String.valueOf(OrderSupporter.getMonthCost(list,systemTime))+"???");
    }


    public void addButton(View view) {
        Intent intent = new Intent(this, AddTransactionActivity.class);
        startActivityForResult(intent, 1);
    }

    public void getStatistics(View view) {
        Intent intent = new Intent(this, StatisticActivity.class);
        intent.putExtra("systemTime",systemTime);
        startActivity(intent);
    }

    //Interface function for current item position
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, AddTransactionActivity.class);
        intent.putExtra("Position", String.valueOf(position));
        startActivityForResult(intent, 2);
        Log.e(TAG,"Position Interface: "+position);
    }
}

