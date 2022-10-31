package vn.tdtu.mad.mywallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    ArrayList<Day> dayList;

    public MainRecyclerAdapter(ArrayList<Day> dayList) {
        this.dayList = dayList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView monthDate;
        RecyclerView childRecycleView;
        TextView tvDayCost;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            monthDate = itemView.findViewById(R.id.tvSectionName);
            childRecycleView = itemView.findViewById(R.id.childRecycleView);
            tvDayCost = itemView.findViewById(R.id.tvDayCost);
        }
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.section_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Day day = dayList.get(position);
        ArrayList<Transaction> items = day.getTransactions();

        holder.monthDate.setText(day.getDayName());
        holder.tvDayCost.setText(String.valueOf(day.getDayCost()));

        ChildRecycleAdapter childRecycleAdapter =new ChildRecycleAdapter(items);
        holder.childRecycleView.setAdapter(childRecycleAdapter);
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }


}
