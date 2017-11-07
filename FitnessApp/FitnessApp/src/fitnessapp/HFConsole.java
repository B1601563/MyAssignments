/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessapp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author ngsm
 */
public class HFConsole {

    private static HELPFit hf;
    public static Scanner sc;

    public static void main(String[] args) {
        hf = new HELPFit();
        sc = new Scanner(System.in);
        System.out.println("Welcome to HELP FIT");

        String menuChoice;
        do {
            /**
             * menu
             */
            System.out.println("\nWould you like to:");
            System.out.println("1. Sign Up.");
            System.out.println("2. Log In");
            System.out.println("0. Quit");
            System.out.print("\nEnter choice :");
            menuChoice = sc.nextLine();
            switch (menuChoice) {
                case "1":
                    signUp();
                    break;
                case "2":
                    login();
                    break;
                case "0":
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (!menuChoice.equals("0"));

    }

    /**
     * allow user to sign up as Customer or FoodTruckOwner
     */
    public static void signUp() {
        System.out.println("\nYou would like to sign up as: ");
        System.out.println("1. Member");
        System.out.println("2. Trainer");
        System.out.print("Enter choice: ");
        String userType = sc.nextLine();
        if (!userType.equals("1") && !userType.equals("2")) {
            System.out.println("Invalid choice.");
        } else {
            User user = null;

            /**
             * request for non-repeated username
             */
            String username;
            do {
                username = validateString("user name");
                user = hf.findUser(username);
                if (user != null) {
                    System.out.println("This username is already taken. Try again");
                }

            } while (user != null);

            String password = validateString("password");
            String fullName = validateString("full name");
            String email = validateString("email");

            if (userType.equals("1")) {
                int levelNo;
                do {
                    System.out.println("Choose level: (1) Beginner, (2) Advanced, (3) Expert");
                    levelNo = sc.nextInt();
                    sc.nextLine();
                } while (levelNo < 0 || levelNo > 3);
                MemberLevel level = MemberLevel.values()[levelNo - 1];

                user = hf.addMember(username, password, fullName, email, level);

            } else // trainer
            {
                String specialty = validateString("specialty");
                user = hf.addTrainer(username, password, fullName, email, specialty);
            }
            if (user != null) {
                System.out.println("Account created.");
                System.out.println(user.toString());
            }
        }
    }

    /**
     * A method to validate a date is after today
     *
     * @return a LocalDate
     */
    public static LocalDate validateDate() {
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("d/M/y");

        LocalDate sessionDate = null;
        boolean valid = false;
        while (!valid) {

            try {
                System.out.print("Enter session date as dd/mm/yyyy :");
                String date = sc.nextLine();
                sessionDate = LocalDate.parse(date, dateformat);
                valid = true;
                if (sessionDate.isBefore(LocalDate.now())) {
                    System.out.println("Session cannot be before today");
                    valid = false;
                }

            } catch (DateTimeParseException dtpe) {
                System.out.println("Invalid date entered, please re-enter date as dd/mm/yyyy");
            }
        }
        return sessionDate;
    }

    /**
     * A method to validate a time is entered correctly
     *
     * @return a LocalTime
     */
    public static LocalTime validateTime() {
        DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("h:m a");

        LocalTime sessionTime = null;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Enter session time as HH:MM AM/PM :");
                String time = sc.nextLine().toUpperCase();
                sessionTime = LocalTime.parse(time, timeformat);
                valid = true;
            } catch (DateTimeParseException dtpe) {
                System.out.println("Invalid time entered, please re-enter time as HH:MM AM/PM");
            }
        }
        return sessionTime;
    }

    /**
     * A method to check that a string that is entered is not blank
     *
     * @param prompt to indicate the value that is prompted for
     * @return the validated input string
     */
    public static String validateString(String prompt) {
        String input;
        do {
            System.out.print("Please enter " + prompt + ":");
            input = sc.nextLine();
            if (input.equals("")) {
                System.out.println("Sorry, " + prompt + " cannot be blank. Please re-enter");
            }
        } while (input.equals(""));
        return input.trim();
    }

    /**
     * login method to find the user and authenticate
     *
     * @return the logged in user
     */
    public static void login() {
        System.out.print("\nEnter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        User user = hf.findUser(username);
        if (user == null) {
            System.out.println("No such username");
        } else if (!user.getPassword().equals(password)) {
            System.out.println("Invalid password");
        } else {
            userMenu(user);
        }
    }

    /**
     * A menu for a user
     *
     * @param theUser who is logged in
     */
    public static void userMenu(User theUser) {
        System.out.println("Welcome " + theUser.getFullName());
        System.out.println("User Menu");
        String choice;

        do {
            System.out.println("\nWould you like to:");
            System.out.println("1. Edit Profile.");
            System.out.println("2. View Training History");

            if (theUser instanceof Trainer) {
                System.out.println("3. Record New Training Session");
                System.out.println("4. Update Training Record");
                System.out.println("5. Show Reviews Received");
            } else {
                System.out.println("3. Register For Training");
                System.out.println("4. Show Trainer Reviews");
                System.out.println("5. Review Trainer");

            }

            System.out.println("0. Logout");
            System.out.print("\nEnter choice :");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    editProfile(theUser);
                    break;
                case "2":
                    viewTrainingHistory(theUser);
                    break;

                case "3":
                    if (theUser instanceof Trainer) {
                        recordNewSession((Trainer) theUser);
                    } else {
                        registerForTraining((Member) theUser);
                    }
                    break;
                case "4":
                    if (theUser instanceof Trainer)
                    updateTrainingRecord((Trainer) theUser);
                    else
                        showTrainerReviews();
                    break;
                case "5":
                    if (theUser instanceof Trainer) {
                        String reviewInfo = ((Trainer) theUser).showAllReviews();
                        System.out.println(reviewInfo);
                    } else {
                        reviewTrainer((Member) theUser);
                    }
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (!choice.equals("0"));

    }

    /**
     * Allow user to update profile
     *
     * @param user who wants to update the profile
     */
    public static void editProfile(User user) {
        String choice;
        do {
            System.out.println("\nYour info: " + user.toString());
            System.out.println("Would you like to update:");
            System.out.println("1. Full Name");
            System.out.println("2. Email");
            System.out.println("3. Password");
            if (user instanceof Trainer) {
                System.out.println("4. Specialty");
            } else {
                System.out.println("4. Level");
            }
            System.out.println("0. Return to User Menu");
            System.out.print("Enter choice :");
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    String fullName = validateString("full Name");
                    user.setFullName(fullName);
                    break;
                case "2":
                    String email = validateString("email");
                    user.setEmail(email);
                    break;
                case "3":
                    String password = validateString("password");
                    user.setPassword(password);
                    break;
                case "4":
                    if (user instanceof Trainer) {
                        String specialty = validateString("specialty");
                        ((Trainer) user).setSpecialty(specialty);

                    } else {
                        int levelNo;
                        do {
                            System.out.println("Choose level: (1) Beginner, (2) Advanced, (3) Expert");
                            levelNo = sc.nextInt();
                            sc.nextLine();
                        } while (levelNo < 0 || levelNo > 3);
                        MemberLevel level = MemberLevel.values()[levelNo - 1];
                        ((Member) user).setLevel(level);
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (!choice.equals("0"));

    }

    /**
     * A method to create a new training session for a Trainer
     */
    public static void recordNewSession(Trainer theTrainer) {
        TrainingSession session;
        System.out.print("Do you want to add (1) Group Session or (2) Personal Training :");
        String type = sc.nextLine();
        while (!type.equals("1") && !type.equals("2")) {
            System.out.println("Invalid type of training session. Please enter 1 for group session and 2 for personal training session :");
            type = sc.nextLine();
        }

        String title = validateString("training session title");
        LocalDate sessionDate = validateDate();
        LocalTime sessionTime = validateTime();
        System.out.print("Enter fees charged per person for this session :");
        double fee = sc.nextDouble();
        sc.nextLine();
        while (fee < 0) {
            System.out.print("Fee cannot be negative. Please enter fees charged :");
            fee = sc.nextDouble();
            sc.nextLine();
        }
        boolean valid;
        if (type.equals("1")) {
            TrainingType classType = null;
            valid = false;
            while (!valid) {
                try {
                    System.out.print("Enter type of session: DANCE, SPORT or MMA :");
                    String sessionType = sc.nextLine().toUpperCase();
                    classType = TrainingType.valueOf(sessionType);
                    valid = true;
                } catch (IllegalArgumentException iae) {
                    System.out.println("Invalid session type.");

                }
            }
            System.out.print("Enter maximum participants allowed for this group session :");
            int maxParticipants = sc.nextInt();
            sc.nextLine();
            while (maxParticipants < 0) {
                System.out.print("Max participants must be positive. Please re-enter :");
                maxParticipants = sc.nextInt();
            }
            session = theTrainer.addGroupTraining(title, sessionDate, sessionTime, fee, classType, maxParticipants);
        } else {
            session = theTrainer.addPersonalTraining(title, sessionDate, sessionTime, fee);
        }
        if (session != null) { // add to HELPFit's list of trainings
            hf.addTrainingSession(session);
            System.out.println("Session Created!");
            System.out.println(session.toString());

        }
    }

    /**
     * A method to view training sessions
     *
     * @param theUser
     */
    public static void viewTrainingHistory(User theUser) {
        System.out.println(theUser.showTrainingHistory());
        if (theUser.getNumTrainings() > 0) {
            if (theUser instanceof Trainer) {
                System.out.println("Would you like to update a session (Y/N)?");
                String yesno = sc.nextLine();
                if (yesno.charAt(0) == 'y' || yesno.charAt(0) == 'Y') {
                    updateTrainingRecord((Trainer) theUser);
                }
            } else {  // is a Member
                System.out.println("Would you like to review a trainer (Y/N)?");

                String yesno = sc.nextLine();
                if (yesno.charAt(0) == 'y' || yesno.charAt(0) == 'Y') {
                    reviewTrainer((Member) theUser);
                }
            }

        }
    }

    /**
     * A method to register for a training session if the session is not full,
     * or cancelled, or the member is not already registered for it.
     *
     * @param theMember
     */
    public static void registerForTraining(Member theMember) {
        System.out.println(hf.showUpcomingTrainingSessions());
        System.out.println("Enter sessionID you would like to register for:");
        String ID = sc.nextLine();

        TrainingSession theSession = hf.findSession(ID);
        if (theSession == null) {
            System.out.println("Session not found");
        } else {

            System.out.println("Session found: " + theSession.toString());
            if (theMember.getTrainingSessions().contains(theSession)) {
                System.out.println("You are already registered for this session");
            } else {
                System.out.println("Confirm to register? ");
                String yesno = sc.nextLine();
                if (yesno.charAt(0) == 'y' || yesno.charAt(0) == 'Y') {
                    if (theSession.register(theMember)) {
                        System.out.println("Registration successful");
                    } else {
                        System.out.println("Registration failed");
                    }
                }
            }

        }
    }

    /**
     * a method to allow trainer to update training record
     *
     * @param theTrainer
     */
    public static void updateTrainingRecord(Trainer theTrainer) {
        System.out.println("Enter sessionID to update :");
        String ID = sc.nextLine();
        TrainingSession theSession = theTrainer.findSession(ID);
        if (theSession == null) {
            System.out.println("You do not have this session");
        } else {

            String choice;
            do {
                System.out.println("\nSession info: " + theSession.toString());
                System.out.println("\nWould you like to update:");
                System.out.println("1. Title");
                System.out.println("2. Date");
                System.out.println("3. Time");
                System.out.println("4. Fee");
                System.out.println("5. Status");
                if (theSession instanceof PersonalTraining) {
                    System.out.println("6. Notes");
                }
                System.out.println("0. Return to User Menu");
                System.out.print("Enter choice :");
                choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        String title = validateString("title");
                        theSession.setTitle(title);
                        break;
                    case "2":
                        LocalDate sessionDate = validateDate();
                        theSession.setDate(sessionDate);
                        break;
                    case "3":
                        LocalTime sessionTime = validateTime();
                        theSession.setTime(sessionTime);

                        break;
                    case "4":
                        System.out.println("Enter fee :");
                        double fee = sc.nextDouble();
                        if (fee <= 0) {
                            System.out.println("Fee cannot be negative");
                        } else {
                            theSession.setFee(fee);
                        }
                        break;
                    case "5":
                        System.out.println("Select status:");
                        System.out.println("1. AVAILABLE");
                        System.out.println("2. CANCELLED");
                        System.out.println("3. COMPLETED");
                        int statchoice = sc.nextInt();
                        if (statchoice < 1 || statchoice > 3) {
                            System.out.println("Invalid status");
                        } else {
                            theSession.setStatus(SessionStatus.values()[statchoice - 1]);
                        }
                        break;
                    case "6":
                        if (theSession instanceof PersonalTraining) {
                            System.out.println("Enter notes :");
                            String notes = sc.nextLine();
                            ((PersonalTraining) theSession).setNotes(notes);
                        }
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            } while (!choice.equals("0"));
        }

    }

    /**
     * a method to allow trainer to update training record
     *
     * @param theTrainer
     */
    public static void reviewTrainer(Member theMember) {
        System.out.print("Enter sessionID to review trainer :");
        String ID = sc.nextLine();
        TrainingSession theSession = theMember.findSession(ID);
        if (theSession == null) {
            System.out.println("You did not have this session");
        } else if (theSession.getStatus() == SessionStatus.AVAILABLE && theSession.getDate().isAfter(LocalDate.now())) {
            System.out.println("The session is not yet over");
        } else {
            System.out.println("Session found: ");
            System.out.println(theSession.toString());
            Trainer sessionTrainer = theSession.getTrainer();
            System.out.println("Trainer info:");
            System.out.println(sessionTrainer.toString());
            System.out.print("\nEnter your review rating from 1 - 5 stars:");
            int rating = sc.nextInt();
            sc.nextLine();
            while (rating < 1 || rating > 5) {
                System.out.println("Please give a rating between 1 and 5 stars");
                System.out.print("\nEnter your review rating from 1 - 5 stars:");
                rating = sc.nextInt();

            }
            System.out.print("Enter your comments, if any :");
            String comments = sc.nextLine();
            Review theReview = new Review(rating, comments, theMember);
            if (sessionTrainer.addReview(theReview)) {
                System.out.println("Review submitted");
            } else {
                System.out.println("Review failed");
            }

        }

    }

    public static void showTrainerReviews()
    {
       System.out.println(hf.showUpcomingTrainingSessions());
        System.out.println("Enter sessionID to view trainer info :");
        String ID = sc.nextLine();

        TrainingSession theSession = hf.findSession(ID);
        if (theSession == null) {
            System.out.println("Session not found");
        } else {
            Trainer theTrainer = theSession.getTrainer();
            System.out.println("Trainer Info: " );
            System.out.println(theTrainer.toString());
            System.out.println("Reviews:");
            System.out.println(theTrainer.showAllReviews());
        }
}
         
}
