package com.mounica.pheramor.views;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mounica.pheramor.MessageEvent.EmailEvent;
import com.mounica.pheramor.MessageEvent.GenderEvent;
import com.mounica.pheramor.MessageEvent.InterestsEvent;
import com.mounica.pheramor.MessageEvent.NameEvent;
import com.mounica.pheramor.MessageEvent.PasswordEvent;
import com.mounica.pheramor.MessageEvent.RaceEvent;
import com.mounica.pheramor.MessageEvent.UriEvent;
import com.mounica.pheramor.R;
import com.mounica.pheramor.models.User;
import com.mounica.pheramor.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Activity that holds all the fragments
 */

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private String mEmail;
    private String mPassword;
    private String mFname;
    private String mLname;
    private String mZipcode;
    private String mHeight;
    private String mGender;
    private String mDob;
    private String mInterestedGender;
    private String mAgeRange;
    private String mRace;
    private String mReligion;
    private Uri mUri;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Register the event bus
        EventBus.getDefault().register(this);
        Toolbar toolbar = mBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.sign_up));

        // Enable home button
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // load the first fragment to take email from the user
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_fragment_holder, new EmailFragment()).addToBackStack("email");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // Subscribe to listen to the data updates from all the fragments
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEmailEvent(EmailEvent emailEvent) {
        mEmail = emailEvent.getEmail();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPasswordEvent(PasswordEvent passwordEvent) {
        mPassword = passwordEvent.getPassword();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNameEvent(NameEvent nameEvent) {
        mFname = nameEvent.getFname();
        mLname = nameEvent.getLname();
        mZipcode = nameEvent.getZipcode();
        mHeight = nameEvent.getHeight();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGenderEvent(GenderEvent genderEvent) {
        mGender = genderEvent.getGender();
        mDob = genderEvent.getDob();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onInterestsEvent(InterestsEvent interestsEvent) {
        mInterestedGender = interestsEvent.getInterestedGender();
        mAgeRange = interestsEvent.getAgeRange();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRaceEvent(RaceEvent raceEvent) {
        mRace = raceEvent.getRace();
        mReligion = raceEvent.getReligion();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUriEvent(UriEvent uriEvent) {
        mUri = uriEvent.getUri();
    }

    public User getUser() {
        if (mUser == null) {
            mUser = new User(mEmail, mPassword, mFname, mLname, mZipcode, mHeight, mGender, mDob, mInterestedGender,
                    mAgeRange, mUri);
            if (mRace != null) {
                mUser.setRace(mRace);
            }
            if (mReligion != null) {
                mUser.setReligion(mReligion);
            }
        }
        return mUser;
    }
}
