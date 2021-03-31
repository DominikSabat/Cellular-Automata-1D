import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainWindow extends JFrame{
    private DataManager dm;
    private JPanel mainPanel;
    private JPanel buttonPanel;

    private JCanvasPanel canvasPanel;

    private JTextField ruleField;

    private JTextField widthField;
    private JTextField heightField;

    private JButton makeImageBut;

    private JComboBox<String> choiceBox;

    private JButton imageBut;
    private JButton animateBut;



    public MainWindow (String title) {
        super(title);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


        animateBut = new JButton("Zanimuj Regule"); animateBut.setBackground(Color.BLACK); animateBut.setForeground(Color.WHITE);
        imageBut = new JButton("Zobrazuj Regule"); imageBut.setBackground(Color.BLACK); imageBut.setForeground(Color.WHITE);

        makeImageBut = new JButton("Zastosuj wymiary"); makeImageBut.setBackground(Color.WHITE.brighter()); makeImageBut.setForeground(Color.BLACK.darker());

        ruleField = new JTextField("90");
        ruleField.setBorder(new TitledBorder("Regula"));

        widthField = new JTextField("1000");
        widthField.setBorder(new TitledBorder("Szerokosc"));

        heightField = new JTextField("500");
        heightField.setBorder(new TitledBorder("Wysokosc"));

        String[] choice={"Periodyczne","Jedynki na brzegach","Zera na brzegach"};
        choiceBox = new JComboBox(choice);

        buttonPanel = new JPanel();
        buttonPanel.setSize(0,0);
        buttonPanel.setLayout(new GridLayout(7,1));

        buttonPanel.add(widthField);
        buttonPanel.add(heightField);
        buttonPanel.add(makeImageBut);

        buttonPanel.add(ruleField);

        buttonPanel.add(choiceBox);

        buttonPanel.add(imageBut);

        buttonPanel.add(animateBut);

        dm = new DataManager(Integer.parseInt(widthField.getText()),Integer.parseInt(heightField.getText()));



        canvasPanel = new JCanvasPanel(dm);

        mainPanel.add(BorderLayout.CENTER, canvasPanel);
        mainPanel.add(BorderLayout.EAST, buttonPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

        this.setSize(new Dimension(1500, 1000));
        this.setLocationRelativeTo(null);

        makeImageBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utility util = new Utility(dm);

                dm.img=new BufferedImage(1,1,BufferedImage.TYPE_BYTE_GRAY);
                dm.width=1;
                dm.height=1;
                dm.pixelLast=new int [1][1];
                util.Initial();

                dm.img=new BufferedImage(Integer.parseInt(widthField.getText()),Integer.parseInt(heightField.getText()),BufferedImage.TYPE_BYTE_GRAY);
                dm.width=Integer.parseInt(widthField.getText());
                dm.height=Integer.parseInt(heightField.getText());
                dm.pixelLast=new int [dm.width][dm.height];
                util.Initial();

                canvasPanel.repaint();
            }
        });

        imageBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utility util = new Utility(dm);

                if(choiceBox.getSelectedIndex()==0){ util.imageRuleNeighborsPeriodic( Integer.parseInt(ruleField.getText()));}
                else if(choiceBox.getSelectedIndex()==1){util.imageRuleNeighborsAreBlack( Integer.parseInt(ruleField.getText()));}
                else{util.imageRuleNeighborsAreWhite( Integer.parseInt(ruleField.getText()));}

                canvasPanel.repaint();

            }
        });


        animateBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utility util = new Utility(dm);

                int tempRule=Integer.parseInt(ruleField.getText());
                int tempWidth=Integer.parseInt(widthField.getText());
                int tempHeight=Integer.parseInt(heightField.getText());

                final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        if(choiceBox.getSelectedIndex()==0){ util.animateRuleNeighborsPeriodic( Integer.parseInt(ruleField.getText()));}
                        else if(choiceBox.getSelectedIndex()==1){util.animateRuleNeighborsAreBlack( Integer.parseInt(ruleField.getText()));}
                        else{util.animateRuleNeighborsAreWhite( Integer.parseInt(ruleField.getText()));}

                    }
                }, 0, 1, TimeUnit.MILLISECONDS);



                final ScheduledExecutorService executorService2 = Executors.newSingleThreadScheduledExecutor();
                executorService2.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {

                        canvasPanel.repaint();

                        if(Integer.parseInt(ruleField.getText())!=tempRule||Integer.parseInt(widthField.getText())!=tempWidth || Integer.parseInt(heightField.getText())!=tempHeight)  {

                            executorService.shutdown();
                            executorService2.shutdown();
                        }

                    }
                }, 0, 1, TimeUnit.MILLISECONDS);

            }
        });


    }

    public static void main(String[] args){
        MainWindow mw = new MainWindow("Automat komorkowy");
        mw.setVisible(true);
        mw.canvasPanel.repaint();

        mw.dm.img=new BufferedImage(Integer.parseInt(mw.widthField.getText()),Integer.parseInt(mw.heightField.getText()),BufferedImage.TYPE_BYTE_GRAY);
        //mw.dm.width=Integer.parseInt(mw.widthField.getText());
       // mw.dm.height=Integer.parseInt(mw.heightField.getText());

        Utility util = new Utility(mw.dm);
        util.Initial();


        //util.saveRGB();
    }
}
