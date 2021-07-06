
# KEHIO: a library for blending RDF in Java Objects

**Why Kehio:** currently there are a some libraries to annotate Java objects and serialiser or deserialise them from a set of RDF triples. Then why Kehio? 

## 1. Defining  subjects

Kehio allows to explicitly annotate an attribute from a  Java class using the annotation '@RdfId' to store the resource URI within, i.e., the subject from a set of triples with that very same URI. This attribute can be either a *String* or *java.net.URI*, any other attribute annotated with '@RdfId' will end up throwing an exception. Notice that a subject can also be a blank node and not a URI, in this case Kehio would handle it in the same way as shown in the following examples.

During the example the caption of the classes are linked to the full classes present in the code, in addition all the code displayed in this section can be found in this [test class.]()

#### 1.1.1 Using a Java String attribute

Let's assume we have [a Java class representing a city]() like the following:

````
public class CityExample1 {

	@RdfId
	private String identifier;
	
	@RdfDatatype("http://xmlns.com/foaf/0.1/name")
	private String name;
	
	// After this the class should have a constructor, getters & setters, ... 
	[...]	
}
````

Assuming that this class is initialised as follows:

````
String subject = "http://kehio.es/resources/madrid";
String name = "Madrid";

CityExample1 city = new CityExample1();
city.setIdentifier(subject);
city.setName(name);

Kehio.deserializeClass(city).write(System.out, "TURTLE");
````

When deserialised, Kehio will output the following triples:

````
<http://kehio.es/resources/madrid> <http://xmlns.com/foaf/0.1/name> "Madrid" .
````

Similarly, if we feed Kehio with the previous triples it will instantiate the Java object. The value of the attribute *identifier* will be *"http://kehio.es/resources/madrid"*:

````
String rdf = "<http://kehio.es/resources/madrid> <http://xmlns.com/foaf/0.1/name> \"Madrid\" ."
Model model = readRDF(rdf);

CityExample1 city = (CityExample1) Kehio.serializeClass(CityExample1.class, model, ResourceFactory.createResource(identifier));
System.out.println(city.getIdentifier()); // outputs "http://kehio.es/resources/madrid"
````

#### 1.1.1 Using a Java URI attribute

Relying on the previous example, let's assume we have [a Java class representing a city like the previous one but the identifier this time is a URI type]():

````
public class CityExample2 {

	@RdfId
	private URI identifier;
	
	@RdfDatatype("http://xmlns.com/foaf/0.1/name")
	private String name;
	
	// After this the class should have a constructor, getters & setters, ... 
	[...]	
}
````

Assuming that this class is initialised as follows:

````
URI subject = new URI("http://kehio.es/resources/madrid");
String name = "Madrid";

CityExample1 city = new CityExample1();
city.setIdentifier(subject);
city.setName(name);

Kehio.deserializeClass(city).write(System.out, "TURTLE");
````

When deserialised, Kehio will output the following triples:

````
<http://kehio.es/resources/madrid> <http://xmlns.com/foaf/0.1/name> "Madrid" .
````

Similarly, if we feed Kehio with the previous triples it will instantiate the Java object. The value of the attribute *identifier* will be *"http://kehio.es/resources/madrid"*:

````
String rdf = "<http://kehio.es/resources/madrid> <http://xmlns.com/foaf/0.1/name> \"Madrid\" ."
Model model = readRDF(rdf);

CityExample1 city = (CityExample1) Kehio.serializeClass(CityExample1.class, model, ResourceFactory.createResource(identifier));
System.out.println(city.getIdentifier()); // outputs "http://kehio.es/resources/madrid"
````


### 1.2 Omitting the attribute

Kehio does not need a user to implicitly annotate an attribute in order to store the subjects. Instead, if no attribute is specified with the annotation *'@RdfId'* Kehio asumes that the subject is always a blank node and handles it transparently.

Let's assume [the following Java class]()

````
public class CityExample3 {
	// Notice that no @RdfId is specified
	
	@RdfDatatype("http://xmlns.com/foaf/0.1/name")
	private String name;
	
	// After this the class should have a constructor, getters & setters, ... 
	[...]	
}
````
Now, assuming the object is initialised and deserialised as follows
````
String name = "Madrid";
CityExample3 city = new CityExample3();
city.setName(name);

Model model = Kehio.deserializeClass(city);
model.write(System.out, "NTRIPLES");
````
The output RDF will be (notice that the blank node may change in different executions)
````
_:B632a5a35X2Dd3e6X2D4af6X2D8453X2Ddcc3e8897cd8 <http://xmlns.com/foaf/0.1/name> "Madrid" .
````

Similarly, if a Java object is serialised from the previous triples it will only contain the value *"Madrid"* as name.


## Datatype properties

###  1.1  Datatype property ([code sample]())

Kehio allows mapping datatype properties directly to any Java wrapping primitive, i.e., Number, String, Integer, Boolean, and others. The following fragment displays several primitive types annotated with Kehio:

```
private  class SongExample1 {

	@RdfDatatype("http://purl.org/dc/elements/1.1/title")
	private String title;

	@RdfDatatype("http://purl.org/dc/elements/1.1/creator")
	private String creator;

	@RdfDatatype("http://purl.org/dc/elements/1.1/identifier")
	private Float identifier;

	@RdfDatatype("http://purl.org/dc/terms/SizeOrDuration")
	private Double length;

	@RdfDatatype("http://purl.org/dc/terms/available")
	private Boolean available;

	@RdfDatatype("https://oeg-upm.github.io/kehio/test_ontology#copies_sold")
	private Integer sold;

	@RdfDatatype("https://oeg-upm.github.io/kehio/test_ontology#likes")
	private Number likes;

	@RdfDatatype("http://purl.org/dc/terms/date")
	private Date created;
	
	// After this the class should have a constructor, getters & setters, ... 
	[...]
```
The previous Java class can be initialised as follows:
````
SongExample1 song = new SongExample1();
song.setAvailable(true);
song.setCreated(new Date().toString());
song.setCreator("Carlos Blas");
song.setIdentifier(Float.valueOf("162461150612"));
song.setLength(129.9);
song.setLikes(1000);
song.setSold(500);
song.setTitle("El Kejío");

Kehio.deserializeClass(song).write(System.out, "NTRIPLES");
````
 After which, the Java object can be deserialise into the following triples, or could be serialised from them:

````
@prefix dc: <http://purl.org/dc/terms/> .
@prefix ns0: <https://oeg-upm.github.io/kehio/test_ontology#> .
@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .
@prefix dc11: <http://purl.org/dc/elements/1.1/> .

[]  dc:date "Fri Jun 25 11:20:35 CEST 2021" ;
	  ns0:likes "1000"^^xsd:int ;
	  ns0:copies_sold "500"^^xsd:int ;
	  dc:available true ;
	  dc:SizeOrDuration 1.299000e+2 ;
	  dc11:identifier "1.62461155E11"^^xsd:float ;
	  dc11:creator "Carlos Blas" ;
	  dc11:title "El Kejío" .
````
Notice that any literal mapped to a Java wrapping primitive different from *String* has automatically assigned an equivalent *xsd* datatype.

Bear in mind that the previous example did not cover all the Java wrapping primitive types and also the code was not complete; it lack of constructor, getters, setters, etc. Please have a look at the [related test]() to see the complete example.

 ### 1.2 Datatype property with language ([code sample]())

The previous example class, i.e., Person, could be enriched specifying the language of some of its attributes' values. For the sake of this example, let's asume that the tile is the attribute to be enriched with language.

```
private  class SongExample2 {

	@RdfDatatype(value="http://purl.org/dc/elements/1.1/title", lang="en")
	private String title;
	
	// The rest of the attributes remain the same as SongExample1
	[...]
	
	// After this the class should have a constructor, getters & setters, ... 
	[...]
```

This Java class can be initialised again as follows:

````
SongExample2 song = new SongExample2();
song.setAvailable(true);
song.setCreated(new Date().toString());
song.setCreator("Carlos Blas");
song.setIdentifier(Float.valueOf("162461150612"));
song.setLength(129.9);
song.setLikes(1000);
song.setSold(500);
song.setTitle("El Kejío");

Kehio.deserializeClass(song).write(System.out, "NTRIPLES");
````

 Notice that this time, when deserialised,  the RDF triples will include the language tag in the tile's value:

````
@prefix dc: <http://purl.org/dc/terms/> .
@prefix ns0: <https://oeg-upm.github.io/kehio/test_ontology#> .
@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .
@prefix dc11: <http://purl.org/dc/elements/1.1/> .

[]  dc:date "Fri Jun 25 11:20:35 CEST 2021" ;
	  ns0:likes "1000"^^xsd:int ;
	  ns0:copies_sold "500"^^xsd:int ;
	  dc:available true ;
	  dc:SizeOrDuration 1.299000e+2 ;
	  dc11:identifier "1.62461155E11"^^xsd:float ;
	  dc11:creator "Carlos Blas" ;
	  dc11:title "El Kejío"@en . # Notice here the language
````

Now, in the case of serialising this Java object from a set of triples containing a title with a different language tag than *'@en'* the object will lack of a title. To provide an example let's assume the next set of triples

````
@prefix dc: <http://purl.org/dc/terms/> .
@prefix ns0: <https://oeg-upm.github.io/kehio/test_ontology#> .
@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .
@prefix dc11: <http://purl.org/dc/elements/1.1/> .

<http://test.song/002>  dc11:creator "Vicente Amigo" ;
					dc11:title "Ciudad de las ideas"@es .
````

If the song Java object is serialised, and its attributes printed this will be the output:

````
Model model = ModelFactory.createDefaultModel();
model.read(new ByteArrayInputStream(TRIPLES.getBytes()), null, "TURTLE");

Resource resource = ResourceFactory.createResource("http://test.song/002");
SongExample2 song = (SongExample2) Kehio.serializeClass(SongExample2.class, model, resource);

System.out.println(song.getTitle()); // this will output "null"
System.out.println(song.getCreator()); // this will output "Vicente Amigo"
````

The reason for the null in the title is due to the fact that the annotation in the class specified the restriction that the value of the title *must* have the *'@en'* tag language. Any the literal with a different language tag will not be serialised.


 ### 1.2 Datatype property with sank language ([code sample]())

As shown in the previous example,  sometimes the language can not be known beforehand or different objects could rely on different languages. For this cases, Kehio offers the possibility of sinking the language tag into the attribute value.

* *sinking language: it consist in storing the value of the RDF literal in the attribute along with the language tag, separated by the character '@'*

For this example, let's assume the opposite scenario in which the following set of triples are provided

````
@prefix dc: <http://purl.org/dc/terms/> .
@prefix ns0: <https://oeg-upm.github.io/kehio/test_ontology#> .
@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .
@prefix dc11: <http://purl.org/dc/elements/1.1/> .

<http://test.song/001>	dc11:creator "Carlos Blas" ;
	  dc11:title "El Kejío"@en .
	  
<http://test.song/002>  dc11:creator "Vicente Amigo" ;
					dc11:title "Ciudad de las ideas"@es .

````

In order to capture the different languages in the *title* attribute such tag can be sank using the following annotation

```
private  class SongExample3 {

	@RdfDatatype(value="http://purl.org/dc/elements/1.1/title", lang="en")
	private String title;
	
	// The rest of the attributes remain the same as SongExample1
	[...]
	
	// After this the class should have a constructor, getters & setters, ... 
	[...]
```

When the triples are serialised in as a Java object, and the titles printed, this is the output








The annotation `@RdfDatatype`  works with any `String` or `Collection` attribute. Consider that in RDF a datatype property has no restriction on its carnality, therefore if this annotation is used with a `String`
