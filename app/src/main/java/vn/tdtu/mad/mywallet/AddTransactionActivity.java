package vn.tdtu.mad.mywallet;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class AddTransactionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView tvAmount;
    EditText edDateTime;
    String date;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        tvAmount = findViewById(R.id.tvEditAmount);
        edDateTime = findViewById(R.id.etDataTIme);
        spinner = findViewById(R.id.tvSpinnerTypes);
        edDateTime.setInputType(InputType.TYPE_NULL);
        edDateTime.setShowSoftInputOnFocus(false);


        edDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateTime(edDateTime);
            }
        });

        tvAmount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event != null&& (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(tvAmount.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return false;
            }
        });

        tvAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(),0);
                }
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setDateTime(final EditText v) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        edDateTime.setText(simpleDateFormat.format(calendar.getTime()));
                        date = simpleDateFormat.format(calendar.getTime());
                    }
                };
                new TimePickerDialog(AddTransactionActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
            }
        };
        new DatePickerDialog(AddTransactionActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void cancel(View view) {
        this.finish();
    }

    public void addTransaction(View view) {

        if(!tvAmount.getText().toString().equals("")&& !edDateTime.getText().toString().equals("")){
            Intent intent = new Intent();
            intent.putExtra("Amount",tvAmount.getText().toString() );
            CharSequence spinnerResult = (CharSequence) spinner.getSelectedItem();
            intent.putExtra("Spinner", spinnerResult.toString());
            intent.putExtra("TimeDate",date);

            String pos = getIntent().getStringExtra("Position");
            intent.putExtra("Position", pos);

            setResult(RESULT_OK, intent);
            Log.e(TAG,"new Transaction("+tvAmount.getText().toString()+", "+spinnerResult+", "+date+") added");
            this.finish();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        parent.getItemAtPosition(0);
    }

}