package com.connectydots.dustin.primal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import java.util.Random;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    public class DrawView extends View {
        Bitmap bufferBitmap;
        Canvas bufferCanvas;
        Point screenSize;
        Random rand = new Random();

        public DrawView(Context context){
            super(context);

            //retreave screen size before canvas is ready
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            screenSize = new Point(metrics.widthPixels,metrics.heightPixels);

            //create back buffer
            bufferBitmap = Bitmap.createBitmap(screenSize.x,screenSize.y,Bitmap.Config.ARGB_8888);
            bufferCanvas = new Canvas(bufferBitmap);
        }

        @Override
        public void onDraw(Canvas canvas){
            super.onDraw(canvas);

            //fill back buffer with graphics
            drawOnBuffer();

            //copy the back buffer to the screen
            canvas.drawBitmap(bufferBitmap, 0, 0, new Paint());
        }

        public void drawOnBuffer() {
            Paint paint = new Paint();
            paint.setAntiAlias(true);

            //clear the buffer with color
            bufferCanvas.drawColor(Color.WHITE);

            //draw random circles
            for (int n=0; n<500; n++){
                //make random color
                int r = rand.nextInt(256);
                int g = rand.nextInt(256);
                int b = rand.nextInt(256);
                paint.setColor(Color.rgb(r, g, b));

                //make random position and radius
                int x = rand.nextInt(bufferCanvas.getWidth());
                int y = rand.nextInt(bufferCanvas.getHeight());
                int radius = rand.nextInt(100) + 20;

                //draw one circle
                bufferCanvas.drawCircle(x, y, radius, paint);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
