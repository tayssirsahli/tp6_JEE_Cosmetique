package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Cosmetique;
import util.JPAutil;

public class CosmetiqueDaoImpl implements ICosmetiqueDao {
	private EntityManager entityManager = JPAutil.getEntityManager("TP5_JEE_Cosmetique");

	@Override
	public Cosmetique save(Cosmetique p) {

		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(p);
		tx.commit();
		return p;
	}

	@Override
	public List<Cosmetique> CosmetiquesParMC(String mc) {
		List<Cosmetique> prods = entityManager.createQuery("select c from Cosmetique c where c.nomCosmetique like :mc")
				.setParameter("mc", "%" + mc + "%").getResultList();

		return prods;
	}

	@Override
	public Cosmetique getCosmetique(Long id) {
		return entityManager.find(Cosmetique.class, id);
	}

	@Override
	public Cosmetique updateCosmetique(Cosmetique p) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(p);
		tx.commit();
		return p;
	}

	@Override
	public void deleteCosmetique(Long id) {
		Cosmetique cosmetique = entityManager.find(Cosmetique.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(cosmetique);
		entityManager.getTransaction().commit();
	}
}