package com.example.fourthmobile;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by kru13 on 10.10.17.
 */

public class BarcodeView extends View {

    //UPC-A code

    //http://en.wikipedia.org/wiki/EAN_code
    //http://www.terryburton.co.uk/barcodewriter/generator/


    static final int[] L = {0x0D,  //000 1101
            0x19,  //001 1001
            0x13,  //001 0011
            0x3D,  //011 1101
            0x23,  //010 0011
            0x31,  //011 0001
            0x2F,  //010 1111
            0x3B,  //011 1011
            0x37,  //011 0111
            0x0B   //000 1011
    };

    static final int[] R = {0x72, //111 0010
            0x66, //110 0110
            0x6C, //110 1100
            0x42, //100 0010
            0x5C, //101 1100
            0x5E, //100 1110
            0x50, //101 0000
            0x44, //100 0100
            0x48, //100 1000
            0x74  //111 0100
    };

    final static int BARCODE_WIDTH =  800;
    final static int BARCODE_HEIGHT = 400;
    final static int BARCODE_LINE_WIDTH = 5;
    private static int real_barcode_height=BARCODE_HEIGHT;
    private static int real_barcode_width=BARCODE_WIDTH;
    private static int real_barcode_line_width=BARCODE_LINE_WIDTH;

    int code[] = new int[12];

    public BarcodeView(Context context) {
        super(context);
    }

    public BarcodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BarcodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BarcodeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        String sizes = w +" " + h + " " + oldw + " " + oldh;
        Log.d("SIZES:",sizes);
        real_barcode_height = h;
        real_barcode_width = w;
        real_barcode_line_width=real_barcode_width/(7*12 + 3*4);


    }

    void setDefaults() {
        int copyFrom[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2};
        System.arraycopy(copyFrom, 0, code, 0, copyFrom.length);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint twPaint = new Paint();
        twPaint.setColor(Color.WHITE);

        Paint tbPaint = new Paint();
        tbPaint.setColor(Color.BLACK);

        Paint trPaint = new Paint();
        trPaint.setColor(Color.RED);
        trPaint.setStrokeWidth(BARCODE_LINE_WIDTH);

        // vykreslí bílý obdelník do kterého se bude kreslit čárový kód
        canvas.drawRect(new Rect(10, 10, real_barcode_width, real_barcode_height), twPaint);

        // tloušťka čáry
        tbPaint.setStrokeWidth(BARCODE_LINE_WIDTH);

        // velikost písma, antialiasing
        trPaint.setTextSize(30);
        trPaint.setAntiAlias(true);

        code = new int[]{4, 5, 4, 8, 7, 1, 1, 2, 1, 4, 7, 8};

        //canvas.drawLine(0, 0, real_barcode_width, real_barcode_height, tbPaint);
        //canvas.drawLine(0, real_barcode_height, real_barcode_width, 0, tbPaint);

        //canvas.drawText("Zde bude čárový kód", (int)(real_barcode_width * 0.3), (int)(canvas.getHeight() * 0.95), trPaint);
        int location = 30;



        //Log.d("BAR","1 red");
        canvas.drawLine(location * BARCODE_LINE_WIDTH, 0, location * BARCODE_LINE_WIDTH, real_barcode_height, trPaint);
        location++;
        //Log.d("BAR","0 red");
        canvas.drawLine(location * BARCODE_LINE_WIDTH, 0, location * BARCODE_LINE_WIDTH, real_barcode_height, twPaint);
        location++;
        //Log.d("BAR","1 red");
        canvas.drawLine(location * BARCODE_LINE_WIDTH, 0, location * BARCODE_LINE_WIDTH, real_barcode_height, trPaint);
        location++;



        for(int j = 0;j<code.length/2;j++) {
            for (int i = 6; i >= 0; i--) {
              //  Log.d(" BAR Drawing number ", String.valueOf(code[j]));
                if (((L[code[j]] >> i) & 0x01) == 1) {
          //          Log.d("BAR", "1");
                    canvas.drawLine(location * BARCODE_LINE_WIDTH, 0, location * BARCODE_LINE_WIDTH, real_barcode_height, tbPaint);
                } else {
            //        Log.d("BAR", "0");
                    canvas.drawLine(location * BARCODE_LINE_WIDTH, 0, location * BARCODE_LINE_WIDTH, real_barcode_height, twPaint);
                }
                location++;
            }
        }
        //Log.d("BAR","0 red");
        canvas.drawLine(location * BARCODE_LINE_WIDTH, 0, location * BARCODE_LINE_WIDTH, real_barcode_height, twPaint);
        location++;
        //Log.d("BAR","1 red");
        canvas.drawLine(location * BARCODE_LINE_WIDTH, 0, location * BARCODE_LINE_WIDTH, real_barcode_height, trPaint);
        location++;
        //Log.d("BAR","0 red");
        canvas.drawLine(location * BARCODE_LINE_WIDTH, 0, location * BARCODE_LINE_WIDTH, real_barcode_height, twPaint);
        location++;
        //Log.d("BAR","1 red");
        canvas.drawLine(location * BARCODE_LINE_WIDTH, 0, location * BARCODE_LINE_WIDTH, real_barcode_height, trPaint);
        location++;
        //Log.d("BAR","0 red");
        canvas.drawLine(location * BARCODE_LINE_WIDTH, 0, location * BARCODE_LINE_WIDTH, real_barcode_height, twPaint);
        location++;

        for(int j = code.length/2;j<code.length;j++){
            for (int i = 6; i >=0 ; i--) {
                Log.d("BAR Drawing number ", String.valueOf(code[j]));
                if (((R[code[j]] >> i) & 0x01) == 1) {
          //          Log.d("BAR","1");
                    canvas.drawLine(location * BARCODE_LINE_WIDTH, 0, location * BARCODE_LINE_WIDTH, real_barcode_height, tbPaint);
                } else {
            //        Log.d("BAR","0");
                    canvas.drawLine(location * BARCODE_LINE_WIDTH, 0, location * BARCODE_LINE_WIDTH, real_barcode_height, twPaint);
                }
                location++;
            }
        }


        //Log.d("BAR","1 red");
        canvas.drawLine(location * BARCODE_LINE_WIDTH, 0, location * BARCODE_LINE_WIDTH, real_barcode_height, trPaint);
        location++;
        //Log.d("BAR","0 red");
        canvas.drawLine(location * BARCODE_LINE_WIDTH, 0, location * BARCODE_LINE_WIDTH, real_barcode_height, twPaint);
        location++;
        //Log.d("BAR","1 red");
        canvas.drawLine(location * BARCODE_LINE_WIDTH, 0, location * BARCODE_LINE_WIDTH, real_barcode_height, trPaint);
        location++;

        String coder = "";
        for(int i =0;i<code.length;i++)
        {

            coder+=code[i]+" ";
        }
        canvas.drawText(coder, (int)(real_barcode_width * 0.3), (int)(real_barcode_height* 1.25), trPaint);

    }


}
