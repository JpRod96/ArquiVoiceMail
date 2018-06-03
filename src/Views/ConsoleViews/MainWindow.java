package Views.ConsoleViews;

import Controllers.UIController;
import Presenters.UIPresenter;
import main.Connection;
import main.Presenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {
    private JPanel rootPanel;
    private JButton a1Button, a2Button, a3Button, a4Button, a5Button, a6Button, a7Button, a8Button, a9Button, a0Button, hButton, actionButton;
    private JLabel labelText;
    private JTextArea userOption;
    private JButton numeralButton;
    private UIPresenter uiPresenter;
    public MainWindow(){
        super("jp,mauri,abel");
        setContentPane(rootPanel);
        setSize(340,400);
        initializeActionButtons();
        initializeNumeralButtons();

    }
    public JLabel getFieldToWrite(){
        return labelText;
    }
    public JTextArea getUserOption(){
        return userOption;
    }
    public void setUiPresenter(UIPresenter uiPresenter){
        this.uiPresenter=uiPresenter;
    }
    public void initializeNumeralButtons(){
        a1Button.addActionListener(this);
        a2Button.addActionListener(this);
        a3Button.addActionListener(this);
        a4Button.addActionListener(this);
        a5Button.addActionListener(this);
        a6Button.addActionListener(this);
        a7Button.addActionListener(this);
        a8Button.addActionListener(this);
        a9Button.addActionListener(this);
        a0Button.addActionListener(this);
    }
    public void initializeActionButtons(){
        hButton.addActionListener(this);
        actionButton.addActionListener(this);
        numeralButton.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==a1Button)
            userOption.setText(userOption.getText().concat("1"));
        if(e.getSource()==a2Button)
            userOption.setText(userOption.getText().concat("2"));
        if(e.getSource()==a3Button)
            userOption.setText(userOption.getText().concat("3"));
        if(e.getSource()==a4Button)
            userOption.setText(userOption.getText().concat("4"));
        if(e.getSource()==a5Button)
            userOption.setText(userOption.getText().concat("5"));
        if(e.getSource()==a6Button)
            userOption.setText(userOption.getText().concat("6"));
        if(e.getSource()==a7Button)
            userOption.setText(userOption.getText().concat("7"));
        if(e.getSource()==a8Button)
            userOption.setText(userOption.getText().concat("8"));
        if(e.getSource()==a9Button)
            userOption.setText(userOption.getText().concat("9"));
        if(e.getSource()==a0Button)
            userOption.setText(userOption.getText().concat("0"));
        if(e.getSource()==numeralButton)
            userOption.setText(userOption.getText().concat("#"));
        if (e.getSource()==actionButton){
            if(userOption.getText().length() == 1
                    && "1234567890#".indexOf(userOption.getText()) >= 0)
            {
                //this.connection.reciveData(userOption.getText());

                uiPresenter.parseModel();
            }
            else
            {
                //this.connection.record(userOption.getText());

            }
            userOption.setText("");
        }
        if (e.getSource()==hButton){
            // this.connection.hangup();

        }
    }

}
