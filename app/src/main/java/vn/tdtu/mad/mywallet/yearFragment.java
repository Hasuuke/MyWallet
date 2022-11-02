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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Objects;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link yearFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class yearFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    private PieChart pieChart;
    private Button btnShowMonth;
    private int index;

    public yearFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static yearFragment newInstance(String param1, String param2) {
        yearFragment fragment = new yearFragment();
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

    private ArrayList getPieData(){
        String currentDate = String.valueOf(StatisticActivity.sectionDays.get(index).getYear());
        currentDate = currentDate.substring(currentDate.length()-4);
        Log.e(TAG,"Statistic Current Date: "+ currentDate);
        ArrayList<Double> general, clothes, food, insurance;
        general = new ArrayList<>();
        clothes = new ArrayList<>();
        food = new ArrayList<>();
        insurance = new ArrayList<>();

        ArrayList arrayList = new ArrayList<>();

        for(SectionDay sectionDay:StatisticActivity.sectionDays){
            if(String.valueOf(sectionDay.getYear()).equals(currentDate)){
                Log.e(TAG,"Year Statistic: " +sectionDay.getYear());

                double [] array = sectionDay.getTypeStatistic();
                if(array[0]!=0){
                    general.add(array[0]);
                }
                if(array[1]!=0) {
                    clothes.add(array[1]);
                }
                if(array[2]!=0) {
                    food.add(array[2]);
                }
                if(array[3]!=0) {
                    insurance.add(array[3]);
                }
            }
        }
        if(!general.isEmpty()){
            Double result= (double) 0;
            for(Double item: general){
                result +=item;
            }
            arrayList.add(new PieEntry(result.floatValue(),"General"));
        }
        if(!clothes.isEmpty()){
            Double result= (double) 0;
            for(Double item: clothes){
                result +=item;
            }
            arrayList.add(new PieEntry(result.floatValue(),"Clothes"));
        }
        if(!food.isEmpty()){
            Double result= (double) 0;
            for(Double item: food){
                result +=item;
            }
            arrayList.add(new PieEntry(result.floatValue(),"Food"));
        }
        if(!insurance.isEmpty()){
            Double result= (double) 0;
            for(Double item: insurance){
                result +=item;
            }
            arrayList.add(new PieEntry(result.floatValue(),"Insurance"));
        }
        Log.e(TAG,"Pie Array list: "+arrayList.toString());
        return arrayList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String position = getArguments().getString("Pos");
        index = Integer.parseInt(position);

        view = inflater.inflate(R.layout.fragment_year, container, false);
        pieChart = view.findViewById(R.id.pcYear);
        btnShowMonth = view.findViewById(R.id.btnShowMonth);
        btnShowMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Pos", String.valueOf(position));
                MonthFragment monthFragment = new MonthFragment();
                monthFragment.setArguments(bundle);
                FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout,monthFragment);
                fragmentTransaction.commit();
                Log.e(TAG, "Fragment year start month");
            }
        });

        PieDataSet pieDataSet = new PieDataSet(getPieData(),"Type of expenses in a year");
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);

        pieDataSet.setValueTextSize(16f);
        pieChart.getDescription().setEnabled(true);

        return view;
    }
}