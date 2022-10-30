package vn.tdtu.mad.mywallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChildRecycleAdapter extends RecyclerView.Adapter<ChildRecycleAdapter.ViewHolder> {

    List<String> items;

    public ChildRecycleAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            textView = itemView.findViewById(R.id.itemTextView);
        }
    }
}
