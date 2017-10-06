package persons;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map.Entry;
import java.util.TreeSet;


public class Pisar extends Person implements Runnable{
	
	

	private static final String DB_IP = "localhost";
	private static final String DB_PORT = "3306";
	private static final String DB_DBNAME = "hr";
	private static final String DB_USER = "user";
	private static final String DB_PASS = "pass";

	private static File obraboteni;
	private static Pisar instance;
	private static Connection con;
	
	public static synchronized Pisar getPisar() {
		if(instance==null) {
			instance = new Pisar();
			Thread me = new Thread(instance);
			me.start();
		}
		return instance;
	}
	
	private Pisar() {
		super(40, "Pisarcho");
		try {
			Class.forName("com.mysql.jdbc.Driver");//Driver...
		} catch (ClassNotFoundException e) {
			System.out.println("opa");
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_DBNAME, DB_USER, DB_PASS);//addres port tablica ime parola
		} catch (SQLException e) {
			System.out.println("leko");
		}
		obraboteni = new File("Momci.txt");
		
	}

	public void insertLutenica(int quantity, String name) {
		
		String sql = "INSERT INTO lutenica (date, quantity, baba_name) VALUES (?,?,?)";//lutenica_id,​ auto
		
		try (PreparedStatement statement = con.prepareStatement(sql)){
			
			statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now())); //may be timeStamp todo
			statement.setInt(2, quantity);
			statement.setString(3, name);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("baba sql");
		}
	}
	
	public void insertNabrani(String name, int quantity, String veggie_name) {
			/*● При бране на зеленчуци и складирането им в кошниците писарът описва
			колко зеленчука от коя мома са набрани, като пази тази информация
			отново в таблица “nabrano”​в базата данни (moma_name,​​veggie_name,
			quantity)​. Ако за тази мома вече съществува запис в базата за този
			зеленчук, то количеството се увеличава.
		*/
		String insert = "INSERT INTO nabrano (moma_name, veggie_name, quantity) VALUES (?,?,?)";//lutenica_id,​ auto

		//Ако за тази мома вече съществува запис в базата 
		String check = "Select n.quantity from nabrano n where n.moma_name = '"+name+"' and veggie_name = '"+veggie_name+"'";
		int prev = 0;
		try (PreparedStatement checks = con.prepareStatement(check)){
			ResultSet r = checks.executeQuery();
			while(r.next()) {
				prev = r.getInt(1);
			}
			int current = prev+quantity;
			String update = "UPDATE nabrano SET quantity = "+current+" WHERE moma_name = '"+name+"' and veggie_name = '"+veggie_name+"'";
			if(prev> 0) {
				try(PreparedStatement upStat = con.prepareStatement(update)){
					upStat.executeUpdate();
				}// may be no catch ot finally here in nested
				
			}else {
				try(PreparedStatement ins = con.prepareStatement(insert)) {
					ins.setString(1, name);
					ins.setString(2, veggie_name);
					ins.setInt(3, quantity+prev);

					ins.executeUpdate();
				}
			}
			
		} catch (SQLException e) {
			System.out.println("op prev"+e.getMessage());
		}//no closing in try with resources	
			
	}
	
	public void insertBrigada() {
		String sql = "INSERT INTO brigada (name, age) VALUES (?,?)";
		for (Entry<String, Integer> en : Person.getBrigada().entrySet()) {
			try (PreparedStatement statement = con.prepareStatement(sql)){
				statement.setString(1, en.getKey());
				statement.setInt(2, en.getValue());
				statement.executeUpdate();
			} catch (SQLException e) {
				System.out.println("exists");
			}
		}
		
		
	}

	
	private void bestMoma() {
//		● Името и годините на момата, набрала най-голям брой зеленчуци.
		String sel1 = "SELECT n.moma_name, sum(n.quantity) as nabrano FROM hr.nabrano n  group by n.moma_name  order by nabrano desc limit 1;";
		try (PreparedStatement pstmt = con.prepareStatement(sel1)){
			ResultSet r = pstmt.executeQuery();
			while(r.next()) {
				String name = r.getString(1);
				int in = r.getInt(2);
				int age = Person.getBrigada().get(name);
				System.out.println("Best moma: "+name+" age: "+age+" nabrala : "+in+" zelenchuka");
			}
		} catch (SQLException e) {
			System.out.println("op");
		}
	}

	private void laziestMomak() {
		TreeSet<Entry<String, Integer>> temp = new TreeSet<>((o1, o2)-> {
				return o1.getValue() - o2.getValue();
			}
		);
		for (Entry<String, Integer> en : Momak.getMomci().entrySet()) {
			temp.add(en);
		}
		System.out.println("Laziest momak is: "+temp.first().getKey()+
				" worktime: "+temp.first().getValue()/1000+" seconds");

	}
	
	private void printPartidi() {
		String sel2 =  "SELECT l.date, count(l.date) as partidi, sum(l.quantity)as total_kg FROM lutenica l GROUP BY l.date";
		try (PreparedStatement pstmt = con.prepareStatement(sel2)){
			ResultSet r = pstmt.executeQuery();
			while(r.next()) {
				String date = r.getDate(1).toString();
				int partidi = r.getInt(2);
				int total = r.getInt(3);
				System.out.println("date: "+date+" partidi "+partidi+" total_kg: "+total);
			}
		} catch (SQLException e) {
			System.out.println("op");
		}
	}
	
	private void mostPickedVeggie() {
		String sel3 = "SELECT n.veggie_name, sum(n.quantity) as number FROM nabrano n group by n.veggie_name order by sum(n.quantity) desc limit 1";
		try (PreparedStatement pstmt = con.prepareStatement(sel3)){
			ResultSet r = pstmt.executeQuery();
			while(r.next()) {
				String name = r.getString(1);
				int total = r.getInt(2);
				System.out.println("Veggie: "+name+" total: "+total);
			}
		} catch (SQLException e) {
			System.out.println("op");
		}
	}
	
	private void bestBaba() {
		String sel4 = "SELECT l.baba_name, sum(l.quantity) as total FROM lutenica l  group by l.baba_name  order by sum(l.quantity) desc  limit 1";
		try (PreparedStatement pstmt = con.prepareStatement(sel4)){
			ResultSet r = pstmt.executeQuery();
			while(r.next()) {
				String name = r.getString(1);
				int total = r.getInt(2);
				System.out.println("Baba: "+name+" total Lutenica: "+total+" kg");
			}
		} catch (SQLException e) {
			System.out.println("op");
		}
	}
	
	private void printAvgAge() {
		String sel4 = "SELECT avg(age) as avg_age FROM brigada b;";
		try (PreparedStatement pstmt = con.prepareStatement(sel4)){
			ResultSet r = pstmt.executeQuery();
			while(r.next()) {
				int avg = r.getInt(1);
				System.out.println("Average age: "+avg);
			}
		} catch (SQLException e) {
			System.out.println("op");
		}
	}
	
	public void saveToTxtMomci() {
	
		try (PrintStream ps = new PrintStream(obraboteni)){
			for (Entry<String, Integer> en : Momak.getMomci().entrySet()) {
				ps.println(en.getKey()+" time: "+en.getValue()/1000+" seconds"); //seconds
				
			}
		} catch (FileNotFoundException e) {
			System.out.println("op");
		}
		
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(30*1000);
			} catch (InterruptedException e) {
				System.out.println("op");
			}

			saveToTxtMomci();
			bestMoma();
			laziestMomak();
			printPartidi();
			mostPickedVeggie();
			bestBaba();
			printAvgAge();
		}
		
	}
	
	@Override
	protected boolean isPisar() {
		return true;
	}
		
	

}
