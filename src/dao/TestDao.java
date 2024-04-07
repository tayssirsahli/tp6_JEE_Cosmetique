package dao;

import java.util.Date;
import java.util.List;

import metier.entities.Classification;
import metier.entities.Cosmetique;

public class TestDao {
	public static void main(String[] args) {
		CosmetiqueDaoImpl pdao = new CosmetiqueDaoImpl();
		 Classification classification = new Classification("clas1", new Date());
	        classification = pdao.saveClassification(classification);
		Cosmetique COSM = pdao.save(new Cosmetique("svr", 280,classification));
		System.out.println(COSM);
		List<Cosmetique> cosms = pdao.CosmetiquesParMC("sv");
		for (Cosmetique p : cosms) {
			System.out.println(p);
		}
	}
}