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
import android.widget.Spinner;

import com.mounica.pheramor.MessageEvent.RaceEvent;
import com.mounica.pheramor.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Fragment that collects user race/religion
 */
public class RaceFragment extends Fragment {

    private Spinner mRace;
    private Spinner mReligion;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
            final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_race, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRace = view.findViewById(R.id.spinner_race);
        mReligion = view.findViewById(R.id.spinner_religion);
        Button continueButton = view.findViewById(R.id.button_continue);

        continueButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {

                // Post information to the subscriber
                EventBus.getDefault().post(new RaceEvent(mRace.getSelectedItem().toString(),
                        mReligion.getSelectedItem().toString()));

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.frame_fragment_holder, new PictureFragment());
                fragmentTransaction.addToBackStack("picture");
                fragmentTransaction.commit();
            }
        });
    }
}
