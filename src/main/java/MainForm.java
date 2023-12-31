import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MainForm extends JFrame {

    // jobs = summa of (1 - VaBank, 2 - Siebel, 4 - ESB)
    public int jobs = 0;
    public static boolean isDT = false;
    public static boolean isSED = false;
    public static boolean isEO = false;
    public static boolean isVBNK = false;
    public static boolean isSBL = false;
    public static boolean isESB = false;
    public static String executorVBNK;
    public static String executorSBL;
    public static String getExecutorESB;

    private final String[] listVBNK = {"Глебов Максим +7900", "Калдин Александр +7900", "Сидоров Дмитрий +7900", "Шибзухов Тимур +7900"};
    private final String[] listSBL = {"Донцов Олег +7900", "Колюкаев Сергей +7900"};
    private final String[] listESB = {"Абидов Тохир +7900", "Альпатов Андрей +7900", "Филиппов Максим +7900"};
    private final int FORM_WIDTH = 500;
    private final int FORM_HEIGHT = 700;
    Calendar calendar = Calendar.getInstance();
    //calendar.add (Calendar.DATE, 1);
    private JLabel lbl1 = new JLabel("Дата проведения работ");
    private JLabel lbl2 = new JLabel("Выберите системы для проведения работ:");
    private JLabel lbl3 = new JLabel("Дополнительные работы:");
    private JTextField tfDate = new JTextField();//calendar.getTime().toString());
    private JCheckBox cbVBNK = new JCheckBox();
    private JLabel lblVBNK = new JLabel("АБС ВаБанк");
    private JCheckBox cbDT = new JCheckBox();
    private JLabel lblDT = new JLabel("Наличие даунтайма");
    private JCheckBox cbSBL = new JCheckBox();
    private JLabel lblSBL = new JLabel("Siebel CRM");
    private JCheckBox cbESB = new JCheckBox();
    private JLabel lblESB = new JLabel("Интеграционная шина (ESB)");
    private JCheckBox cbSED = new JCheckBox();
    private JLabel lblSED = new JLabel("СЭД Пенсионер/ОСЗН");
    private JCheckBox cbEO = new JCheckBox();
    private JLabel lblEO = new JLabel("Единое окно/Электронный архив");
    private JLabel lblExecutorVBNK = new JLabel("Исполнитель работ на ВаБанк:");
    private JLabel lblExecutorSBL = new JLabel("Исполнитель работ на Siebel:");
    private JLabel lblExecutorESB = new JLabel("Исполнитель работ на ESB:");
    private JComboBox cbxVBNK = new JComboBox<>(listVBNK);
    private JComboBox cbxSBL = new JComboBox<>(listSBL);
    private JComboBox cbxESB = new JComboBox<>(listESB);
    private JButton btn1 = new JButton("Сформировать RFC");

    public MainForm() throws HeadlessException {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();
        setBounds((dim.width-FORM_WIDTH)/2, 100, FORM_WIDTH, FORM_HEIGHT);
        setTitle("RFC Maker");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int start_y = 10;
        lbl1.setBounds(10, start_y, 200, 20);
        add(lbl1);
        tfDate.setBounds(210, start_y, 150, 20);
        add(tfDate);
        LocalDate date = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String curdate = date.format(formatter);
        tfDate.setText(curdate);
        lbl2.setBounds(10, start_y+30, 320, 20);
        add(lbl2);
        cbVBNK.setBounds(10, start_y+57, 25, 25);
        lblVBNK.setBounds(40, start_y+60, 100, 20);
        add(cbVBNK);
        add(lblVBNK);
        cbVBNK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbVBNK.isSelected()) {
                    lblExecutorVBNK.setEnabled(true);
                    cbxVBNK.setEnabled(true);
                    cbDT.setEnabled(true);
                    lblDT.setEnabled(true);
                    isVBNK = true;
                }
                else {
                    lblExecutorVBNK.setEnabled(false);
                    cbxVBNK.setEnabled(false);
                    cbDT.setEnabled(false);
                    lblDT.setEnabled(false);
                    isVBNK = false;
                }
            }
        });
        cbDT.setBounds(250, start_y+57, 25, 25);
        cbDT.setEnabled(false);
        lblDT.setBounds(280, start_y+60, 150, 20);
        lblDT.setEnabled(false);
        add(cbDT);
        add(lblDT);
        cbSBL.setBounds(10, start_y+87, 25, 25);
        lblSBL.setBounds(40, start_y+90, 100, 20);
        add(cbSBL);
        add(lblSBL);
        cbSBL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbSBL.isSelected()) {
                    lblExecutorSBL.setEnabled(true);
                    cbxSBL.setEnabled(true);
                    isSBL = true;
                }
                else {
                    lblExecutorSBL.setEnabled(false);
                    cbxSBL.setEnabled(false);
                    isSBL = false;
                }
            }
        });
        cbESB.setBounds(10, start_y+117, 25, 25);
        lblESB.setBounds(40, start_y+120, 200, 20);
        add(cbESB);
        add(lblESB);
        cbESB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbESB.isSelected()) {
                    lblExecutorESB.setEnabled(true);
                    cbxESB.setEnabled(true);
                    isESB = true;
                }
                else {
                    lblExecutorESB.setEnabled(false);
                    cbxESB.setEnabled(false);
                    isESB = false;
                }
            }
        });
        lblExecutorVBNK.setBounds(10, start_y+150, 250, 20);
        cbxVBNK.setBounds(260, start_y+150, 200, 20);
        lblExecutorVBNK.setEnabled(false);
        cbxVBNK.setEnabled(false);
        add(lblExecutorVBNK);
        add(cbxVBNK);
        //cbxVBNK.;
        lblExecutorSBL.setBounds(10, start_y+180, 250, 20);
        cbxSBL.setBounds(260, start_y+180, 200, 20);
        lblExecutorSBL.setEnabled(false);
        cbxSBL.setEnabled(false);
        add(lblExecutorSBL);
        add(cbxSBL);
        lblExecutorESB.setBounds(10, start_y+210, 250, 20);
        cbxESB.setBounds(260, start_y+210, 200, 20);
        lblExecutorESB.setEnabled(false);
        cbxESB.setEnabled(false);
        add(lblExecutorESB);
        add(cbxESB);
        lbl3.setBounds(10, start_y+240, 300, 20);
        add(lbl3);
        cbSED.setBounds(10, start_y+267, 25, 25);
        lblSED.setBounds(40, start_y+270, 200, 20);
        add(cbSED);
        add(lblSED);
        cbEO.setBounds(10, start_y+297, 25, 25);
        lblEO.setBounds(40, start_y+300, 250, 20);
        add(cbEO);
        add(lblEO);
        btn1.setBounds(170, 600, 200, 20);
        add(btn1);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //if ()
                    if (cbDT.isSelected()) isDT = true;
                    if (cbSED.isSelected()) isSED = true;
                    if (cbEO.isSelected()) isEO = true;
                    if (cbVBNK.isSelected()) executorVBNK = cbxVBNK.getSelectedItem().toString();
                    Word word = new Word(jobs, tfDate.getText());
                    word.BuildDoc();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
/*        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        container.add(lbl1);
        container.add(cbVBNK);
        container.add(lblVBNK);/**/
/*        JPanel TopPanel = new JPanel(new BorderLayout());
        add(TopPanel, BorderLayout.NORTH);
        TopPanel.add(lbl1);
        TopPanel.add(cbVBNK);
        TopPanel.add(lblVBNK);
        JPanel PanelVBNK = new JPanel(new BorderLayout());
        add(PanelVBNK, BorderLayout.WEST);
        PanelVBNK.add(lblExecutorVBNK);
        JPanel PanelSBL = new JPanel(new BorderLayout());
        add(PanelSBL, BorderLayout.EAST);
        PanelSBL.add(lblExecutorSBL);
        JPanel PanelESB = new JPanel(new BorderLayout());
        add(PanelESB, BorderLayout.SOUTH);
        PanelESB.add(lblExecutorESB);/**/
        setVisible(true);
    }
}
