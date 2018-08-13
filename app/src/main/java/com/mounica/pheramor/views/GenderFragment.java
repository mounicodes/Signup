package com.mounica.pheramor.views;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.mounica.pheramor.MessageEvent.GenderEvent;
import com.mounica.pheramor.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.greenrobot.eventbus.EventBus;

/**
 * Fragment to collect user gender and date-of-birth
 */
public class GenderFragment extends Fragment {

    private static final int MIN_YEARS_REQUIRED = 18;
    private Calendar mMinDate;
    private static final String ERROR_MIN_AGE = "Need to be 18 years and above";
    private boolean mGender;
    private Button mContinuButton;
    private Button mMale;
    private Button mFemale;
    private EditText mBirthday;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gender, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mMale = view.findViewById(R.id.button_male);
        mFemale = view.findViewById(R.id.button_female);
        mBirthday = view.findViewById(R.id.edit_dob);
        mContinuButton = view.findViewById(R.id.button_continue);
        final TextInputLayout textInputBirthday = view.findViewById(R.id.textinput_dob);

        final Calendar calendar = Calendar.getInstance();
        mMinDate = Calendar.getInstance();
        mMinDate.add(Calendar.YEAR, -1 * MIN_YEARS_REQUIRED);
        final DatePickerDialog.OnDateSetListener date = new OnDateSetListener() {
            @Override
            public void onDateSet(final DatePicker view, final int year, final int month, final int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if (calendar.after(mMinDate)) {
                    textInputBirthday.setError(ERROR_MIN_AGE);
                    mContinuButton.setEnabled(false);
                } else {
                    if (textInputBirthday.isErrorEnabled()) {
                        textInputBirthday.setErrorEnabled(false);
                    }
                    mBirthday.setText(new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(calendar.getTime()));
                    continueButtonPreCheck();
                }
            }
        };

        mBirthday.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(final View v, final boolean hasFocus) {
                if (hasFocus) {
                    new DatePickerDialog(v.getContext(), date, mMinDate.get(Calendar.YEAR),
                            mMinDate.get(Calendar.MONTH), mMinDate.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        mBirthday.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                new DatePickerDialog(v.getContext(), date, mMinDate.get(Calendar.YEAR),
                        mMinDate.get(Calendar.MONTH), mMinDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mMale.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (mFemale.isSelected()) {
                    mFemale.setSelected(false);
                }
                mGender = true;
                v.setSelected(true);
                continueButtonPreCheck();
            }
        });

        mFemale.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (mMale.isSelected()) {
                    mMale.setSelected(false);
                }
                mGender = false;
                v.setSelected(true);
                continueButtonPreCheck();
            }
        });

        mContinuButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {

                // Post information to the subscriber
                EventBus.getDefault()
                        .post(new GenderEvent(mGender ? "Male" : "Female", mBirthday.getText().toString()));

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.frame_fragment_holder, new InterestsFragment());
                fragmentTransaction.addToBackStack("interests");
                fragmentTransaction.commit();
            }
        });

    }

    private void continueButtonPreCheck() {

        // Check if all fields are filled before enable continue button
        if ((mMale.isSelected() || mFemale.isSelected()) && mBirthday.getText().length() > 1) {
            mContinuButton.setEnabled(true);
        }
    }
}
