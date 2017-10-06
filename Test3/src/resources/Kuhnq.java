package resources;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import zelenchuci.Zelenchuk;
import zelenchuci.Zelenchuk.Veggie;

public class Kuhnq {
	private ConcurrentHashMap<String, BlockingQueue<Zelenchuk>> tavi;

	public Kuhnq() {

		//todo validate strings 
		this.tavi = new ConcurrentHashMap<>();
		this.tavi.put(Veggie.DOMAT.toString(), new LinkedBlockingQueue<>(30));
		this.tavi.put(Veggie.CHUSHKA.toString(), new LinkedBlockingQueue<>(30));
		this.tavi.put(Veggie.PATLADJAN.toString(), new LinkedBlockingQueue<>(30));
	}
	
	public  void puhalMomak(Zelenchuk z){// not need synchronized
		
		try {
			this.tavi.get(z.getName()).put(z);
		} catch (InterruptedException e) {
			System.out.println("op");
		}
		
	}
	public void prailaBaba(Zelenchuk z) {
		
		try {
			this.tavi.get(z.getName()).take();
		} catch (InterruptedException e) {
			System.out.println("op");
		}
	}

}
