package com.truck.airlines.airlines.fragments;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.truck.airlines.airlines.R;
import com.truck.airlines.airlines.utils.C;
import com.truck.airlines.airlines.utils.Util;

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
    private String documentName;
    private Dialog dialog;

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
        rlPanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                documentName = C.PAN;
                choosePhotoFromGallary();
            }
        });
        rlAadharNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                documentName = C.AADHAR;
                choosePhotoFromGallary();
            }
        });
        rlPassportPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                documentName = C.PASSPORT;
                choosePhotoFromGallary();
            }
        });
        rlVisitingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                documentName = C.VISITING_CARD;
                choosePhotoFromGallary();
            }
        });

        btnSubmitPancard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!tvPancard.getText().toString().equals("")) {
                    fileUploading();
                }
                else
                {
                    Toast.makeText(getActivity(), R.string.please_select_file, Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnAadharNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btnAadharNumber.getText().toString().equals("")) {
                    fileUploading();
                }
                else
                {
                    Toast.makeText(getActivity(), R.string.please_select_file, Toast.LENGTH_SHORT).show();

                }

            }
        });
        btnPassportPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!btnPassportPhoto.getText().toString().equals("")) {
                    fileUploading();
                }
                else
                {
                    Toast.makeText(getActivity(), R.string.please_select_file, Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnVisitingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!btnVisitingCard.getText().toString().equals("")) {
                    fileUploading();
                }
                else
                {
                    Toast.makeText(getActivity(), R.string.please_select_file, Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void fileUploading() {

        dialog = Util.getProgressDialog(getActivity(), R.string.please_wait);
        dialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(getActivity(), R.string.file_uploaded_succesfully, Toast.LENGTH_SHORT).show();
                if (C.AADHAR.equals(documentName)) {
                    tvAadharCard.setText("");
                } else if (C.PASSPORT.equals(documentName)) {
                    tvPassportPic.setText("");
                } else if (C.PAN.equals(documentName)) {
                    tvPancard.setText("");
                } else if (C.VISITING_CARD.equals(documentName)) {
                    tvVisitingCard.setText("");
                }
                dialog.dismiss();
            }

        }, 2000);

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

                    if (C.AADHAR.equals(documentName)) {
                        tvAadharCard.setText(Util.getFileName(contentURI, getActivity()));
                    } else if (C.PASSPORT.equals(documentName)) {
                        tvPassportPic.setText(Util.getFileName(contentURI, getActivity()));
                    } else if (C.PAN.equals(documentName)) {
                        tvPancard.setText(Util.getFileName(contentURI, getActivity()));
                    } else if (C.VISITING_CARD.equals(documentName)) {

                        tvVisitingCard.setText(Util.getFileName(contentURI, getActivity()));
                    }

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
