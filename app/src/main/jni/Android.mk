LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)


OpenCV_INSTALL_MODULES := on
OpenCV_CAMERA_MODULES := off

OPENCV_LIB_TYPE :=STATIC

LOCAL_OPENCV_PATH := /home/sk/sk/myapp/OpenCV-android-sdk/sdk/native/jni

ifeq ("$(wildcard $(OPENCV_MK_PATH))", "")
include $(LOCAL_OPENCV_PATH)/OpenCV.mk
else
include $(OPENCV_MK_PATH)
endif

LOCAL_MODULE := OpenCV

LOCAL_C_INCLUDES += $(LOCAL_OPENCV_PATH) \
                    $(LOCAL_OPENCV_PATH)/include \
                    $(LOCAL_OPENCV_PATH)/include/opencv \
                    $(LOCAL_OPENCV_PATH)/include/opencv2

LOCAL_SRC_FILES := com_sk_opencv_OpenCVHelper.cpp

LOCAL_LDLIBS +=  -lm -llog

include $(BUILD_SHARED_LIBRARY)
