package fitnessapp;

import java.io.Serializable;
import java.util.*;

/**
 * The User class represents an abstract user with username and password
 *
 * @author ngsm
 */
public abstract class User implements Serializable{

    private String username;
    private String password;
    private String fullName;
    private String email;
    private ArrayList<TrainingSession> userSessions;

    /**
     * Constructor with arguments
     *
     * @param username if empty will set to "undefined"
     * @param password if empty will set to "undefined"
     * @param fullName if empty will set to "undefined"
     * @param email if empty will set to "undefined"
     */
    public User(String username, String password, String fullName, String email) {
        this.username = username.isEmpty() ? "undefined" : username;
        this.password = password.isEmpty() ? "undefined" : password;
        this.fullName = fullName.isEmpty() ? "undefined" : fullName;
        this.email = email.isEmpty() ? "undefined" : email;
        userSessions = new ArrayList<>();

    }

    /**
     * get the username of the user.
     *
     * @return the user name of user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username of the user
     *
     * @param username the username to be set.
     */
    public void setUsername(String username) {
        if (!username.isEmpty()) {
            this.username = username;
        }
    }

    /**
     * gets the password of the user
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets the password of the user.
     *
     * @param password the password of the user.
     */
    public void setPassword(String password) {
        if (!password.isEmpty()) {
            this.password = password;
        }
    }

    /**
     * gets the full name of the user
     *
     * @return the full name of the user
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * sets the full name for the user.
     *
     * @param fullName the full name for the user.
     */
    public void setFullName(String fullName) {
        if (!fullName.isEmpty()) {
            this.fullName = fullName;
        }
    }

    /**
     * gets the email of the user.
     *
     * @return the email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the email of the user.
     *
     * @param email the email of the user.
     */
    public void setEmail(String email) {
        if (!email.isEmpty()) {
            this.email = email;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.username);
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
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    /**
     * A method to find a Training Session based on ID
     *
     * @param sessionID
     * @return the Session that matches the sessionID
     */
    public TrainingSession findSession(String sessionID) {
        for (TrainingSession s : getTrainingSessions()) {
            if (s.getSessionID().equals(sessionID)) {
                return s;
            }
        }
        return null;
    }

    /**
     * A method to add a session to a user, the user must not already
     * have the session
     * @param aSession
     * @return true if successful
     */
    public boolean addTrainingSession(TrainingSession aSession)
    {
        if (userSessions.contains(aSession))
            return false;
        return (userSessions.add(aSession));
    }
    /**
     * A method that returns the training sessions associated with this user
     *
     * @return
     */
    public ArrayList<TrainingSession> getTrainingSessions() {
        return userSessions;
    }

    /**
     * returns the number of trainings this user has
     *
     * @return number of trainings
     */
    public int getNumTrainings() {
        return userSessions.size();
    }

    /**
     * A method to show all training sessions for this user
     *
     * @return String showing all upcoming sessions
     */
    public String showTrainingHistory() {
        if (userSessions.size() == 0) {
            return "No Trainings yet";
        }
        String trainList = "Training History";
        trainList += "\nID\t  Title  \t  Type  \tDate\tTime";
        trainList += "\n--\t---------\t--------\t----\t----";
        for (TrainingSession t : userSessions) {
            String type = "Personal";
            if (t instanceof GroupTraining) {
                type = ((GroupTraining) t).getClassType().toString();
            }

            trainList += '\n'+t.getSessionID() + '\t' + t.getTitle()
                    + '\t' + type + '\t' + t.getDate() + '\t' + t.getTime();

        }
        return trainList;
    }

    @Override
    public abstract String toString();
}
