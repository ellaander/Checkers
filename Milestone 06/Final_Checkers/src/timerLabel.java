import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


public class timerLabel extends JLabel {
    int sec=00;
    int min=00;

    DecimalFormat dFormat=new DecimalFormat("00");
    String ddsecond,ddminute="00";

    Timer timer;
    public timerLabel(){
        super();

        ddsecond=dFormat.format(sec);
        ddminute=dFormat.format(min);
        setText(ddminute+":"+ddsecond);

        timer= new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sec++;
                ddsecond=dFormat.format(sec);
                setText(ddminute+":"+ddsecond);

                if(sec==60){
                    sec=0;
                    ddsecond=dFormat.format(sec);
                    min++;
                    ddminute=dFormat.format(min);
                    setText(ddminute+":"+ddsecond);
                }

            }
        });
    }

    public void turnOn(){
        timer.start();
    }

    public void turnOff(){
        timer.stop();
    }

    public String getTimer(){
        return this.getText();
    }

    public void resetTimer()
    {
        ddsecond = "00";
        ddminute = "00";
        sec = 0;
        min = 0;

    }




}
