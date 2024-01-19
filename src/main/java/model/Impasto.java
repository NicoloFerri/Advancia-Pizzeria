package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "impasto")
public class Impasto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "impasto_id")
	private int impastoId;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "impasto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Pizza> pizzas;

	public Impasto() {
	}

	public Impasto(int impasto_id, String name, List<Pizza> pizzas) {
		super();
		this.impastoId = impasto_id;
		this.name = name;
		this.pizzas = pizzas;
	}

	public Impasto(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public int getId() {
		return impastoId;
	}

}
