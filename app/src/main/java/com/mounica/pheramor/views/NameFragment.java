package com.mounica.pheramor.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mounica.pheramor.MessageEvent.NameEvent;
import com.mounica.pheramor.R;
import org.greenrobot.eventbus.EventBus;

/**
 * Fragment that collects user details name/zipcode/height
 */
public class NameFragment extends Fragment {

    private EditText mFirstName;
    private EditText mLastName;
    private EditText mZipCode;
    private EditText mHeight;
    private Button mContinueButton;
    private TextInputLayout mTextFName;
    private TextInputLayout mTextHeight;
    private TextInputLayout mTextLName;
    private TextInputLayout mTextZipCode;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
            final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_name, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFirstName = view.findViewById(R.id.edit_fname);
        mLastName = view.findViewById(R.id.edit_lname);
        mZipCode = view.findViewById(R.id.edit_zip);
        mHeight = view.findViewById(R.id.edit_height);
        mContinueButton = view.findViewById(R.id.button_continue);
        mTextFName = view.findViewById(R.id.textinput_fname);
        mTextHeight = view.findViewById(R.id.textinput_ht);
        mTextLName = view.findViewById(R.id.textinput_lname);
        mTextZipCode = view.findViewById(R.id.textinput_zip);

        mFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                if (s.length() < 1) {
                    mTextFName.setError(getResources().getString(R.string.required));
                    mTextFName.setErrorEnabled(true);
                } else {
                    mTextHeight.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(final Editable s) {
            }
        });

        mHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                if (s.length() < 1) {
                    mTextHeight.setError(getResources().getString(R.string.required));
                    mTextHeight.setErrorEnabled(true);
                } else {
                    mTextHeight.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(final Editable s) {
            }
        });

        mLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                if (s.length() < 1) {
                    mTextLName.setError(getResources().getString(R.string.required));
                    mTextLName.setErrorEnabled(true);
                } else {
                    mTextLName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(final Editable s) {
            }
        });

        mZipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                if (s.length() < 1) {
                    mTextZipCode.setError(getResources().getString(R.string.required));
                    mTextZipCode.setErrorEnabled(true);
                } else {
                    mTextZipCode.setErrorEnabled(false);
                    mContinueButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(final Editable s) {
            }
        });

        mContinueButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {

                // Post information to the subscriber
                EventBus.getDefault()
                        .post(new NameEvent(mFirstName.getText().toString(), mLastName.getText().toString(),
                                mZipCode.getText().toString(), mHeight.getText().toString()));

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.frame_fragment_holder, new GenderFragment());
                fragmentTransaction.addToBackStack("gender");
                fragmentTransaction.commit();
            }
        });
    }
}
