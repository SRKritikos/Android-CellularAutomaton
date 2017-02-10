package sl.on.ca.comp208.gameoflife.colors;


import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import sl.on.ca.comp208.gameoflife.R;

/**
 * Created by Steven on 2/9/2017.
 */

public class CanvasColors {
    private Context context;
    private String canvasColor;
    private String rectColor;
    private boolean isRandom;
    private Map<String, Integer> colorMap;
    List<String> randomColorList;


    public CanvasColors(CanvasColorsBuilder builder) {
        this.canvasColor = builder.builderCanvasColor;
        this.rectColor = builder.builderRectColor;
        this.isRandom = builder.builderIsRandom;
        this.context = builder.builderContext;
        this.randomColorList = builder.builderRandomColorList;
        this.initColorMap();
    }

    private void initColorMap() {
        this.colorMap = new HashMap<>();
        this.colorMap.put("white", ContextCompat.getColor(context, R.color.white));
        this.colorMap.put("black", ContextCompat.getColor(context, R.color.black));
        this.colorMap.put("red", ContextCompat.getColor(context, R.color.red));
        this.colorMap.put("green", ContextCompat.getColor(context, R.color.green));
        this.colorMap.put("violet", ContextCompat.getColor(context, R.color.violet));
        this.colorMap.put("indigo", ContextCompat.getColor(context, R.color.indigo));
        this.colorMap.put("yellow", ContextCompat.getColor(context, R.color.yellow));
        this.colorMap.put("orange", ContextCompat.getColor(context, R.color.orange));
        this.colorMap.put("blue", ContextCompat.getColor(context, R.color.blue));
    }

    public int getCanvasColor() {
        return this.colorMap.get(this.canvasColor);
    }

    public int getRectColor() {
        if (isRandom) {
            return this.getNextRandomColor();
        }
        return this.colorMap.get(this.rectColor);
    }

    private int getNextRandomColor() {
        if (!this.randomColorList.isEmpty()) {
            Random random = new Random();
            int nextRandom = random.nextInt(this.randomColorList.size());
            String randomColor = this.randomColorList.get(nextRandom);
            return this.colorMap.get(randomColor);
        } else {
            return this.colorMap.get("black");
        }
    }

    public static class CanvasColorsBuilder {
        private String builderCanvasColor;
        private String builderRectColor;
        private Context builderContext;
        private boolean builderIsRandom;
        private List<String> builderRandomColorList;


        public CanvasColorsBuilder(Context context) {
            this(context, "black", "white");
        }

        public CanvasColorsBuilder(Context context, String canvasColor, String rectColor) {
            this.builderContext = context;
            this.builderCanvasColor = canvasColor;
            this.builderRectColor = rectColor;
            builderIsRandom = false;
            builderRandomColorList = new ArrayList<>();
        }

        public CanvasColorsBuilder setCanvasColor(String color) {
            this.builderCanvasColor = color;
            return this;
        }

        public CanvasColorsBuilder setRectColor(String color) {
            this.builderRectColor = color;
            return this;
        }

        public CanvasColorsBuilder isRandom(boolean isRandom) {
            this.builderIsRandom = isRandom;
            return this;
        }

        public CanvasColorsBuilder chrismassColors() {
            this.builderCanvasColor = "red";
            this.builderRectColor = "green";
            return this;
        }

        public CanvasColorsBuilder invertedChristmassColors() {
            this.builderCanvasColor = "green";
            this.builderRectColor = "red";
            return this;
        }

        public CanvasColorsBuilder blackAndWhite() {
            this.builderCanvasColor = "white";
            this.builderRectColor = "black";
            return this;
        }

        public CanvasColorsBuilder rainbow() {
            this.builderIsRandom = true;
            this.builderCanvasColor = "white";
            this.builderRandomColorList.add("violet");
            this.builderRandomColorList.add("indigo");
            this.builderRandomColorList.add("blue");
            this.builderRandomColorList.add("green");
            this.builderRandomColorList.add("yellow");
            this.builderRandomColorList.add("orange");
            this.builderRandomColorList.add("red");
            return this;
        }

        public CanvasColorsBuilder addToRandomColors(String color) {
            this.builderRandomColorList.add(color);
            return this;
        }
    }
}
