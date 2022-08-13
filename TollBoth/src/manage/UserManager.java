package manage;

import java.util.ArrayList;

import users.Person;
import users.User;

public class UserManager {
	private static ArrayList<User> users = new ArrayList<User>();
	
	
	public ArrayList<User> getUsers() {
		return users;
		
	}
	
	public static void addPerson(User user) {
		users.add(user);
	}
	
	
}
