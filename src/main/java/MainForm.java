import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MainForm extends JFrame {

    // jobs = summa of (1 - VaBank, 2 - Siebel, 4 - ESB)
    public int jobs = 0;
    public static boolean isDT = true;
    public static boolean isSED = false;
    public static boolean isEO = false;
    public static boolean isVBNK = false;
    public static boolean isSBL = false;
    public static boolean isESB = false;
    public static String executorVBNK;
    public static String executorSBL;
    public static String getExecutorESB;
    public static String patchName;

    private final String[] listInit = {"Маркин Станислав Олегович, доб. 1321, почта markinso1@pochtabank.ru", "Савин Юрий Алексеевич, доб. 1674, почта savinya@pochtabank.ru"};
    private final String[] listVBNK = {"", "Глебов Максим +7(910)282-43-25", "Калдин Александр +7(950)757-13-86", "Кучеев Константин +7(951)850-07-77", "Сидоров Дмитрий +7(952)542-18-26", "Шибзухов Тимур +7(962)328-62-32"};
    private final String[] listSBL = {"", "Донцов Олег +7(980)344-77-27)", "Колюкаев Сергей +7(915)543-78-95)"};
    private final String[] listESB = {"", "Абидов Тохир +7(980)559-34-31", "Альпатов Андрей +7(950)777-76-32", "Филиппов Максим +7(916)788-86-26"};
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
    private JLabel lblInitiator = new JLabel("Инициатор работ:");
    private JLabel lblExecutorVBNK = new JLabel("Исполнитель работ на ВаБанк:");
    private JLabel lblExecutorSBL = new JLabel("Исполнитель работ на Siebel:");
    private JLabel lblExecutorESB = new JLabel("Исполнитель работ на ESB:");
    private JComboBox cbxInit = new JComboBox<>(listInit);
    private JComboBox cbxVBNK = new JComboBox<>(listVBNK);
    private JComboBox cbxSBL = new JComboBox<>(listSBL);
    private JComboBox cbxESB = new JComboBox<>(listESB);
    private JButton btn1 = new JButton("Сформировать RFC");

    public MainForm() throws HeadlessException {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();
        setBounds((dim.width-FORM_WIDTH)/2, 10, FORM_WIDTH, FORM_HEIGHT);
        setTitle("RFC Maker");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        int start_y = 10;
        lbl1.setBounds(10, start_y, 200, 20);
        add(lbl1);
        tfDate.setBounds(210, start_y, 150, 20);
        add(tfDate);
        LocalDate date = LocalDate.now().plusDays(1);
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyMMdd");
        String curdate = date.format(formatter);
        patchName = date.format(formatter1);
        String prevdate = date1.format(formatter);
        String nextdate = date2.format(formatter);
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
        cbDT.setSelected(true);
        lblDT.setBounds(280, start_y+60, 150, 20);
        lblDT.setEnabled(false);
        add(cbDT);
        add(lblDT);
        cbDT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbDT.isSelected()) isDT = true;
                else isDT = false;
            }
        });
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
        cbESB.setEnabled(false);
        lblESB.setBounds(40, start_y+120, 200, 20);
        lblESB.setEnabled(false);
        add(cbESB);
        add(lblESB);
        /*cbESB.addActionListener(new ActionListener() {
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
        });*/
        lblInitiator.setBounds(10, start_y+150, 250, 20);
        cbxInit.setBounds(260, start_y+150, 200, 20);
        add(lblInitiator);
        add(cbxInit);
        lblExecutorVBNK.setBounds(10, start_y+180, 250, 20);
        cbxVBNK.setBounds(260, start_y+180, 200, 20);
        lblExecutorVBNK.setEnabled(false);
        cbxVBNK.setEnabled(false);
        add(lblExecutorVBNK);
        add(cbxVBNK);
        lblExecutorSBL.setBounds(10, start_y+210, 250, 20);
        cbxSBL.setBounds(260, start_y+210, 200, 20);
        lblExecutorSBL.setEnabled(false);
        cbxSBL.setEnabled(false);
        add(lblExecutorSBL);
        add(cbxSBL);
        lblExecutorESB.setBounds(10, start_y+240, 250, 20);
        cbxESB.setBounds(260, start_y+240, 200, 20);
        lblExecutorESB.setEnabled(false);
        cbxESB.setEnabled(false);
        add(lblExecutorESB);
        add(cbxESB);
        lbl3.setBounds(10, start_y+270, 300, 20);
        add(lbl3);
        cbSED.setBounds(10, start_y+297, 25, 25);
        lblSED.setBounds(40, start_y+300, 200, 20);
        cbSED.setEnabled(false);
        lblSED.setEnabled(false);
        add(cbSED);
        add(lblSED);
        cbEO.setBounds(10, start_y+327, 25, 25);
        lblEO.setBounds(40, start_y+330, 250, 20);
        cbEO.setEnabled(false);
        lblEO.setEnabled(false);
        add(cbEO);
        add(lblEO);
        btn1.setBounds(170, 600, 200, 20);
        add(btn1);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (cbDT.isSelected()) isDT = true;
                    if (cbSED.isSelected()) isSED = true;
                    if (cbEO.isSelected()) isEO = true;
                    if (cbVBNK.isSelected()) {
                        executorVBNK = cbxVBNK.getSelectedItem().toString();
                        if (executorVBNK == "") {
                            JOptionPane.showMessageDialog(null, "Необходимо выбрать исполнителя работ на ВаБанк");
                            return;
                        }
                    }
                    if (cbSBL.isSelected()) {
                        executorSBL = cbxSBL.getSelectedItem().toString();
                        if (executorSBL == "") {
                            JOptionPane.showMessageDialog(null, "Необходимо выбрать исполнителя работ на Siebel");
                            return;
                        }
                    }
                    Word word = new Word(jobs, tfDate.getText(), prevdate, nextdate);
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
