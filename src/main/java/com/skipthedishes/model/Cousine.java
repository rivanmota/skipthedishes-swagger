package com.skipthedishes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cousine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cousineId;
	private String name;

	public Cousine() {
	}

	public Long getCousineId() {
		return cousineId;
	}

	public void setCousineId(Long cousineId) {
		this.cousineId = cousineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cousineId == null) ? 0 : cousineId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Cousine other = (Cousine) obj;
		if (cousineId == null) {
			if (other.cousineId != null)
				return false;
		} else if (!cousineId.equals(other.cousineId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cousine [cousineId=" + cousineId + ", name=" + name + "]";
	}

}
