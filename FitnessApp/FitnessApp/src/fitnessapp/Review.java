package fitnessapp;

import java.io.Serializable;
import java.time.*;

/**
 * A Review is written by a Member about a Trainer
 * @author ngsm
 */
public class Review implements Serializable {
    
    private LocalDate timestamp;
    private int rating;
    private String comments;
    private Member writtenBy;
    
    public Review(int rating, String comments, Member writtenBy){
        this.writtenBy = writtenBy;
        setTimestamp();
        setRating(rating);
        setComments(comments);
    }

    /**
     * set the time when the review is created.
     */
    public void setTimestamp() {
        this.timestamp = LocalDate.now();
    }

    /**
     * get the rating given by the member for this review.
     * 
     * @return rating given by member.
     */
    public int getRating() {
        return rating;
    }

    /**
     * A rating given by a member for a trainer
     * A rating of 0 should not be calculated
     * @param rating is between 1 and 5
     */
    public void setRating(int rating) {
        if (rating >=1 && rating <=5)
            this.rating = rating;
        else 
            rating = 0;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public Member getWrittenBy() {
        return writtenBy;
    }

    /**
     * Get comments given by a member
     * 
     * @return comments given 
     */
    public String getComments() {
        return comments;
    }

    /**
     * set comments given by member to this review.
     * Comments can be blank
     * @param comments given by member
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
}
