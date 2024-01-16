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
    private String date;
    private boolean _isVBNK = false;
    private boolean _isSBL = false;

    public Sender(String _date) {
        initiator = "Маркин Станислав Олегович доб. 1321 почта markinso1@pochtabank.ru";
        curator = "Маркин Станислав";
        phone = "+7(968)891-72-72";
        date = _date;
        downtime = "на время установки патчей недоступность Ва-Банк и ДБО до 60 минут и частичная недоступность системы Siebel в рамках процессов с ВаБанком.";
        if (MainForm.isVBNK) {
            executorVBNK = MainForm.executorVBNK;
            _isVBNK = true;
            if (systems.isEmpty()) systems += "АБС ВаБанк";
                else systems += ", АБС ВаБанк";
            if (jobs.isEmpty()) jobs += "БД ВаБанк АБС: vabank4, vabank5, vabank6; сервера приложений: VBNKAPPPRD05, VBNKAPPPRD06, appabs02";
                else jobs += "; БД ВаБанк АБС: vabank4, vabank5, vabank6; сервера приложений: VBNKAPPPRD05, VBNKAPPPRD06, appabs02";
            if (dependencies.isEmpty()) dependencies += "АБС ВаБанк, Siebel, ДБО";
                else dependencies += ", АБС ВаБанк, Siebel, ДБО";
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

    public String isVBNK() { return ""; }

    public String isSBL() { return ""; }
}
