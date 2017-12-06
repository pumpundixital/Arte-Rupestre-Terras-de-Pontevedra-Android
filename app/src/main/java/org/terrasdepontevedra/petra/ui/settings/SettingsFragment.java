package org.terrasdepontevedra.petra.ui.settings;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.data.local.LanguageCollector;
import org.terrasdepontevedra.petra.data.local.PreferencesCollector;
import org.terrasdepontevedra.petra.data.service.DownloadContentService;
import org.terrasdepontevedra.petra.data.service.DownloadMapService;
import org.terrasdepontevedra.petra.data.service.DownloadResultReceiver;
import org.terrasdepontevedra.petra.domain.pojo.DownloadedContent;
import org.terrasdepontevedra.petra.domain.pojo.DownloadedMap;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.util.IntroConstants;
import org.terrasdepontevedra.petra.util.LanguageUtils;
import org.terrasdepontevedra.petra.util.MapUtils;

import javax.inject.Inject;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import co.mobiwise.materialintro.prefs.PreferencesManager;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

import static org.terrasdepontevedra.petra.util.Constants.ARG_RECEIVER;

@RuntimePermissions
public class SettingsFragment extends BaseFragment {

    @Inject
    LanguageCollector mLanguageCollector;
    @Inject
    PreferencesCollector mPreferencesCollector;
    @Inject
    EventBus mEventBus;

    @BindView(R.id.cb_english)
    RadioButton mRbEnglish;
    @BindView(R.id.cb_galician)
    RadioButton mRbGalician;
    @BindView(R.id.cb_spain)
    RadioButton mRbSpain;
    @BindView(R.id.ts_petra_tips)
    ToggleSwitch mToggleSwitch;
    @BindView(R.id.btn_download_map)
    FrameLayout mBtnDownloadMap;
    @BindView(R.id.tv_download_map)
    TextView mTvDownloadMap;
    @BindView(R.id.btn_download_content)
    FrameLayout mBtnDownloadContent;
    @BindView(R.id.tv_download_content)
    TextView mTvDownloadContent;

    private String mLanguage;
    private PreferencesManager mPreferencesManager;

    public SettingsFragment() {

    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferencesManager = new PreferencesManager(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLanguage = mLanguageCollector.getLanguage();
        if (mLanguage.equals("gl"))
            mRbGalician.setChecked(true);
        else if (mLanguageCollector.getLanguage().equals("en"))
            mRbEnglish.setChecked(true);
        else
            mRbSpain.setChecked(true);

        mToggleSwitch.setCheckedTogglePosition(isPetraActive());
        mToggleSwitch.setOnToggleSwitchChangeListener((position, isChecked) -> {
            if (isChecked)
                mPreferencesManager.resetAll();
            mPreferencesCollector.setPetraActive(position == 0);
        });


        disabledButtonMapIfDownloaded();
        disabledButtonContentIfDownloaded();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!mEventBus.isRegistered(this))
            mEventBus.register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mEventBus.isRegistered(this))
            mEventBus.unregister(this);
    }

    private void disabledButtonMapIfDownloaded() {
        MapUtils.isMapDownloaded(getContext(), downloaded -> {
            if (downloaded) {
                mBtnDownloadMap.setAlpha(0.5F);
                mBtnDownloadMap.setEnabled(false);
                mTvDownloadMap.setText(R.string.downloaded_map);
            }
        });
    }

    private void disabledButtonContentIfDownloaded() {
        if (mPreferencesCollector.isContentDownloaded()) {
            mBtnDownloadContent.setAlpha(0.5F);
            mBtnDownloadContent.setEnabled(false);
            mTvDownloadContent.setText(R.string.donwloaded_content);
        }
    }

    @Subscribe
    public void onEvent(DownloadedMap downloadedMap) {
        disabledButtonMapIfDownloaded();
    }

    @Subscribe
    public void onEvent(DownloadedContent downloadedContent) {
        mPreferencesCollector.setContentDownloaded(true);
        disabledButtonContentIfDownloaded();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SettingsFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showDenied() {
        Toast.makeText(getContext(), R.string.must_accept_storage_permission_to_donwload_content, Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showNeverAsk() {
        Toast.makeText(getContext(), R.string.must_accept_storage_permission_to_donwload_content_in_settings, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_download_map)
    void onClickDownloadMap() {
        DownloadResultReceiver receiver = getApplication().getReceiver();
        Intent intent = new Intent(Intent.ACTION_SYNC, null, getContext(), DownloadMapService.class);
        intent.putExtra(ARG_RECEIVER, receiver);
        getActivity().startService(intent);
    }


    @OnClick(R.id.btn_download_content)
    void onClickDownloadContent() {
        SettingsFragmentPermissionsDispatcher.startDownloadContentServiceWithPermissionCheck(this);
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void startDownloadContentService() {
        DownloadResultReceiver receiver = getApplication().getReceiver();
        Intent intent = new Intent(Intent.ACTION_SYNC, null, getContext(), DownloadContentService.class);
        intent.putExtra(ARG_RECEIVER, receiver);
        getActivity().startService(intent);
    }

    @OnCheckedChanged({R.id.cb_english, R.id.cb_galician, R.id.cb_spain})
    public void onCheckedChangeLanguage(RadioButton radioButton, boolean isChecked) {
        String tag = (String) radioButton.getTag();
        if (isChecked && !tag.equals(mLanguage)) {
            LanguageUtils.apply(getContext(), tag);
            mLanguageCollector.setLanguage(tag);
            getActivity().recreate();
        }
    }

    public int isPetraActive() {
        boolean allIntrosSee =
                (mPreferencesManager.isDisplayed(IntroConstants.FILTER)
                        && mPreferencesManager.isDisplayed(IntroConstants.MAIN)
                        && mPreferencesManager.isDisplayed(IntroConstants.FULL_SCREEN)
                        && mPreferencesManager.isDisplayed(IntroConstants.MENU_HORIZONTAL));

        return mPreferencesCollector.isPetraActive() && !allIntrosSee ? 0 : 1;
    }
}
