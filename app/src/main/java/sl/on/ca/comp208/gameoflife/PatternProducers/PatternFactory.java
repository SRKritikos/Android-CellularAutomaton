package sl.on.ca.comp208.gameoflife.patternproducers;

import java.util.HashMap;
import java.util.Map;

import sl.on.ca.comp208.gameoflife.R;

/**
 * Created by Steven on 2/9/2017.
 */

public class PatternFactory {
    private Map<Integer, IPatternProducer> patternProducerMap;

    public PatternFactory() {
        patternProducerMap = new HashMap<>();
        TopLeftGlider topLeftGlider = new TopLeftGlider();
        TopRightGlider topRightGlider = new TopRightGlider();
        BotRightGlider botRightGlider = new BotRightGlider();
        BotLeftGlider botLeftGlider = new BotLeftGlider();
        GliderGunCreator gliderGunCreator = new GliderGunCreator();
        SingleCell singleCell = new SingleCell();
        patternProducerMap.put(R.id.createTopLeftGliderBtn, topLeftGlider);
        patternProducerMap.put(R.id.createTopRightGliderBtn, topRightGlider);
        patternProducerMap.put(R.id.createBotLeftGliderBtn, botLeftGlider);
        patternProducerMap.put(R.id.createBotRightGliderBtn, botRightGlider);
        patternProducerMap.put(R.id.createGliderGunBtn, gliderGunCreator);
        patternProducerMap.put(R.id.createSingleCellBtn, singleCell);
    }

    public IPatternProducer getInstance(int id) {
        return patternProducerMap.get(id);
    }
}
