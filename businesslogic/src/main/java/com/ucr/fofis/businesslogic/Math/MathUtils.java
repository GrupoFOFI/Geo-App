package com.ucr.fofis.businesslogic.Math;

/**
 * Clase con utilidades para operar con vectores.
 *
 * Created by enrico on 5/23/17.
 */
public class MathUtils {
    /**Calcula el producto cruz de dos vectores.
     *
     *
     * @param vec1 el primer vector
     * @param vec2 el segundo vector
     * @return el resultado de el producto punto de vec1 y vec2
     */
    public static double[] cross(double[] vec1, double[] vec2) {
        double[] result = new double[3];
        result[0] = vec1[1]*vec2[2] - vec2[1]*vec1[2];
        result[1] = vec1[0]*vec2[2] - vec2[0]*vec1[2];
        result[2] = vec1[0]*vec2[1] - vec2[0]*vec1[1];
        return result;
    }

    /**
     * Calcula la proyección del vecto en u
     *
     * @param vector el vector
     * @param u el vector al cual se proyecta
     * @return la prouyección del vector en u.
     */
    public static double[] proj(double[] vector, double[] u) {
        double[] result = new double[3];
        double d = dot(vector, u);
        double mag = Math.pow(magnitude(u), 2);
        for (int i = 0; i < 3; i++) {
            result[i] = u[i] * (d / mag);
        }
        return result;
    }

    /**
     * Calcula la proyección escalar del vector en u
     *
     * @param vector el vector.
     * @param u el vector al cual se proyecta
     * @return la proyección escalar del vetor en u
     */
    public static double scalar_proj(double[] vector, double[] u) {
        double d = dot(vector, u);
        double mag = Math.pow(magnitude(u), 2);
        return d / mag;
    }

    /**
     * Calcula el ángulo entre dos vectores
     *
     * @param vec1 el primer vector.
     * @param vec2 el segundo vector.
     * @return el ángulo en radianes entre los dos vectores
     */
    public static double angle(double[] vec1, double[] vec2) {
        return Math.acos((dot(vec1, vec2)) / (magnitude(vec1) * magnitude(vec2)));
    }

    /**
     * Calcula el producto punto de dos vectores
     *
     * @param vec1 el primer vector.
     * @param vec2 el segundo vector.
     * @return el producto punto entre vec1 y vec2
     */
    public static double dot(double[] vec1, double[] vec2) {
        return vec1[0] * vec2[0] + vec1[1] * vec2[1] + vec1[2] * vec2[2];
    }

    /**
     * Calcula la magnitud de un vector
     *
     * @param vector el vector.
     * @return la magnitud del vector.
     */
    public static double magnitude(double[] vector) {
        return Math.sqrt(Math.pow(vector[0], 2) + Math.pow(vector[1], 2) + Math.pow(vector[2], 2));
    }
}
