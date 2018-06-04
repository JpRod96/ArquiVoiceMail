package Views.ConsoleViews;

import Controllers.UIController;
import Presenters.UIPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {
    private JPanel rootPanel;
    private JButton option1;
    private JButton option4;
    private JButton option2;
    private JButton option3;
    private JButton leaveMessageButton;
    private JTextArea userInput;
    private JLabel showedMessage;
    private JButton actionButton;

    private UIPresenter uiPresenter;
    public UIController uiController;
    public MainWindow(){
        super("jp,mauri,abel");
        setContentPane(rootPanel);
        setSize(400,430);
        initializeActionButtons();
    }
    public JLabel getFieldToWrite(){
        return showedMessage;
    }
    public JButton getOption1Field() {
        return option1;
    }

    public JButton getOption4Field() {
        return option4;
    }

    public JButton getOption2Field() {
        return option2;
    }

    public JButton getOption3Field() {
        return option3;
    }

    public void setController(UIController uiController){
        this.uiController = uiController;

    }
    public void setUiPresenter(UIPresenter uiPresenter){
        this.uiPresenter=uiPresenter;
    }
    public void initializeActionButtons(){
        leaveMessageButton.addActionListener(this);
        option1.addActionListener(this);
        option2.addActionListener(this);
        option3.addActionListener(this);
        option4.addActionListener(this);
        actionButton.addActionListener(this);

    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==actionButton) {
            if (userInput.getText().length() == 1
                    && "1234567890#".indexOf(userInput.getText()) >= 0) {
                uiController.recibeData(userInput.getText());
            } else {
                uiController.record(userInput.getText());
            }
            userInput.setText("");
        }
        if (e.getSource()==option1){
            uiController.recibeData("1");
        }
        if (e.getSource()==option2){
            uiController.recibeData("2");
        }
        if (e.getSource()==option3){
            uiController.recibeData("3");
        }
        if (e.getSource()==option4){
            uiController.recibeData("4");
        }
        if (e.getSource()== leaveMessageButton){
            uiController.hangUp();
        }
    }

}
