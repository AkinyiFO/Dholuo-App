package com.simpleapps22.dholuo;

public class Word {

    /**
     * {@link Word} represents a vocabulary word that the user wants to learn.
     * It contains resource IDs for the default translation, Dholuo translation, and audio file.
     */

    /**
     * Default translation for the word
     */
    private final String mDefaultTranslation;

    /**
     * Dholuo translation for the word
     */
    private final String mDholuoTranslation;

    /**
     * Audio resource ID for the word
     */
    private final int mAudioResorceId;

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param dholuoTranslation  is the word in the Dholuo language
     * @param audioResourceId    is the resource ID for the audio file associated with this word
     */
    public Word(String defaultTranslation, String dholuoTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mDholuoTranslation = dholuoTranslation;
        mAudioResorceId = audioResourceId;
    }

    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Dholuo translation of the word.
     */
    public String getDholuoTranslation() {
        return mDholuoTranslation;
    }

    /**
     * Return the audio resource ID of the word.
     */
    public int getAudioResourceId() {
        return mAudioResorceId;
    }

}