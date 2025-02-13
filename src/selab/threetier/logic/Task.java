package selab.threetier.logic;

import selab.threetier.storage.Storage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Task extends Entity {
    private String title;
    private Date start;
    private Date end;

    public String getTitle() { return title; }
    public void setTitle(String value) { title = value; }

    public void setStart(Date value) { start = value; }
    public Date getStart() {
        return start;
    }
    public Date getEnd() {
        return end;
    }
    public String getStartDate() {
        return new SimpleDateFormat("YYYY-MM-DD").format(start);
    }
    public String getStartTime() {
        return new SimpleDateFormat("HH:mm:ss").format(start);
    }

    public void setEnd(Date value) { end = value; }
    public String getEndTime() {
        return new SimpleDateFormat("HH:mm:ss").format(end);
    }

    public void save() {
        Storage.getInstance().getTasks().addOrUpdate(this);
    }

    public boolean isValid(){
        if(end.before(start))
            return false;
        for(Entity t:Storage.getInstance().getTasks().getAll())
            if(!isOk(t))
                return false;
        return true;
    }

    public boolean isOk(Entity item){
        Task it = (Task)item;
        Date is = it.getStart();
        Date ie =it.getEnd();
        if(start.before(ie) ^ end.before(is))
            return false;
        return true;
    }


    public static ArrayList<Task> getAll() {
        return Storage.getInstance().getTasks().getAll();
    }
}
