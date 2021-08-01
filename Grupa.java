package grafikaa;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//	Grupa class is used to group accounts together to better maintain data

public class Grupa {
	String name;
	private ArrayList<konto> accounts;
	
	//constructor
	Grupa(String n) {
		this.name=n;
		accounts = new ArrayList<konto>();
	}
	
	public int addAccount(User u,double m, double r) {
		konto tmp;
		tmp = new konto(u,m,r);
		accounts.add(tmp);
		return tmp.getId();
	}
	
	public int getSize() {
		return accounts.size();
	}
	
	public void removeAll() {
		accounts.clear();
	}
	
	public void printAll() {	//prints to console information about all accounts
		
		if (this.getSize()==0) {
			System.out.println("No accounts found");
			return;
		}
		
		System.out.printf("Amount of accounts: %d%n", this.getSize());
		
		for(konto a: accounts) {
			a.printInfo();
		}
	}
	
	public String printAllString() {	//returns a string containing information about all accounts
		if (this.getSize()==0) {
			return "No accounts found";
		}
		
		String output = String.format("Amount of account: %d%n%n", this.getSize());
		
		for(konto a: accounts) {
			output+=a.stringInfo();
			output+=System.getProperty("line.separator");
		}
		
		return output;
	}
	
	public konto findId(int x) {	//finds account by id
		for (konto i: accounts) {
			if (i.getId()==x) {
				return i;
			}
		}
		return null;
	}
	
	
	public ArrayList<konto> findAllAccId(int x) {		//finds all accounts of a user by id
		ArrayList<konto> res = new ArrayList<konto>();
		
		for (konto i: accounts) {
			if (i.getOwner_id()==x) {
				res.add(i);
			}
		}
		
		return res;
	}
	
	public void updateBalanceId(int i, double b) {
		konto temp = this.findId(i);
		
		if (temp==null) {
			System.out.println("Account not found");
			return;
		}
		
		temp.setMoney(b);
	}
	
	public void updateRateId(int i, double r) {
		konto temp = this.findId(i);
		
		if (temp==null) {
			System.out.println("Account not found");
			return;
		}
		
		temp.setRate(r);
	}
	
	public void addMoneyId(int i, double b) {
		konto temp = this.findId(i);
		
		if (temp==null) {
			System.out.println("Account not found");
			return;
		}
		
		temp.setMoney(temp.getMoney()+b);
	}
	
	public void newRateGlobally(double r) {
		for(konto i: accounts) {
			i.setRate(r);
		}
	}
	
	public String printAllAccountsString(User o) {		//returns string containing information about all accounts of a user
		
		ArrayList<konto> temp = this.findAllAccId(o.getId());
		if (temp.size()==0) {
			return "Account not found";
		}
		
		String res ="";
		for(konto x: temp) {
			res+=x.stringInfo();
			res+=System.getProperty("line.separator");
		}
		
		return res;
	}
	
	public String printAccountIdString(int x) {		//returns string containing information about account with id x
		konto temp = this.findId(x);
		
		if (temp==null) {
			return "Account not found";
		}
		
		return temp.stringInfo();
	}
	
	public void printAccountId(int x) {	//prints information about account with id x
		konto temp = this.findId(x);
		
		if (temp==null) {
			System.out.println("Account not found");
			return;
		}
		
		temp.printInfo();
	}
	
	
	public void deleteId(int x) {
		konto temp = this.findId(x);
		
		if (temp==null) {
			System.out.println("Account not found");
			return;
		}
		
		accounts.remove(temp);		
	}
	
	public void deleteOwnersAccounts(User o) {
		ArrayList<konto> temp = new ArrayList<konto>();
		
		for(konto i: accounts) {
			if (i.getOwner_id()==o.getId()) {
				temp.add(i);		
			}
		}
		
		for(konto i: temp) {
			accounts.remove(i);
		}
	}
	
	public String prediction(int x, int years) {	//returns a string containing predicted balance
		konto temp = this.findId(x);
		
		if (temp==null) {
			return "Account not found";
		}
		
		return temp.predictionString(years);
	}
	
	public void addIntrest(int x) {
		konto temp = this.findId(x);
		
		if (temp==null) {
			System.out.println("Account not found");
			return;
		}
		
		temp.setMoney(temp.prediction(1));
	}
	
	public void addIntrestEveryone() {
		for(konto i:accounts) {
			i.setMoney(i.prediction(1));
		}
	}
	
	public void writeToFile(String filename) {
		try {
			FileOutputStream o = new FileOutputStream(new File(filename));
			ObjectOutputStream output = new ObjectOutputStream(o);
			
			for(konto i: this.accounts) {
				output.writeObject(i);
			}
			
			o.close();
			output.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error while writing to file");
		}
	}
	
	public void readFromFile(String filename) {
		this.accounts.clear();
		konto aux;
		
		try {
			FileInputStream in = new FileInputStream(new File(filename));
			ObjectInputStream input = new ObjectInputStream(in);
			
			while (true) {
				try {
						aux = (konto) input.readObject();
						accounts.add(aux);
						konto.COUNT++;
					}
				catch (IOException e) {break;}
				catch (ClassNotFoundException e) {break;}
			}
			
			in.close();
			input.close();
			
		} catch (IOException e) {
			System.out.println("error while reading from file");
		}
	}
}
