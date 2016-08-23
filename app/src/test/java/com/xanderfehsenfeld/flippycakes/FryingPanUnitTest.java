package com.xanderfehsenfeld.flippycakes;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class FryingPanUnitTest {


    @Test
    public void crossProduct_isCorrect() throws Exception {


        FryingPan fryingPan = new FryingPan(50);
        fryingPan.physicsPresence.applyRotationForce(new float[]{(float) (Math.PI / 10), 0, 0, 1});
        fryingPan.updateSelf(GameEnv.E, GameEnv.light_source);

        float[] normal = fryingPan.getFaces().get(0).getNormal();
        float[] xpositive = new float[]{ 1, 0, 0, 1};
        float[] ypositive = new float[]{ 0, 1, 0, 1};
        //assertEquals( 0, VectorOps.cosAngBtwn(normal, xpositive) );
        assertTrue(Math.abs(VectorOps.cosAngBtwn(normal, xpositive)) < .001);

        assertTrue( Math.abs(Math.cos(Math.PI / 2 + Math.PI / 10) - VectorOps.cosAngBtwn(normal, ypositive)) < .0001 );

    }





}