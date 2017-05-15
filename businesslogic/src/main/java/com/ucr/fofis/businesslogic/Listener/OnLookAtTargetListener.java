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
    void onStartLookingAtBuilding(Punto targetObject);

    /**
     * Method called when the user stops looking to a target
     *
     * @param targetObject
     */
    void onStopLookingAtBuilding(Punto targetObject);
}
