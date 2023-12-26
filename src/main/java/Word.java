import com.aspose.words.Document;

public class Word {
    private int jobs;
    private String date;
    private Document doc;
    private String filename = "RFC";

    public Word(int _jobs, String _date) throws Exception {
        jobs = _jobs;
        date = _date;
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
        doc.save(filename+"_"+date+".docx");
    }
}
