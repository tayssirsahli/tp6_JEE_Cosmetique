package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Classification;
import util.JPAutil;

public class ClassificationDaoImpl implements IClassificationDao {
// TP6_JEE à replacer par votre persistence unit, consultez votre
//fichier persistence.xml <persistence-unit name="TP6_JEE">
	private EntityManager entityManager = JPAutil.getEntityManager("TP5_JEE_Cosmetique");

	@Override
	public Classification save(Classification cat) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(cat);
		tx.commit();

		return cat;
	}

	@Override
	public Classification getClassification(Long id) {
		return entityManager.find(Classification.class, id);
	}

	@Override
	public Classification updateCategorie(Classification cat) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(cat);
		tx.commit();
		return cat;
	}

	@Override
	public void deleteClassification(Long id) {
		Classification classification = entityManager.find(Classification.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(classification);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Classification> getAllClassifications() {
	    List<Classification> cats = entityManager.createQuery("SELECT c FROM Classification c", Classification.class).getResultList();
	    return cats;
	}

}