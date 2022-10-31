package vn.tdtu.mad.mywallet;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnMinus, btnStatistic;
    TextView tvCurrentDate, tvMonthCost, tvDayCost;
    RecyclerView mainRecyclerView;
    MainRecyclerAdapter mainRecyclerAdapter;

    ArrayList<Section> sectionList = new ArrayList<>();
    ArrayList<Transaction> transactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.add);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnStatistic = (Button) findViewById(R.id.btbStatistic);

        tvMonthCost = (TextView) findViewById(R.id.tvMonthCost);
        tvCurrentDate = (TextView) findViewById(R.id.tvCurrentDate);
        tvDayCost = (TextView) findViewById(R.id.tvDayCost);

        mainRecyclerView = (RecyclerView) findViewById(R.id.MainRecyclerView);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");
        tvCurrentDate.setText(simpleDateFormat.format(new Date()));

        mainRecyclerAdapter = new MainRecyclerAdapter(sectionList);
        mainRecyclerView.setAdapter(mainRecyclerAdapter);
        mainRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        transactionList = new ArrayList<>();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
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
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                    Date date = simpleDateFormat.parse(timeData);
                    transactionList.add(0, new Transaction(date, Double.parseDouble(amount), transactionTypes));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                HashMap<String, ArrayList<Transaction>> sortedList = OrderSupporter.update(transactionList);
                ArrayList<Section> newList = new ArrayList<>();

                for (HashMap.Entry<String, ArrayList<Transaction>> set : sortedList.entrySet()) {
                    newList.add(new Section(set.getKey(), set.getValue()));
                }
                sectionList = newList;

                mainRecyclerAdapter = new MainRecyclerAdapter(sectionList);
                mainRecyclerView.setAdapter(mainRecyclerAdapter);
                mainRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            }
        }
    }


    public void addButton(View view) {
        Intent intent = new Intent(this, AddTransactionActivity.class);
        startActivityForResult(intent, 1);

    }

    public void getStatistics(View view) {
    }

    public void minus(View view) {

    }
}

