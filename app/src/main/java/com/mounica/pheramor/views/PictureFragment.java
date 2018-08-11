package com.mounica.pheramor.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.mounica.pheramor.MessageEvent.UriEvent;
import com.mounica.pheramor.R;
import org.greenrobot.eventbus.EventBus;

public class PictureFragment extends Fragment {

    private static final String TAG = "PictureFragment";
    private static final int RESULT_IMAGELOAD_FROM_GALLERY = 1;
    private ImageView mProfilePic;
    private Button mContinueButton;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_picture, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Button gallery = view.findViewById(R.id.button_gallery);
        mContinueButton = view.findViewById(R.id.button_continue);
        mProfilePic = view.findViewById(R.id.image_profile_picture);
        gallery.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                gallery.setVisibility(View.GONE);
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_IMAGELOAD_FROM_GALLERY);
            }
        });

        mContinueButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.frame_fragment_holder, new ReviewFragment());
                fragmentTransaction.addToBackStack("review");
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        Uri imageUri = data.getData();

        mProfilePic.setImageURI(imageUri);
        mProfilePic.setVisibility(View.VISIBLE);
        mContinueButton.setEnabled(true);

        // Post information to the subscriber
        EventBus.getDefault().post(new UriEvent(imageUri));
    }
}
