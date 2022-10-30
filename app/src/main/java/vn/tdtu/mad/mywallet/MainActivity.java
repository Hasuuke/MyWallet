package vn.tdtu.mad.mywallet;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements AddDialog.AddDialogListener {
    ArrayList<Transaction> transactionList;
    Button add, minus, statistic;
    WalletAdapter adapter;
    TextView tvCurrentDate, tvMonthCost, tvDayCost;

    RecyclerView mainRecyclerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //RecyclerView rvContacts = (RecyclerView) findViewById(R.id.MainRecyclerView);
        add = (Button) findViewById(R.id.add);
        minus = (Button) findViewById(R.id.minus);
        statistic = (Button) findViewById(R.id.statistic);

        tvMonthCost = (TextView) findViewById(R.id.tvMonthCost);
        tvCurrentDate = (TextView) findViewById(R.id.tvCurrentDate);
        tvDayCost = (TextView) findViewById(R.id.tvDayCost);


        transactionList = new ArrayList<>();
//        adapter = new WalletAdapter(transactionList);
//        rvContacts.setAdapter(adapter);
//        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");
        tvCurrentDate.setText(simpleDateFormat.format(new Date()));

        initData();
        mainRecyclerView = (RecyclerView) findViewById(R.id.MainRecyclerView);
        MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(sectionList);
        mainRecyclerView.setAdapter(mainRecyclerAdapter);
        mainRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    List<Section> sectionList = new ArrayList<>();

    private void initData(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");



        String sectionOneName = simpleDateFormat.format(date);
        List<String> sectionOneItems = new ArrayList<>();
        sectionOneItems.add("Test");
        sectionOneItems.add("Whatever");
        sectionOneItems.add("Why not");

        date.setTime(date.getTime() + (1000 * 60 * 60 * 24));
        String sectionTwoName = simpleDateFormat.format(date);
        List<String> sectionTwoItems = new ArrayList<>();
        sectionTwoItems.add("Test");
        sectionTwoItems.add("i dont know");
        sectionTwoItems.add("sup");


        date.setTime(date.getTime() + (1000 * 60 * 60 * 24));
        String sectionThreeName = simpleDateFormat.format(date);
        List<String> sectionThreeItems = new ArrayList<>();
        sectionThreeItems.add("Test");
        sectionThreeItems.add("wuzz");
        sectionThreeItems.add("bruuhh");



        sectionList.add(new Section(sectionOneName,sectionOneItems));
        sectionList.add(new Section(sectionTwoName,sectionTwoItems));
        sectionList.add(new Section(sectionThreeName,sectionThreeItems));

        Log.e(TAG,"initData:" + sectionList.toString());

    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String amount = data.getStringExtra("Amount");
                String type = data.getStringExtra("Spinner");
                String timeData = data.getStringExtra("TimeDate");
                TransactionTypes transactionTypes = TransactionTypes.GENERAL;

                switch(type){
                    case "General":
                        transactionTypes = TransactionTypes.GENERAL;
                        break;
                    case "Clothes":
                        transactionTypes = TransactionTypes.CLOTHES;
                        break;
                    case  "Food":
                        transactionTypes = TransactionTypes.FOOD;

                        break;
                    case "Insurance":
                        transactionTypes = TransactionTypes.INSURANCE;
                        break;
                }


                Log.e(TAG,"OK");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd HH:mm");
                Date date = null;
                try {
                    date = simpleDateFormat.parse(timeData);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                transactionList.add(0, new Transaction(date, Float.parseFloat(amount), transactionTypes));
                adapter.notifyItemInserted(0);

            }
        }
    }


    public void addButton(View view) {
        /*Intent intent = new Intent(this, AddTransactionActivity.class);
        startActivityForResult(intent, 1);*/

    }

    public void getStatistics(View view) {
    }

    public void minus(View view) {
    }

    @Override
    public void applyTexts(Float amount, String type, Date date) {

        transactionList.add(0, new Transaction(date, amount, TransactionTypes.GENERAL));
        adapter.notifyItemInserted(0);

    }
}