package sl.on.ca.comp208.gameoflife.colors;


import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import sl.on.ca.comp208.gameoflife.R;

/**
 * Created by Steven on 2/9/2017.
 */

public class ColorPalette {
    private Context context;
    private String canvasColor;
    private String rectColor;
    private boolean isRandom;
    private Map<String, Paint> colorMap;
    List<String> randomColorList;


    public ColorPalette(ColorPaletteBuilder builder) {
        this.canvasColor = builder.builderCanvasColor;
        this.rectColor = builder.builderRectColor;
        this.isRandom = builder.builderIsRandom;
        this.context = builder.builderContext;
        this.randomColorList = builder.builderRandomColorList;
        this.initColorMap();
    }

    private void initColorMap() {
        this.colorMap = new HashMap<>();
        Paint whitePaint = new Paint();
        whitePaint.setColor(ContextCompat.getColor(context, R.color.white));
        this.colorMap.put("white", whitePaint);
        Paint blackPaint = new Paint();
        blackPaint.setColor(ContextCompat.getColor(context, R.color.black));
        this.colorMap.put("black", blackPaint);
        Paint redPaint = new Paint();
        redPaint.setColor(ContextCompat.getColor(context, R.color.red));
        this.colorMap.put("red", redPaint);
        Paint greenPaint = new Paint();
        greenPaint.setColor(ContextCompat.getColor(context, R.color.green));
        this.colorMap.put("green", greenPaint);
        Paint violetPaint = new Paint();
        violetPaint.setColor(ContextCompat.getColor(context, R.color.violet));
        this.colorMap.put("violet", violetPaint);
        Paint indigoPaint = new Paint();
        indigoPaint.setColor(ContextCompat.getColor(context, R.color.indigo));
        this.colorMap.put("indigo", indigoPaint);
        Paint yellowPaint = new Paint();
        yellowPaint.setColor(ContextCompat.getColor(context, R.color.yellow));
        this.colorMap.put("yellow", yellowPaint);
        Paint orangePaint = new Paint();
        orangePaint.setColor(ContextCompat.getColor(context, R.color.orange));
        this.colorMap.put("orange", orangePaint);
        Paint bluePaint = new Paint();
        bluePaint.setColor(ContextCompat.getColor(context, R.color.blue));
        this.colorMap.put("blue", bluePaint);
    }

    public Paint getCanvasColor() {
        return this.colorMap.get(this.canvasColor);
    }

    public Paint getRectColor() {
        if (isRandom) {
            return this.getNextRandomColor();
        }
        return this.colorMap.get(this.rectColor);
    }

    private Paint getNextRandomColor() {
        if (!this.randomColorList.isEmpty()) {
            Random random = new Random();
            int nextRandom = random.nextInt(this.randomColorList.size());
            String randomColor = this.randomColorList.get(nextRandom);
            return this.colorMap.get(randomColor);
        } else {
            return this.colorMap.get("black");
        }
    }


}
