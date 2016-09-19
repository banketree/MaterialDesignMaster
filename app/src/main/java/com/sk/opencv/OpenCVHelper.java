package com.sk.opencv;

/**
 * Created by sk on 16-9-19.
 */
public class OpenCVHelper {

    static {
        System.loadLibrary("OpenCV");
    }

    public static native int[] gray(int[] buf, int w, int h);



}
