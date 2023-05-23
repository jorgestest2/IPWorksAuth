/*
 * IPWorks Auth 2022 C++ Edition - Demo Application
 *
 * Copyright (c) 2023 /n software inc. - All rights reserved. - www.nsoftware.com
 *
 */


#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "../../include/ipworksauth.h"
#define LINE_LEN 100
#define MESSAGE_LEN 1024

int main(int argc, char **argv)
{
	Kerberos kerberos;
	char buffer[LINE_LEN];
	int ret_code = 0;

	printf("This demo shows hpw to use the Kerberos component to authenticate a user. \n");
	printf("To begin specify the User, Password, SPN(Service Principal Name), and \n");
	printf("KDCHost(the Kerberos server).\n");
	printf("\n");

	printf("Please enter your User: ");
	fgets(buffer, LINE_LEN, stdin);
	buffer[strlen(buffer) - 1] = '\0';
	kerberos.SetUser(buffer);

	printf("Please enter your Password: ");
	fgets(buffer, LINE_LEN, stdin);
	buffer[strlen(buffer) - 1] = '\0';
	kerberos.SetPassword(buffer);

	printf("Please enter your SPN: ");
	fgets(buffer, LINE_LEN, stdin);
	buffer[strlen(buffer) - 1] = '\0';
	kerberos.SetSPN(buffer);

	printf("Please enter your KDCHost: ");
	fgets(buffer, LINE_LEN, stdin);
	buffer[strlen(buffer) - 1] = '\0';
	kerberos.SetKDCHost(buffer);

	ret_code = kerberos.Authenticate();
	
	if (ret_code) {
		printf("\nError: [%i] %s\n\n", ret_code, kerberos.GetLastError());
	}
	else {
		printf("\nAuthenticated!\n\n");
	}

	fprintf(stderr, "\npress <return> to continue...\n");
	getchar();
	exit(ret_code);
	return 0;
}





