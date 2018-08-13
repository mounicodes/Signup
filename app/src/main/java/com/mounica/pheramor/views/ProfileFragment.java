package com.mounica.pheramor.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.mounica.pheramor.ApiEndpointInterface;
import com.mounica.pheramor.R;
import com.mounica.pheramor.databinding.FragmentProfileBinding;
import com.mounica.pheramor.models.PhermoreResponse;
import com.mounica.pheramor.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    private static final String PHERAMOR_URL = "https://external.dev.pheramor.com";
    private static final String TAG = "ProfileFragment";
    private FragmentProfileBinding mFragmentProfileBinding;
    private Button mOkButton;
    private User mUser;
    private ImageView mProfileImage;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        mFragmentProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        return mFragmentProfileBinding.getRoot();
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mOkButton = mFragmentProfileBinding.buttonOk;
        mProfileImage = mFragmentProfileBinding.imageDp;
        mUser = ((MainActivity) getActivity()).getUser();
        if (mUser != null) {
            mFragmentProfileBinding.setUser(mUser);
            performDataProcessing();
        }

        mOkButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                performNetworkOperations();
            }
        });
    }

    private void performDataProcessing() {
        String fullname = mUser.getFirstName() + " " + mUser.getLastName();
        mFragmentProfileBinding.textUname.setText(fullname);

    }

    public void performNetworkOperations() {

        new Retrofit.Builder()
                .baseUrl(PHERAMOR_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build()
                .create(ApiEndpointInterface.class)
                .postUser(mUser)
                .enqueue(new Callback<PhermoreResponse>() {
                    @Override
                    public void onResponse(final Call<PhermoreResponse> call,
                            final Response<PhermoreResponse> response) {
                        if (!response.body().isStatusOK()) {
                            return;
                        }
                        Intent successIntent = new Intent(getActivity(), EndActivity.class);
                        startActivity(successIntent);
                        getActivity().finishAffinity();
                    }

                    @Override
                    public void onFailure(final Call<PhermoreResponse> call, final Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage(), t);
                        Toast.makeText(getContext(), "Error signing-up", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
