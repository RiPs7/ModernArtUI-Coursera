package perimara.modernartui.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import perimara.modernartui.R;
import perimara.modernartui.dialogs.MoreInfoDialog;

public class MainActivity extends AppCompatActivity {

    //region Variables

    //store all the views in this array
    View[] views = new View[8];

    //store all the starting colors as the offsets for the seek bar progress
    final int[] startingColors = new int[] {
        Color.rgb(255, 255, 255), //white
        Color.rgb(255, 0, 0),
        Color.rgb(0, 255, 0),
        Color.rgb(0, 0, 255),
        Color.rgb(0, 123, 123),
        Color.rgb(123, 123, 0),
        Color.rgb(123, 0, 123),
        Color.rgb(123, 123, 123), //grey
    };

    //multiplier for the progress bar
    final double multiplier = 0.01;

    //store the seekbar
    SeekBar seekBar1;

    //define menu item ids
    final int MENU_MORE_INFO = Menu.FIRST;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate all the views
        views[0] = findViewById(R.id.view1);
        views[1] = findViewById(R.id.view2);
        views[2] = findViewById(R.id.view3);
        views[3] = findViewById(R.id.view4);
        views[4] = findViewById(R.id.view5);
        views[5] = findViewById(R.id.view6);
        views[6] = findViewById(R.id.view7);
        views[7] = findViewById(R.id.view8);

        //initiate views' background colors
        for (int i = 0; i < startingColors.length; i++){
            views[i].setBackgroundColor(startingColors[i]);
        }

        //instantiate seekbar
        seekBar1 = (SeekBar)findViewById(R.id.seekBar1);

        //implement the OnSeekBarChangeListener
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                //for each view...
                for (int i = 0; i < views.length; i++) {

                    //skip processing white and grey views
                    if (startingColors[i] == Color.rgb(255, 255, 255) || startingColors[i] == Color.rgb(123, 123, 123)){
                        continue;
                    }

                    //get the r, g, b components for the corresponding view starting color
                    int starting_red = Color.red(startingColors[i]);
                    int starting_green = Color.green(startingColors[i]);
                    int starting_blue = Color.blue(startingColors[i]);

                    //define a new color to process the starting color
                    int subtracted_color = (0x00FFFFFF - (startingColors[i] | 0xAA00DD) | (startingColors[i] & 0x9922FF));

                    //get the r, g, b components for new color
                    int subtracted_red = Color.red(subtracted_color);
                    int subtracted_green = Color.green(subtracted_color);
                    int subtracted_blue = Color.blue(subtracted_color);

                    //generate the new current color
                    int currentColor = Color.argb(
                            Color.alpha(startingColors[i]),
                            (int) (starting_red + (subtracted_red - starting_red) * progress * multiplier),
                            (int) (starting_green + (subtracted_green - starting_green) * progress * multiplier),
                            (int) (starting_blue + (subtracted_blue - starting_blue) * progress * multiplier)
                    );

                    //set the new current color to the corresponding view's background
                    views[i].setBackgroundColor(currentColor);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //region Options Menu

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        menu.add(Menu.NONE, MENU_MORE_INFO, Menu.NONE, "More Information");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case MENU_MORE_INFO:
                (new MoreInfoDialog()).show(getFragmentManager(), null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //endregion
}
