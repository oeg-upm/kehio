package kehio.example.identity.test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.junit.Assert;
import org.junit.Test;

import kehio.Kehio;

public class CityExampleTest {

	// Example city 1
	@Test
	public void example1() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, URISyntaxException {
		String subject = "http://kehio.es/resources/madrid";
		String name = "Madrid";
		
		CityExample1 city = new CityExample1();
		city.setIdentifier(subject);
		city.setName(name);
		
		Model model = Kehio.deserializeClass(city);
		// model.write(System.out, "TURTLE"); // uncomment this for printing
		Assert.assertTrue(model.listSubjects().next().asResource().toString().equals(subject));
	}
	
	@Test
	public void example2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, URISyntaxException {
		String identifier = "http://kehio.es/resources/madrid";
		
		String rdf = "<"+identifier+"> <http://xmlns.com/foaf/0.1/name> \"Madrid\" .";
		Model model = readRDF(rdf);
		CityExample1 city = (CityExample1) Kehio.serializeClass(CityExample1.class, model, ResourceFactory.createResource(identifier));
		
		// System.out.println(city.getIdentifier()); // uncomment this for printing
		Assert.assertTrue(city.getIdentifier().equals(identifier));
	}
	
	// Example city 2
	
	@Test
	public void example3() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, URISyntaxException {
		URI subject = new URI("http://kehio.es/resources/madrid");
		String name = "Madrid";
		
		CityExample2 city = new CityExample2();
		city.setIdentifier(subject);
		city.setName(name);
		
		Model model = Kehio.deserializeClass(city);
		// model.write(System.out, "TURTLE"); // uncomment this for printing
		Assert.assertTrue(model.listSubjects().next().asResource().toString().equals(subject.toString()));
	}
	
	@Test
	public void example4() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, URISyntaxException {
		String identifier = "http://kehio.es/resources/madrid";
		
		String rdf = "<"+identifier+"> <http://xmlns.com/foaf/0.1/name> \"Madrid\" .";
		Model model = readRDF(rdf);
		CityExample2 city = (CityExample2) Kehio.serializeClass(CityExample2.class, model, ResourceFactory.createResource(identifier));
		
		// System.out.println(city.getIdentifier()); // uncomment this for printing
		Assert.assertTrue(city.getIdentifier().equals(new URI(identifier)));
	}
	
	// Example city 3
	
		@Test
		public void example5() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, URISyntaxException {
			String name = "Madrid";
			
			CityExample3 city = new CityExample3();
			city.setName(name);
			
			Model model = Kehio.deserializeClass(city);
			// model.write(System.out, "NTRIPLES"); // uncomment this for printing
			Assert.assertTrue(model.listSubjects().next().asResource().toString()!=null);
		}
		
		@Test
		public void example6() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, URISyntaxException {
			String rdf = "_:B632a5a35X2Dd3e6X2D4af6X2D8453X2Ddcc3e8897cd8 <http://xmlns.com/foaf/0.1/name> \"Madrid\" .";
			
			Model model = readRDF(rdf);
			CityExample3 city = (CityExample3) Kehio.serializeClass(CityExample3.class, model, null);

			// System.out.println(city.getIdentifier()); // uncomment this for printing
			Assert.assertTrue(city.getName().equals("Madrid"));
		}
	
	private Model readRDF(String rdf) {
		Model model = ModelFactory.createDefaultModel();
		model.read(new ByteArrayInputStream(rdf.getBytes()), null, "TURTLE");
		return model;
	}
}
