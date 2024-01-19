package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Impasto;
import model.Ingrediente;
import model.Pizza;
import model.User;
import util.JPAUtil;

public class DAO {

	public void insertPizza(String imp, String[] ingredients, String pizzaName, User user) {
		Impasto dough = new Impasto();
		dough.setName(imp);
		insertImpasto(dough);		
		
		List<Ingrediente> ingList = new ArrayList<Ingrediente>();
		for (int i = 0; i < ingredients.length; i++) {
			Ingrediente ingrediente = new Ingrediente();
			ingrediente.setName(ingredients[i]);
			ingList.add(ingrediente);
		}
		insertListaIngredienti(ingList);
		
		Pizza pizza = new Pizza();
		
		pizza.setImpasto(dough);
		pizza.setIngredientiList(ingList);
		pizza.setName(pizzaName);
		pizza.setUser(user);
		user.getPizzas().add(pizza);
		insertEntity(pizza);
	}
	
	public void insertListaIngredienti(List<Ingrediente> list) {
	    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
	    try {
	        entityTransaction.begin();
	        for (Ingrediente ingrediente : list) {
	            if (!entityManager.contains(ingrediente)) {
	                ingrediente = entityManager.merge(ingrediente);
	            }
	            entityManager.persist(ingrediente);
	        }
	        entityTransaction.commit();
	    } catch (Exception e) {
	        if (entityTransaction != null && entityTransaction.isActive()) {
	            entityTransaction.rollback();
	        }
	        e.printStackTrace(); // Tratta l'eccezione in modo appropriato
	    } finally {
	        entityManager.close();
	    }
	}

	
	public void insertImpasto(Impasto impasto) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(impasto);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	
	public List<String> getPizzaListByUser(User user){
		List<String> listOfPizza = new ArrayList<String>();
		  for (Pizza pizza : user.getPizzas()) {
			  listOfPizza.add(pizza.getName());
		  }
		return listOfPizza;
	}
	
	public List<String> getImpastoListByUser(User user){
		List<String> impastoList = new ArrayList<String>();
		List<Pizza> listaPizze = user.getPizzas();
		for ( Pizza pizza : listaPizze) {
			impastoList.add(pizza.getImpasto().getName());
		}
		return impastoList;
	}
	
	public List<List<String>> getIngredientsListByUser(User user) {
	    List<List<String>> ingredientList = new ArrayList<>();
	    
	    for (Pizza pizza : user.getPizzas()) {
	        List<String> ingredientList1 = new ArrayList<>();
	        for (Ingrediente ingrediente : pizza.getIngredientiList()) {
	            ingredientList1.add(ingrediente.getName());
	        }
	        ingredientList.add(ingredientList1);
	    }
	    return ingredientList;
	}


	public User selectCredentials(String username, String password) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		List<User> listaUtenti = new ArrayList<>();
		try {
			TypedQuery<User> query = entityManager.createQuery(
					"SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
			query.setParameter("username", username);
			query.setParameter("password", password);
			List<User> resultList = query.getResultList();
			if (!resultList.isEmpty()) {
				listaUtenti.addAll(resultList);
			}
		} catch (NoResultException e) {
		} finally {
			entityManager.getTransaction().commit();
			entityManager.close();
		}
		return listaUtenti.isEmpty() ? null : listaUtenti.get(0);
	}

	public void insertEntity(Pizza pizza) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(pizza);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public List<Pizza> selectListPizzas(int userId) {
		List<Pizza> listPizzas = new ArrayList<>();
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {

			TypedQuery<Pizza> query = entityManager.createQuery("SELECT p FROM Pizza p WHERE p.user.id = :userId",
					Pizza.class);
			query.setParameter("userId", userId);
			listPizzas = query.getResultList();

		} catch (NoResultException e) {
			e.printStackTrace();
		} finally {
			entityManager.getTransaction().commit();
			entityManager.close();
		}

		return listPizzas;
	}

	public User getUserById(int userId) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :userId", User.class);
			query.setParameter("userId", userId);
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}
	}

}
