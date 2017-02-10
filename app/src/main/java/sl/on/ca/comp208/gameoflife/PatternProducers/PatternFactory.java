package sl.on.ca.comp208.gameoflife.PatternProducers;

import java.util.HashMap;
import java.util.Map;

import sl.on.ca.comp208.gameoflife.R;

/**
 * Created by Steven on 2/9/2017.
 */

public class PatternFactory {
    private static Map<Integer, IPatternProducer> patternProducerMap;
    protected PatternFactory(){}

    static {
        patternProducerMap = new HashMap<>();
        GliderCreator gliderCreator = new GliderCreator();
        GliderGunCreator gliderGunCreator = new GliderGunCreator();
        patternProducerMap.put(R.id.createGliderBtn, gliderCreator);
        patternProducerMap.put(R.id.createGliderGunBtn, gliderGunCreator);
    }

    public static IPatternProducer getInstance(int id) {
        return patternProducerMap.get(id);
    }
}
