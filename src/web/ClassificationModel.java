package web;

import java.util.ArrayList;
import java.util.List;

import metier.entities.Classification;

public class ClassificationModel {

	List<Classification> classifications = new ArrayList<>();

	public List<Classification> getClassifications() {
		return classifications;
	}

	public void setClassifications(List<Classification> classifications) {
		this.classifications = classifications;
	}
}
