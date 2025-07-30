public class PreferencesCreator {

    private String year;
    private String school;
    private String topic;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public PreferencesCreator(String year, String school, String topic) {
        this.year = year;
        this.school = school;
        this.topic = topic;
    }
}
