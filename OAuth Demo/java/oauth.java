/*
 * IPWorks Auth 2022 Java Edition- Demo Application
 *
 * Copyright (c) 2023 /n software inc. - All rights reserved. - www.nsoftware.com
 *
 */

import java.io.*;

import java.util.TooManyListenersException;
import ipworksauth.*;

public class oauth extends ConsoleDemo {

	private static Oauth oauth1 = null;
	
	public static void main(String[] args) {
		
		try { 
			oauth1 = new Oauth();
			
			// Handle the LaunchBrowser event
			oauth1.addOauthEventListener(new DefaultOauthEventListener() {
				public void launchBrowser(OauthLaunchBrowserEvent event) {
					// Normally, the component will execute the command property to launch the browser for authorization.
					// Setting the command to an empty string will prevent a browser from opening the URL. The following 
					// line can be un-commented to exhibit this behavior.
					//event.command = "";
					System.out.println("Authorization URL: " + event.URL + "");
				}
			});
			
			System.out.println("This application demonstrates how to use the OAuth component to authenticate with Google using OAuth 2.0 (Device Profile). \n" 
							+ "It will guide you through the steps to perform authorization using OAuth. \n" 
							+ "Please see the Introduction page within the help for more detailed instructions. \n");
						
			// Client ID and Client Secret
			System.out.println("1. Obtain and set your Client ID and Client Secret. For Google, these values can be found in the API Console: \n"
							+ "https://code.google.com/apis/console#access \n"
							+ "The default values are from a Google test account that we have setup for you to easily run this demo. \n");
			
			String clientID = prompt("Client ID", ":", "723966830965.apps.googleusercontent.com");
			String clientSecret = prompt("Client Secret", ":", "_bYMDLuvYkJeT_99Q-vkP1rh");
						
			
			// Server Auth URL, Server Token URL, and Authorization Scope
			System.out.println("\n2. You can also set Server Auth URL, Server Token URL, and Authorization Scope to the values desired. \n" 
							+ "These are preset to values for Google's User Info service. \n");
			
			String serverAuthURL = prompt("Server Auth URL", ":", "https://accounts.google.com/o/oauth2/auth");
			String serverTokenURL = prompt("Server Token URL", ":", "https://accounts.google.com/o/oauth2/token");
			String authScope = prompt("Authorization Scope", ":", "https://www.googleapis.com/auth/userinfo.email");
			
			
			// Get Authorize URL for user to authenticate
			oauth1.setClientId(clientID);
			oauth1.setClientSecret(clientSecret);
			oauth1.setServerAuthURL(serverAuthURL);
			oauth1.setServerTokenURL(serverTokenURL);
			oauth1.setAuthorizationScope(authScope);
			
			System.out.println("\n3. The following URL will open in a web browser to authenticate to the\n"
							+ "service. Upon successfully authenticating and allowing access, the user will\n"
							+ "be redirected back to an embedded web server within the component.\n"
							+ "The Authorization String will then be set to the 'Authorization' property\n"
							+ "of the JSONS component and used to retrieve the user info for the authenticated\n"
							+ "client.\n");
			oauth1.config("BrowserResponseTimeout=30");
			String authString = oauth1.getAuthorization();
			
			// Retrieve the user info for the authenticated client.
			System.out.println("\n4. Authorization String: " + authString);
						
			prompt("\nPress enter to exit...", "");
			
		} catch (IPWorksAuthException e) {
			System.out.println("Error: " + e.getMessage());
	        System.exit(e.getCode());
	        return;
		} catch (TooManyListenersException e) {
			System.out.println(e.toString());
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




