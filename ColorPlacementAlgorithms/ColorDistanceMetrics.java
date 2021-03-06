package com.company.ColorPlacementAlgorithms;

import java.awt.*;

/**
 * Created by sky on 5/23/15.
 */
public final class ColorDistanceMetrics {

    private static float[] getRGBDists(Color c1, Color c2) {
        float dr = c1.getRed() - c2.getRed();
        float dg = c1.getGreen() - c2.getGreen();
        float db = c1.getBlue() - c2.getBlue();
        return new float[]{dr, dg, db};
    }

    private static float[] getHSBDists(Color c1, Color c2) {
        float[] hsb1 = new float[3];
        float[] hsb2 = new float[3];
        Color.RGBtoHSB(c1.getRed(), c1.getGreen(), c1.getBlue(), hsb1);
        Color.RGBtoHSB(c2.getRed(), c2.getGreen(), c2.getBlue(), hsb2);
        float dh = hsb1[0] - hsb2[0];
        float ds = hsb1[1] - hsb2[1];
        float db = hsb1[2] - hsb2[2];
        return new float[]{dh, ds, db};
    }

    /**
     * Chebychev distance between two color vectors.
     */
    public static class RGBChebychev extends ColorMetric {
        double dist(Color c1, Color c2) {
            float max = 0;
            for (float val : getRGBDists(c1, c2)) {
                if (val > max) max = val;
            }
            return max;
        }
    }

    /**
     * Square of the cosine distance between the two RGB vectors.
     */
    public static class cosineSqRGBDist extends ColorMetric {
        double dist(Color c1, Color c2) {
            float r1 = c1.getRed();
            float r2 = c2.getRed();
            float g1 = c1.getGreen();
            float g2 = c2.getGreen();
            float b1 = c1.getBlue();
            float b2 = c2.getBlue();

            float denom = (r1 * r1 + g1 * g1 + b1 * b1) * (r2 * r2 + g2 * g2 + b2 * b2);
            float numer = Math.abs(r1 * r2) + Math.abs(g1 * g2) + Math.abs(b1 * b2);
            numer *= numer;

            return (numer / denom);
        }
    }


    /**
     * Chebychev distance between two color vectors.
     */
    public static class HSBChebychev extends ColorMetric {
        double dist(Color c1, Color c2) {
            float max = 0;
            for (float val : getHSBDists(c1, c2)) {
                if (val > max) max = val;
            }
            return max;
        }
    }

    /**
     * Square of the cosine distance between the two RGB vectors.
     */
    public static class cosineSqHSBDist extends ColorMetric {
        double dist(Color c1, Color c2) {

            float[] hsb1 = new float[3];
            float[] hsb2 = new float[3];

            int r1 = c1.getRed();
            int r2 = c2.getRed();
            int g1 = c1.getGreen();
            int g2 = c2.getGreen();
            int B1 = c1.getBlue();
            int B2 = c2.getBlue();

            Color.RGBtoHSB(r1, B1, g1, hsb1);
            Color.RGBtoHSB(r2, B2, g2, hsb2);

            float h1 = hsb1[0]*255;
            float h2 = hsb2[0]*255;
            float s1 = hsb1[1]*255;
            float s2 = hsb2[1]*255;
            float b1 = hsb1[2]*255;
            float b2 = hsb2[2]*255;

            float denom = (h1 * h1 + s1 * s1 + b1 * b1) * (h2 * h2 + s2 * s2 + b2 * b2);
            float numer = Math.abs(h1 * h2) + Math.abs(s1 * s2) + Math.abs(b1 * b2);
            numer *= numer;

            return (numer / denom);
        }
    }



    /**
     * "Flawed" metric taking the absolute value post-summation.
     */
    public static class sumRGBDist extends ColorMetric {
        double dist(Color c1, Color c2) {
            float ret = 0;
            for (float val : getRGBDists(c1, c2)) {
                ret += val;
            }
            return Math.abs(ret);
        }
    }

    public static class sumSqRGBDist extends ColorMetric {
        double dist(Color c1, Color c2) {
            float ret = 0;
            for (float val : getRGBDists(c1, c2)) {
                ret += val * val;
            }
            return ret;
        }
    }

    /**
     * "Flawed" metric taking the absolute value post-summation.
     */
    public static class sumCubeRGBDist extends ColorMetric {
        double dist(Color c1, Color c2) {
            float ret = 0;
            for (float val : getRGBDists(c1, c2)) {
                ret += val * val * val;
            }
            return Math.abs(ret);
        }
    }

    public static class sumQrtRGBDist extends ColorMetric {
        double dist(Color c1, Color c2) {
            float ret = 0;
            for (float val : getRGBDists(c1, c2)) {
                ret += val * val * val * val;
            }
            return ret;
        }
    }



    /**
     * "Flawed" metric taking the absolute value post-summation.
     */
    public static class sumHSBDist extends ColorMetric {
        double dist(Color c1, Color c2) {
            float ret = 0;
            for (float val : getHSBDists(c1, c2)) {
                ret += val;
            }
            return Math.abs(ret);
        }
    }

    public static class sumSqHSBDist extends ColorMetric {
        double dist(Color c1, Color c2) {
            float ret = 0;
            for (float val : getHSBDists(c1, c2)) {
                ret += val * val;
            }
            return ret;
        }
    }

    /**
    * "Flawed" metric taking the absolute value post-summation.
    */
    public static class sumCubeHSBDist extends ColorMetric {
        double dist(Color c1, Color c2) {
            float ret = 0;
            for (float val : getHSBDists(c1, c2)) {
                ret += val * val * val;
            }
            return Math.abs(ret);
        }
    }

    public static class sumQrtHSBDist extends ColorMetric {
        double dist(Color c1, Color c2) {
            float ret = 0;
            for (float val : getHSBDists(c1, c2)) {
                ret += val * val * val * val;
            }
            return ret;
        }
    }

}
