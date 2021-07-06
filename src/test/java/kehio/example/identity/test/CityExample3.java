package kehio.example.identity.test;

import java.net.URI;

import kehio.annotations.RdfDatatype;
import kehio.annotations.RdfId;

public class CityExample3 {
	
	@RdfDatatype("http://xmlns.com/foaf/0.1/name")
	private String name;
	
	public CityExample3() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
