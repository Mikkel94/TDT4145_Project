package frontend;

public class AparatusType {
	
	private String name;
	@SuppressWarnings("unused")
	private String description;
	private int id;
	
	public AparatusType(String name, String description, int id) {
		this.name = name;
		this.description = description;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
