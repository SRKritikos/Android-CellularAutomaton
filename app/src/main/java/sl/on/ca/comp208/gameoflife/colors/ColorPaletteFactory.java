package sl.on.ca.comp208.gameoflife.colors;

import java.util.HashMap;
import java.util.Map;

import sl.on.ca.comp208.gameoflife.R;

/**
 * Created by Steven on 2/11/2017.
 */

public class ColorPaletteFactory {
    private ColorPaletteBuilder colorPaletteBuilder;
    private static Map<Integer, ColorPalette> canvasColorsMap;

    public ColorPaletteFactory(ColorPaletteBuilder colorPaletteBuilder) {
        this.colorPaletteBuilder = colorPaletteBuilder;
        this.canvasColorsMap = new HashMap<>();
        this.canvasColorsMap.put(R.id.blackWhiteColorBtn, this.blackAndWhiteInstance());
        this.canvasColorsMap.put(R.id.invertedColorBtn, this.invertedBlackAndWhiteInstance());
        this.canvasColorsMap.put(R.id.christmasColorBtn, this.christmasInstance());
        this.canvasColorsMap.put(R.id.rainbowColorBtn, this.rainbowInstance());
    }

    private ColorPalette blackAndWhiteInstance(){
        return this.colorPaletteBuilder.blackAndWhite().build();
    }

    private ColorPalette invertedBlackAndWhiteInstance() {
        return this.colorPaletteBuilder.setCanvasColor("black").setRectColor("white").build();
    }

    private ColorPalette christmasInstance() {
        return this.colorPaletteBuilder.chrismassColors().build();
    }

    private ColorPalette rainbowInstance() {
        return this.colorPaletteBuilder.rainbow().build();
    }

    public ColorPalette getInstance(int id) {
        return this.canvasColorsMap.get(id);
    }
}
