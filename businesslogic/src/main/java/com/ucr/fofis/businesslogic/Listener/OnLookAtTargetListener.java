package com.ucr.fofis.businesslogic.Listener;

import com.ucr.fofis.dataaccess.entity.Punto;

/**
 * <h1> OnLookAtTargetListener </h1>
 * <p>
 * Interface usada para crear un llamado cuando el usuario apunta al Target
 * </p>
 */
public interface OnLookAtTargetListener {

    /**
     * Método llamado cuando el usuario comienza a apuntar un objeto.
     *
     * @param targetObject
     */
    void onStartLookingAtTarget(Punto targetObject);

    /**
     * Método llamado cuando el usuario deja de apuntar un objeto.
     *
     * @param targetObject
     */
    void onStopLookingAtTarget(Punto targetObject);

    /**
     * Método llamado cuando la rotación del dispositivo cambia.
     *
     * @param rotationVector
     */
    void onRotationUpdate(float[] rotationVector);
}
