package vn.tdtu.mad.mywallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class statisticChildRecycleAdapter extends RecyclerView.Adapter<statisticChildRecycleAdapter.ViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    ArrayList<Transaction> items;

    public statisticChildRecycleAdapter(ArrayList<Transaction> items, RecyclerViewInterface recyclerViewInterface) {
        this.items = items;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvAmountTextView;
        public TextView tvDateTextView;
        public TextView tvCategoryTextView;
        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface){
            super(itemView);

            tvAmountTextView = (TextView) itemView.findViewById(R.id.tvItemAmount);
            tvDateTextView = (TextView) itemView.findViewById(R.id.tvItemDate);
            tvCategoryTextView = (TextView) itemView.findViewById(R.id.tvItemCategory);

        }
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_transaction,parent,false);
        return new ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Transaction transaction = items.get(position);
        holder.tvAmountTextView.setText(transaction.getAmount()+"€");
        holder.tvCategoryTextView.setText(transaction.getTransactionTypes());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM HH:mm");
        holder.tvDateTextView.setText(simpleDateFormat.format(transaction.getDate()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
