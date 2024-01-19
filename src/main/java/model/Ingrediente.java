package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ingrediente")

public class Ingrediente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ingrediente_id")
	private int ingredienteId;

	@Column(name = "name")
	private String name;	

	public Ingrediente() {
		super();
	}

	public Ingrediente(int ingrediente_id, String name) {
		super();
		this.ingredienteId = ingrediente_id;
		this.name = name;
	}


	public Ingrediente(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return ingredienteId;
	}

  
}
