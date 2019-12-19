package me.abir.dailycricketbd.view.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import me.abir.dailycricketbd.BaseActivity;
import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.model.image_module_v2.Photo;
import me.abir.dailycricketbd.util.Configuration;

/**
 * Created by Abir on 11-May-18.
 */
public class FullImageActivity extends BaseActivity {

    private ImageView ivFullImage;
    private TextView tvTitle;

    private String url;
    private String title;
    private Photo imageObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        extractData();
        initUI();
        setData();
    }


    private void extractData() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Configuration.TAG_IMAGE_MODEL)) {
            imageObject = intent.getParcelableExtra(Configuration.TAG_IMAGE_MODEL);
            url = imageObject.getUrl();
            title = imageObject.getTitle();
        }
    }

    private void initUI() {
        ivFullImage = findViewById(R.id.ivFullImage);
        tvTitle = findViewById(R.id.tvTitle);
    }


    private void setData() {
        tvTitle.setText(title);
        Picasso.with(this.getApplicationContext())
                .load(url)
                .placeholder(R.mipmap.app_icon)
                .error(R.mipmap.app_icon)
                .into(ivFullImage);
    }
}
