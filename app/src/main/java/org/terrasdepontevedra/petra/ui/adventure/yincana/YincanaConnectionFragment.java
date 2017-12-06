package org.terrasdepontevedra.petra.ui.adventure.yincana;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.terrasdepontevedra.petra.PetraApplication;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.data.service.GeofenceTrasitionService;
import org.terrasdepontevedra.petra.data.service.LocationService;
import org.terrasdepontevedra.petra.domain.pojo.OnGeofenceServiceCreated;
import org.terrasdepontevedra.petra.domain.pojo.OnGooogleApiConnected;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.ui.views.PetraLoading;
import org.terrasdepontevedra.petra.util.IntentUtils;

import javax.inject.Inject;

import butterknife.BindView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import timber.log.Timber;


@RuntimePermissions
public class YincanaConnectionFragment extends BaseFragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private Context mContext;

    @BindView(R.id.loading)
    PetraLoading mLoading;

    @BindView(R.id.layout_action_required)
    View mLayourError;

    @Inject
    EventBus mBus;

    private OnYincanaConnectionListener mListener;

    /*
    * TODO: Revisar google play services
    * TODO: Revisar permisos
    * TODO: Iniciar servicios
    * TODO: Iniciar geofences
    * TODO: Mostrar errores
    * */
    public static YincanaConnectionFragment newInstance(){
        return new YincanaConnectionFragment();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        YincanaConnectionFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_yincana_connection;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if(mContext instanceof OnYincanaConnectionListener) {
            mListener = (OnYincanaConnectionListener)mContext;
        }
        else{
            throw new RuntimeException(this.getActivity().getClass().getName()+" must implement some interface");
        }
        if (!mBus.isRegistered(this))
            mBus.register(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(checkGoogleServices()) {
            YincanaConnectionFragmentPermissionsDispatcher.startServicesWithPermissionCheck(this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mBus.isRegistered(this))
            mBus.unregister(this);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Timber.i("on connected");
        ((PetraApplication)(getActivity().getApplication())).setGoogleApiClient(mGoogleApiClient);
        getActivity().startService(LocationService.getCallingIntent(mContext));
        getActivity().startService(GeofenceTrasitionService.getCallingIntent(mContext));
    }

    @Override
    public void onConnectionSuspended(int i) {
        Timber.i("on connection suspended");
        Toast.makeText(mContext,"onConnectionSuspended!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Timber.i("on connection failed");
        Toast.makeText(mContext,"onConnectionFailed!",Toast.LENGTH_SHORT).show();
    }

    private void connectGoogleApi(){
         mGoogleApiClient =  new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi( LocationServices.API )
                .build();
         mGoogleApiClient.connect();
    }


    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
     void startServices(){

        if(((PetraApplication)(getActivity().getApplication())).getGoogleApiClient()==null) {
            connectGoogleApi();
        }
        else{
            mListener.onGoogleApiConnected();
        }
    }

    @OnShowRationale(Manifest.permission.ACCESS_FINE_LOCATION)
    void showRationaleForLocation(final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setMessage(R.string.permission_location_rationale)
                .setPositiveButton(R.string.button_allow, (dialog, button) -> request.proceed())
                .setNegativeButton(R.string.button_deny, (dialog, button) -> request.cancel())
                .show();
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    void showDeniedForLocation() {
         showErrorState();
        showDeniedPermissionForLocation();
    }

    @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
    void showNeverAskForLocation() {
        showNeverAskForPermissions();
    }

    private void showErrorState(){
        mLoading.setVisibility(View.GONE);
        mLayourError.setVisibility(View.VISIBLE);
    }

    private void showDeniedPermissionForLocation(){
        ((Button) mLayourError.findViewById(R.id.btn_action_error)).setText(R.string.activate_location);
        mLayourError.findViewById(R.id.btn_action_error).setOnClickListener(view -> IntentUtils.openActivitySettings(getContext()));
    }

    private void showNeverAskForPermissions(){
        ((Button) mLayourError.findViewById(R.id.btn_action_error)).setText(R.string.go_to_settings);
        showErrorState();
        mLayourError.findViewById(R.id.btn_action_error).setOnClickListener(view -> IntentUtils.openActivitySettings(getContext()));
    }

    private boolean checkGoogleServices(){
        GoogleApiAvailability gApi = GoogleApiAvailability.getInstance();
        int resultCode = gApi.isGooglePlayServicesAvailable(getContext());
        if (resultCode != ConnectionResult.SUCCESS) {
           showErrorState();
           return false;
        }
        return true;
    }

    @Subscribe
    public void onGeofenceServiceCreated(OnGeofenceServiceCreated event) {
        Timber.i("on geofence service received");
        mBus.post(new OnGooogleApiConnected(mGoogleApiClient));
        mListener.onGoogleApiConnected();

    }

    public interface OnYincanaConnectionListener{
        void onGoogleApiConnected();
    }




}
