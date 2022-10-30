package vn.tdtu.mad.mywallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class WalletAdapter extends
        RecyclerView.Adapter<WalletAdapter.ViewHolder> {

    private List<Transaction> mTransactions;
    public WalletAdapter(List<Transaction> list){
        mTransactions = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView amountTextView;
        public TextView dateTextView;

        public TextView categoryTextView;
        public Button editButton;

        public ViewHolder(View itemView) {

            super(itemView);

            amountTextView = (TextView) itemView.findViewById(R.id.itemAmount);
            dateTextView = (TextView) itemView.findViewById(R.id.itemDate);
            editButton = (Button) itemView.findViewById(R.id.edit_button);
            categoryTextView = (TextView) itemView.findViewById(R.id.itemCategory);
        }
    }

    @Override
    public WalletAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_transaction, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(WalletAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Transaction transaction = mTransactions.get(position);

        // Set item views based on your views and data model
        TextView aTextView = holder.amountTextView;
        TextView dTextView = holder.dateTextView;
        TextView cTextView = holder.categoryTextView;

        aTextView.setText(transaction.getAmount()+"€");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd HH:mm");
        dTextView.setText(simpleDateFormat.format(transaction.getDate()));

        cTextView.setText(transaction.getTransactionTypes());

        Button button = holder.editButton;

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mTransactions.size();
    }
}
