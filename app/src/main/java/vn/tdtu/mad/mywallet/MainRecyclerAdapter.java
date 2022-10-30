package vn.tdtu.mad.mywallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    List<Section> sectionList;

    public MainRecyclerAdapter(List<Section> sectionList) {
        this.sectionList = sectionList;
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

        Section section = sectionList.get(position);
        String sectionName = section.getSectionName();
        List<String> items = section.getSectionsItems();

        holder.monthDate.setText(sectionName);
        ChildRecycleAdapter childRecycleAdapter =new ChildRecycleAdapter(items);
        holder.childRecycleView.setAdapter(childRecycleAdapter);
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView monthDate;
        RecyclerView childRecycleView;


        public ViewHolder(@NonNull View itemView){
            super(itemView);

            monthDate = itemView.findViewById(R.id.sectionNameTextView);
            childRecycleView = itemView.findViewById(R.id.childRecycleView);
        }
    }
}
