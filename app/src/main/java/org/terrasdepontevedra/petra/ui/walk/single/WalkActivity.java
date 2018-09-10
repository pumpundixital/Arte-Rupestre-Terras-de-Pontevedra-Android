package org.terrasdepontevedra.petra.ui.walk.single;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.util.Constants;

import me.yokeyword.fragmentation.SupportActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class WalkActivity extends SupportActivity {

    public static Intent getCallingIntent(Context context, String itineraryUrl) {
        Intent intent = new Intent(context, WalkActivity.class);
        intent.putExtra(Constants.ARG_ITINERARY_SLUG, itineraryUrl);
        return intent;
    }

    public static Intent getCallingIntent(Context context, int itineraryId) {
        Intent intent = new Intent(context, WalkActivity.class);
        intent.putExtra(Constants.ARG_ITINERARY_ID, itineraryId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);

        String url = getIntent().getStringExtra(Constants.ARG_ITINERARY_SLUG);
        int id = getIntent().getIntExtra(Constants.ARG_ITINERARY_ID,-1);
        if(url!=null) {
            if (findFragment(ItineraryTabFragment.class) == null) {
                loadRootFragment(R.id.fl_container, ItineraryTabFragment.newInstance(url));
            }
        }
        else if(id!=-1){
            if (findFragment(ItineraryTabFragment.class) == null) {
                loadRootFragment(R.id.fl_container, ItineraryTabFragment.newInstance(id));
            }
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
