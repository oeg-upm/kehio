package kehio.example.datatype.test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.DC;
import org.junit.Assert;
import org.junit.Test;

import kehio.Kehio;
import kehio.annotations.RdfDatatype;

public class SongExampleTest {

	private static final Property propertyCreator = ResourceFactory.createProperty("http://purl.org/dc/elements/1.1/creator");
	private static final Property propertyIdentifier = ResourceFactory.createProperty("http://purl.org/dc/elements/1.1/identifier");
	private static final Property propertyTitle = ResourceFactory.createProperty("http://purl.org/dc/elements/1.1/title");
	private static final Property propertyAvailable = ResourceFactory.createProperty("http://purl.org/dc/terms/available");
	private static final Property propertyDate = ResourceFactory.createProperty("http://purl.org/dc/terms/date");
	private static final Property propertyLenght = ResourceFactory.createProperty("http://purl.org/dc/terms/SizeOrDuration");
	private static final Property propertyLikes = ResourceFactory.createProperty("https://oeg-upm.github.io/kehio/test_ontology#likes");
	private static final Property propertyCopiesSold = ResourceFactory.createProperty("https://oeg-upm.github.io/kehio/test_ontology#copies_sold");

	
	@Test
	public void example1() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, URISyntaxException {
		String date = "2021/01/06 15:00:12";
		String creator = "Carlos Blas";
		Float identifier = Float.valueOf("162461150612");
		Double lenght = 129.9;
		Number likes = 1000;
		Integer sold = 500;
		String title = "El Kejío";
		Boolean available = true;
		
		SongExample1 song = new SongExample1();
		song.setAvailable(available);
		song.setCreated(date);
		song.setCreator(creator);
		song.setIdentifier(identifier);
		song.setLength(lenght);
		song.setLikes(likes);
		song.setSold(sold);
		song.setTitle(title);
		
		Model rdf = Kehio.deserializeClass(song);
		// rdf.write(System.out, "TURTLE"); // uncomment this for printing
		
		Assert.assertTrue(rdf.listObjectsOfProperty(propertyDate).next().asLiteral().equals(ResourceFactory.createTypedLiteral(date)));
		Assert.assertTrue(rdf.listObjectsOfProperty(propertyCreator).next().asLiteral().equals(ResourceFactory.createTypedLiteral(creator)));
		Assert.assertTrue(rdf.listObjectsOfProperty(propertyIdentifier).next().asLiteral().equals(ResourceFactory.createTypedLiteral(identifier)));
		Assert.assertTrue(rdf.listObjectsOfProperty(propertyLenght).next().asLiteral().equals(ResourceFactory.createTypedLiteral(lenght)));
		Assert.assertTrue(rdf.listObjectsOfProperty(propertyLikes).next().asLiteral().equals(ResourceFactory.createTypedLiteral(likes)));
		Assert.assertTrue(rdf.listObjectsOfProperty(propertyCopiesSold).next().asLiteral().equals(ResourceFactory.createTypedLiteral(sold)));
		Assert.assertTrue(rdf.listObjectsOfProperty(propertyTitle).next().asLiteral().equals(ResourceFactory.createTypedLiteral(title)));
		Assert.assertTrue(rdf.listObjectsOfProperty(propertyAvailable).next().asLiteral().equals(ResourceFactory.createTypedLiteral(available)));

		
	}
	
	@Test
	public void example2_1() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, URISyntaxException {
		SongExample2 song = new SongExample2();
		song.setAvailable(true);
		song.setCreated(new Date().toString());
		song.setCreator("Carlos Blas");
		song.setIdentifier(Float.valueOf("162461150612"));
		song.setLength(129.9);
		song.setLikes(1000);
		song.setSold(500);
		song.setTitle("El Kejío");
		
		//Kehio.deserializeClass(song).write(System.out, "NTRIPLES"); // uncomment this for printing
		//Assert.assertTrue()
	}
	
	private static final String TEST_2_2 = "@prefix dc: <http://purl.org/dc/terms/> .\n" + 
			"@prefix ns0: <https://oeg-upm.github.io/kehio/test_ontology#> .\n" + 
			"@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .\n" + 
			"@prefix dc11: <http://purl.org/dc/elements/1.1/> .\n" + 
			"\n" + 
			"<http://test.song/002> dc11:creator \"Vicente Amigo\" ;\n" + 
					"	  dc11:title \"Ciudad de las ideas\"@es . # Notice here the language";
	
	/*@Test
	public void example2_2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, URISyntaxException {
		Model model = ModelFactory.createDefaultModel();
		model.read(new ByteArrayInputStream(TEST.getBytes()), null, "TURTLE");
		@SuppressWarnings("unused")
		SongExample2 song = (SongExample2) Kehio.serializeClass(SongExample2.class, model, ResourceFactory.createResource("http://test.song/001"));
		//System.out.println(song.getTitle());  // uncomment this for printing
		Assert.assertTrue(song.getTitle()==null && song.getCreator()!=null);
	}
	
	
	
	
	@Test
	public void example3() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, URISyntaxException {
		Model model = ModelFactory.createDefaultModel();
		model.read(new ByteArrayInputStream(TEST.getBytes()), null, "TURTLE");
		
		SongExample3 song = (SongExample3) Kehio.serializeClass(SongExample3.class, model, ResourceFactory.createResource("http://test.song/001"));
		//System.out.println(song.getTitle());  // uncomment this for printing
	}
	
	
	
	
	private Model readRDF(String rdf) {
		Model model = ModelFactory.createDefaultModel();
		model.read(new ByteArrayInputStream(rdf.getBytes()), null, "TURTLE");
		return model;
	}*/
	
}


