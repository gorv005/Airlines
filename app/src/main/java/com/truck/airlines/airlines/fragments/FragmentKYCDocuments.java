package com.truck.airlines.airlines.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.truck.airlines.airlines.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_CANCELED;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentKYCDocuments extends Fragment {

    @BindView(R.id.rlPanCard)
    RelativeLayout rlPanCard;

    @BindView(R.id.btnSubmitPancard)
    Button btnSubmitPancard;

    @BindView(R.id.tvPancard)
    TextView tvPancard;

    @BindView(R.id.rlPassportPhoto)
    RelativeLayout rlPassportPhoto;

    @BindView(R.id.btnPassportPhoto)
    Button btnPassportPhoto;

    @BindView(R.id.tvPassportPic)
    TextView tvPassportPic;

    @BindView(R.id.rlVisitingCard)
    RelativeLayout rlVisitingCard;

    @BindView(R.id.btnVisitingCard)
    Button btnVisitingCard;

    @BindView(R.id.tvVisitingCard)
    TextView tvVisitingCard;

    @BindView(R.id.rlAadharNumber)
    RelativeLayout rlAadharNumber;

    @BindView(R.id.btnAadharNumber)
    Button btnAadharNumber;

    @BindView(R.id.tvAadharCard)
    TextView tvAadharCard;

    private int GALLERY = 1;
    public FragmentKYCDocuments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kycdocuments, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }


    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                   /* Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                    //   String path = saveImage(bitmap);
                    bitmap = Utils.scaleDown(bitmap, 500, true);*/


                } catch (Exception e) {
                    e.printStackTrace();

                }
            }

        }
    }
    }
