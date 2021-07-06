package kehio.example.identity.test;

import java.net.URI;

import kehio.annotations.RdfDatatype;
import kehio.annotations.RdfId;

public class CityExample2 {

	@RdfId
	private URI identifier;
	
	@RdfDatatype("http://xmlns.com/foaf/0.1/name")
	private String name;
	
	public CityExample2() {
		super();
	}

	public URI getIdentifier() {
		return identifier;
	}

	public void setIdentifier(URI identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
