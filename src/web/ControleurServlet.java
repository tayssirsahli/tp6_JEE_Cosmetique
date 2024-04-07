package web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassificationDaoImpl;
import dao.CosmetiqueDaoImpl;
import dao.IClassificationDao;
import dao.ICosmetiqueDao;
import metier.entities.Classification;
import metier.entities.Cosmetique;

@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {
	ICosmetiqueDao metier;
	IClassificationDao metierClas;

	@Override
	public void init() throws ServletException {
		metier = new CosmetiqueDaoImpl();
		metierClas = new ClassificationDaoImpl();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/index.do")) {
			request.getRequestDispatcher("Cosmetiques.jsp").forward(request, response);
		} else if (path.equals("/chercher.do")) {
			String motCle = request.getParameter("motCle");
			CosmetiqueModele model = new CosmetiqueModele();
			model.setMotCle(motCle);
			List<Cosmetique> Cosms = metier.CosmetiquesParMC(motCle);
			model.setCosmetiques(Cosms);
			request.setAttribute("model", model);
			request.getRequestDispatcher("Cosmetiques.jsp").forward(request, response);
		} else if (path.equals("/saisie.do")) {
			
			List<Classification> cats = metierClas.getAllClassifications();
			ClassificationModel model= new ClassificationModel();
			model.setClassifications(cats);
			request.setAttribute("catModel", model);
			request.getRequestDispatcher("saisieCosmetique.jsp").forward(request, response);
			
		} else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
			String nom = request.getParameter("nom");
			Long classificationId=Long.parseLong(request.getParameter("classification"));
			double prix = Double.parseDouble(request.getParameter("prix"));
			Classification cat = metierClas.getClassification(classificationId);
			Cosmetique c = metier.save(new Cosmetique(nom, prix,cat));
			request.setAttribute("Cosmetique", c);
			response.sendRedirect("chercher.do?motCle=");
			} else if (path.equals("/supprimer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			metier.deleteCosmetique(id);
			response.sendRedirect("chercher.do?motCle=");
		} else if (path.equals("/editer.do")) {
			
			
			
			Long id = Long.parseLong(request.getParameter("id"));
			Cosmetique c = metier.getCosmetique(id);
			request.setAttribute("Cosmetique", c);
			
			List<Classification> cats = metierClas.getAllClassifications();
			ClassificationModel model= new ClassificationModel();
			model.setClassifications(cats);
			request.setAttribute("catModel", model);
			
			
			request.getRequestDispatcher("editerCosmetique.jsp").forward(request, response);
		} else if (path.equals("/update.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nom");
			double prix = Double.parseDouble(request.getParameter("prix"));
			Long classificationId=Long.parseLong(request.getParameter("classification"));

			Cosmetique p = new Cosmetique();
			p.setIdCosmetique(id);
			p.setNomCosmetique(nom);
			p.setPrix(prix);
			Classification cat = metierClas.getClassification(classificationId);
			p.setClassification(cat);
			metier.updateCosmetique(p);
			request.setAttribute("Cosmetique", p);
			request.getRequestDispatcher("chercher.do?motCle=").forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}