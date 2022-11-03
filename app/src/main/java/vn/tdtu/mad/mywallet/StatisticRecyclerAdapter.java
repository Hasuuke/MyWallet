package vn.tdtu.mad.mywallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StatisticRecyclerAdapter extends RecyclerView.Adapter<StatisticRecyclerAdapter.ViewHolder> {
    ArrayList<SectionDay> sectionDayList;
    private final RecyclerViewInterface recyclerViewInterface;

    public StatisticRecyclerAdapter(ArrayList<SectionDay> sectionDayList, RecyclerViewInterface recyclerViewInterface) {
        this.sectionDayList = sectionDayList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder{
        TextView monthDate;
        RecyclerView childRecycleView;
        TextView tvDayCost;

        public ViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface){
            super(itemView);

            monthDate = itemView.findViewById(R.id.tvSectionName);
            childRecycleView = itemView.findViewById(R.id.childRecycleView);
            tvDayCost = itemView.findViewById(R.id.tvDayCost);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface !=null){
                        int pos = getAdapterPosition();
                        if(pos !=RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.section_row,parent,false);
        return new ViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        SectionDay sectionDay = sectionDayList.get(position);
        ArrayList<Transaction> items = sectionDay.getTransactions();

        holder.monthDate.setText(sectionDay.getDayName());
        holder.tvDayCost.setText("Month Cost: " + String.valueOf(sectionDay.getDayCost()));

        statisticChildRecycleAdapter childRecycleAdapter =new statisticChildRecycleAdapter(items,recyclerViewInterface);
        holder.childRecycleView.setAdapter(childRecycleAdapter);
    }

    @Override
    public int getItemCount() {
        return sectionDayList.size();
    }
}
