package resources;

import persons.Baba;
import persons.Moma;
import persons.Momak;
import persons.Pisar;

public class Demo {
	
	public static void main(String[] args) {
		
		
		Baraka b = new Baraka();
		Kuhnq k = new Kuhnq();
		
		Thread moma1 = new Thread(new Moma(b));
		Thread moma2 = new Thread(new Moma(b));
		Thread moma3 = new Thread(new Moma(b));
		Thread moma4 = new Thread(new Moma(b));
		Thread moma5 = new Thread(new Moma(b));
		
		moma1.start();
		moma2.start();
		moma3.start();
		moma4.start();
		moma5.start();
		
		Thread momak1 = new Thread(new Momak(b, k));
		Thread momak2 = new Thread(new Momak(b, k));
		Thread momak3 = new Thread(new Momak(b, k));
		Thread momak4 = new Thread(new Momak(b, k));
		Thread momak5 = new Thread(new Momak(b, k));
		
		momak1.start();
		momak2.start();
		momak3.start();
		momak4.start();
		momak5.start();
		
		Thread baba1 =new Thread(new Baba(k));
		Thread baba2 =new Thread(new Baba(k));
		Thread baba3 =new Thread(new Baba(k));
		baba1.start();
		baba2.start();
		baba3.start();
		
		Pisar.getPisar().insertBrigada();
	}

}
