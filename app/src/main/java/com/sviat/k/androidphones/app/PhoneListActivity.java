package com.sviat.k.androidphones.app;

import android.support.v4.app.Fragment;

/**
 * Created by Sviat on 04.11.14.
 */
public class PhoneListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new PhonesListFragment();
    }
}