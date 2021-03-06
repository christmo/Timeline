package com.smartpocket.timeline2.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.smartpocket.timeline2.R;
import com.squareup.picasso.Picasso;

/**
 * Displays an image in the screen, as big as possible.<br>
 * Includes an immersive mode to hide the toolbar/status bar on tap.
 */
public class ViewImageActivity extends AppCompatActivity {
    private static final String EXTRA_IMAGE = "ViewImageActivity:image";
    private boolean isImmersive = false;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        toolbar = (Toolbar) findViewById(R.id.view_image_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ImageView image = (ImageView) findViewById(R.id.image);
        ViewCompat.setTransitionName(image, EXTRA_IMAGE);
        Picasso.with(this).load(getIntent().getStringExtra(EXTRA_IMAGE)).into(image);
    }

    public static void launch(Activity activity, View transitionView, String url) {
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, transitionView, EXTRA_IMAGE);
        Intent intent = new Intent(activity, ViewImageActivity.class);
        intent.putExtra(EXTRA_IMAGE, url);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    /**
     * Enables immersive mode
     */
    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    /**
     * Disables immersive mode
     */
    private void showSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(0);
    }

    public void toggleImmersiveMode(View v) {
        if (!isImmersive) {
            hideSystemUI();
            toolbar.setVisibility(View.GONE);
        }else {
            showSystemUI();
            toolbar.setVisibility(View.VISIBLE);
        }

        isImmersive = !isImmersive;
    }
}
