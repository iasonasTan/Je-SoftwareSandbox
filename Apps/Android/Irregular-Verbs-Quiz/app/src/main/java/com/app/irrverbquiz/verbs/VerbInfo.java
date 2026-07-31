package com.app.irrverbquiz.verbs;

import android.util.Log;

import androidx.annotation.NonNull;

public final class VerbInfo {
    private final String mBase, mMeaning;
    private final String[] mPast, mParticiple;

    public VerbInfo() {
        mBase = "Unknown";
        mMeaning = "Unknown";
        mPast = new String[]{"Unknown"};
        mParticiple = new String[]{"Unknown"};
    }

    public VerbInfo(String base, String past, String participle, String meaning) {
        mBase = base;
        mMeaning = meaning;
        mPast = past.split("/");
        mParticiple = participle.split("/");
    }

    public boolean past(String past) {
        for (String s : mPast) {
            Log.d("comparing", "Comparing "+past+" with "+s);
            if(s.equals(past))
                return true;
        }
        return false;
    }

    public boolean participle(String participle) {
        for (String s : mParticiple) {
            Log.d("comparing", "Comparing "+participle+" with "+s);
            if(s.equals(participle))
                return true;
        }
        return false;
    }

    @NonNull
    @Override
    public String toString() {
        return getBase()+" ("+getMeaning()+"), "+getPast()+", "+getParticiple();
    }

    public String getBase() {
        return mBase;
    }

    public String getMeaning() {
        return mMeaning;
    }

    public String getPast() {
        return String.join("/", mPast);
    }

    public String getParticiple() {
        return String.join("/", mParticiple);
    }
}
