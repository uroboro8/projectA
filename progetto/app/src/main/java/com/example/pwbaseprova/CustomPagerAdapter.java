package com.example.pwbaseprova;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CustomPagerAdapter extends FragmentStateAdapter {
    int nCount;

    public CustomPagerAdapter(FragmentActivity fragmentActivity, int n) {
        super(fragmentActivity);
        this.nCount = n;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentPiatti();
            case 2:
                return new FragmentItinerari();
            case 3:
                return new FragmentContatti();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return this.nCount;
        //return 4;
    }
}
