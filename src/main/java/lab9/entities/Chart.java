package lab9.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQuery(name = "chart.findById", query = "SELECT c FROM Chart c WHERE c.id = :id")
@NamedQuery(name = "chart.findByName", query = "SELECT c FROM Chart c WHERE c.name= :name")
@Entity
@Table(name = "CHARTS")
public class Chart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private int id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "CREATION_DATE")
	private Date creationDate;

	@OneToMany(mappedBy = "chart")
	private Set<ChartEntry> chartsEntries;

	public Chart() {
		chartsEntries = new HashSet<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creation_date) {
		this.creationDate = creation_date;
	}

	public Set<ChartEntry> getChartsEntries() {
		return chartsEntries;
	}

	public void setChartsEntries(Set<ChartEntry> chartsEntries) {
		this.chartsEntries = chartsEntries;
	}
}
