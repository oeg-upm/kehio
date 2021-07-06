package kehio.example.identity.test;

import kehio.annotations.RdfDatatype;
import kehio.annotations.RdfId;

public class CityExample1 {

	@RdfId
	private String identifier;
	
	@RdfDatatype("http://xmlns.com/foaf/0.1/name")
	private String name;
	
	public CityExample1() {
		super();
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
