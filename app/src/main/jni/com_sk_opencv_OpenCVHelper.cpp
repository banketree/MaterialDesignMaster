//
// Created by sk on 16-9-19.
//

#include <jni.h>
#include <stdlib.h>
#include <stdio.h>
#include <com_sk_opencv_OpenCVHelper.h>

#include <opencv2/core/core.hpp>
#include <opencv2/opencv.hpp>



//using namespace cv;


extern "C" {

#define CV_R (0.299)
#define CV_G (0.587)
#define CV_B (0.114)

JNIEXPORT jintArray JNICALL Java_com_sk_opencv_OpenCVHelper_gray(
        JNIEnv *env, jclass obj, jintArray buf, int w, int h) {

    jint *cbuf;
    cbuf = env->GetIntArrayElements(buf, JNI_FALSE );
    if (cbuf == NULL) {
        return 0;
    }

    cv::Mat imgData(h, w, CV_8UC4, (unsigned char *) cbuf);

    uchar* ptr = imgData.ptr(0);
    for(int i = 0; i < w*h; i ++){
        //计算公式：Y(亮度) = 0.299*R + 0.587*G + 0.114*B
        //对于一个int四字节，其彩色值存储方式为：BGRA
        int grayScale = (int)(ptr[4*i+2]*CV_R + ptr[4*i+1]*CV_G + ptr[4*i+0]*CV_B);
        ptr[4*i+1] = grayScale;
        ptr[4*i+2] = grayScale;
        ptr[4*i+0] = grayScale;
    }

    int size = w * h;
    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, cbuf);
    env->ReleaseIntArrayElements(buf, cbuf, 0);
    return result;

}


}
