package grafikaa; 
import java.io.Serializable;
import java.lang.Math;
import java.util.ArrayList;


//	konto class is used for storing data about accounts

public class konto implements Serializable {
	
	private static final long serialVersionUID = 1123581321; 
	static int COUNT = 0;
	private int id;
	private double money;
	private double rate;
	private String name;
	private String surname;
	private int owner_id;
	static private ArrayList<Integer> id_database= new ArrayList<Integer>(); //database to ensure that all ids are unique
	
	public void printInfo() {	//Displays info about account in console
		System.out.printf("Account nr: %d%nOwner: %s %s%nOwner Id: %d%nMoney: %.2f Rate: %.2f%n",id,name,surname,owner_id,money,rate);
	}
	
	public String stringInfo() {	//returns a string containing information about account
		return String.format("Account nr: %d%nOwner: %s %s%nOwner Id: %d%nMoney: %.2f Rate: %.2f%n",id,name,surname,owner_id,money,rate);
	}
	
	public double prediction(int y) {	//Predicts future balance after y years based on yearly rate
		return money*Math.pow((1+rate/100),y);
	}
	
	public String predictionString(int y) {	//returns a string containing predicted balance after y years
		return this.stringInfo() + System.getProperty("line.separator") + String.format("Balance after %d years: %.2f", y,this.prediction(y)) + System.getProperty("line.separator");
	}
	
	//constructors
	
	konto() {
		COUNT++;
		this.id=COUNT;
		while(id_database.contains(this.id)) {
			this.id++;
		}
	}
	
	konto(double m) {
		COUNT++;
		this.money=m;
		this.id=COUNT;
		while(id_database.contains(this.id)) {
			this.id++;
		}
	}
	
	konto(double m, double r) {
		COUNT++;
		this.money=m;
		this.rate=r;
		this.id=COUNT;
		while(id_database.contains(this.id)) {
			this.id++;
		}
	}
	konto(String n, String ln, double m, double r) {
		COUNT++;
		this.id=COUNT;
		while(id_database.contains(this.id)) {
			this.id++;
		}
		this.money=m;
		this.rate=r;
		this.name=n;
		this.surname=ln;
	}
	konto(User o, double m, double r) {
		COUNT++;
		this.id=COUNT;
		while(id_database.contains(this.id)) {
			this.id++;
		}
		this.money=m;
		this.rate=r;
		this.name=o.getName();
		this.surname=o.getSurname();
		this.owner_id=o.getId();
		o.getAccounts().add(this.getId());
	}
	
	//getters and setters
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public int getId() {
		return id;
	}

	public int getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}

	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
}
