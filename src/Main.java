import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        deserializeSimple();
        serializeSimple();
    }

    static void serializeSimple(){
        Todos losdias = new Todos("Walk the dog",false,0,3,"Dog");
        Todos losdias2 = new Todos("Pay the bills",false,1,1,"Bills");
        ArrayList<Todos> todosList = new ArrayList<>();
        todosList.add(losdias);
        todosList.add(losdias2);
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("data.json")){
            gson.toJson(todosList,writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void deserializeSimple(){
        try (FileReader reader = new FileReader("data.json")){
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(reader);
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Todos>>(){}.getType();
            ArrayList<Todos> losdias = gson.fromJson(jsonElement,type);
            System.out.println(losdias.get(0).getBody());
            losdias.get(0).setBody("Walk the fish");
            System.out.println(losdias);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Todos{
    private String body;
    private boolean isDone;
    private int id;
    private int priority;
    private String title;

    public Todos(String body, boolean isDone, int id, int priority, String title) {
        this.body = body;
        this.isDone = isDone;
        this.id = id;
        this.priority = priority;
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Todos{" +
                "body='" + body + '\'' +
                ", isDone=" + isDone +
                ", id=" + id +
                ", priority=" + priority +
                ", title='" + title + '\'' +
                '}';
    }
}