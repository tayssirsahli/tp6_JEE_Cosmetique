package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import dao.ClassificationDaoImpl;
import dao.IClassificationDao;
import metier.entities.Classification;

@WebServlet(name = "clasServ", urlPatterns = { "/classifications", "/saisieClassification", "/saveClassification",
		"/supprimerClas", "/editerClas", "/updateClas" })

public class ClassificationServlet extends HttpServlet {
	IClassificationDao metier;

	@Override
	public void init() throws ServletException {
		metier = new ClassificationDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		System.out.println("PATH " + path);
		if (path.equals("/classifications")) {
			ClassificationModel model = new ClassificationModel();
			List<Classification> cats = metier.getAllClassifications();
			model.setClassifications(cats);
			request.setAttribute("model", model);
			request.getRequestDispatcher("classification.jsp").forward(request, response);
		} else if (path.equals("/saisieClassification")) {
			request.getRequestDispatcher("saisieClassification.jsp").forward(request, response);
		}

		else if (path.equals("/saveClassification") && request.getMethod().equals("POST"))

		{
			Date dateCat = new Date();
			String nom = request.getParameter("nom");
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			try {

				dateCat = simpleDateFormat.parse(request.getParameter("dateCat"));

			} catch (ParseException e) {
				e.printStackTrace();
			}
			Classification cat = metier.save(new Classification(nom, dateCat));
			request.setAttribute("classification", cat);
			response.sendRedirect("classifications");
		} else if (path.equals("/supprimerClas")) {
			Long id = Long.parseLong(request.getParameter("id"));
			metier.deleteClassification(id);
			response.sendRedirect("classifications");
		} else if (path.equals("/editerClas")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Classification cat = metier.getClassification(id);
			request.setAttribute("classification", cat);
			request.getRequestDispatcher("editerClassification.jsp").forward(request, response);
		} else if (path.equals("/updateClas")) {
			Date dateCat = new Date();
			Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nom");
			Classification cat = new Classification();
			cat.setIdClas(id);
			cat.setNomClas(nom);
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			try {

				dateCat =

						simpleDateFormat.parse(request.getParameter("dateCreation"));

			} catch (ParseException e) {
				e.printStackTrace();
			}
			cat.setDateCreation(dateCat);
			metier.updateCategorie(cat);
			response.sendRedirect("classifications");

		} else {
			response.sendError(Response.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,

			HttpServletResponse response) throws

	ServletException, IOException {
		doGet(request, response);
	}
}