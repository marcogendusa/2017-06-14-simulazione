package it.polito.tdp.artsmia.model;

import java.util.*;

public class Exhibition {
	
	private int exhibitionId;
	private String department;
	private String title;
	private int beginYear;
	private int endYear;
	private List<Integer> artObjectsId;

	public Exhibition(int id, String department, String title, int beginYear, int endYear) {
		super();
		this.exhibitionId = id;
		this.department = department;
		this.title = title;
		this.beginYear = beginYear;
		this.endYear = endYear;
		artObjectsId = new ArrayList<Integer>();
	}

	public int getId() {
		return exhibitionId;
	}

	public void setId(int id) {
		this.exhibitionId = id;
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
		Exhibition other = (Exhibition) obj;
		if (exhibitionId != other.exhibitionId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return title + " (id: " + exhibitionId + ")";
	}

}
