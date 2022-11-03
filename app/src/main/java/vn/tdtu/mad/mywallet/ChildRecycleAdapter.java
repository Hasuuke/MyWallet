package vn.tdtu.mad.mywallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ChildRecycleAdapter extends RecyclerView.Adapter<ChildRecycleAdapter.ViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    ArrayList<Transaction> items;

    public ChildRecycleAdapter(ArrayList<Transaction> items, RecyclerViewInterface recyclerViewInterface) {
        this.items = items;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvAmountTextView;
        public TextView tvDateTextView;
        public TextView tvCategoryTextView;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface){
            super(itemView);

            tvAmountTextView = (TextView) itemView.findViewById(R.id.tvItemAmount);
            tvDateTextView = (TextView) itemView.findViewById(R.id.tvItemDate);
            tvCategoryTextView = (TextView) itemView.findViewById(R.id.tvItemCategory);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
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
        View view = layoutInflater.inflate(R.layout.item_transaction,parent,false);
        return new ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM HH:mm");
        Transaction transaction = items.get(position);

        holder.tvAmountTextView.setText(transaction.getAmount()+"€");
        holder.tvCategoryTextView.setText(transaction.getTransactionTypes());
        holder.tvDateTextView.setText(simpleDateFormat.format(transaction.getDate()));

        if(transaction.getTransactionTypes().equals("General")){
            holder.imageView.setImageResource(R.drawable.house_icon);
        }else if (transaction.getTransactionTypes().equals("Clothes")){
            holder.imageView.setImageResource(R.drawable.clothes_icon);
        }else if(transaction.getTransactionTypes().equals("Food")){
            holder.imageView.setImageResource(R.drawable.food_icon);
        }else if(transaction.getTransactionTypes().equals("Insurance")){
            holder.imageView.setImageResource(R.drawable.insurance_icon);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
