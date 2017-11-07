package fitnessapp;

import java.io.Serializable;
import java.time.*;
import java.util.*;

/**
 * Trainer is a subclass of User and offers Trainings and gets reviews
 *
 * @author ngsm
 */
public class Trainer extends User implements Serializable {

    private String specialty;
    private ArrayList<Review> trainerReviews;

    public Trainer(String username, String password, String fullName, String email, String specialty) {
        super(username, password, fullName, email);
        setSpecialty(specialty);
        trainerReviews = new ArrayList<>();
    }

    /**
     * gets the trainer specialty .
     *
     * @return the trainer specialty.
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * sets the trainer specialty. Can be blank
     *
     * @param specialty the trainer specialty.
     */
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }



    /**
     * This method creates a new Personal Session for the Trainer
     *
     * @param title title of session.
     * @param date date of session.
     * @param time time of session.
     * @param fee fee of session.
     * @return PersonalTraining if the training is added successfully
     */
    public PersonalTraining addPersonalTraining(String title, LocalDate date, LocalTime time, double fee) {

        PersonalTraining newPT = new PersonalTraining(title, date, time, fee, this);
        if (getTrainingSessions().add(newPT))
            return newPT;
        else
            return null;
    }
    
    /**
     * This method creates a new Group Session for the Trainer
     *
     * @param title title of session.
     * @param date date of session.
     * @param time time of session.
     * @param fee fee of session.
     * @return PersonalTraining if the training is added successfully
     */
    public GroupTraining addGroupTraining(String title, LocalDate date, LocalTime time, double fee, TrainingType classType, int maxParticipants) {

        GroupTraining newGT = new GroupTraining(title, date, time, fee, classType, maxParticipants, this);
        if (getTrainingSessions().add(newGT))
            return newGT;
        else
            return null;
    }

    

    /**
     * Add a Review for a Trainer
     *
     * @param review review for trainer.
     */
    public boolean addReview(Review review) {
        return trainerReviews.add(review);
    }

    /**
     *
     * Returns all the reviews
     *
     * @return all available review from review list.
     */
    public ArrayList<Review> getTrainerReviews() {
        return trainerReviews;
    }

    /**
     * calculates the average rating for the trainer from total reviews based on
     * number of reviews given. If no reviews return 0
     */
    public double getAverageRating() {
        double totalRating = 0;
        int numReviews = 0;
        for (Review r : trainerReviews) {
            if (r.getRating() != 0) {
                numReviews++;
                totalRating += r.getRating();
            }
        }
        if (numReviews != 0) 
            return (totalRating / numReviews);
         else 
            return 0;
        
    }
    
    /**
     * Returns a string showing information about all reviews received
     * @return information about reviews including the member
     */
   public String showAllReviews()
   {
       if (trainerReviews.size()==0)
           return "No reviews yet";
       String allInfo = "\nTimestamp\tWritten By\tRating\tComments";
       allInfo+=        "\n---------\t----------\t------\t--------";
       for (Review r:trainerReviews)
       {
           allInfo+="\n"+ r.getTimestamp()+'\t' + r.getWrittenBy().getUsername()+'\t'+ r.getRating() +'\t' + r.getComments();
       }
       return allInfo;
               
       
   }
    
    public String toString()
    {
        String info =  "Trainer : " + this.getFullName() + "\nSpeciality : " + this.getSpecialty();
        double rating = this.getAverageRating();
        if (rating == 0)
            return info;
        else
            return info + "\nAverage rating : " + rating;
    }
}
