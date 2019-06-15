package it.polito.tdp.artsmia.model;

import java.util.List;

public class Mostra {
	
	private int exhibitionId;
	private String department;
	private String title;
	private int beginYear;
	private int endYear;
	private List<Integer> artObjectsId;
	
	public Mostra(int exhibitionId, String department, String title, int beginYear, int endYear) {
		super();
		this.exhibitionId = exhibitionId;
		this.department = department;
		this.title = title;
		this.beginYear = beginYear;
		this.endYear = endYear;
	}

	public int getExhibitionId() {
		return exhibitionId;
	}

	public void setExhibitionId(int exhibitionId) {
		this.exhibitionId = exhibitionId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBeginYear() {
		return beginYear;
	}

	public void setBeginYear(int beginYear) {
		this.beginYear = beginYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public List<Integer> getArtObjectsId() {
		return artObjectsId;
	}

	public void setArtObjectsId(List<Integer> artObjectsId) {
		this.artObjectsId = artObjectsId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + exhibitionId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mostra other = (Mostra) obj;
		if (exhibitionId != other.exhibitionId)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return title + " (id: " + exhibitionId + ")";
	}

}
