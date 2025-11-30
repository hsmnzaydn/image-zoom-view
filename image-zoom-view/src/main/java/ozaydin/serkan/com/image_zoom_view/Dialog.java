package ozaydin.serkan.com.image_zoom_view;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import static android.content.ContentValues.TAG;

import java.util.Objects;

/**
 * Created by hsmnzaydn on 04.08.2018.
 */

public class Dialog extends DialogFragment {

    ImageView dialogBackImageView;
    ImageView threeDotImageView;
    SubsamplingScaleImageView dialogImageView;
    View view;
    ImageViewZoomBottomSheet imageViewZoomBottomSheet;
    ImageViewZoomConfig imageViewZoomConfig;
    ImageSaveProperties imageSaveProperties;
    private Bitmap bitmap;
    private RelativeLayout root;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog, container, false);

        createFullScreenDialogFragment();

        init();

        dialogBackImageView.setOnClickListener(view -> dismiss());


        threeDotImageView.setOnClickListener(view -> {
            if (imageSaveProperties != null) {
                imageViewZoomBottomSheet.setConfiguration(getParentFragmentManager(), imageViewZoomConfig, imageSaveProperties, bitmap);
            } else {
                imageViewZoomBottomSheet.setConfiguration(getParentFragmentManager(), imageViewZoomConfig, bitmap);
            }
        });


        configuration(imageViewZoomConfig);

        dialogImageView.setImage(ImageSource.bitmap(bitmap));

        return view;
    }


    public void init() {
        dialogBackImageView = view.findViewById(R.id.dialog_back_image_view);
        dialogImageView = view.findViewById(R.id.dialog_image_view);
        threeDotImageView = view.findViewById(R.id.dialog_three_dot);
    }

    /**
     * When ImageSaveProperties is null
     *
     * @param fragmentManager
     * @param bitmapFromView
     * @param imageViewZoomConfig
     */
    public void show(FragmentManager fragmentManager, Bitmap bitmapFromView, ImageViewZoomConfig imageViewZoomConfig) {
        super.show(fragmentManager, TAG);
        bitmap = bitmapFromView;
        this.imageViewZoomConfig = imageViewZoomConfig;
    }

    /**
     * When ImageSaveProperties is not null
     *
     * @param fragmentManager
     * @param bitmapFromView
     * @param imageViewZoomConfig
     * @param imageSaveProperties
     */
    public void show(FragmentManager fragmentManager, Bitmap bitmapFromView, ImageViewZoomConfig imageViewZoomConfig, ImageSaveProperties imageSaveProperties) {
        super.show(fragmentManager, TAG);
        bitmap = bitmapFromView;
        this.imageViewZoomConfig = imageViewZoomConfig;
        this.imageSaveProperties = imageSaveProperties;
    }

    /**
     * Decide show three dot icon show
     *
     * @param imageViewZoomConfig ImageViewZoom configuration object
     */
    public void configuration(ImageViewZoomConfig imageViewZoomConfig) {
        if (imageViewZoomConfig != null) {
            threeDotImageView.setVisibility(View.VISIBLE);
            imageViewZoomBottomSheet = new ImageViewZoomBottomSheet();
            this.imageViewZoomConfig = imageViewZoomConfig;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog()).getWindow().getAttributes().windowAnimations = R.style.DialogStyle;
    }

    /**
     * Use for show full screen ImageViewZoom
     */
    public void createFullScreenDialogFragment() {
        root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        Objects.requireNonNull(getDialog()).requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setContentView(root);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }


}
