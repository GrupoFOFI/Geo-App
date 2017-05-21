package com.ucr.fofis.businesslogic.Listener;

import com.ucr.fofis.dataaccess.entity.Punto;

/**
 * <h1> OnLookAtTargetListener </h1>
 * <p>
 * Interface used to create a callback when the user points to a Target
 * </p>
 */
public interface OnLookAtTargetListener {

    /**
     * Method called when the user starts looking to a building
     *
     * @param targetObject
     */
    void onStartLookingAtTarget(Punto targetObject);

    /**
     * Method called when the user stops looking to a target
     *
     * @param targetObject
     */
    void onStopLookingAtTarget(Punto targetObject);

    /**
     * Method called when the device's rotation changes
     *
     * @param rotationVector
     */
    void onRotationUpdate(float[] rotationVector);
}
