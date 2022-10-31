package vn.tdtu.mad.mywallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ChildRecycleAdapter extends RecyclerView.Adapter<ChildRecycleAdapter.ViewHolder> {

    ArrayList<Transaction> items;

    public ChildRecycleAdapter(ArrayList<Transaction> items) {
        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvAmountTextView;
        public TextView tvDateTextView;
        public TextView tvCategoryTextView;
        public Button btnEdit;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            tvAmountTextView = (TextView) itemView.findViewById(R.id.tvItemAmount);
            tvDateTextView = (TextView) itemView.findViewById(R.id.tvItemDate);
            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
            tvCategoryTextView = (TextView) itemView.findViewById(R.id.tvItemCategory);
        }
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_transaction,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Transaction transaction = items.get(position);
        TextView aTextView = holder.tvAmountTextView;
        TextView dTextView = holder.tvDateTextView;
        TextView cTextView = holder.tvCategoryTextView;
        Button button = holder.btnEdit;


        aTextView.setText(transaction.getAmount()+"€");
        cTextView.setText(transaction.getTransactionTypes());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd HH:mm");
        dTextView.setText(simpleDateFormat.format(transaction.getDate()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
