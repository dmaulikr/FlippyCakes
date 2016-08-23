package com.xanderfehsenfeld.flippycakes;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    public static final String TAG = "ApplicationTest";

    public ApplicationTest() {
        super(Application.class);

        Log.i(TAG, "ApplicationTest.");

        faceComparatorTest();

    }

    public void faceComparatorTest() {
        super(Application.class);

        Log.i(TAG, "faceComparatorTest.");



        FaceComparator fc = new FaceComparator(GameEnv.E);

        Region3D a = new Region3D(new ArrayList<float[]>(){
            {
                add(new float[]{0, 0, 0, 1});
                add(new float[]{0, 1, 0, 1});
                add(new float[]{1, 1, 0, 1});
            }
        }, null);

        Region3D b = new Region3D(new ArrayList<float[]>(){
            {
                add(new float[]{0, 0, -1, 1});
                add(new float[]{0, 1, -1, 1});
                add(new float[]{1, 1, -1, 1});
            }
        }, null);

        assertEquals(-1, fc.compare(a, b));
        //Log.i(TAG, "ApplicationTest.");
    }
}