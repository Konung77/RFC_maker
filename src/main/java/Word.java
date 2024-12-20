import com.aspose.words.*;

import javax.swing.*;

public class Word {
    private int jobs;
    private String date, prevDate, nextDate;
    private Document doc;
    private String filename = "RFC";

    public Word(int _jobs, String _curdate, String _prevdate, String _nextdate) throws Exception {
        jobs = _jobs;
        date = _curdate;
        prevDate = _prevdate;
        nextDate = _nextdate;
        doc = new Document("Шаблон RFC.docx");
        if (MainForm.isSBL) filename += "_Siebel";
        if (MainForm.isVBNK) filename += "_ВаБанк";
        if (MainForm.isESB) filename += "_ESB";
        /*switch (jobs) {
            case 1: filename += "_ВаБанк"; break;
            case 2: filename += "_Siebel"; break;
            case 3: filename += "_Siebel_ВаБанк"; break;
            case 4: filename += "_ESB"; break;
            case 5: filename += "_ВаБанк_ESB"; break;
            case 6: filename += "_Siebel_ESB"; break;
            case 7: filename += "_Siebel_ВаБанк_ESB"; break;
        }*/
    }

    public void BuildDoc () throws Exception {
        DocumentBuilder builder = new DocumentBuilder(doc);
        //DataSet ds = new DataSet();
        Table table = (Table) doc.getChild(NodeType.TABLE, 2, true);
        Table tableCancelESB = (Table) doc.getChild(NodeType.TABLE, 4, true);
        Table tableCancelSED = (Table) doc.getChild(NodeType.TABLE, 5, true);
        Table tableVBNK = (Table) doc.getChild(NodeType.TABLE, 6, true);
        Table tableSBL = (Table) doc.getChild(NodeType.TABLE, 7, true);
        //Table tableESB = (Table) doc.getChild(NodeType.TABLE, 9, true);
        //Table tableSED = (Table) doc.getChild(NodeType.TABLE, 10, true);

        // Удаляем лишние строки в таблице
        // При работах на ВаБанк без ДТ на патч ставим 15 мин вместо 30
        if (MainForm.isVBNK && !MainForm.isDT) {
            for (Row row : table.getRows())
                if (row.getText().contains("s.isDT")) row.remove();
            doc.getRange().replace("Запрос в ДС и ", "");
            doc.getRange().replace("30 мин", "15 мин");
            tableVBNK.remove();
        }
        if (!MainForm.isSBLScript)
            for (Row row : table.getRows())
                if (row.getText().contains("s.isSBLScript")) row.remove();
            //doc.getRange().replace("Выполнить скрипт на БД Siebel:", "");
        if (!MainForm.isSBL) {
            for (Row row : table.getRows())
                if (row.getText().contains("s.isSBL()")) row.remove();
            //doc.getRange().replace("Приложение 2", "");
            tableSBL.remove();
            doc.getRange().replace("1 и Приложения 2", "");
        }
        if (!MainForm.isSED) {
            for (Row row : table.getRows())
                if (row.getText().contains("s.isSED")) row.remove();
            //doc.getRange().replace("План отката доработок СЭД Пенсионер", "");
            tableCancelSED.remove();
            //tableSED.remove();
        }
        if (!MainForm.isESB) {
            tableCancelESB.remove();
            //tableESB.remove();
            //doc.getRange().replace("План отката на корпоративной интеграционной шине (ESB):", "");/**/
            doc.getRange().replace("После выполнения работ на ESB", "");/**/
        }
        //if (!MainForm.isSED) tableCancelSED.remove();

        Node[] nodes = doc.getChildNodes(NodeType.PARAGRAPH, true).toArray();
        Paragraph[] rows = new Paragraph[nodes.length];
        // Удаляем лишние строки в документе вне таблицы
        for (int i = 0; i < rows.length; i++)
            rows[i] = (Paragraph) doc.getChild(NodeType.PARAGRAPH, i, true);
        for (Paragraph row : rows) {
            if (!MainForm.isSBL)
                if (row.getText().contains("Приложение 2")) row.remove();
            if (MainForm.isVBNK) {
                if (row.getText().contains("Приложение 1")) row.remove();
                if (!MainForm.isDT)
                    if (row.getText().contains("s.isDT()")) row.remove();
            }
            if (!MainForm.isSED) {
                //if (row.getText().contains("План отката доработок СЭД Пенсионер")) row.remove();
                if (row.getText().contains("s.isSED()")) row.remove();
            }
            if (!MainForm.isESB) {
                //if (row.getText().contains("План отката на корпоративной интеграционной шине")) row.remove();
                if (row.getText().contains("s.isESB()")) row.remove();
            }
        }
        Sender sender = new Sender(date, prevDate, nextDate);
        ReportingEngine engine = new ReportingEngine();
        engine.buildReport(doc, sender, "s");
        doc.save(filename+"_"+date+".docx");
        JOptionPane.showMessageDialog(null, "Файл RFC сформирован");
    }
}
