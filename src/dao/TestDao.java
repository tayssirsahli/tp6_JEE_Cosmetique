package dao;

import java.util.List;
import metier.entities.Cosmetique;

public class TestDao {
	public static void main(String[] args) {
		CosmetiqueDaoImpl pdao = new CosmetiqueDaoImpl();
		Cosmetique COSM = pdao.save(new Cosmetique("svr", 280));
		System.out.println(COSM);
		List<Cosmetique> cosms = pdao.CosmetiquesParMC("sv");
		for (Cosmetique p : cosms)
			System.out.println(p);
	}
}