package vn.tdtu.mad.mywallet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class AddDialog extends AppCompatDialogFragment {
    private EditText amountText;
    private Spinner spinner;
    private AddDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_date,null);


        builder.setView(view)
                .setTitle("Add Transaction")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Float amount = Float.parseFloat(amountText.getText().toString());
                        String type = spinner.toString();
                        listener.applyTexts(amount,type, new Date());
                    }
                });


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        try {
            listener = (AddDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+ "must implement AddDialog Listener");
        }
    }

    public interface AddDialogListener{
        void applyTexts(Float amount, String type, Date date);
    }


}
