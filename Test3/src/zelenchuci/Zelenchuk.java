package zelenchuci;

public abstract class Zelenchuk {

	//protected String name;
	protected int prepareTime;
	public enum Veggie{ DOMAT, CHUSHKA, PATLADJAN}  
	protected Veggie name;
	public Zelenchuk(Veggie name, int prepareTime) {
		super();
		this.name = name;
		this.prepareTime = prepareTime;
	}

	public String getName() {
		return name.toString();
	}

	public int getPrepareTime() {
		return prepareTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + prepareTime;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zelenchuk other = (Zelenchuk) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (prepareTime != other.prepareTime)
			return false;
		return true;
	}
	
	
	
}
