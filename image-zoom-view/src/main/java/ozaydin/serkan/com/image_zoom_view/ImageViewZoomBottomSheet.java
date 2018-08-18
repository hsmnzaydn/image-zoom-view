package ozaydin.serkan.com.image_zoom_view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

import static android.content.ContentValues.TAG;

public class ImageViewZoomBottomSheet extends BottomSheetDialogFragment {

    private View root;
    private LinearLayout downloadImageLinearLayout;
    private ImageViewZoomConfig config;
    private Bitmap bitmap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        init();
        configuration();

        downloadImageLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageProperties.saveImage(bitmap, Bitmap.CompressFormat.JPEG, new SaveFileListener() {
                    @Override
                    public void onSuccess(File file) {
                        Toast.makeText(getActivity(),getActivity().getResources().getText(R.string.description_success),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(Exception exception) {
                        Toast.makeText(getActivity(),getActivity().getResources().getText(R.string.description_fail),Toast.LENGTH_SHORT).show();
                    }
                });

                dismiss();
            }
        });


        return root;

    }

    public void configuration(){
        if(config.getIsSave()){
            downloadImageLinearLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Set configuration
     * @param fragmentManager
     * @param imageViewZoomConfig Imageviewzoom configuration object
     * @param bitmap Image bitmap
     */
    public  void setConfiguration(FragmentManager fragmentManager, ImageViewZoomConfig imageViewZoomConfig, Bitmap bitmap) {
        super.show(fragmentManager, TAG);
        this.config=imageViewZoomConfig;
        this.bitmap=bitmap;
    }

    public void init(){
        downloadImageLinearLayout=root.findViewById(R.id.bottom_sheet_layout_download_image_linear_layout);
    }

}
