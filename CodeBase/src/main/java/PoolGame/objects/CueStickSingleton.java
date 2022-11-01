package PoolGame.objects;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

public class CueStickSingleton {
   private static Line cueStick = null;

   public CueStickSingleton(){
   }

    public static Line getInstance(MouseEvent event){
        if(cueStick == null){
            cueStick = new Line(event.getX(), event.getY(), event.getX(), event.getY());
        }
        return cueStick;
    }
}
