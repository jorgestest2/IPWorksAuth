/*
 * IPWorks Auth 2022 Java Edition- Demo Application
 *
 * Copyright (c) 2023 /n software inc. - All rights reserved. - www.nsoftware.com
 *
 */

import java.io.*;

import ipworksauth.IPWorksAuthException;
import ipworksauth.Kerberos;
public class kerberos extends ConsoleDemo {
	public static void main(String[] args) {
		Kerberos kerberos = new Kerberos();
		try {
			System.out.println("This demo shows hpw to use the Kerberos component to authenticate a user. \n" 
					+ "To begin specify the User, Password, SPN (Service Principal Name), and KDCHost (the Kerberos server).\n");
			
			kerberos.setUser(prompt("Please enter your User", ":"));
			kerberos.setPassword(prompt("Please enter your Password", ":"));
			kerberos.setSPN(prompt("Please enter your SPN", ":"));
			kerberos.setKDCHost(prompt("Please enter your KDCHost", ":"));
			
			kerberos.authenticate();
			System.out.println("\nAuthenticated!");
		}
		catch (Exception ex) {
			System.out.println("\n" + ex.getMessage());
		}
	}
}

class ConsoleDemo {
  private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

  static String input() {
    try {
      return bf.readLine();
    } catch (IOException ioe) {
      return "";
    }
  }
  static char read() {
    return input().charAt(0);
  }

  static String prompt(String label) {
    return prompt(label, ":");
  }
  static String prompt(String label, String punctuation) {
    System.out.print(label + punctuation + " ");
    return input();
  }

  static String prompt(String label, String punctuation, String defaultVal)
  {
	System.out.print(label + " [" + defaultVal + "] " + punctuation + " ");
	String response = input();
	if(response.equals(""))
		return defaultVal;
	else
		return response;
  }

  static char ask(String label) {
    return ask(label, "?");
  }
  static char ask(String label, String punctuation) {
    return ask(label, punctuation, "(y/n)");
  }
  static char ask(String label, String punctuation, String answers) {
    System.out.print(label + punctuation + " " + answers + " ");
    return Character.toLowerCase(read());
  }

  static void displayError(Exception e) {
    System.out.print("Error");
    if (e instanceof IPWorksAuthException) {
      System.out.print(" (" + ((IPWorksAuthException) e).getCode() + ")");
    }
    System.out.println(": " + e.getMessage());
    e.printStackTrace();
  }
}




