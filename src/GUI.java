import main.Observable;
import observers.Observer;

import javax.swing.*;

public class GUI extends JFrame implements Observer{
    private JPanel rootPanel;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JLabel labelText;

    Observable observable;


    public GUI(Observable observable){
            super("jp,mauri,abel");
            setContentPane(rootPanel);
            setSize(400,200);
            this.observable=observable;
            this.observable.addObserver(this);
        }
    @Override
    public void update(){
        labelText.setText(observable.toString());
    }

}
