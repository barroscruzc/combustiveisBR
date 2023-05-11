package com.distribuidorabr.Model;

import java.io.Serializable;
import java.util.Objects;

import com.distribuidorabr.Model.enums.BusinessRelationship;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name="company")
public class Company extends Person implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false, length = 100)
	private String corporateName;
	
	@Column(nullable=false, length = 18, unique=true)
	private String cnpj;
	
	@Enumerated(EnumType.STRING)
	private BusinessRelationship businessRelationship;
	
	public Company() {
		super();
	}

	public Company(String corporateName, String cnpj, BusinessRelationship businessRelationship) {
		super();
		this.corporateName = corporateName;
		this.cnpj = cnpj;
		this.businessRelationship = businessRelationship;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public BusinessRelationship getBusinessRelationship() {
		return businessRelationship;
	}

	public void setBusinessRelationship(BusinessRelationship businessRelationship) {
		this.businessRelationship = businessRelationship;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cnpj);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Company [corporateName=" + corporateName + ", cnpj=" + cnpj + ", businessRelationship="
				+ businessRelationship + ", id=" + id + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", country=" + country + ", state=" + state + "]";
	}
	
}
