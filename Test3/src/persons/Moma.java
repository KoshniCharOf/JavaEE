package persons;
import java.util.Random;
import resources.Baraka;
import zelenchuci.Zelenchuk;



public class Moma extends Person implements Runnable{
	
	private Baraka baraka;
	
	
	
	public Moma(Baraka baraka) {
		super(new Random().nextInt(6)+14, "Kochka");
		this.baraka = baraka;

		
	}


	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("op");
			}
			int broi = new Random().nextInt(4)+3;
			Zelenchuk z = Baraka.ranZelenchuk();
			for (int i = 0; i < broi; i++) {
				baraka.bralaMoma(z);
			}
			System.out.println(this+"Brala "+broi+" "+z.getName());
			Pisar.getPisar().insertNabrani(this.name, broi, z.getName());
		}
	}
	
	

}
