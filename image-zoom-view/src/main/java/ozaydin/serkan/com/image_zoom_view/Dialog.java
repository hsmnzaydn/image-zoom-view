package ozaydin.serkan.com.image_zoom_view;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.github.chrisbanes.photoview.PhotoView;


import static android.content.ContentValues.TAG;

/**
 * Created by hsmnzaydn on 04.08.2018.
 */

public class Dialog extends DialogFragment {

    ImageView dialogBackImageView;

    PhotoView dialogImageView;

    View view;
    private Bitmap bitmap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog, container, false);

        createFullScreenDialogFragment();

        dialogBackImageView=view.findViewById(R.id.dialog_back_image_view);
        dialogImageView=view.findViewById(R.id.dialog_image_view);

        dialogBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        dialogImageView.setImageBitmap(bitmap);
        return view;
    }

    public void show(FragmentManager fragmentManager, Bitmap bitmapFromView) {
        super.show(fragmentManager, TAG);
        bitmap = bitmapFromView;

    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().getAttributes().windowAnimations=R.style.DialogStyle;
    }

    public void createFullScreenDialogFragment() {

        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setContentView(root);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }





}
