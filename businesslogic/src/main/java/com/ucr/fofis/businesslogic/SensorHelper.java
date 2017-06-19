package com.ucr.fofis.businesslogic;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.ucr.fofis.businesslogic.Listener.OnLookAtTargetListener;
import com.ucr.fofis.dataaccess.entity.Punto;

/**
 * <h1> Sensor Helper </h1>
 *
 * <p>
 *     Clase de apoyo para calcular la rotación del dispositivo.
 * </p>
 *
 *
 * @author Fofis
 * @version 1.0
 * @since 1.0
 */
public class SensorHelper implements SensorEventListener {

    private SensorManager snsrmngr;
    private Sensor accl, mgnt;
    private OnLookAtTargetListener mListener;
    Punto mBuilding = null;

    float[] acclReading = new float[3], mgntReading = new float[3];
    float[] orientationAngles = new float[3];
    float[] rotationVector = new float[3];
    float[] iMat = new float[9];
    float[] rMat = new float[9];
    private int mAzimuth = 0; // grados

//    LocationHelper locationHelper = new LocationHelper();

    public SensorHelper(Context context) {
        // comeinza sensor
        snsrmngr = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        accl = snsrmngr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mgnt = snsrmngr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public void setTarget(Punto point) {
        mBuilding = point;
    }

    /**
     * Settea el listener de la rotación del dispositivo para recibir actulizaciones de rotaciones.
     *
     * @param listener la clase impelementando la interface OnDeviceRotationListener
     */
    public void setOnLookAtBuildingListener(OnLookAtTargetListener listener) {
        mListener = listener;
    }

    /**
     * Comeinza a checkear actualizaciones del sensor
     */
    public void start() {
        if (sensorsSupported()) {
            snsrmngr.registerListener(this, snsrmngr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
            snsrmngr.registerListener(this, snsrmngr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
        }/* else if (snsrmngr.getDefaultSensor(Sensor.TYPE_ORIENTATION) != null) { // check for deprecated orientation sensor

        }*/
    }

    /**
     * Detiene el checkeo de actualizaciones del sensor
     */
    public void stop() {
        if (sensorsSupported()) {
            snsrmngr.unregisterListener(this, accl);
            snsrmngr.unregisterListener(this, mgnt);
        }
    }

    /**
     * Revisa si el dispositivo tiene acelerómetro y sensor de campo magnético.
     *
     * @return verdadero si el dispositivo tiene acelerómetro y sensor de campo magnético, de lo contrario falso.
     */
    public boolean sensorsSupported() {
        return (accl != null && mgnt != null);
    }

    /**
     * Método que crea la lógica cuando el sensor cambia.
     *
     * @param sensorEvent
     */
    @Override
    public synchronized void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == accl.getType()) {
            acclReading = sensorEvent.values;
        } else if (sensorEvent.sensor.getType() == mgnt.getType()) {
            mgntReading = sensorEvent.values;
        }
        // Matriz de rotación basao en los valores del acelerómetro y el magnetometro
        final float[] rotationMatrix = new float[9];
        SensorManager.getRotationMatrix(rotationMatrix, null, acclReading, mgntReading);

        // [0] is yaw, [1] is pitch, [2] is roll
        orientationAngles = SensorManager.getOrientation(rotationMatrix, orientationAngles);
        /*rotationVector[0] = (float) Math.cos(orientationAngles[1]) * (float)Math.cos(orientationAngles[0]);// x
        //rotationVector[0] = (float) Math.sin(orientationAngles[0]);// x
        rotationVector[2] = (float) Math.cos(orientationAngles[1]) * (float)Math.sin(orientationAngles[0]);// z
        rotationVector[1] = (float) Math.sin(orientationAngles[1]) * (float)Math.cos(orientationAngles[0]);// y*/
        rotationVector[0] = rotationMatrix[0];
        rotationVector[2] = rotationMatrix[3];
        rotationVector[1] = -1 * rotationMatrix[8];

        // z doesn't matter
        Punto ed =null;
        if(LocationHelper.getLastLocation()!= null) {
//            ed = locationHelper.pointingCamera((double) rotationVector[0], (double) rotationVector[1], LocationHelper.getLastLocation());
            ed = null;
        }
        if (mListener != null) {
            if (mBuilding != ed) {
                if (mBuilding != null)
                    mListener.onStopLookingAtTarget(mBuilding);
                mBuilding = ed;
                if (ed != null)
                    mListener.onStartLookingAtTarget(ed);
            }
        }
        if ( SensorManager.getRotationMatrix( rMat, iMat, acclReading, mgntReading ) ) {
            mAzimuth= (int) ( Math.toDegrees( SensorManager.getOrientation( rMat, orientationAngles )[0] ) + 360 ) % 360;
        }
        if (mListener != null) mListener.onRotationUpdate(rotationVector);
    }

    /**
     * Método para notificar cuando un sensor cambió su precisión.
     * @param sensor
     * @param accuracy
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public int getAzimuth(){
        return mAzimuth;
    }
}
