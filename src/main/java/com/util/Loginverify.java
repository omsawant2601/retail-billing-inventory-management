package com.util;

public class Loginverify {
	 public boolean inventoryAssistanceVerify(int pass){
		 boolean login = false;
		int iApass = 1111;
		 if(pass == iApass) {
			 login = true;
		 }
		 else {
			 login = false;
		 }
		 return login;
	}
	 public boolean managerVerify(int pass) {
		 boolean login = false;
		 int mPass = 3333;
		 if(pass == mPass ) {
			 login = true;
		 }
		 else {
			 login = false;
		 }
		 return login;
	 }
	 public boolean cashiorVerify(int pass) {
		 boolean login = false;
		 int cPass = 2222;
		 if(pass == cPass ) {
			 login = true;
		 }
		 else {
			 login = false;
		 }
		 return login;
	 }
}
