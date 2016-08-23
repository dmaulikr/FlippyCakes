package com.xanderfehsenfeld.flippycakes;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class VectorOpsUnitTest {


    @Test
    public void crossProduct_isCorrect() throws Exception {

        /* test for right */
        /* y axis */
        float[] vectorA = new float[] {0, 1, 0, 1};

        /* x axis */
        float[] vectorB = new float[] {1, 0, 0, 1};
        float[] expected = new float[] {0, 0, -1, 1};
        float[] result = VectorOps.crossProduct(vectorA, vectorB);
        assertFloatArrEquals(expected, result);


        /* length of input check */
        vectorA = new float[]{1, 2};
        boolean thrown = false;
        String message = "no exception";
        try {
            VectorOps.crossProduct(vectorA, vectorB);
        } catch (IllegalArgumentException e){
            message = "exception message: " + e.getMessage();
            if ( e.getMessage().contains(VectorOps.INPUT_LENGTH_EXCEPTION)) thrown = true;
        }
        assertTrue(message, thrown);

        /* parallel input */
        thrown = false;
        message = "no exception";
        try {
            VectorOps.crossProduct(vectorB, vectorB);
        } catch (IllegalArgumentException e){
            message = "exception message: " + e.getMessage();
            if ( e.getMessage().contains(VectorOps.MAGNITUDE_CROSSPRODUCT_EXCEPTION)) thrown = true;
        }
        assertTrue(message, thrown);

        /* input with magnitude length 0 */
        vectorA = new float[]{0, 0, 0, 1};
        thrown = false;
        message = "no exception";
        try {
            VectorOps.crossProduct(vectorA, vectorB);
        } catch (IllegalArgumentException e){
            message = "exception message: " + e.getMessage();
            if ( e.getMessage().contains(VectorOps.MAGNITUDE_CROSSPRODUCT_EXCEPTION)) thrown = true;
        }
        assertTrue(message, thrown);

    }

    @Test
    public void cosAngBtwn_isCorrect() throws Exception {

        /* test for right */
        /* y axis */
        float[] vectorA = new float[] {0, 1, 0, 1};
        /* x axis */
        float[] vectorB = new float[] {1, 0, 0, 1};
        assertEquals(0.0f, (float) VectorOps.cosAngBtwn(vectorA, vectorB));

        /* order doesn't matter */
        assertEquals(VectorOps.cosAngBtwn(vectorA, vectorB), VectorOps.cosAngBtwn(vectorB, vectorA));

        /* doesn't return negative values */
        vectorB =  new float[] {0, -1, 0, 1};
        assertEquals(-1.0f, VectorOps.cosAngBtwn(vectorA, vectorB));

    }

    /**dotProduct_isCorrect
     *      dotProduct is already tested is cosAngBtwn_isCorrect
     * @throws Exception
     */
    @Test
    public void dotProduct_isCorrect() throws Exception{
        /* length of input check */
        float[] vectorA = new float[]{1, 2};
        float[] vectorB = new float[] {1, 0, 0, 1};
        boolean thrown = false;
        String message = "no exception";
        try {
            VectorOps.crossProduct(vectorA, vectorB);
        } catch (IllegalArgumentException e){
            message = "exception message: " + e.getMessage();
            if ( e.getMessage().contains(VectorOps.INPUT_LENGTH_EXCEPTION)) thrown = true;
        }
        assertTrue(message, thrown);

    }

    @Test
    public void get_magnitude_isCorrect() throws Exception{
        float[] vectorB = new float[] {1, 0, 0, 1};

        /* can return correct magnitude */
        assertEquals(1f, VectorOps.get_magnitude(vectorB));

        vectorB[0] = 0;
        /* can return 0 */
        assertEquals(0f, VectorOps.get_magnitude(vectorB));

    }

    @Test
    public void dist_isCorrect() throws Exception{
        float[] vectorB = new float[] {1, 0, 0, 1};
        float[] vectorA = new float[] {-1, 0, 0, 1};

        /* can return correct magnitude */
        assertEquals(2f, VectorOps.dist(vectorB, vectorA));

    }

    @Test
    public void getVector_isCorrect() throws Exception{
        float[] vectorA = new float[] {1, 0, 0, 1};
        float[] vectorB = new float[] {-1, 0, 0, 1};

        float[] expected = new float[] {-2, 0, 0, 1};
        /* can return correct magnitude */
        float[] result = VectorOps.getVector(vectorA, vectorB);
        assertFloatArrEquals(expected, result);


    }

    @Test
    public void midPoint_isCorrect() throws Exception{
        float[] vectorA = new float[] {1, 0, 0, 1};
        float[] vectorB = new float[] {-1, 0, 0, 1};

        float[] expected = new float[] {0, 0, 0, 1};
        /* can return correct magnitude */
        float[] result = VectorOps.midPoint(vectorA, vectorB);
        assertFloatArrEquals(expected, result);

    }

    @Test
    public void getNormal_isCorrect() throws Exception{
        float[] a = new float[] {0,0,0,1};
        float[] b = new float[] {0,1,0,1};
        float[] c = new float[] {1,1,0,1};
        float[] normal = VectorOps.calcNormal(a, b, c) ;
        assertFloatArrEquals(new float[]{0, 0, -1, 1}, normal);

    }




    @Test
    public void getIntersectionPoint_isCorrect() throws Exception{
        float[] a = new float[] {0,0,0,1};
        float[] b = new float[] {0,1,0,1};
        float[] c = new float[] {1,1,0,1};
        float[] plane = VectorOps.calcEquationOfPlane(VectorOps.calcNormal(a, b, c), a);
        float[] result = VectorOps.getIntersectionPoint(new float[]{0, 0, 1, 0}, a, plane, true);

        assertFloatArrEquals(a, result);


    }

    @Test
    public void getUnitVector_isCorrect() throws Exception{
        float[] a = new float[] {400,300,222,1};
        float[] unit = VectorOps.getUnitVector(a);
        assertEquals(1f, VectorOps.get_magnitude(unit));

        a = new float[] {0,0,0,1};
        boolean thrown = false;
        String message = "no exception raised";
        try {
            VectorOps.getUnitVector(a);
        } catch (Exception e){
            message = e.getMessage();
            if (message.contains(VectorOps.MAGNITUDE_INPUT_0_EXCEPTION)) thrown = true;
        }
        assertTrue(message, thrown);


    }

    /** assertFloatArrEquals
     *      asserts whether or not the two arrays have equal values,
     *      throws assertion error if they are not
     * @param expecteds
     * @param actuals
     */
    public static void assertFloatArrEquals(float[] expecteds, float[] actuals){
        AssertionError error = new AssertionError("Arrays not equal (expecteds, actuals: ({"
                + VectorOps.arrayToString(expecteds) + "}, {"
                + VectorOps.arrayToString(actuals) + "})");

        if (expecteds.length != actuals.length ) { throw error;
        }else {

            for (int i = 0; i < expecteds.length; i++) {
                if (expecteds[i] != actuals[i]) throw error;
            }
        }
    }






}