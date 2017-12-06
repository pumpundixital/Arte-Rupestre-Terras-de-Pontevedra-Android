package org.terrasdepontevedra.petra.ui.itinerary.header;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.slider.library.GlideApp;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.base.Url;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;

import butterknife.BindView;

public class ItineraryImageFragment extends BaseFragment {

    private static final String IMAGE_URL = "IMAGE_URL";

    @BindView(R.id.image_itinerary)
    ImageView mImage;

    private Url mUrl;


    public static ItineraryImageFragment newInstance(String imageUrlAsString) {
        ItineraryImageFragment itineraryImageFragment = new ItineraryImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMAGE_URL, imageUrlAsString);
        itineraryImageFragment.setArguments(bundle);
        return itineraryImageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(IMAGE_URL))
            mUrl = Url.from(arguments.getString(IMAGE_URL));

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GlideApp
                .with(this)
                .load(mUrl.asString())
                .centerCrop()
                .into(mImage);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_itinerary_image;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }
}
