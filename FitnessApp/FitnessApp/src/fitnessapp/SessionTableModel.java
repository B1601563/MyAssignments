/*
 */
package fitnessapp;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author celine_yin
 */
public class SessionTableModel extends AbstractTableModel {
    // set up the column headers
    private static final String[] colHeader = {"Session ID", "Title", "Date", 
        "Time", "Fee", "Type", "Trainer", "Rating"};
    // This model manages an array of session objects
    private ArrayList<TrainingSession> sessions;

    public SessionTableModel(ArrayList<TrainingSession> sessions) {
        setSessions(sessions);
    }
    
    public void setSessions(ArrayList<TrainingSession> sessions) {
        this.sessions = sessions;
    }
    
    @Override
    // number of rows is the size of the arraylist (all sessions)
    public int getRowCount() {
        return sessions.size();
    }
    
    @Override
    // number of columns follows the column header
    public int getColumnCount() {
        return colHeader.length;
    }
    
    // to fill in the values in the table cells
    public Object getValueAt(int rowIndex, int columnIndex) {
        String type = "Personal";
        String rating = "none";
        TrainingSession theSession = (TrainingSession) sessions.get(rowIndex);
        if (theSession instanceof GroupTraining) {
            type = ((GroupTraining) theSession).getClassType().toString();
        }
        if (theSession.getTrainer().getAverageRating() > 0) {
            rating = theSession.getTrainer().getAverageRating() + "";
        }
        switch(columnIndex) {
            case 0: return new Integer(theSession.getSessionID());
            case 1: return new Integer(theSession.getTitle());
            case 2: return theSession.getDate();
            case 3: return theSession.getTime().getHour() + ":"
                    + theSession.getTime().getMinute();
            case 4: return theSession.getFee();
            case 5: return type;
            case 6: return theSession.getTrainer();
            case 7: return rating;
            default: return "";
        }
    }
    
    public String getColumnName(int column){
        return colHeader[column];
    }
    
    // to return the TrainingSession object at a specified row
    public TrainingSession getSessionAt(int index){
        TrainingSession theSession = (TrainingSession) sessions.get(index);
        return theSession;
    }

}
