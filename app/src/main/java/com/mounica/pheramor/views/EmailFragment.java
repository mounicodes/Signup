package com.mounica.pheramor.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mounica.pheramor.MessageEvent.EmailEvent;
import com.mounica.pheramor.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Fragment to collect user email and validate it
 */
public class EmailFragment extends Fragment {

    private static final String ERROR = "Please enter a valid email address";
    private Button mContinueButton;
    private EditText mEmail;
    private TextInputLayout mTextInputLayout;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
            final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email, container, false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEmail = view.findViewById(R.id.edit_email);
        mTextInputLayout = view.findViewById(R.id.textinput_email);
        mContinueButton = view.findViewById(R.id.button_continue);

        mContinueButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (!validateEmail(mEmail.getText().toString())) {
                    mTextInputLayout.setError(ERROR);
                    mTextInputLayout.setErrorEnabled(true);
                } else {

                    // Post the email to subscriber
                    EventBus.getDefault().post(new EmailEvent(mEmail.getText().toString()));
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                            .beginTransaction();
                    fragmentTransaction.replace(R.id.frame_fragment_holder, new PasswordFragment());
                    fragmentTransaction.addToBackStack("password");
                    fragmentTransaction.commit();
                }
            }
        });

        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                if (s.length() > 0) {
                    enableContinueButton();
                } else {
                    disableContinueButton();
                }

                if (mTextInputLayout.isErrorEnabled()) {
                    mTextInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(final Editable s) {
            }
        });
    }

    private void disableContinueButton() {
        mContinueButton.setEnabled(false);
    }

    private void enableContinueButton() {
        mContinueButton.setEnabled(true);
    }

    private boolean validateEmail(final String email) {

        if (email == null || email.length() == 0) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
