package fitnessapp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
/**
 * The GroupTraining class is a subclass of TrainingSession
 * @author ngsm
 */
public class GroupTraining extends TrainingSession implements Serializable {
    private TrainingType classType;
    private int maxParticipants;
    private ArrayList<Member> sessionParticipants;
    
    public GroupTraining(String title, LocalDate date, LocalTime time, double fee, TrainingType classType, int maxParticipants, Trainer theTrainer) {
        super(title, date, time, fee, theTrainer);
        this.classType = classType;
        if (maxParticipants > 0)
         this.maxParticipants = maxParticipants;
        else
            this.maxParticipants = 5;
        sessionParticipants = new ArrayList<>();
    }

    public TrainingType getClassType() {
        return classType;
    }

    public void setClassType(TrainingType classType) {
        this.classType = classType;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        if (maxParticipants > 0)
        this.maxParticipants = maxParticipants;
    }
    
    /**
     * A method to add a member to a group session
     * If the number of participants is max, change status to full
     * @param theMember
     * @return false if the session is full, cancelled or already registered
     */
    public boolean register(Member theMember)
    {
        // already full or cancelled
        if (this.getStatus() != SessionStatus.AVAILABLE)
            return false;
        // already registered
        if (sessionParticipants.contains(theMember))
            return false;
        sessionParticipants.add(theMember);     // add member to the group training
        theMember.addTrainingSession(this);     // add the session to the user
        
        
        // change status if full
        if (sessionParticipants.size() == maxParticipants)
            this.setStatus(SessionStatus.FULL);
        return true;
    }
    
    public int getNumParticipants()
    {
        return sessionParticipants.size();
    }
    
    
    public String toString() {
        String info =  "Session ID: " + this.sessionID + "\nTraining Type: Group Class " + this.classType +  "\nTitle: " + this.title + "\nDate: " + this.date + "\nTime: " + this.time + "\nFee: RM" + this.fee;
        info += "\nNumber of places: " + this.maxParticipants;
        info += "\nNumber registered :" + this.getNumParticipants();
        info +="\nStatus :" + this.getStatus();
        return info;
    }
}
