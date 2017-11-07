
package fitnessapp;

import java.io.Serializable;

/**
 * An enumerated type for the Session status attribute
 * @author ngsm
 */
public enum SessionStatus implements Serializable {
    AVAILABLE, CANCELLED, COMPLETED, FULL;
}
