package com.ianduran.flashlight;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button switchButton;
    private Camera cam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cam = Camera.open();
        cam.setParameters(cam.getParameters());
        switchButton = (Button) findViewById(R.id.switch_button);
        switchButton.setText(getResources().getString(R.string.off_text));
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Camera.Parameters p = cam.getParameters();
                if(p.getFlashMode().equals(Camera.Parameters.FLASH_MODE_OFF)){
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    switchButton.setText(getResources().getString(R.string.on_text));
                }else{
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    switchButton.setText(getResources().getString(R.string.off_text));
                }
                cam.setParameters(p);
                cam.startPreview();
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        cam.release();
    }
}
