package School;

public class Subject {
    private String subID;
    private String name;
    private int maxMarks;
    private int marks;
    public Subject(String subID, String name){
        this.subID = subID;
        this.name = name;
    }
    public Subject(String subID, String name,int maxMarks, int marks){
        this.subID = subID;
        this.name = name;
        this.maxMarks = maxMarks;
        this.marks = marks;
    }
    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }
    public void setMarks(int marks) {
        this.marks = marks;
    }
    public String getSubID() {
        return subID;
    }
    public String getName() {
        return name;
    }
    public int getMaxMarks() {
        return maxMarks;
    }
    public int getMarks() {
        return marks;
    }
    public String toString(){
        return subID+" "+name+"\n";
    }
}
