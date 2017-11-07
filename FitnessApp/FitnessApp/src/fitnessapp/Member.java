package fitnessapp;

import java.io.Serializable;

/**
 * 
 * Member is a subclass of User. 
 * 
 * @author ngsm
 */
public class Member extends User implements Serializable {

    private MemberLevel level;
 
    public Member(String username, String password, String fullName, String email, MemberLevel level) {

        super(username, password, fullName, email);
        setLevel(level);
     }

    /**
     * gets the level of the member.
     *
     * @return the level of the member.
     */
    public MemberLevel getLevel() {
        return level;
    }

    /**
     * sets the level of the member.
     *
     * @param level the member level.
     */
    public void setLevel(MemberLevel level) {
        this.level = level;
    }

 

    

 
    @Override
    public String toString() {
        return "Member :" + getFullName() + "\nEmail :" + getEmail() + "\nLevel : " + getLevel();
    }

}
