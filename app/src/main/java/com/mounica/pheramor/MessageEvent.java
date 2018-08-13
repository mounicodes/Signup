package com.mounica.pheramor;

import android.net.Uri;

/**
 * Event class
 */
public class MessageEvent {

    public static class EmailEvent {

        private String mEmail;

        public EmailEvent(final String email) {
            mEmail = email;
        }

        public String getEmail() {
            return mEmail;
        }
    }

    public static class PasswordEvent {

        private String mPassword;

        public PasswordEvent(final String password) {
            mPassword = password;
        }

        public String getPassword() {
            return mPassword;
        }
    }

    public static class NameEvent {

        private String mFname;
        private String mLname;
        private String mZipcode;
        private String mHeight;

        public NameEvent(final String fname, final String lname, final String zipcode, final String height) {
            mFname = fname;
            mLname = lname;
            mZipcode = zipcode;
            mHeight = height;
        }

        public String getFname() {
            return mFname;
        }

        public String getLname() {
            return mLname;
        }

        public String getZipcode() {
            return mZipcode;
        }

        public String getHeight() {
            return mHeight;
        }
    }

    public static class GenderEvent {

        private String mGender;
        private String mDob;

        public GenderEvent(final String gender, final String dob) {
            mGender = gender;
            mDob = dob;
        }

        public String getGender() {
            return mGender;
        }

        public String getDob() {
            return mDob;
        }
    }

    public static class InterestsEvent {

        private String mInterestedGender;
        private String mAgeRange;

        public InterestsEvent(final String interestedGender, final String ageRange) {
            mInterestedGender = interestedGender;
            mAgeRange = ageRange;
        }

        public String getInterestedGender() {
            return mInterestedGender;
        }

        public String getAgeRange() {
            return mAgeRange;
        }
    }

    public static class RaceEvent {

        private String mRace;
        private String mReligion;

        public RaceEvent(final String race, final String religion) {
            mRace = race;
            mReligion = religion;
        }

        public String getRace() {
            return mRace;
        }

        public String getReligion() {
            return mReligion;
        }
    }

    public static class UriEvent {

        private Uri mUri;

        public UriEvent(final Uri uri) {
            mUri = uri;
        }

        public Uri getUri() {
            return mUri;
        }
    }
}
