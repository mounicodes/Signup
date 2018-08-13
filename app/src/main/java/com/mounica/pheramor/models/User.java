package com.mounica.pheramor.models;

import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * User model
 */
public class User implements Serializable {

    @SerializedName("email")
    @Expose
    private String mEmail;
    @SerializedName("password")
    @Expose
    private String mPassword;
    @SerializedName("fname")
    @Expose
    private String mFirstName;
    @SerializedName("lname")
    @Expose
    private String mLastName;
    @SerializedName("zipcode")
    @Expose
    private String mZipCode;
    @SerializedName("height")
    @Expose
    private String mHeight;
    @SerializedName("gender")
    @Expose
    private String mGender;
    @SerializedName("dob")
    @Expose
    private String mDob;
    @SerializedName("genderinterested")
    @Expose
    private String mInterestedGender;
    @SerializedName("agerange")
    @Expose
    private String mAgeRange;
    @SerializedName("race")
    @Expose
    private String mRace;
    @SerializedName("religion")
    @Expose
    private String mReligion;
    @SerializedName("profileuri")
    @Expose
    private Uri mProfilePicture;

    public User(final String email, final String password, final String firstName, final String lastName,
            final String zipCode, final String height, final String gender, final String dob,
            final String interestedGender, final String ageRange, final Uri profilePicture) {
        mEmail = email;
        mPassword = password;
        mFirstName = firstName;
        mLastName = lastName;
        mZipCode = zipCode;
        mHeight = height;
        mGender = gender;
        mDob = dob;
        mInterestedGender = interestedGender;
        mAgeRange = ageRange;
        mProfilePicture = profilePicture;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getZipCode() {
        return mZipCode;
    }

    public String getHeight() {
        return mHeight;
    }

    public String getGender() {
        return mGender;
    }

    public String getDob() {
        return mDob;
    }

    public String getInterestedGender() {
        return mInterestedGender;
    }

    public String getFromAge() {
        return mAgeRange;
    }

    public String getRace() {
        return mRace;
    }

    public String getReligion() {
        return mReligion;
    }

    public Uri getProfilePicture() {
        return mProfilePicture;
    }

    public void setRace(final String race) {
        mRace = race;
    }

    public void setReligion(final String religion) {
        mReligion = religion;
    }
}
