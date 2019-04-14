package selab.threetier.presentation;

import org.json.JSONArray;
import org.json.JSONObject;
import selab.threetier.logic.Entity;
import selab.threetier.logic.Task;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class TasksListPresentation extends JSONPresentation {
    @Override
    public JSONObject getData(String method, InputStream body) {
        JSONObject result = new JSONObject();
        ArrayList<Task> aa =Task.getAll();
        Collections.sort(aa,(x,y)->x.getStart().compareTo(y.getStart()));
        result.put("tasks", new JSONArray(aa));
        return result;
    }
}
