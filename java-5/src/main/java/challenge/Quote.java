package challenge;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "scripts")
public class Quote {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private Integer episode;
	private String episodeName;
	private String segment;
	private String type;
	private String actor;
	private String character;
	@Column(name = "detail")
	private String quote;
	private Date recordDate;
	private String series;
	private Date transmissionDate;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActor() {
		return this.actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getQuote() {
		return this.quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

}