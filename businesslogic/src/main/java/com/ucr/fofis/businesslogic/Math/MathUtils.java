package com.ucr.fofis.businesslogic.Math;

/**
 * Created by tete on 5/23/17.
 */

public class MathUtils {
    /**
     * Calculates the cross product of two vectors.
     *
     * @param vec1 the first vector.
     * @param vec2 the second vector.
     * @return the result of the cross product between vec1 and vec2.
     */
    public static double[] cross(double[] vec1, double[] vec2) {
        double[] result = new double[3];
        result[0] = vec1[1]*vec2[2] - vec2[1]*vec1[2];
        result[1] = vec1[0]*vec2[2] - vec2[0]*vec1[2];
        result[2] = vec1[0]*vec2[1] - vec2[0]*vec1[1];
        return result;
    }

    /**
     * Calculates the projection of vec in u.
     *
     * @param vector the vector.
     * @param u the vector to project to.
     * @return the projection of vec in u.
     */
    public static double[] proj(double[] vector, double[] u) {
        double[] result = new double[3];
        double d = dot(vector, u);
        double mag = Math.pow(vectorMagnitude(u), 2);
        for (int i = 0; i < 3; i++) {
            result[i] = u[i] * (d / mag);
        }
        return result;
    }

    public static double scalar_proj(double[] vector, double[] u) {
        double[] result = new double[3];
        double d = dot(vector, u);
        double mag = Math.pow(vectorMagnitude(u), 2);
        return d / mag;
    }

    public static double angle(double[] vec1, double[] vec2) {
        return Math.acos((dot(vec1, vec2)) / (vectorMagnitude(vec1) * vectorMagnitude(vec2)));
    }

    public static double dot(double[] vec1, double[] vec2) {
        return vec1[0] * vec2[0] + vec1[1] * vec2[1] + vec1[2] * vec2[2];
    }

    public static double vectorMagnitude(double[] vector) {
        return Math.sqrt(Math.pow(vector[0], 2) + Math.pow(vector[1], 2) + Math.pow(vector[2], 2));
    }

    public static double vectorMagnitude(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
