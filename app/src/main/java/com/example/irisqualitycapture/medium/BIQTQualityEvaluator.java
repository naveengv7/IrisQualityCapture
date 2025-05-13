package com.example.irisqualitycapture.medium;

import org.json.JSONObject;

import java.util.Map;

public class BIQTQualityEvaluator {

    /**
     * Checks if all specified quality metrics meet thresholds.
     * @param qualityScores JSONObject from BIQT server (quality_scores)
     * @param thresholds Map of metric names → threshold values
     * @return true if ALL metrics meet thresholds, false if any fail
     */
    public static boolean checkQualityScores(JSONObject qualityScores, Map<String, Float> thresholds) {
        if (qualityScores == null) return false;

        for (Map.Entry<String, Float> entry : thresholds.entrySet()) {
            String key = entry.getKey();
            float threshold = entry.getValue();
            if (!qualityScores.has(key)) return false; // required metric missing

            float value = (float) qualityScores.optDouble(key, -1);
            if (value < threshold) return false;  // fails quality
        }
        return true;
    }
}
