package org.terrasdepontevedra.petra.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.mapbox.mapboxsdk.offline.OfflineManager;
import com.mapbox.mapboxsdk.offline.OfflineRegion;

import org.json.JSONException;
import org.json.JSONObject;
import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.domain.model.PlaceCollection;

import java.util.List;

import timber.log.Timber;

import static org.terrasdepontevedra.petra.data.service.DownloadMapService.JSON_FIELD_REGION_NAME;
import static org.terrasdepontevedra.petra.util.Constants.MAPBOX_REGION_NAME;

public class MapUtils {


    public static void canUseMapBox(Context context, OnMapUtilsListener mapUtilsListener) {
        if (NetworkUtil.isConnected(context)) {
            mapUtilsListener.canUseMapBox(false);
        } else {
            isMapDownloaded(context, mapUtilsListener);
        }
    }

    public static void isMapDownloaded(Context context, OnMapUtilsListener mapUtilsListener) {
        OfflineManager offlineManager = OfflineManager.getInstance(context);
        offlineManager.listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() {
            @Override
            public void onList(OfflineRegion[] offlineRegions) {
                if (offlineRegions.length == 0) {
                    mapUtilsListener.canUseMapBox(false);
                } else {
                    for (OfflineRegion offlineRegion : offlineRegions) {
                        try {
                            JSONObject json = new JSONObject(new String(offlineRegion.getMetadata()));
                            String name = json.getString(JSON_FIELD_REGION_NAME);
                            Timber.i("name :" + name);
                            mapUtilsListener.canUseMapBox(name.equals(MAPBOX_REGION_NAME));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onError(String error) {
                mapUtilsListener.canUseMapBox(false);
            }
        });
    }

    public static BitmapDescriptor getBitmapDescriptor(Context context, int id) {
        Bitmap bm = null;
        Drawable vectorDrawable = context.getDrawable(id);
        int h = DimensUtil.convertDpToPixel(context, 42);
        int w = DimensUtil.convertDpToPixel(context, 42);
        if (vectorDrawable != null) {
            vectorDrawable.setBounds(0, 0, w, h);
            bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bm);
            vectorDrawable.draw(canvas);
        }
        return BitmapDescriptorFactory.fromBitmap(bm);
    }

    public static Bitmap drawableToBitmap(Context context, int id) {
        Drawable drawable = ContextCompat.getDrawable(context, id);
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static LatLngBounds getCenterFrom(PlaceCollection placeCollection) {
        LatLngBounds.Builder latLngBounds = new LatLngBounds.Builder();
        double latitudeMax = 0;
        double longitudeMax = 0;

        for (Place place : placeCollection.asList()) {
            latLngBounds.include(new LatLng(place.getLocale().getLatitude(), place.getLocale().getLongitude()));

        }

        return latLngBounds.build();
    }

    public static LatLngBounds getCenterFrom(List<org.terrasdepontevedra.petra.domain.model.walk.Place> placeCollection) {
        LatLngBounds.Builder latLngBounds = new LatLngBounds.Builder();
        double latitudeMax = 0;
        double longitudeMax = 0;

        for (org.terrasdepontevedra.petra.domain.model.walk.Place place : placeCollection) {
            latLngBounds.include(new LatLng(place.getLat(), place.getLng()));

        }

        return latLngBounds.build();
    }

    public interface OnMapUtilsListener {
        void canUseMapBox(boolean downloaded);
    }


}
