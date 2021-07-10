package com.example.news;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class NewsPagesAdapter extends FragmentPagerAdapter {

    /**
     *@param mPagesCount     - Count the number of pages.
     */
    int mPagesCount;

    public NewsPagesAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        mPagesCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0 : return new GeneralNewsFragment("https://saurav.tech/NewsAPI/top-headlines/category/general/in.json");
            case 1 : return new HealthNewsFragment("https://saurav.tech/NewsAPI/top-headlines/category/health/in.json");
            case 2 : return new BusinessNewsFragment("https://saurav.tech/NewsAPI/top-headlines/category/business/in.json");
            case 3 : return new EntertainmentNewsFragment("https://saurav.tech/NewsAPI/top-headlines/category/entertainment/in.json");
            case 4 : return new ScienceNewsFragment("https://saurav.tech/NewsAPI/top-headlines/category/science/in.json");
            case 5 : return new SportsNewsFragment("https://saurav.tech/NewsAPI/top-headlines/category/sports/in.json");
            case 6 : return new TechnologyNewsFragment("https://saurav.tech/NewsAPI/top-headlines/category/technology/in.json");

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return mPagesCount;
    }
}
