package collection;

public class Human {
    private int age; //Значение поля должно быть больше 0
    public Human(int age){
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return "\nHuman: " +
                "\nage: " + age;
    }
}

