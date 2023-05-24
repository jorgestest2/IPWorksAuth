#
# IPWorks Auth 2022 Python Edition - Demo Application
#
# Copyright (c) 2023 /n software inc. - All rights reserved. - www.nsoftware.com
#

import sys
import string
from ipworksauth import *

input = sys.hexversion<0x03000000 and raw_input or input


def fireError(e):
  print(e.message)

hotp = HOTP()
hotp.on_error = fireError

try:
	option = ""
	while option != "q":
		print("Please select an operation:")
		print("1)  Create Password")
		print("2) Validate Password")
		print("q) Quit")
		option = input("Option > ")

		if option == "1":
			hotp.reset()
			secret = input("Secret: ")
			if secret == "":
				secret = "ABCDEFGHIJKLMNOP"
			hotp.set_secret(secret)
			counter = input("Counter: ")
			if counter == "":
				counter = 1
			hotp.set_counter(int(counter))
			hotp.create_password()
			print("Password: " + hotp.get_password() + "\r\n")
			print("Password len: " + str(len(hotp.get_password())))
		elif option == "2":
			hotp.reset()
			secret = input("Secret: ")
			if secret == "":
				secret = "ABCDEFGHIJKLMNOP"
			hotp.set_secret(secret)
			counter = input("Counter: ")
			if counter == "":
				counter = 1
			hotp.set_counter(int(counter))
			password = input("Password: ")
			print("Password len: " + str(len(password)))
			hotp.set_password(str(password))
			print("Secret: " + secret)
			print("counter: " + str(counter))
			if hotp.validate_password():
				print("VALID")
			else:
				print("INVALID")
		elif option != "q":
			print("Error: Input not recognized")
			continue
		else:
			continue
except IPWorksAuthError as e:
  print("ERROR %s" %e.message)

