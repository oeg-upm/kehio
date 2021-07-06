package kehio.example.datatype.test;

import kehio.annotations.RdfDatatype;

public class SongExample4 {

		@RdfDatatype(value="http://purl.org/dc/elements/1.1/title")
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
		private String created;

		public SongExample4() {
			super();
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getCreator() {
			return creator;
		}

		public void setCreator(String creator) {
			this.creator = creator;
		}

		public Float getIdentifier() {
			return identifier;
		}

		public void setIdentifier(Float identifier) {
			this.identifier = identifier;
		}

		public Double getLength() {
			return length;
		}

		public void setLength(Double length) {
			this.length = length;
		}

		public Boolean getAvailable() {
			return available;
		}

		public void setAvailable(Boolean available) {
			this.available = available;
		}

		public Integer getSold() {
			return sold;
		}

		public void setSold(Integer sold) {
			this.sold = sold;
		}

		public Number getLikes() {
			return likes;
		}

		public void setLikes(Number likes) {
			this.likes = likes;
		}

		public String getCreated() {
			return created;
		}

		public void setCreated(String created) {
			this.created = created;
		}
		
	
}
