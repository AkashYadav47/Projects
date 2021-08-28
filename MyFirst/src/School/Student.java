package School;

public class Student {
    private int roll;
    private String name;
    private String dept;
    private Subject subs[];
    public Student(int roll,String name,String dept){
        this.roll = roll;
        this.name = name;
        this.dept = dept;
    }
    public void setSubs(Subject ...subs) {
        for(Subject sub: subs){

        }
    }
    public Subject[] getSubs() {
        return subs;
    }
}
