package Presenters;

import Views.ConsoleViews.MainWindow;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by CORE i7 on 03/06/2018.
 */
public class WindowChooser {
    String chosedMenu;
    int optionCounter = 1;
    ArrayList listOfMenuOptions = new ArrayList<String>();
    MainWindow mainWindow;
    public WindowChooser(String chosedMenu){
        this.chosedMenu=chosedMenu;
    }
    public void assignWindow(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }
    public void generateOptions(){

        String option="";
        if(isAMenu(chosedMenu)){
            enableButtons();
            for (int i=0 ; i< chosedMenu.length();i++){
                option = option + chosedMenu.charAt(i);
                if(chosedMenu.charAt(i)=='\n' || i==chosedMenu.length()-1){
                    getCorrectOption(optionCounter).setText(option);
                    option="";
                    optionCounter = optionCounter +1;
                }

            }
        }
        else{
            disableRestantButtons();
            writeMessage(chosedMenu);
        }
        disableUnusedButtons();
    }
    private void disableUnusedButtons(){

        if (optionCounter==3){
            mainWindow.getOption4Field().setVisible(false);
            mainWindow.getOption3Field().setVisible(false);}
        if(optionCounter==4){
            mainWindow.getOption4Field().setVisible(false);}
    }
    private void enableButtons(){
        mainWindow.getOption1Field().setVisible(true);
        mainWindow.getOption2Field().setVisible(true);
        mainWindow.getOption4Field().setVisible(true);
        mainWindow.getOption3Field().setVisible(true);
    }
    private void disableRestantButtons(){
        mainWindow.getOption1Field().setVisible(false);
        mainWindow.getOption2Field().setVisible(false);
    }
    private JButton getCorrectOption(int option) {

        if (option == 1) {
            return mainWindow.getOption1Field();
        }
        if (option == 2) {
            return mainWindow.getOption2Field();
        }
        if (option == 3) {
            return mainWindow.getOption3Field();
        }
        else{
            return mainWindow.getOption4Field();
        }
    }
    private void writeMessage(String message){
        mainWindow.getFieldToWrite().setText("<html>" + message.replaceAll("\n", "<br/>") + "</html>");
    }
    private boolean isAMenu(String menu){
        if(menu.charAt(menu.length()-1)=='#'){
            return false;
        }
        else{
            return true;
        }
    }
}

