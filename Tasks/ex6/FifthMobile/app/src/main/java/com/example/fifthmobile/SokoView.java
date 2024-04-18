package com.example.fifthmobile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by kru13 on 12.10.16.
 */
public class SokoView extends View {

    Bitmap[] bmp;

    int lx = 10;
    int ly = 10;

    int width;
    int height;
    int heroloc = 46;
    int prevbmp = 0;
    private int level[] = {
            1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
            1, 0, 0, 0, 0, 0, 0, 0, 1, 0,
            1, 0, 2, 3, 3, 2, 1, 0, 1, 0,
            1, 0, 1, 3, 2, 3, 2, 0, 1, 0,
            1, 0, 2, 3, 3, 2, 4, 0, 1, 0,
            1, 0, 1, 3, 2, 3, 2, 0, 1, 0,
            1, 0, 2, 3, 3, 2, 1, 0, 1, 0,
            1, 0, 0, 0, 0, 0, 0, 0, 1, 0,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    };

    public void set(int herol,int prevbitmap ,int[] l,int levx,int levy)
    {level=l;heroloc=herol;prevbmp=prevbitmap;lx=levx;ly=levy;}
    public SokoView(Context context) {
        super(context);
        init(context);
    }

    public SokoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SokoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        bmp = new Bitmap[6];

        bmp[0] = BitmapFactory.decodeResource(getResources(), R.drawable.empty);
        bmp[1] = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
        bmp[2] = BitmapFactory.decodeResource(getResources(), R.drawable.box);
        bmp[3] = BitmapFactory.decodeResource(getResources(), R.drawable.goal);
        bmp[4] = BitmapFactory.decodeResource(getResources(), R.drawable.hero);
        bmp[5] = BitmapFactory.decodeResource(getResources(), R.drawable.boxok);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / ly;
        height = h / lx;
        super.onSizeChanged(w, h, oldw, oldh);


    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < lx; i++) {
            for (int j = 0; j < ly; j++) {
                canvas.drawBitmap(bmp[level[i * 10 + j]], null,
                        new Rect(j * width, i * height, (j + 1) * width, (i + 1) * height), null);
            }
        }

    }

    private void moveHorizontally(int step) {
        switch (level[heroloc + step]) {
            case 0:
            case 3: {
                Log.d("MOVE:", "You can move here!");
                level[heroloc] = prevbmp;
                prevbmp = level[heroloc + step];
                heroloc += step;
                level[heroloc] = 4;

                break;
            }
            case 5:
            case 2: {
                Log.d("MOVE:", "You can move here and push!");
                level[heroloc] = prevbmp;
                if (step > 0) {
                    int behindbox = level[heroloc + step + 1];
                    if (behindbox == 0 || behindbox == 3) {
                        prevbmp = level[heroloc + step];
                        heroloc += step;

                        level[heroloc] = 4;
                        level[heroloc + 1] = behindbox + 2;

                    }

                } else if (step < 0) {
                    int behindbox = level[heroloc + step - 1];
                    if (behindbox == 0 || behindbox == 3) {
                        prevbmp = level[heroloc + step];
                        heroloc += step;

                        level[heroloc] = 4;
                        level[heroloc - 1] = behindbox + 2;

                    }
                }


                break;


            }

            default: {
                Log.d("MOVE:", "You cannot move here! (YET)");
                break;
            }
        }


    }

    private void moveVertically(int step) {
        switch (level[heroloc + 10 * step]) {
            case 0:
            case 3: {
                Log.d("MOVE:", "You can move here!");
                level[heroloc] = prevbmp;
                prevbmp = level[heroloc + 10 * step];
                heroloc += 10 * step;
                level[heroloc] = 4;
                break;
            }

            case 5:
            case 2: {
                Log.d("MOVE:", "You can move here and push!");
                level[heroloc] = prevbmp;
                if (step > 0) {
                    int behindbox = level[heroloc + 10 * step + 10];
                    if (behindbox == 0 || behindbox == 3) {
                        prevbmp = level[heroloc + 10 * step];
                        heroloc += 10 * step;

                        level[heroloc] = 4;
                        level[heroloc + 10] = behindbox + 2;

                    }

                } else if (step < 0) {
                    int behindbox = level[heroloc + 10 * step - 10];
                    if (behindbox == 0 || behindbox == 3) {
                        prevbmp = level[heroloc + 10 * step];
                        heroloc += 10 * step;

                        level[heroloc] = 4;
                        level[heroloc - 10] = behindbox + 2;

                    }
                }


                break;


            }

            default: {
                Log.d("MOVE:", "You cannot move here! (YET)");
                break;
            }
        }


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d("TOUCH", event.getX() + " " + event.getY());
            if (event.getX() < width * 2) {
                Log.d("MOVER:", "Left");
                moveHorizontally(-1);
            } else if (event.getX() > width * (lx - 2)) {
                Log.d("MOVER:", "Right");
                moveHorizontally(1);
            } else if (event.getY() < (height * 2)) {
                Log.d("MOVER:", "Above");
                moveVertically(-1);
            } else if (event.getY() > height * (ly - 2)) {
                Log.d("MOVER:", "Under");
                moveVertically(1);
            } else {
                Log.d("MOVER:", "inside");

            }


        }


        invalidate();

        return true;
    }
}
