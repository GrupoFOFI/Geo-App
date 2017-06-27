package com.ucr.fofis.geoapp.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.LinearLayout;

/**
 * Base class for dialogs.
 * Classes that inherit from this class must implement onCreateDialog and call setContentView.
 *
 * Created by enrico on 8/30/16.
 */
public abstract class Dialog implements DialogInterface.OnDismissListener {

    private Context thisContext;
    private final android.app.Dialog dialog;

    /**
     * Event called after the dialog has been created.
     * Should be used to set content view and get views from the dialog.
     *
     * @param dialog the created dialog.
     */
    protected abstract void onCreateDialog(final android.app.Dialog dialog);

    public Dialog(Context context) {
        thisContext = context;
        if (context != null) {
            dialog = new android.app.Dialog(context);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setOnDismissListener(this);
        } else dialog = null;
    }

    /**
     * Creates the dialog
     */
    public void create() {
        if (dialog != null)
            onCreateDialog(dialog);
    }

    /**
     * Displays the dialog
     */
    public void showDialog() {
        if (dialog != null)
            dialog.show();
    }

    /**
     * Specifies the resource id for the dialog's layout.
     * @param id the resource id.
     */
    protected void setContentView(int id) {
        dialog.setContentView(id);
    }

    protected android.app.Dialog getDialog(){
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

    }

    public void AdjustLayoutToScreen(LinearLayout layout, android.app.Dialog dialog){
        AdjustLayoutToScreen(layout, 0.825f, 0.73f, dialog);
    }

    public void AdjustLayoutToScreen(LinearLayout layout, float widthRatio, float heightRatio, android.app.Dialog dialog){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        dialog.getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        double displaySize = height * heightRatio;
        layout.getLayoutParams().height = (int)displaySize;
        displaySize = width * widthRatio;
        layout.getLayoutParams().width = (int) displaySize;
        layout.requestLayout();
    }

    public abstract void onCreate(Bundle savedInstanceState);
}