package vn.tdtu.mad.mywallet;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class StatisticActivity extends AppCompatActivity implements RecyclerViewInterface{

    Button btnFragmentMonth, btnFragmentYear;
    RecyclerView mainRecyclerView;
    StatisticRecyclerAdapter mainRecyclerAdapter;
    static ArrayList<SectionDay> sectionDays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        mainRecyclerView = (RecyclerView) findViewById(R.id.rvStatistics);

        sectionDays = OrderSupporter.updateMonth(MainActivity.transactionList,getIntent().getStringExtra("systemTime"));
        Log.e(TAG,"Statistic Section" + sectionDays.toString());

        mainRecyclerAdapter = new StatisticRecyclerAdapter(sectionDays,this);
        mainRecyclerView.setAdapter(mainRecyclerAdapter);
        mainRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("Pos", String.valueOf(position));
        MonthFragment monthFragment = new MonthFragment();
        monthFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,monthFragment);
        fragmentTransaction.commit();
        Log.e(TAG,"I'm here!!!!!!"+ position);
    }
}