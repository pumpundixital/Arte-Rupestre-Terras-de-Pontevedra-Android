package org.terrasdepontevedra.petra.ui.adventure.yincana;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.ui.base.BaseActivity;
import org.terrasdepontevedra.petra.util.services.FragmentService;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class YincanaActivity extends BaseActivity implements YincanaConnectionFragment.OnYincanaConnectionListener {

    private static final String TAG = YincanaActivity.class.getSimpleName();

    @Inject
    FragmentService mFragmentService;


    public static Intent getCallingIntent(Context context) {
        return new Intent(context, YincanaActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialize GoogleMaps
        ButterKnife.bind(this);
        mFragmentService.add(YincanaConnectionFragment.newInstance(), R.id.layout_container);


    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_yincana;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        /*if (!mEventBus.isRegistered(this))
            mEventBus.register(this);*/
    }

    @Override
    public void onStop() {
        super.onStop();
        /*if (mEventBus.isRegistered(this))
            mEventBus.unregister(this);*/
    }


    @Override
    public void onGoogleApiConnected() {
        mFragmentService.replace(YincanaMapFragment.newInstance(), R.id.layout_container);
    }
}

