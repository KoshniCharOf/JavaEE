package persons;
import java.util.HashSet;
import java.util.Random;

import resources.Kuhnq;
import zelenchuci.Chushka;
import zelenchuci.Domat;
import zelenchuci.Patladjan;
import zelenchuci.Zelenchuk;

public class Baba extends Person implements Runnable{

	
	private Kuhnq kuhnq;

	public Baba(Kuhnq kuhnq) {
		super(new Random().nextInt(21)+35, "Goshka");
		
		this.kuhnq = kuhnq;
	}


	@Override
	public void run() {
		while(true) {
			HashSet<Zelenchuk> prods = new HashSet<>();
			//old baba's reciept
			for (int i = 0; i < 5; i++) {
				prods.add(new Domat());
				prods.add(new Chushka());
				prods.add(new Patladjan());
			}
			for (Zelenchuk zelenchuk : prods) {
				kuhnq.prailaBaba(zelenchuk);
			}
			try {
				Thread.sleep(10*1000);
			} catch (InterruptedException e) {
				System.out.println("op 4e se oparish");
			}
			int kg = new Random().nextInt(10)+3;
			System.out.println(this+" aide na lutenicata ima "+kg+" kg");
			Pisar.getPisar().insertLutenica(kg, this.name);
		}
		
	}
	
	

}
