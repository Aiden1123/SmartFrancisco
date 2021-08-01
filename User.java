package grafikaa;
import java.util.ArrayList;
import java.io.Serializable;

//	User class stores data about a client

public class User  implements Serializable{
	private static final long serialVersionUID = 10;
	private int id;
	private String name;
	private String surname;
	private String document_num;
	private ArrayList<Integer> accounts;	//List containing ids of all user's accounts
	static private int COUNT=0;
	static private ArrayList<Integer> id_database= new ArrayList<Integer>();	//database to ensure that all ids are unique
	
	//constructors
	
	User(){
		COUNT++;
		this.id=COUNT;
		while(id_database.contains(this.id)) {
			this.id++;
		}
		this.name=null;
		this.surname=null;
		this.document_num=null;
		this.accounts=new ArrayList<Integer>();
	}
	
	User(String n, String s, String d) {
		COUNT++;
		this.id=COUNT;
		while(id_database.contains(this.id)) {
			this.id++;
		}
		this.name=n;
		this.surname=s;
		this.document_num=d;
		this.accounts=new ArrayList<Integer>();
	}
	
	public void credentials(String n, String s, String d) {	//this function sets the new credentials
		this.name=n;
		this.surname=s;
		this.document_num=d;
	}
	
	public void info() {	//prints information in console about user
		System.out.printf("id: %d%nname and surname: %s %s%nid number: %s%namount of accounts: %d%n", id,name,surname,document_num,accounts.size());
	}
	
	public String stringInfo() {	//returns a string containing information about user
		return String.format("id: %d%nname and surname: %s %s%nid number: %s%namount of accounts: %d%n", id,name,surname,document_num,accounts.size()); 
	}
	
	public ArrayList<Integer> accountsIds() {	//retuns a dynamic list of all accounts' ids
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		for(int i: accounts) {
			temp.add(i);
		}
		
		return temp;
	}
	
	//getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getDocument_num() {
		return document_num;
	}

	public void setDocument_num(String document_num) {
		this.document_num = document_num;
	}

	public ArrayList<Integer> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Integer> accounts) {
		this.accounts = accounts;
	}

	public static int getCOUNT() {
		return COUNT;
	}

	public static void setCOUNT(int cOUNT) {
		COUNT = cOUNT;
	}
}
