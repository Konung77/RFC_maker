import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class MainForm extends JFrame implements ActionListener {

    private final int FORM_WIDTH = 500;
    private final int FORM_HEIGHT = 700;
    Calendar calendar = Calendar.getInstance();
    //calendar.add (Calendar.DATE, 1);
    private JLabel lbl1 = new JLabel("Дата проведения работ");
    private JLabel lbl2 = new JLabel("Выберите системы для проведения работ:");
    private JLabel lbl3 = new JLabel("Дополнительные работы");
    private JTextField tfDate = new JTextField();//calendar.getTime().toString());
    private JCheckBox cbVBNK = new JCheckBox();
    private JLabel lblVBNK = new JLabel("АБС ВаБанк");
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
    private JComboBox cbxVBNK = new JComboBox<>();
    private JComboBox cbxSBL = new JComboBox<>();
    private JComboBox cbxESB = new JComboBox<>();
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
        lbl2.setBounds(10, start_y+30, 300, 20);
        add(lbl2);
        cbVBNK.setBounds(10, start_y+57, 25, 25);
        lblVBNK.setBounds(40, start_y+60, 100, 20);
        add(cbVBNK);
        add(lblVBNK);
        cbSBL.setBounds(10, start_y+87, 25, 25);
        lblSBL.setBounds(40, start_y+90, 100, 20);
        add(cbSBL);
        add(lblSBL);
        cbESB.setBounds(10, start_y+117, 25, 25);
        lblESB.setBounds(40, start_y+120, 200, 20);
        add(cbESB);
        add(lblESB);
        lblExecutorVBNK.setBounds(10, start_y+150, 200, 20);
        cbxVBNK.setBounds(210, start_y+150, 200, 20);
        lblExecutorVBNK.setEnabled(false);
        cbxVBNK.setEnabled(false);
        add(lblExecutorVBNK);
        add(cbxVBNK);
        lblExecutorSBL.setBounds(10, start_y+180, 200, 20);
        cbxSBL.setBounds(210, start_y+180, 200, 20);
        lblExecutorSBL.setEnabled(false);
        cbxSBL.setEnabled(false);
        add(lblExecutorSBL);
        add(cbxSBL);
        lblExecutorESB.setBounds(10, start_y+210, 200, 20);
        cbxESB.setBounds(210, start_y+210, 200, 20);
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
        btn1.setBounds(170, 600, 150, 20);
        add(btn1);
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        MainForm main_form = new MainForm();
    }

}
