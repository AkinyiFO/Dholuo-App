package com.simpleapps22.dholuo;

import android.app.Fragment;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * {@link CategoryAdapter} is a {@link FragmentPagerAdapter} that can provide the layout for
 * each list item based on a data source which is a list of {@link Word} objects.
 */
public class CategoryAdapter extends FragmentPagerAdapter {

    /**
     * Context of the app
     */
    private final Context mContext;

    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param context is the context of the app
     * @param fm      is the fragment manager that will keep each fragment's state in the adapter
     *                across swipes.
     */
    public CategoryAdapter(MainActivity context, androidx.fragment.app.FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     *
     * @return
     */
    @NonNull
    @Override
    public androidx.fragment.app.Fragment getItem(int position) {
        if (position == 0) {
            return new VocabulariesFragment();
        } else if (position == 1) {
            return new Vocabularies1Fragment();
        } else if (position == 2) {
            return new ExtrasFragment();
        } else {
            return new PhrasesFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_vocabularies);
        } else if (position == 1) {
            return mContext.getString(R.string.category_vocabularies1);
        } else if (position == 2) {
            return mContext.getString(R.string.category_extras);
        } else {
            return mContext.getString(R.string.category_phrases);
        }
    }
}