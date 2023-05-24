/*
 * IPWorks Auth 2022 Java Edition- Demo Application
 *
 * Copyright (c) 2023 /n software inc. - All rights reserved. - www.nsoftware.com
 *
 */

import java.io.*;

import ipworksauth.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class hotp extends ConsoleDemo {
	public static void main(String[] args) {
		Hotp hotp = new Hotp();

		try {

			String option = "";
			while (!option.equalsIgnoreCase("q")) {
				System.out.println("Please select an operation:");
				System.out.println("1)  Create Password");
				System.out.println("2)  Validate Password");
				System.out.println("Q)  Quit");
				option = prompt("Option", ">");

				// Initial setup
				if (option.equals("1"))
				{
					hotp.reset();
					hotp.setSecret(prompt("Secret", ":", "ABCDEFGHIJKLMNOP"));
					hotp.setCounter(Integer.parseInt(prompt("Counter", ":", "1")));
					hotp.createPassword();
					System.out.println("Password: " + hotp.getPassword() + "\r\n");
				} else if (option.equals("2")) {
					hotp.reset();
					hotp.setSecret(prompt("Secret", ":", "ABCDEFGHIJKLMNOP"));
					hotp.setCounter(Integer.parseInt(prompt("Counter", ":", "1")));
					hotp.setPassword(prompt("Password", ":"));
					if (hotp.validatePassword()) {
						System.out.println("VALID!\r\n");
					} else {
						System.out.println("INVALID!\r\n");
					}
				} else if (!option.equalsIgnoreCase("q")){
					System.out.println("Error: Input not recognized.");
					continue;
				} else {
					continue;
				}
			}

		} catch (IPWorksAuthException ex) {
			System.out.println("IPWorksAuth exception thrown: " + ex.getCode()
					+ " [" + ex.getMessage() + "].");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
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




