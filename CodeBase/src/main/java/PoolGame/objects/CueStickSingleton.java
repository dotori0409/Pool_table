package PoolGame.objects;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

/**
 * Creates the cue sitck line for the table
 */
public class CueStickSingleton {
   private static Line cueStick = null;

   public CueStickSingleton(){
   }

   /**
    * Creates the cue stick line variable if it does not exists 
    * Returns the cueStick variable
    * @param event
    * @return
    */
    public static Line getInstance(MouseEvent event){
        if(cueStick == null){
            cueStick = new Line(event.getX(), event.getY(), event.getX(), event.getY());
        }
        return cueStick;
    }
}
