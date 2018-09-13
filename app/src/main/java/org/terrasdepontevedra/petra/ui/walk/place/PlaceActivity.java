package org.terrasdepontevedra.petra.ui.walk.place;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.GlideApp;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.walk.Place;
import org.terrasdepontevedra.petra.ui.gallery.GalleryActivity;
import org.terrasdepontevedra.petra.util.AndroidUtils;
import org.terrasdepontevedra.petra.util.Constants;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class PlaceActivity extends AppCompatActivity implements OnMapReadyCallback, GalleryAdapter.OnGalleryClick, PlaceContract.View {


    @BindView(R.id.toolbar_single)
    Toolbar mToolbar;

    @BindView(R.id.image_toolbar)
    ImageView mImageToolbar;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    @BindView(R.id.appbar_single)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.map_place_detail)
    View mLayoutMap;

    @BindView(R.id.text_single_title)
    TextView mTextTitle;

    @BindView(R.id.text_single_place_description)
    TextView mTextDescription;

    @BindView(R.id.galleryRecycler)
    RecyclerView mRecyclerGallery;

    private String mPlaceLink;
    private int mPlaceId = -1;
    private Place mPlace;
    private MapFragment mMapFragment;
    private GoogleMap mMap;
    PlacePresenter mPresenter;


    public static Intent getCallingIntent(Context context, String link) {
        Intent intent = new Intent(context, PlaceActivity.class);
        intent.putExtra(Constants.ARG_PLACE_LINK, link);
        return intent;
    }

    public static Intent getCallingIntent(Context context, int placeId) {
        Intent intent = new Intent(context, PlaceActivity.class);
        intent.putExtra(Constants.ARG_PLACE_ID, placeId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        ButterKnife.bind(this);
        mPresenter = new PlacePresenter(this);
        mPresenter.attachView(this);
        initEventAndData();
    }

    protected void initEventAndData() {
        onNewIntent(getIntent());
        if(mPlaceLink==null || mPlaceLink.isEmpty()) {
            mPlaceLink = getIntent().getStringExtra(Constants.ARG_PLACE_LINK);
        }
        mPlaceId = getIntent().getIntExtra(Constants.ARG_PLACE_ID,-1);
        if(mPlaceLink!=null) {
            mPresenter.getData(mPlaceLink);
        }
        else if(mPlaceId!=-1){
            mPresenter.getData(mPlaceId);
        }
    }

    protected void onNewIntent(Intent intent) {
        String action = getIntent().getAction();
        String data = getIntent().getDataString();
        if (Intent.ACTION_VIEW.equals(action) && data != null) {
            mPlaceLink = data;
        }
    }

    public void showContent(Place place) {
        mPlace = place;
        setupToolbar();
        initMap();
        setDescription();
        setAddress();
        setDistance();
        setWebsite();
        setPhone();
        setGallery();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        Drawable backArrow = getResources().getDrawable(R.drawable.ic_back);
        actionBar.setHomeAsUpIndicator(backArrow);
        AndroidUtils.systemBarLolipop(this);
        mToolbar.setNavigationOnClickListener(v -> finish());
        setImageToolbar();
        mTextTitle.setText(mPlace.getTitle());
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mCollapsingToolbar.setTitle(Html.fromHtml(mPlace.getTitle()));
                    final Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
                    mCollapsingToolbar.setCollapsedTitleTypeface(tf);
                    mCollapsingToolbar.setExpandedTitleTypeface(tf);
                    isShow = true;
                } else if (isShow) {
                    mCollapsingToolbar.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

    }

    private void initMap() {
        if(mPlace.getPosition()!=null && mPlace.getPosition().latitude!=-1) {
            mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_place_detail);
            mMapFragment.getMapAsync(this);
        }
        else{
            mLayoutMap.setVisibility(View.GONE);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Timber.i("map ready");
        mMap=googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style));
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View marker_view = inflater.inflate(R.layout.maps_marker, null);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(mPlace.getPosition()).zoom(12).build();
        MarkerOptions markerOptions = new MarkerOptions().position(mPlace.getPosition());
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(AndroidUtils.createBitmapFromView(this, marker_view)));
        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    private void setImageToolbar(){
        if(mPlace.getFeaturedImage()==null){
            Timber.i("image null");
        }
        else {
            GlideApp.with(this).load(mPlace.getFeaturedImage().getUrlMedium()).into(mImageToolbar);
        }
    }

    private void setDescription(){
        mTextDescription.setText(Html.fromHtml(mPlace.getContent()));
    }

    private void setAddress(){
        if(mPlace.getAddress()!=null && mPlace.getAddress().length()>2){
            findViewById(R.id.lyt_address).setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.text_address)).setText(mPlace.getAddress());
        }
        else{
            findViewById(R.id.lyt_address).setVisibility(View.GONE);
        }
    }

    private void setDistance(){
        Random r= new Random();
    /*    if(mPresenter.getLocation().latitude!=-1) {
            LatLng myLocation = new LatLng(mPresenter.getLocation().latitude,mPresenter.getLocation().longitude);
            //TODO: Format
            ((TextView) findViewById(R.id.text_distance)).setText((int)AndroidUtils.calculateDistance(myLocation,mPlace.getPosition()) + "m");
        } */
    }

    private void setWebsite(){
        findViewById(R.id.text_website).setOnClickListener(view -> {
            Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(mPlace.getWebsite()));
            startActivity(browserIntent);
        });
        if(mPlace.getWebsite()!=null && !mPlace.getWebsite().isEmpty()) {
            ((TextView) findViewById(R.id.text_website)).setText(mPlace.getWebsite());
        }
        else{
            findViewById(R.id.text_website).setVisibility(View.GONE);
        }
    }

    private void setPhone(){
        if(mPlace.getPhone()!=null && mPlace.getPhone().length()>2){
            findViewById(R.id.lyt_phone).setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.text_phone)).setText(mPlace.getPhone());
        }
        else{
            findViewById(R.id.lyt_phone).setVisibility(View.GONE);
        }
    }

    private void setGallery(){
        if(mPlace.getImagesUrl()!=null && !mPlace.getImagesUrl().isEmpty()) {
            mRecyclerGallery.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            mRecyclerGallery.setAdapter(new GalleryAdapter(mPlace.getImagesUrl(), this));
        }
        else{
            findViewById(R.id.card_gallery_details).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClickImageWithUrl(String url) {
        startActivity(GalleryActivity.getCallingIntent(this,0, new ArrayList<String>(Arrays.asList(url))));
    }

    @OnClick(R.id.bt_navigate)
    void onClickNavigate(){
        Intent navigation = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + mPlace.getLat() + "," + mPlace.getLng()));
        startActivity(navigation);
    }


    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    @Override
    public void stateErrorNetWork() {

    }
}
