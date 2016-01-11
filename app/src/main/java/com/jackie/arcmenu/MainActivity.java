package com.jackie.arcmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jackie.arcmenu.view.ArcMenu;

public class MainActivity extends Activity {
    private ArcMenu mArcMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mArcMenu = (ArcMenu) findViewById(R.id.arc_menu);
        mArcMenu.setonMenuItemClickListener(new ArcMenu.onMenuItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, position + ": " + view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
