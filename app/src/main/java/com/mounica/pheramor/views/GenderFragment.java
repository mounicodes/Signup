package com.mounica.pheramor.views;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import com.mounica.pheramor.MessageEvent.GenderEvent;
import com.mounica.pheramor.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;

public class GenderFragment extends Fragment {

    private static final int YEAR = 2000;
    private static final int MONTH = 01;
    private static final int DAY = 01;
    private boolean mGender;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gender, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Button male = view.findViewById(R.id.button_male);
        final Button female = view.findViewById(R.id.button_female);
        final EditText birthday = view.findViewById(R.id.edit_dob);
        final Button continueButton = view.findViewById(R.id.button_continue);

        final Calendar calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new OnDateSetListener() {
            @Override
            public void onDateSet(final DatePicker view, final int year, final int month, final int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                birthday.setText(new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(calendar.getTime()));
            }
        };

        birthday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                if (s.length() > 1) {
                    continueButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(final Editable s) {
            }
        });

        birthday.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                new DatePickerDialog(v.getContext(), date, YEAR, MONTH, DAY).show();
            }
        });

        male.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (female.isSelected()) {
                    female.setSelected(false);
                }
                mGender = true;
                v.setSelected(true);
            }
        });

        female.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (male.isSelected()) {
                    male.setSelected(false);
                }
                mGender = false;
                v.setSelected(true);
            }
        });

        continueButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Post information to the subscriber
                EventBus.getDefault()
                        .post(new GenderEvent(mGender ? "Male" : "Female", birthday.getText().toString()));

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.frame_fragment_holder, new InterestsFragment());
                fragmentTransaction.addToBackStack("interests");
                fragmentTransaction.commit();
            }
        });

    }
}
