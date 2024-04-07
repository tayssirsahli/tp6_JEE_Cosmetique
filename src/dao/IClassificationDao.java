package dao;

import java.util.List;

import metier.entities.Classification;

public interface IClassificationDao {
	public Classification save(Classification cat);

	public Classification getClassification(Long id);

	public Classification updateCategorie(Classification cat);

	public void deleteClassification(Long id);

	public List<Classification> getAllClassifications();
}
