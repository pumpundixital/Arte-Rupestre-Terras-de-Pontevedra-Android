package org.terrasdepontevedra.petra.ui.walk.single;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.daimajia.slider.library.GlideApp;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.walk.Itinerary;
import org.terrasdepontevedra.petra.domain.model.walk.Place;
import org.terrasdepontevedra.petra.util.AndroidUtils;
import org.terrasdepontevedra.petra.util.MapUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;
import timber.log.Timber;

import static org.terrasdepontevedra.petra.util.Constants.ARG_ITINERARY;
import static org.terrasdepontevedra.petra.util.Constants.ARG_PLACES_LIST;


/**
 * Created by pablopumpun on 22/9/17.
 */

public class ItineraryDetailFragment extends SupportFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private Itinerary mItinerary;
    private List<Place> mPlaces;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.appbar_single)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    @BindView(R.id.image_toolbar)
    ImageView mImage;

    @BindView(R.id.text_single_title)
    TextView mTextTitle;

    @BindView(R.id.text_single_itinerary_description)
    TextView mTextDescription;

    SupportMapFragment mMapFragment;
    private Runnable mAnimation;
    private Handler mHandler;


    public static ItineraryDetailFragment newInstance(Itinerary itinerary, List<Place> places) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_ITINERARY, itinerary);
        args.putParcelableArrayList(ARG_PLACES_LIST, new ArrayList<>(places));
        ItineraryDetailFragment fragment = new ItineraryDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItinerary = getArguments().getParcelable(ARG_ITINERARY);
        mPlaces = getArguments().getParcelableArrayList(ARG_PLACES_LIST);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_walk_detail, container, false);
        ButterKnife.bind(this, view);
        mHandler = new Handler();
        mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);
        mMapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setData();
    }

    public void setContent(Itinerary itinerary) {
        mItinerary = itinerary;
    }

    private void setData() {

        Timber.i("data:" + mItinerary.getTitle());
        setTitle();
        setDescription();
        setupToolbar();
        if (mItinerary.getFeaturedImage() != null) {
            GlideApp.with(getContext())
                    .load(mItinerary.getFeaturedImage().getUrlMedium())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            getActivity().findViewById(R.id.progress_image).setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            getActivity().findViewById(R.id.progress_image).setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(mImage);
        }


    }

    private void setTitle() {
        mTextTitle.setText(mItinerary.getTitle());
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mCollapsingToolbar.setTitle(mItinerary.getTitle());
                    //final Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/walsheim_bold.ttf");
                    //mCollapsingToolbar.setCollapsedTitleTypeface(tf);
                    //mCollapsingToolbar.setExpandedTitleTypeface(tf);
                    isShow = true;
                } else if (isShow) {
                    mCollapsingToolbar.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
    }

    private void setDescription() {
        mTextDescription.setText(Html.fromHtml(mItinerary.getContent()));
    }


    private void setupToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        Drawable backArrow = getResources().getDrawable(R.drawable.ic_back);
        actionBar.setHomeAsUpIndicator(backArrow);
        AndroidUtils.systemBarLolipop(getActivity());
        mToolbar.setNavigationOnClickListener(v -> getActivity().finish());
        mTextTitle.setText(mItinerary.getTitle());
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mCollapsingToolbar.setTitle(mItinerary.getTitle());
                    //final Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/walsheim_bold.ttf");
                    //mCollapsingToolbar.setCollapsedTitleTypeface(tf);
                    //mCollapsingToolbar.setExpandedTitleTypeface(tf);
                    isShow = true;
                } else if (isShow) {
                    mCollapsingToolbar.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {


        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style));
        googleMap.setOnMarkerClickListener(this);

        LatLng initialLocation = null;
        boolean startMarker = false;
        //Add markers
        for (int i = 0; i < mPlaces.size(); i++) {
            if (!startMarker) {
                initialLocation = mPlaces.get(i).getPosition();
                startMarker = true;
            }
            LatLng location = mPlaces.get(i).getPosition();
            String title = mPlaces.get(i).getTitle();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View marker_view = inflater.inflate(R.layout.maps_marker, null);
            googleMap.addMarker(new MarkerOptions().position(location).title(title)).setIcon((BitmapDescriptorFactory.fromBitmap(AndroidUtils.createBitmapFromView(getActivity(), marker_view))));
        }

        googleMap.getUiSettings().setZoomControlsEnabled(true);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 14));

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getContext(), R.string.allow_location_permissions, Toast.LENGTH_SHORT).show();
        } else {
            googleMap.setMyLocationEnabled(true);
        }
        MapsInitializer.initialize(this.getActivity());


        LatLng center = MapUtils.getCenterFrom(mPlaces).getCenter();
        CameraPosition cameraPosition = new CameraPosition.Builder().target(center).zoom(10).bearing(0).tilt(0).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        final long start = SystemClock.uptimeMillis();
        final long duration = 1500L;
        mHandler.removeCallbacks(mAnimation);
        // mAnimation = new MapPlacesFragment.BounceAnimation(start, duration, marker, mHandler);
        mHandler.post(mAnimation);
        return false;
    }

}
