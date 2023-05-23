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
  HOTP hotp;
  char buffer[LINE_LEN];
  int ret_code = 0;

  while (1)
  {
    printf("Please specify an operation:\n");
    printf("1) Create Password \n");
    printf("2) Validate Password \n");
    printf("Q) Quit\n");
    printf("Option: ");
    fgets(buffer, LINE_LEN, stdin);
    buffer[strlen(buffer)-1] = '\0';

    if (! strcmp(buffer, "1"))
    {
      hotp.Reset();
      
      printf("Secret: ");
      fgets(buffer, LINE_LEN, stdin);
      buffer[strlen(buffer)-1] = '\0';
      hotp.SetSecret(buffer);

      printf("Counter: ");
      fgets(buffer, LINE_LEN, stdin);
      buffer[strlen(buffer)-1] = '\0';
      hotp.SetCounter(atoi(buffer));

      hotp.CreatePassword();
      printf("Password: %s\n\n", hotp.GetPassword());
    }
    else if (!strcmp(buffer, "2"))
    {
      printf("Secret: ");
      fgets(buffer, LINE_LEN, stdin);
      buffer[strlen(buffer)-1] = '\0';
      hotp.SetSecret(buffer);

      printf("Counter: ");
      fgets(buffer, LINE_LEN, stdin);
      buffer[strlen(buffer)-1] = '\0';
      hotp.SetCounter(atoi(buffer));

      printf("Password: ");
      fgets(buffer, LINE_LEN, stdin);
      buffer[strlen(buffer)-1] = '\0';
      hotp.SetPassword(buffer);

      if (hotp.ValidatePassword()) 
      {
        printf("VALID!\n\n");
      }
      else 
      {
        printf("INVALID!\n\n");
      }
    }
    else if ((!strcmp(buffer, "q")) || (!strcmp(buffer, "Q")))
    {
      exit(0);
    }
    else
    {
      printf("Error: Input not recognized.\n\n");
    }
  }

	exit(ret_code);
	return 0;
}


