package vn.tdtu.mad.mywallet;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Objects;

import static android.content.ContentValues.TAG;


public class MonthFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    private Button btnShowYear;

    private ArrayList data;
    private int index;

    public MonthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static MonthFragment newInstance(String param1, String param2) {
        MonthFragment fragment = new MonthFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private ArrayList pcData(){
        data = new ArrayList<>();
        double [] array = StatisticActivity.sectionDays.get(index).getTypeStatistic();
        if(array[0]!=0){
            data.add(new PieEntry(new BigDecimal(array[0]).setScale(2, RoundingMode.HALF_UP).floatValue(),"General"));

        }
        if(array[1]!=0) {
            data.add(new PieEntry(new BigDecimal(array[1]).setScale(2, RoundingMode.HALF_UP).floatValue(),"Clothes"));

        }
        if(array[2]!=0) {
            data.add(new PieEntry(new BigDecimal(array[2]).setScale(2, RoundingMode.HALF_UP).floatValue(),"Food"));

        }
        if(array[3]!=0) {
            data.add(new PieEntry(new BigDecimal(array[3]).setScale(2, RoundingMode.HALF_UP).floatValue(),"Insurance"));
        }
        return data;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String position = getArguments().getString("Pos");
        index = Integer.parseInt(position);
        view = inflater.inflate(R.layout.fragment_month, container, false);
        btnShowYear = view.findViewById(R.id.btnShowYear);

        btnShowYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Pos", String.valueOf(position));
                yearFragment yearFragment = new yearFragment();
                yearFragment.setArguments(bundle);
                FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout,yearFragment);
                fragmentTransaction.commit();
                Log.e(TAG, "Fragment month start year");
            }
        });

        PieChart pieChart = view.findViewById(R.id.pcMonth);
        pcData();

        PieDataSet pieDataSet = new PieDataSet(data,"Type of expenses");
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);

        pieDataSet.setValueTextSize(16f);
        pieChart.getDescription().setEnabled(true);
        return view;
    }
}