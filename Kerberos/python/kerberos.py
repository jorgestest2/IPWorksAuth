#
# IPWorks Auth 2022 Python Edition - Demo Application
#
# Copyright (c) 2023 /n software inc. - All rights reserved. - www.nsoftware.com
#

import sys
import string
from ipworksauth import *

input = sys.hexversion<0x03000000 and raw_input or input



def fireLog(e):
  print(e.message)

def fireError(e):
  print(e.description)

def fireTrail(e):
  print(e.message)

kerberos = Kerberos()
kerberos.on_log = fireLog
kerberos.on_error = fireError
kerberos.on_pi_trail = fireTrail

try:
  print("This demo shows how to use the Kerberos component to authenticate a user.")
  print("The demo will prompt for the User, Password, SNP (Service Principle Name), and KDCHost (kerberos server)")
  kerberos.set_user = input("Please enter you User: ")
  kerberos.set_password = input("Please enter your Password: ")
  kerberos.set_spn = input("Please enter your SPN: ")
  kerberos.set_kdc_host = input("Please enter your KDCHost: ")

  kerberos.authenticate()
  print("Authenticated!")
except IPWorksAuthError as e:
  print("ERROR %s" %e.message)





