package com.xanderfehsenfeld.flippycakes;

import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ProjectorUnitTest {


    @Test
    public void projectPoint_isCorrect() throws Exception {

        /* test for right */
        /* y axis */
        float[] point = new float[] {0, 0, 1, 1};

        float[] pov = GameEnv.E;
        float[] expected = new float[]{0, 0, 1};
        VectorOpsUnitTest.assertFloatArrEquals(expected, Projector.projectPoint(point, pov));


    }



}