/* B1601563
 */
package b1601563;

/**
 * The Person class is to be used in Lab Test 2
 *
 * @author ngsm
 * @author celine_yin
 */
public class Person {

    // instance variables
    private String ID;
    private static int nextNo = 10000;
    private String salutation;
    private String firstName;
    private String lastName;
    private String gender;

    public Person() {
        this.ID = "S" + nextNo++;
        this.salutation = "";
        this.firstName = "unknown";
        this.lastName = "unknown";
        this.gender = "unknown gender";
    }

    /**
     * Constructor with arguments
     *
     * @param salutation can be "Dr", "Mr" or "Ms"
     * @param firstName
     * @param lastName
     * @param gender "male" or "female"
     */
    public Person(String salutation, String firstName, String lastName, String gender) {
        this.ID = "S" + nextNo++;
        this.salutation = salutation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * To String method returns the full name
     *
     * @return
     */
    @Override
    public String toString() {
        return "User: " + ID + " " + salutation + " " + firstName + " " + lastName + "(" + gender + ")";
    }

}
