package com.example.irisqualitycapture.utils;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;

public class CameraUtils {

    public static boolean isBackCameraAvailable(Context context) {
        CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        try {
            if (cameraManager != null) {
                String[] cameraIds = cameraManager.getCameraIdList();
                for (String cameraId : cameraIds) {
                    CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(cameraId);
                    // Check if the camera is available for use
                    Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);
                    if (facing != null && facing == CameraCharacteristics.LENS_FACING_BACK) {
                        return true; // Found a back-facing camera, consider it available
                    }
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        return false; // No back-facing camera found or error occurred
    }
}
