package solo.shinhan.com.solo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class CollocateFurnitureView extends View {
    float x = 980;
    float y = 420;

    int currentFurniture =-1;

    public CollocateFurnitureView(Context context, AttributeSet attrs) {
        super(context);
    }

    public CollocateFurnitureView(Context context) {
        super(context);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for(int i=0; i<SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().size(); i++) {
            if (SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(i).isSelectFurniture()) {
                currentFurniture = i;
                break;
            } else {
                currentFurniture = -1;
            }
        }

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                if( currentFurniture != -1) {
                    SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(currentFurniture).setX(x);
                    SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(currentFurniture).setY(y);
                    invalidate();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                if( currentFurniture != -1) {
                    SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(currentFurniture).setX(x);
                    SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(currentFurniture).setY(y);
                    invalidate();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        if(SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().size() > 0 ) {
            for(int i = 0 ; i<SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().size(); i++) {
                final Bitmap[] newBitmap = new Bitmap[1];
                final int finalI = i;
                Picasso.get().load(SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(i).getFurnitureInfo().getFurnitureImageString()).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        canvas.drawBitmap( resizeBitmapImage(bitmap,
                                150),SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(finalI).getX(),
                                SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(finalI).getY(),null);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
                /*
                canvas.drawBitmap( resizeBitmapImage(SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(i).getFurnitureInfo().getFurnitureImage(),
                        150),SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(i).getX(),
                        SoloSingleton.getInstance().getMyCollocateFurnitureInfoList().get(i).getY(),null);*/
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

    }

    public Bitmap resizeBitmapImage(Bitmap source, int maxResolution)
    {
        /*
        int width = source.getWidth();
        int height = source.getHeight();
        int newWidth = width;
        int newHeight = height;
        float rate = 0.0f;

        if(width > height) {
            if(maxResolution < width) {
                rate = maxResolution / (float) width;
                newHeight = (int) (height * rate);
                newWidth = maxResolution;
            }
        }
        else {
            if(maxResolution < height) {
                rate = maxResolution / (float) height;
                newWidth = (int) (width * rate);
                newHeight = maxResolution;
            }
        }
        */
        return Bitmap.createScaledBitmap(source, 110, 110, true);
    }
}
