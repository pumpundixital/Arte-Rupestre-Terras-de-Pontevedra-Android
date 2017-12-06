package org.terrasdepontevedra.petra.ui.gallery.horizontal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

import static org.terrasdepontevedra.petra.ui.gallery.GalleryActivity.getCallingIntent;
import static org.terrasdepontevedra.petra.util.Constants.ARG_IMAGES_URLS;


public class GalleryHorizontalFragment extends BaseFragment implements GalleryHorizontalAdapter.OnClickImageGalleryAdapter {

    private ArrayList<String> mImagesUrl;

    @BindView(R.id.recycler_horizontal_gallery)
    RecyclerView mRecycler;

    public static GalleryHorizontalFragment newInstance(ArrayList<String> imagesUrls) {
        GalleryHorizontalFragment fragment = new GalleryHorizontalFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_IMAGES_URLS, imagesUrls);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImagesUrl = getArguments().getStringArrayList(ARG_IMAGES_URLS);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecycler.setLayoutManager(linearLayoutManager);
        GalleryHorizontalAdapter adapter = new GalleryHorizontalAdapter(mImagesUrl);
        adapter.setListener(this);
        mRecycler.setAdapter(adapter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_gallery_horizontal;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onClickItem(int position) {
        startActivity(getCallingIntent(getContext(), position, mImagesUrl));
    }


}
