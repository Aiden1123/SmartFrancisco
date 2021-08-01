package grafikaa;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.Serializable;

// UserDatabase class is used to group users together to better maintain the data
public class UserDatabase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4860302564913136929L;
	private String name;
	private ArrayList<User> users;
	
	//constructor
	UserDatabase(String n) {
		this.name=n;
		this.users = new ArrayList<User>();
	}
	
	public void addUser(User u) {
		this.users.add(u);
	}
	
	public void deleteUser(User u) {
		this.users.remove(u);
	}
	
	public User findId(int x) {
		for(User i: users) {
			if (i.getId()==x) {
				return i;
			}
		}
		return null;
	}
	
	public ArrayList<User> findName(String n, String s) {		//returns array containing all users with matching name
		ArrayList<User> temp = new ArrayList<User>();
		
		for(User i: users) {
			if (i.getName().equals(n) && i.getSurname().equals(s)) {
				temp.add(i);
			}
		}
		
		return temp;		
	}
	
	public int size() {
		return users.size();
	}
	
	public void info() {	//prints info about all users
		for(User i: users) {
			i.info();
		}
	}
	
	public String stringInfo() {	//returns a string with info about all users
		String temp = "";
		
		for(User i: users) {
			temp+=i.stringInfo();
			temp+=System.getProperty("line.separator");
		}
		
		return temp;
	}
	
	public ArrayList<String> Names() {		//returns array with all users' credentials
		ArrayList<String> temp= new ArrayList<String>();
		
		for(User i: users) {
			temp.add(i.getName()+" "+i.getSurname());
		}
		
		return temp;
	}
	
	public ArrayList<Integer> num() {		//returns array with ids of all the users
		ArrayList<Integer> temp= new ArrayList<Integer>();
		
		for(User i: users) {
			temp.add(i.getId());
		}
		
		return temp;
	}
	
	
	public void writeToFile(String filename) {
		try {
			FileOutputStream o = new FileOutputStream(new File(filename));
			ObjectOutputStream output = new ObjectOutputStream(o);
			output.writeObject(this);
			o.close();
			output.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error while writing to file");
		}
	}
	
	static public UserDatabase readFromFile(String filename) {
		UserDatabase aux=null;
		
		try {
			FileInputStream in = new FileInputStream(new File(filename));
			ObjectInputStream input = new ObjectInputStream(in);
			
			try {
				aux = (UserDatabase) input.readObject();
			}
			catch (IOException e) {e.printStackTrace();}
			catch (ClassNotFoundException e) {e.printStackTrace();}
			
			in.close();
			input.close();
		} catch (IOException e) {
			System.out.println("error while reading accounts from file");
			
		}
		
		User.setCOUNT(aux.size());
		
		return aux;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
