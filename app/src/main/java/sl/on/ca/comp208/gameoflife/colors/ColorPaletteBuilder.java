package sl.on.ca.comp208.gameoflife.colors;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steven on 2/11/2017.
 */

public class ColorPaletteBuilder {
    protected String builderCanvasColor;
    protected String builderRectColor;
    protected Context builderContext;
    protected boolean builderIsRandom;
    protected List<String> builderRandomColorList;


    public ColorPaletteBuilder(Context context) {
        this(context, "white", "black");
    }

    public ColorPaletteBuilder(Context context, String canvasColor, String rectColor) {
        this.builderContext = context;
        this.builderCanvasColor = canvasColor;
        this.builderRectColor = rectColor;
        this.builderIsRandom = false;
        this.builderRandomColorList = new ArrayList<>();
    }

    public ColorPaletteBuilder setCanvasColor(String color) {
        this.builderCanvasColor = color;
        return this;
    }

    public ColorPaletteBuilder setRectColor(String color) {
        this.builderRectColor = color;
        return this;
    }

    public ColorPaletteBuilder isRandom(boolean isRandom) {
        this.builderIsRandom = isRandom;
        return this;
    }

    public ColorPaletteBuilder chrismassColors() {
        this.builderCanvasColor = "red";
        this.builderRectColor = "green";
        return this;
    }

    public ColorPaletteBuilder invertedChristmassColors() {
        this.builderCanvasColor = "green";
        this.builderRectColor = "red";
        return this;
    }

    public ColorPaletteBuilder blackAndWhite() {
        this.builderCanvasColor = "white";
        this.builderRectColor = "black";
        return this;
    }

    public ColorPaletteBuilder rainbow() {
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

    public ColorPaletteBuilder addToRandomColors(String color) {
        this.builderRandomColorList.add(color);
        return this;
    }

    public ColorPalette build() {
        return new ColorPalette(this);
    }
}

