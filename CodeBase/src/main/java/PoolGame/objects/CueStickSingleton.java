package PoolGame.objects;

public class CueStickSingleton {
   private static CueStick cueStick = null;

   public CueStickSingleton(){
   }

    public static CueStick getInstance(){
        if(cueStick == null){
            cueStick = new CueStick();
           
        }
        return cueStick;
    }
}
