package Presenters;

import Controllers.ConsoleController;
import main.*;
import observers.Telephone;

/**
 * Created by Jp on 27/05/2018.
 */
public class ConsolePresenter implements Presenter<String>{

    private Telephone telephone;

    public ConsolePresenter(Telephone telephone){
        //this.consoleController=consoleController;
        this.telephone=telephone;
    }

    @Override
    public void showView(String toShowString){
        telephone.speak(toShowString);
    }


    @Override
    public String getModelFromView(){
        return null;
    }
}
