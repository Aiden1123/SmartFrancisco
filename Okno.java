package grafikaa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextPane;
import javax.swing.JButton;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
public class Okno extends JFrame {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1106307491368504662L;
	ArrayList<Integer> intArray = new ArrayList<Integer>();
	private JPanel contentPane;
	int auxillary_int;
	String filename = "";
	String user_filename = "";
	Grupa acc = new Grupa("new group");
	UserDatabase users;
	JTextPane textPane;
	boolean unsaved_changes=false;			
	konto currAcc = null;
	User currUser = null;
	JTextPane textPane_name;
	JTextPane textPane_surname;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Okno frame = new Okno();
				    frame.addWindowListener(new WindowAdapter() {
				        public void windowClosing(WindowEvent we) {		//Dialog window which asks user for confirmation before closing is unsaved changes are present
				          if (frame.unsaved_changes) {
				        	int result = JOptionPane.showConfirmDialog(frame,
				              "Some changes have not been saved"+ System.getProperty("line.separator")
				              + "do you want to leave anyway?", "unsaved changes", JOptionPane.YES_NO_OPTION);
				          if (result == JOptionPane.YES_OPTION)
				            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				          else if (result == JOptionPane.NO_OPTION)
				            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				          } else {
				        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				          }
				          }
				      });
				    frame.chooseUserDatabase();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Okno() {

		setTitle("SmartFrancisco");
		setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 931, 528);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(20, 0, 7, 47);
		contentPane.add(textPane);
		
		JTextPane txtpnAccountId = new JTextPane();
		txtpnAccountId.setEditable(false);
		txtpnAccountId.setText("Account id:");
		txtpnAccountId.setBackground(Color.LIGHT_GRAY);
		txtpnAccountId.setBounds(651, 14, 103, 20);
		contentPane.add(txtpnAccountId);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(289, 147, 270, 125);
		contentPane.add(textPane_1);
		
		JTextPane textPane_1_1 = new JTextPane();
		textPane_1_1.setEditable(false);
		textPane_1_1.setText("Owner Id:");
		textPane_1_1.setBackground(Color.LIGHT_GRAY);
		textPane_1_1.setBounds(289, 14, 88, 20);
		contentPane.add(textPane_1_1);
		
		JTextPane textPane_ownerId = new JTextPane();
		textPane_ownerId.setBounds(391, 14, 172, 20);
		contentPane.add(textPane_ownerId);
		
		JTextPane textPane_1_2 = new JTextPane();
		textPane_1_2.setEditable(false);
		textPane_1_2.setText("Name:");
		textPane_1_2.setBackground(Color.LIGHT_GRAY);
		textPane_1_2.setBounds(289, 45, 80, 20);
		contentPane.add(textPane_1_2);
		
		JTextPane textPane_2_2 = new JTextPane();
		textPane_2_2.setEditable(false);
		textPane_2_2.setText("Surname:");
		textPane_2_2.setBackground(Color.LIGHT_GRAY);
		textPane_2_2.setBounds(289, 79, 103, 20);
		contentPane.add(textPane_2_2);
		
		textPane_surname = new JTextPane();
		textPane_surname.setBounds(391, 79, 172, 20);
		contentPane.add(textPane_surname);
		
		textPane_name = new JTextPane();
		textPane_name.setBounds(391, 45, 172, 20);
		contentPane.add(textPane_name);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(569, 147, 336, 125);
		contentPane.add(scrollPane_1);
		
		JTextPane textPane_5 = new JTextPane();
		scrollPane_1.setViewportView(textPane_5);
		textPane_5.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(569, 147, 304, 125);
		contentPane.add(scrollPane);
		
		JTextPane textPane_accId = new JTextPane();
		textPane_accId.setBounds(764, 14, 141, 20);
		contentPane.add(textPane_accId);
		
		JButton btnNewButton = new JButton("New account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (currUser==null) {
					textPane_5.setText("Please select a client");
					return;
				}
				
				currAcc = acc.findId(acc.addAccount(currUser,0,0));
				textPane_5.setText(currAcc.stringInfo());
				unsaved_changes=true;
			}
		});
		btnNewButton.setBounds(691, 110, 214, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Show accounts");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setText(acc.printAllString());
			} 
		});
		btnNewButton_1.setBounds(10, 11, 128, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Select account");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (textPane_accId.getText().equals("")) {
					textPane_5.setText("Please enter account id");
					return;
				}
				
				currAcc = acc.findId(Integer.parseInt(textPane_accId.getText()));
				
				if (currAcc==null) {
					textPane_5.setText("Account not found");
					return;
				}
				
				textPane_5.setText(currAcc.stringInfo());
			}
		});
		btnNewButton_2.setBounds(691, 45, 214, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("Find all clients accounts");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (currUser==null) {
					textPane_5.setText("Please select a client");
					return;
				}
				
				textPane_5.setText(acc.printAllAccountsString(currUser));
			}
		});
		btnNewButton_4.setBounds(691, 79, 214, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Set the new balance");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (currAcc==null) {
					textPane_5.setText("Please select an account");
					return;
				}
				
				acc.updateBalanceId(currAcc.getId(),Double.parseDouble(JOptionPane.showInputDialog("Enter new balance:")));
				textPane_5.setText(currAcc.stringInfo());
				unsaved_changes=true;
			}
		});
		btnNewButton_5.setBounds(569, 356, 171, 23);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Set the new rate");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (currAcc==null) {
					textPane_5.setText("Please select an account");
					return;
				}
				
				acc.updateRateId(currAcc.getId(),Double.parseDouble(JOptionPane.showInputDialog("Enter new rate:")));
				textPane_5.setText(currAcc.stringInfo());
				unsaved_changes=true;				
			}
		});
		btnNewButton_6.setBounds(569, 283, 171, 23);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Modify balance");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (currAcc==null) {
					textPane_5.setText("Please select an account");
					return;
				}
				
				acc.addMoneyId(currAcc.getId(),Double.parseDouble(JOptionPane.showInputDialog("Enter the amount of money:")));
				textPane_5.setText(currAcc.stringInfo());
				unsaved_changes=true;
			}
		});
		btnNewButton_7.setBounds(569, 322, 171, 23);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Delete Account");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (currAcc==null) {
					textPane_5.setText("Please select an account");
					return;
				}
				
				users.findId(currAcc.getOwner_id()).getAccounts().remove(((Integer)currAcc.getId()));
				acc.deleteId(currAcc.getId());
				currAcc=null;
				textPane_5.setText("Account has been deleted");
				unsaved_changes=true;
			}
		});
		btnNewButton_8.setBounds(750, 356, 155, 23);
		contentPane.add(btnNewButton_8);
		
		JButton btnNewButton_12 = new JButton("Save accounts");
		btnNewButton_12.setBounds(750, 455, 155, 23);
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				filename = JOptionPane.showInputDialog("Enter the name of the file:",filename);
				
				if (!filename.endsWith(".sf")) {
					filename+=".sf";
				}
				
				acc.writeToFile(users.getName() + "\\" + filename);
				acc.printAll();
				users.writeToFile(users.getName()+".sfu");
				unsaved_changes=false;
			}
		});
		contentPane.add(btnNewButton_12);
		
		JButton btnNewButton_13 = new JButton("Load accounts");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (unsaved_changes) {
					auxillary_int = JOptionPane.showConfirmDialog(contentPane, "Some changes have not been saved and will be lost after loading new file" 
									+ System.getProperty("line.separator") + "Do you want to load new file?", "Unsaved changes", JOptionPane.YES_NO_OPTION);
					
					if (auxillary_int==JOptionPane.NO_OPTION) {
						return;
					}
				
				}
				
				filename = JOptionPane.showInputDialog("Enter the name of the file:",filename);
				
				if (!filename.endsWith(".sf")) {
					filename+=".sf";
				}
				
				acc.readFromFile(users.getName() + "\\" + filename);
				acc.printAll();
				unsaved_changes=false;
			}
		});
		btnNewButton_13.setBounds(569, 455, 171, 23);
		contentPane.add(btnNewButton_13);
	
		JScrollPane jsp = new JScrollPane(textPane);
		jsp.setBounds(10, 86, 269, 392);
		contentPane.add(jsp);
		
		JButton btnNewButton_11 = new JButton("Calculate intrest");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (currAcc==null) {
					textPane_5.setText("Please select an account");
					return;
				}
				
				textPane_5.setText(acc.prediction(currAcc.getId(), Integer.parseInt(JOptionPane.showInputDialog("Enter number of years:"))));
			}
		});
		btnNewButton_11.setBounds(750, 322, 155, 23);
		contentPane.add(btnNewButton_11);
		
		JButton btnNewButton_14 = new JButton("Set the rate to all accounts");
		btnNewButton_14.setBounds(10, 45, 128, 23);
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				acc.newRateGlobally(Double.parseDouble(JOptionPane.showInputDialog("Enter new rate:")));
				textPane.setText(acc.printAllString());
				unsaved_changes=true;
			}
		});
		contentPane.add(btnNewButton_14);
		
		JButton btnNewButton_15 = new JButton("Add intrest");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (currAcc==null) {
					textPane_5.setText("Please select an account");
					return;
				}
				
				acc.addIntrest(currAcc.getId());
				textPane_5.setText(currAcc.stringInfo());
				unsaved_changes=true;
			}
		});
		btnNewButton_15.setBounds(750, 283, 155, 23);
		contentPane.add(btnNewButton_15);
		
		JButton btnNewButton_16 = new JButton("Add intrest to all");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				acc.addIntrestEveryone();
				textPane.setText(acc.printAllString());
				unsaved_changes=true;
			}
		});
		btnNewButton_16.setBounds(148, 45, 119, 23);
		contentPane.add(btnNewButton_16);
		
		
		//Client database is now being saved while saving accounts
//		JButton btnNewButton_17 = new JButton("Wczytaj Uzytkownikow");
//		btnNewButton_17.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				user_filename=JOptionPane.showInputDialog("Podaj nazwe pliku",user_filename);
//				if (!user_filename.endsWith(".sfu")) {
//					user_filename+=".sfu";
//				}
//				users=UserDatabase.readFromFile(user_filename);
//			}
//		});
//		btnNewButton_17.setBounds(289, 455, 136, 23);
//		contentPane.add(btnNewButton_17);
//		
//		JButton btnNewButton_18 = new JButton("Zapisz Uzytkownikow");
//		btnNewButton_18.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				user_filename=JOptionPane.showInputDialog("Podaj nazwe pliku",user_filename);
//				if (!user_filename.endsWith(".sfu")) {
//					user_filename+=".sfu";
//				}
//				users.writeToFile(user_filename);
//			}
//		});
//		btnNewButton_18.setBounds(435, 455, 128, 23);
//		contentPane.add(btnNewButton_18);
		
		JButton btnNewButton_3 = new JButton("New client");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				users.addUser(new User( JOptionPane.showInputDialog("Enter name:"),
										JOptionPane.showInputDialog("Enter surname:"),
										JOptionPane.showInputDialog("Enter ID number:")));
				
				unsaved_changes=true;
			}
		});
		btnNewButton_3.setBounds(289, 283, 269, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_19 = new JButton("Delete client");
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				acc.deleteOwnersAccounts(currUser);
				clearAllAccounts(currUser.getId());
				users.deleteUser(currUser);
				textPane_1.setText("User has been deleted");
				unsaved_changes=true;
				
			}
		});
		btnNewButton_19.setBounds(289, 318, 270, 23);
		contentPane.add(btnNewButton_19);
		
		JButton btnNewButton_20 = new JButton("Show clients");
		btnNewButton_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setText(users.stringInfo());
			}
		});
		btnNewButton_20.setBounds(147, 11, 120, 23);
		contentPane.add(btnNewButton_20);
		


		
		JButton btnNewButton_9 = new JButton("Select Client");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(textPane_ownerId.getText());
				
				if (!(textPane_ownerId.getText().equals(""))) {
					currUser = users.findId(Integer.parseInt(textPane_ownerId.getText()));
					
					if (currUser==null) {
						textPane_1.setText("User with id: " + textPane_ownerId.getText() + " has not been found");
						return;
					}
					
					textPane_1.setText(currUser.stringInfo());
				}
				
				else {
					ArrayList<User> temp1 = users.findName(textPane_name.getText(), textPane_surname.getText());
					
					if (temp1.size()==0) {
						textPane_1.setText("User: " + textPane_name.getText() + " " +textPane_surname.getText() + " has not been found");
						return;
					}
					
					if (temp1.size()==1) {
						currUser = temp1.get(0);
						textPane_1.setText(currUser.stringInfo());
						return;
					}
					
					Integer[] temp2 = new Integer[temp1.size()]; 
					
					for(int i=0;i<temp1.size();i++) {
						temp2[i] = temp1.get(i).getId();
					}
					
					int input =(int) JOptionPane.showInputDialog(null, "Choose id of the user", "Multiple accounts", JOptionPane.PLAIN_MESSAGE,
								     							 null, temp2, temp2[0]);
					currUser = users.findId(input);
					textPane_1.setText(currUser.stringInfo());
				}
			}
		});
		btnNewButton_9.setBounds(289, 110, 274, 23);
		contentPane.add(btnNewButton_9);
		

		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(289, 147, 270, 125);
		contentPane.add(scrollPane_2);
	}
	
	public void chooseUserDatabase() {		//asks user to choose one of databases present in the application folder or creates a new one
		
		File folder = new File(System.getProperty("user.dir"));
		String[] aux = folder.list();
		ArrayList<String> userDatabases= new ArrayList<String>();
		
		for(String i: aux) {								//for all files in the folder:
		
			if (i.endsWith(".sfu")) {						//find all user databases files
				userDatabases.add(i);
			}
		}
		
		userDatabases.add("New User Database");				//add option to create new database
		aux = new String[userDatabases.size()];
		
		for(int i=0;i<userDatabases.size();i++) {			//copy data to auxiliary array
			aux[i]=userDatabases.get(i);
		}
															//choose database
		String input =(String) JOptionPane.showInputDialog(null,"Choose user database or create new one", "Select user database",
														   JOptionPane.PLAIN_MESSAGE, null, aux, aux[0]);
		
		if (input==null) {
			System.exit(0);
		}
		
		if (input.equals("New User Database")) {			//creating a new database
			
			do {
				users = new UserDatabase(JOptionPane.showInputDialog("Enter name of the new database (it must be unique):"));	//get unique name from user
				if (users.getName()==null) {
					System.exit(0);
				}
			} while(userDatabases.contains(users.getName()+".sfu"));
			
			File directory = new File(users.getName());		//create a directory to store database's accounts
			directory.mkdir();
			return;
		}
		
		users = UserDatabase.readFromFile(input);			//opening existing database
		user_filename=input;
	}
	
	public void clearAllAccounts(int x) {	//function that deletes all accounts of a specific user
		ArrayList<konto> temp__;
		File folder = new File(users.getName());
		String[] aux = folder.list();
		File tm;
		konto auxillary;
		ArrayList<konto> accountsToDelete = new ArrayList<konto>();
		
		temp__ = new ArrayList<konto>();
		for(String i: aux) {								//search all filenames in user database folder
			if (i.endsWith(".sf")) {						//for all .sf files:
				
				tm = new File(users.getName() + "\\" + i);			//open file
				intArray.clear();									//clear all auxiliary arrays
				temp__.clear();
				accountsToDelete.clear();

				try {														//load accounts
					FileInputStream in = new FileInputStream(tm);		
					ObjectInputStream input = new ObjectInputStream(in);
					
					while (true) {
					
						try {
							auxillary = (konto) input.readObject();
							temp__.add(auxillary);
						}
						catch (IOException e) {break;}
						catch (ClassNotFoundException e) {break;}
					}
					
					in.close();
					input.close();
				
				} catch (IOException e) {
					System.out.println("error");
				}
				
				for(konto j: temp__) {											//create a list of all account to delete
					if (j.getOwner_id()==x) {
						accountsToDelete.add(j);
					}
				}
				
				for(konto j: accountsToDelete) {								//delete accounts
					temp__.remove(j);
				}
				
				try {															//update the file
					FileOutputStream o = new FileOutputStream(tm);
					ObjectOutputStream output = new ObjectOutputStream(o);
					
					for(konto j: temp__) {
						output.writeObject(j);
					}
					
					o.close();
					output.close();
					
					} catch (FileNotFoundException e) {
						System.out.println("File not Found");
						
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("error");
				}
				
			}
			}
		}
	
}
