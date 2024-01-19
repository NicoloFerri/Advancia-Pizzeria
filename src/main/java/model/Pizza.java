package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pizza")
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pizza_id")
	private int pizzaId;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "user")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "impasto")
	private Impasto impasto;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "pizza_ingredienti", joinColumns = @JoinColumn(name = "pizza_id"), 
	               inverseJoinColumns = @JoinColumn(name = "ingredienti_id"))
	private List<Ingrediente> ingredientiList = new ArrayList<>();
	
	
	public Pizza() {
		super();
	}

	public Pizza(int pizza_id, String name, User user, Impasto impasto, List<Ingrediente> ingredientiList) {
		super();
		this.pizzaId = pizza_id;
		this.name = name;
		this.user = user;
		this.impasto = impasto;
		this.ingredientiList = ingredientiList;
	}

	public Impasto getImpasto() {
		return impasto;
	}

	public void setImpasto(Impasto impasto) {
		this.impasto = impasto;
	}

	public List<Ingrediente> getIngredientiList() {
		return ingredientiList;
	}

	public void setIngredientiList(List<Ingrediente> ingredientiList) {
		this.ingredientiList = ingredientiList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPizzaId() {
		return pizzaId;
	}
	
	

}
