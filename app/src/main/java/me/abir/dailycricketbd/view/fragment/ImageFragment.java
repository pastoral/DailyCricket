package me.abir.dailycricketbd.view.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.adapter.ImageAdapter;
import me.abir.dailycricketbd.interfaces.OnPhotoResponseListener;
import me.abir.dailycricketbd.model.image_module.WrapperImageModel;
import me.abir.dailycricketbd.model.image_module_v2.Datum;
import me.abir.dailycricketbd.model.image_module_v2.Photo;
import me.abir.dailycricketbd.model.image_module_v2.PhotosModel;
import me.abir.dailycricketbd.rest.CricApiResponseHandler;
import me.abir.dailycricketbd.util.LocaleManager;

/**
 * Created by Abir on 01-Nov-17.
 */

public class ImageFragment extends Fragment implements OnPhotoResponseListener {

    private static final String TAG = "ImageFragment";
    private WrapperImageModel wrapperModel = null;
    private CricApiResponseHandler handler;
    private ImageAdapter imageAdapter;
    private List<Object> photoList;
    private RecyclerView rvImages;
    private boolean shouldRequest;
    private ProgressBar pbImage;
    private boolean isHalfView;
    private int photoCount = 0;
    private Context context;
    private int pageNo = 1;
    private AdView mAdView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        init(view);
        initAd(view);
        getPhotosFromApi();
        return view;
    }

    private void initAd(View view) {
        mAdView = view.findViewById(R.id.adBanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    private void getPhotosFromApi() {
        Log.d(TAG, "getPhotosFromApi() called");
        handler = new CricApiResponseHandler(LocaleManager.getLanguage(getContext()));
        handler.setOnPhotoResponseListener(this);
        handler.getPhotos(pageNo);
        shouldRequest = false;
    }

    private void init(View view) {
        photoList = new ArrayList<>();

        pbImage = view.findViewById(R.id.pbImage);
        rvImages = view.findViewById(R.id.rvImages);

        final LinearLayoutManager manager = new LinearLayoutManager(context);
        rvImages.setLayoutManager(manager);

        /*rvImages.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    EventBus.getDefault().post(new ScrollEventBus(true));
                } else {
                    EventBus.getDefault().post(new ScrollEventBus(false));
                }

                int last = (manager).findLastVisibleItemPosition();
                Log.e(TAG, "onScrolled() lastPos: " + last);
                if (last == photoList.size() - 1 && pageNo != 0 && shouldRequest) {
                    handler.getPhotos(pageNo);
                    pbImage.setVisibility(View.VISIBLE);
                    shouldRequest = false;
                }

            }
        });*/

        imageAdapter = new ImageAdapter();
        rvImages.setAdapter(imageAdapter);
    }

    @Override
    public void onPhotoResponse(PhotosModel imageModel) {
        Log.d(TAG, "onPhotoResponse() called with: imageModel = [" + imageModel + "]");
        if (imageModel != null) {
            List<Datum> dataList = imageModel.getData();
            for (Datum datum : dataList) {
                List<Photo> photos = datum.getPhotos();
                photoList.addAll(photos);
            }
            imageAdapter.setData(photoList);
        } else {
            Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
        }
        pbImage.setVisibility(View.INVISIBLE);
    }


    /*@Override
    public void onPhotoResponse(ImageModel imageModel) {
        Log.d(TAG, "onPhotoResponse() called with: imageModel = [" + imageModel + "]");
        if (imageModel != null) {
            List<ImageObject> objects = imageModel.getData();
            //int pageCount = imageModel.getCurrentPage();

            for (ImageObject object : objects) {
                Log.d(TAG, "onPhotoResponse() val = [" + object.getTitle() + "]");
                if (photoCount == 0) {
                    photoList.add(object);
                } else {
                    if (photoCount % 2 == 0) {
                        if (wrapperModel != null) {
                            wrapperModel.setSecondImageModel(object);
                            Log.w(TAG, "onPhotoResponse() adding 2");
                        }
                    } else {
                        wrapperModel = new WrapperImageModel();
                        wrapperModel.setFirstImageModel(object);
                        isHalfView = false;
                        Log.w(TAG, "onPhotoResponse() adding 1");
                    }
                    //adding rule
                    if (photoCount % 2 == 0 && !isHalfView || photoCount == objects.size() - 1) {
                        photoList.add(wrapperModel);
                        isHalfView = false;
                    }
                }
                photoCount++;
            }
            // Setting Data
            if (photoCount % 2 == 0) {
                isHalfView = true;
            }
            imageAdapter.setData(photoList);

            if (imageModel.getNextPageUrl() != null) {
                shouldRequest = true;
                pageNo++;
            } else {
                shouldRequest = false;
                pageNo = 0;
            }
        } else {
            Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
        }
        pbImage.setVisibility(View.INVISIBLE);
    }*/
}