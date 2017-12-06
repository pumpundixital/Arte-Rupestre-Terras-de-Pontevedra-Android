package org.terrasdepontevedra.petra.ui.wizard;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.terrasdepontevedra.petra.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static org.terrasdepontevedra.petra.util.Constants.ARG_WIZARD_DESC;
import static org.terrasdepontevedra.petra.util.Constants.ARG_WIZARD_ICON;
import static org.terrasdepontevedra.petra.util.Constants.ARG_WIZARD_POSITION;
import static org.terrasdepontevedra.petra.util.Constants.ARG_WIZARD_TITLE;

public class WizardFragment extends Fragment {

    @BindView(R.id.image_wizard_icon)
    ImageView mIconWizard;

    @BindView(R.id.text_wizard_title)
    TextView mTextWizardTitle;

    @BindView(R.id.text_wizard_desc)
    TextView mTextWizardDesc;

    @BindView(R.id.layout_wizard)
    View mLayoutWizard;

    private Unbinder mUnbind;

    @DrawableRes
    private int mIcon;

    @StringRes
    private int mTitle;
    @StringRes
    private int mDescription;

    public int position;


    public static WizardFragment newInstance(int position, @DrawableRes int icon, @StringRes int title, @StringRes int description) {
        WizardFragment fragment = new WizardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_WIZARD_POSITION, position);
        args.putInt(ARG_WIZARD_ICON, icon);
        args.putInt(ARG_WIZARD_TITLE, title);
        args.putInt(ARG_WIZARD_DESC, description);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            if (getArguments().containsKey(ARG_WIZARD_POSITION)) {
                position = getArguments().getInt(ARG_WIZARD_POSITION);
            }
            if (getArguments().containsKey(ARG_WIZARD_TITLE)) {
                mTitle = getArguments().getInt(ARG_WIZARD_TITLE);
            }
            if (getArguments().containsKey(ARG_WIZARD_DESC)) {
                mDescription = getArguments().getInt(ARG_WIZARD_DESC);
            }
            if (getArguments().containsKey(ARG_WIZARD_ICON)) {
                mIcon = getArguments().getInt(ARG_WIZARD_ICON);
            }
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_wizard, container, false);
        mUnbind = ButterKnife.bind(this, v);
        mIconWizard.setImageResource(mIcon);
        mTextWizardTitle.setText(mTitle);
        mTextWizardDesc.setText(mDescription);



        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbind.unbind();
    }

}

