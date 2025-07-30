public class RecordCreator {

    private String Name;
    private String StudentNum;
    private String CourseName;
    private String CourseID;
    private String HouseNum;
    private String StreetName;
    private String Town;
    private String Postcode;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStudentNum() {
        return StudentNum;
    }

    public void setStudentNum(String studentNum) {
        StudentNum = studentNum;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String courseID) {
        CourseID = courseID;
    }

    public String getHouseNum() {
        return HouseNum;
    }

    public void setHouseNum(String houseNum) {
        HouseNum = houseNum;
    }

    public String getStreetName() {
        return StreetName;
    }

    public void setStreetName(String streetName) {
        StreetName = streetName;
    }

    public String getTown() {
        return Town;
    }

    public void setTown(String town) {
        Town = town;
    }

    public String getPostcode() {
        return Postcode;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }

    public RecordCreator(String name, String studentNum, String courseName, String courseID, String houseNum, String streetName, String town, String postcode) {
        Name = name;
        StudentNum = studentNum;
        CourseName = courseName;
        CourseID = courseID;
        HouseNum = houseNum;
        StreetName = streetName;
        Town = town;
        Postcode = postcode;
    }

    public RecordCreator() {
        super();
    }
}
