package persons;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import resources.Baraka;
import resources.Kuhnq;
import zelenchuci.Zelenchuk;

public class Momak extends Person implements Runnable{
	
	private Baraka baraka;
	private Kuhnq kuhnq;
	private static ConcurrentHashMap<String, Integer> momci = new ConcurrentHashMap<>();
	
	
	public Momak(Baraka baraka, Kuhnq kuhnq) {
		super(new Random().nextInt(11)+15, "Toshko");
		this.baraka = baraka;
		this.kuhnq = kuhnq;
	}


	@Override
	public void run() {
		while(true) {
			Zelenchuk z = Baraka.ranZelenchuk();
			addToMomci(this, z.getPrepareTime());
			this.baraka.prailMomak(z);
			System.out.println(this+"prail  "+z.getName());
			try {
				Thread.sleep(z.getPrepareTime());
			} catch (InterruptedException e) {
				System.out.println("op");
			}
			
			this.kuhnq.puhalMomak(z);
			System.out.println(this+"pahal  "+z.getName());
			
		}
	}


	private   void addToMomci(Momak momak, int prepareTime) {
		int time = 0;
		if(momci.containsKey(momak.name)) {
			time = momci.get(momak.name);
			
		}
		momci.put(momak.name, prepareTime+time);
	}


	public static Map<String, Integer> getMomci() {
		return Collections.unmodifiableMap(momci);
	}
	
	
	

}
