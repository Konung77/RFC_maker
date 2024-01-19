import com.aspose.words.*;
import com.aspose.words.net.System.Data.DataSet;

import java.util.Arrays;
//import com.aspose.words.ReportingEngine;

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
        if (MainForm.isVBNK) filename += "_ВаБанк";
        if (MainForm.isSBL) filename += "_Siebel";
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
        DataSet ds = new DataSet();
        Table table = (Table) doc.getChild(NodeType.TABLE, 2, true);
        Table tableCancelESB = (Table) doc.getChild(NodeType.TABLE, 4, true);
        Table tableCancelSED = (Table) doc.getChild(NodeType.TABLE, 5, true);
        Table tableVBNK = (Table) doc.getChild(NodeType.TABLE, 6, true);
        Table tableSBL = (Table) doc.getChild(NodeType.TABLE, 7, true);
        if (!MainForm.isSBL) {
            for (Row row : table.getRows())
                if (row.getText().contains("s.isSBL")) row.remove();
            tableSBL.remove();
            //builder.getDocument().;
            //builder.moveToParagraph(100,0);
            builder.moveToSection(5);
            builder.write("!!!10---");
        }
        if (!MainForm.isSED)
            for (Row row : table.getRows())
                if (row.getText().contains("s.isSED")) row.remove();
        if (!MainForm.isESB) tableCancelESB.remove();
        if (!MainForm.isSED) tableCancelSED.remove();
        Sender sender = new Sender(date, prevDate, nextDate);
        ReportingEngine engine = new ReportingEngine();
        engine.buildReport(doc, sender, "s");
        doc.save(filename+"_"+date+".docx");
    }
}
