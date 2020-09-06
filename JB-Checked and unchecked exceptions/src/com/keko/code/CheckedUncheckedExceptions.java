package com.keko.code;

import java.util.concurrent.TimeUnit;

public class CheckedUncheckedExceptions {

	public static void main(String[] args) {
		
		
		/*
		 * The code bellow is an example of Checked Exception,
		 * because it extends Throwable and do not extends RuntimeException and Error. 
		 * Checked Exceptions are checked at compile time in Java.
		 *
		 * https://docs.oracle.com/javase/8/docs/api/java/lang/InterruptedException.html
		 */
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		 * The code bellow is an example of Unchecked Exception,
		 * because it extends RuntimeException
		 * Unchecked Exceptions happen at Runtime, so they are not checked
		 * at compile time.
		 */
		
		Computer laptop = null;
		laptop.turnItOn();

	}
	
	protected class Computer {
		Boolean isOn;
		
		public void turnItOn() {
			isOn = true;			
		}
		
		public Boolean isOn() {
			return isOn;
		}
		
	}
	

}



