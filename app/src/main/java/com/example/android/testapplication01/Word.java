package com.example.android.testapplication01;

/**
 * Created by def on 08.04.18.
 */

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mWordImage = NO_IMAGE_PROVIDED;
    private int mWordAudio = NO_AUDIO_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1; // If has no image
    private static final int NO_AUDIO_PROVIDED = -1; // If has no audio

    /**
     *
     * @param defaultTranslation translation on Engilsh language
     * @param miwokTranslation translation on Miwok lang
     */
    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    public Word(String defaultTranslation, String miwokTranslation, int wordAudio){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mWordAudio = wordAudio;
    }

    /**
     *
     * @param defaultTranslation - Word in English
     * @param miwokTranslation - Word in Miwok
     * @param wordImage - Word image for show in Item List
     * @param wordAudio - Word audio
     */
    public Word(String defaultTranslation, String miwokTranslation, int wordImage, int wordAudio){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mWordImage = wordImage;
        mWordAudio = wordAudio;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmWordImage() { return mWordImage; }

    public int getmWordAudio() { return mWordAudio; }

    public boolean hasWordAudio() { return mWordAudio != NO_AUDIO_PROVIDED; }

    public boolean hasWordImage(){return mWordImage != NO_IMAGE_PROVIDED;}

    /**
     * This is automaticaly generated and can be used in Activities for calling the currend Word
     * infromation like there Log.v("NumbersActivity", "Current word: " + word);
     * @return
     */
    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mWordImage=" + mWordImage +
                ", mWordAudio=" + mWordAudio +
                '}';
    }
}
