package javaTester;

public class Topic_04_Getter_Setter {
 String carName;
 public static void main(String[] args) {
	 Topic_04_Getter_Setter topic_04=new Topic_04_Getter_Setter();
	 topic_04.setCarName("Toyota Camry");
	 System.out.println(topic_04.getCarName());
 }
 public String getCarName() {
	 return this.carName;
 }
 public void setCarName (String car) {
	 carName=car;
 }
}
