public class Sender {
    private String systems = "";
    private String initiator;
    private String curator;
    private String phone;
    private String dependencies = "";
    private String downtime;
    private String jobs = "";
    private String executorVBNK;
    private String executorSBL;
    private String executorESB;
    private String date, prevDate, nextDate;
    private boolean _isVBNK = false;
    private boolean _isDT = false;
    private boolean _isSBL = false;
    private boolean _isESB = false;
    private boolean _isSED = false;

    public Sender(String _date, String _prevdate, String _nextdate) {
        initiator = "Маркин Станислав Олегович доб. 1321 почта markinso1@pochtabank.ru";
        curator = "Маркин Станислав";
        phone = "+7(968)891-72-72";
        date = _date;
        prevDate = _prevdate;
        nextDate = _nextdate;
        if (MainForm.isDT) downtime = "на время установки патчей недоступность Ва-Банк и ДБО до 60 минут и частичная недоступность системы Siebel в рамках процессов с ВаБанком";
        else downtime = "не предусмотрен при штатном поведении систем";
        if (MainForm.isVBNK) {
            executorVBNK = MainForm.executorVBNK;
            _isVBNK = true;
            if (!systems.isEmpty()) systems += ", ";
            systems += "АБС ВаБанк";
            if (!jobs.isEmpty()) jobs += "; ";
            if (MainForm.isDT) jobs += "БД ВаБанк АБС: vabank4, vabank5, vabank6; сервера приложений: VBNKAPPPRD05, VBNKAPPPRD06, appabs02";
            else jobs += "БД ВаБанк АБС: vabank4, vabank5";
        }
        if (MainForm.isSBL) {
            executorSBL = MainForm.executorSBL;
            _isSBL = true;
            if (!systems.isEmpty()) systems += ", ";
            systems += "Siebel";
            if (!jobs.isEmpty()) jobs += "; ";
            jobs += "БД Siebel (sbldb, sblstdb); сервера Siebel (sblgw1-3, sblweb10-17, sblapp20-31)";
        }
        if (_isVBNK || _isSBL) {
            if (!dependencies.isEmpty()) dependencies += ", ";
            if (MainForm.isDT) dependencies += "АБС ВаБанк, Siebel, ДБО";
            else dependencies += "АБС ВаБанк";
        }
    }

    public String getSystems() {
        return systems;
    }

    public String getInitiator() {
        return initiator;
    }

    public String getCurator() {
        return curator;
    }

    public String getPhone() {
        return phone;
    }

    public String getDependencies() {
        return dependencies;
    }

    public String getDowntime() {
        return downtime;
    }

    public String getJobs() {
        return jobs;
    }

    public String getExecutorVBNK() {
        return executorVBNK;
    }

    public String getExecutorSBL() {
        return executorSBL;
    }

    public String getExecutorESB() {
        return executorESB;
    }

    public String getDate() {
        return date;
    }

    public String getPrevDate() {
        return prevDate;
    }

    public String getNextDate() {
        return nextDate;
    }

    public String getEndTime() {
        if (MainForm.isVBNK && !MainForm.isDT) return "01:30";
        else return "02:30";
    }

    public String getPatchName() {
        if (MainForm.isVBNK) {
            if (MainForm.isDT) return MainForm.patchName;
            else return MainForm.patchName+"#TUNE";
        }
        else return "";
    }

    public String isVBNK() { return ""; }

    public String isDT() { return ""; }

    public String isSBL() { return ""; }

    public String isESB() { return ""; }

    public String isSED() { return ""; }
}
