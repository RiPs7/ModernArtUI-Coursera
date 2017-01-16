package perimara.modernartui.dialogs;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import perimara.modernartui.R;

/**
 * Created by periklismaravelias on 16/01/17.
 */
public class MoreInfoDialog extends DialogFragment {

    String mTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.more_info_dialog, container, false);
        rootView.findViewById(R.id.visitMoMA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visit = new Intent( Intent.ACTION_VIEW, Uri.parse("http://www.moma.org") );
                Intent chooser = Intent.createChooser(visit, getResources().getString(R.string.open_with));
                startActivity(chooser);
                getDialog().dismiss();
            }
        });
        rootView.findViewById(R.id.notNow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return rootView;
    }

}
