package fitnessapp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * TrainingSession is offered by HELPFit
 * @author ngsm
 */
public abstract class TrainingSession implements Serializable {
    private static int IDnum = 100;
    
    protected String sessionID;
    protected String title;
    protected LocalDate date;
    protected LocalTime time;
    protected double fee;
    protected SessionStatus status;
    protected Trainer trainer;
    
    private void setSessionID() {
        sessionID = "S" + IDnum++;
    }

    public static int getIDnum() {
        return IDnum;
    }

    public String getSessionID() {
        return sessionID;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public double getFee() {
        return fee;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.sessionID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TrainingSession other = (TrainingSession) obj;
        if (!Objects.equals(this.sessionID, other.sessionID)) {
            return false;
        }
        return true;
    }
    
    /**
     * Constructor to create a Training Session, sets the attributes
     * including who the trainer is.
     * @param title
     * @param date
     * @param time
     * @param fee
     * @param trainer 
     */
    public TrainingSession(String title, LocalDate date, LocalTime time, double fee, Trainer trainer) {
        setSessionID();
        
        if (!title.isEmpty())
            this.title = title;
        
        if (date.isAfter(LocalDate.now()))
            this.date = date;
        else
            this.date = LocalDate.now();
        
        this.time = time;
        
        if (fee >= 0)
            this.fee = fee;
        
        this.status = SessionStatus.AVAILABLE;
        this.trainer = trainer;
             
    }
    
    /**
     * Register a member for a training session
     * @param theMember
     * @return true if successful
     */
    public abstract boolean register(Member theMember);
    
    @Override
    public abstract String toString();
}
