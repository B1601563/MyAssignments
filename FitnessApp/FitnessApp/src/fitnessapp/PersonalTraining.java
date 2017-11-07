package fitnessapp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class PersonalTraining extends TrainingSession implements Serializable {

    private String notes;
    private Member theMember;

    public PersonalTraining(String title, LocalDate date, LocalTime time, double fee, Trainer theTrainer) {
        super(title, date, time, fee, theTrainer);
        this.notes = "no comments yet";
        theMember = null;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * To register a Member for the Personal Training
     * only if nobody has signed up and it is available
     * @param theMember
     * @return true if the member can be set
     */
    public boolean register(Member theMember) {
        if (this.getStatus()==SessionStatus.AVAILABLE) {
            this.theMember = theMember;
            theMember.addTrainingSession(this);
            this.setStatus(SessionStatus.FULL);
            return true;
        }
        return false;
    }

    public String toString() {
        return "Session ID: " + this.sessionID + "\nTraining Type: Personal Training" + "\nTitle: " + this.title + "\nDate: " + this.date + "\nTime: " + this.time + "\nFee: RM" + this.fee + "\nStatus : " + this.getStatus() + "\nNotes: " + this.getNotes();
    }
}
