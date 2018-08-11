package com.mounica.pheramor.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.mounica.pheramor.MessageEvent.InterestsEvent;
import com.mounica.pheramor.R;
import org.florescu.android.rangeseekbar.RangeSeekBar;
import org.florescu.android.rangeseekbar.RangeSeekBar.OnRangeSeekBarChangeListener;
import org.greenrobot.eventbus.EventBus;

public class InterestsFragment extends Fragment implements OnClickListener {

    private static final String TAG = "InterestsFragment";

    private Button mContinueButton;
    private Button mMale;
    private Button mFemale;
    private RangeSeekBar mCustomSeekBar;
    private String mAgeRange;
    private boolean mGender;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_interests, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMale = view.findViewById(R.id.button_men);
        mFemale = view.findViewById(R.id.button_women);
        mContinueButton = view.findViewById(R.id.button_continue);
        mCustomSeekBar = view.findViewById(R.id.custom_seekbar);
        mMale.setOnClickListener(this);
        mFemale.setOnClickListener(this);
        mContinueButton.setOnClickListener(this);

        mCustomSeekBar.setOnRangeSeekBarChangeListener(new OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(final RangeSeekBar bar, final Object min, final Object max) {
                mAgeRange = min.toString() + "-" + max.toString();
            }
        });
    }

    @Override
    public void onClick(final View v) {
        if (v.getId() == R.id.button_men) {
            if (mFemale.isSelected()) {
                mFemale.setSelected(false);
            }
            v.setSelected(true);
            mGender = true;
            mContinueButton.setEnabled(true);
        } else if (v.getId() == R.id.button_women) {
            if (mMale.isSelected()) {
                mMale.setSelected(false);
            }
            v.setSelected(true);
            mGender = false;
            mContinueButton.setEnabled(true);
        } else {

            // Post information to the subscriber
            EventBus.getDefault().post(new InterestsEvent(mGender ? "Men" : "Women", mAgeRange.toString()));

            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.frame_fragment_holder, new RaceFragment());
            fragmentTransaction.addToBackStack("race");
            fragmentTransaction.commit();
        }
    }
}
