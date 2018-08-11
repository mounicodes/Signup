package com.mounica.pheramor.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.mounica.pheramor.MessageEvent.PasswordEvent;
import com.mounica.pheramor.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.greenrobot.eventbus.EventBus;

public class PasswordFragment extends Fragment implements TextWatcher {

    private TextInputEditText mPassword;
    private TextView mPasswordRules;
    private Button mContinue;
    private TextInputLayout mTextInputPassword;
    private static final String PASSWORD_IS_NOT_VALID = "Password is not valid";

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_password, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPassword = view.findViewById(R.id.edit_password);
        mPasswordRules = view.findViewById(R.id.text_password_rules);
        String passwordRules = "&#8226; Atleast 8 characters<br/>\n"
                + "&#8226; 1 Number<br/>\n"
                + "&#8226; 1 Uppercase<br/>\n"
                + "&#8226; 1 Special Character<br/>";
        mPasswordRules.setText(Html.fromHtml(passwordRules));

        mContinue = view.findViewById(R.id.button_continue);
        mTextInputPassword = view.findViewById(R.id.textinput_password);
        mPassword.addTextChangedListener(this);
        mContinue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (isPasswordValid(mPassword.getText().toString())) {

                    // Post the email to subscriber
                    EventBus.getDefault().post(new PasswordEvent(mPassword.getText().toString()));
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                            .beginTransaction();
                    fragmentTransaction.replace(R.id.frame_fragment_holder, new NameFragment());
                    fragmentTransaction.addToBackStack("name");
                    fragmentTransaction.commit();
                } else {
                    mTextInputPassword.setError(PASSWORD_IS_NOT_VALID);
                    mTextInputPassword.setErrorEnabled(true);
                }
            }
        });
    }

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
    }

    @Override
    public void afterTextChanged(final Editable s) {
    }

    private boolean isPasswordValid(String password) {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches() ? true : false;
    }

    private void disableContinueButton() {
        mContinue.setEnabled(false);
    }

    private void enableContinueButton() {
        mContinue.setEnabled(true);
    }

}
