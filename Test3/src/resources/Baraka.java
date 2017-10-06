package resources;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import zelenchuci.Chushka;
import zelenchuci.Domat;
import zelenchuci.Patladjan;
import zelenchuci.Zelenchuk;
import zelenchuci.Zelenchuk.Veggie;

public class Baraka {
	
	private ConcurrentHashMap<String, BlockingQueue<Zelenchuk>> koshnici;

	public Baraka() {

		//todo validate strings 
		this.koshnici = new ConcurrentHashMap<>();
		this.koshnici.put(Veggie.DOMAT.toString(), new LinkedBlockingQueue<>(40));
		this.koshnici.put(Veggie.CHUSHKA.toString(), new LinkedBlockingQueue<>(40));
		this.koshnici.put(Veggie.PATLADJAN.toString(), new LinkedBlockingQueue<>(40));
	}
	
	public  void bralaMoma(Zelenchuk z){// not need synchronized
		
		try {
			this.koshnici.get(z.getName()).put(z);
		} catch (InterruptedException e) {
			System.out.println("op");
		}
		
	}
	public void prailMomak(Zelenchuk z) {
		
		try {
			this.koshnici.get(z.getName()).take();
		} catch (InterruptedException e) {
			System.out.println("op");
		}
	}
	
	public static Zelenchuk ranZelenchuk() {
		Zelenchuk z = null;
		switch (new Random().nextInt(3)) {
		case 0:
			z=  new Chushka();
			break;
		case 1:
			z=  new Domat();
			break;
		case 2:
			z=  new Patladjan();
			break;
		default:
			break;
		}
		return z;
	}

}
