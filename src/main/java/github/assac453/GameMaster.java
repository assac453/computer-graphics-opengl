package github.assac453;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.glLoadIdentity;

public class GameMaster {
    List<Object> objects;
    public GameMaster(){
        objects = new ArrayList<>();
    }
    public void AddObject(Object object){
        objects.add(object);
    }
    public void DrawObjects(){
        for (Object o : objects) {
            glLoadIdentity();
            o.draw();
        }
    }
    public Object GetObjectById(int index){
        return objects.get(index);
    }
}
