import com.aspose.words.*;

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
        switch (jobs) {
            case 1: filename += "_ВаБанк"; break;
            case 2: filename += "_Siebel"; break;
            case 3: filename += "_Siebel_ВаБанк"; break;
            case 4: filename += "_ESB"; break;
            case 5: filename += "_ВаБанк_ESB"; break;
            case 6: filename += "_Siebel_ESB"; break;
            case 7: filename += "_Siebel_ВаБанк_ESB"; break;
        }
    }

    public void BuildDoc () throws Exception {
        DocumentBuilder builder = new DocumentBuilder(doc);
        Table table = (Table) doc.getChild(NodeType.TABLE, 2, true);
        for (Row row : table.getRows()) {
            if ((row.getText().contains("s.isSBL")) || (row.getText().contains("s.isSED"))) row.remove();
        }
        //if (!is)
        Sender sender = new Sender(date, prevDate, nextDate);
        ReportingEngine engine = new ReportingEngine();
        engine.buildReport(doc, sender, "s");
        doc.save(filename+"_"+date+".docx");
    }
}
