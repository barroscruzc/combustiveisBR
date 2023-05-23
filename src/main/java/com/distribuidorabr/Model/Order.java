package com.distribuidorabr.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.distribuidorabr.Model.enums.OrderType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order implements Serializable{
	
	private static final long serialVersionUID = -4200924561915341468L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	//@Positive(message="Insira um valor válido")
	//@NotNull(message="Campo obrigatório")
	private double totalValue;
	
	@Column(name="order_date")
	//@NotNull(message="Campo obrigatório")
	private Date orderDate;
	
	@ManyToOne
	@JoinColumn
	//@NotNull(message="Campo obrigatório")
	private Company company;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.MERGE)
	@JsonIgnoreProperties("order")
	//@NotNull(message="Campo obrigatório")
	private List<Item> items = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private OrderType type;
	
	public Order() {
		super();
	}

	public Order(int id, double totalValue, Company company, List<Item> items, OrderType type) {
		super();
		this.id = id;
		this.totalValue = totalValue;
		this.orderDate = new java.sql.Date(new Date().getTime());
		this.company = company;
		this.items = items;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotalValue() {
		return totalValue;
	}

	/* totalValue is the sum of the unit values 
	 * ​​of all items in the order
	 */
	public void setTotalValue() {
		double totalValue = 0;
		for(Item item : items) {
			totalValue += item.getUnitValue();
		}
		this.totalValue = totalValue;
	}

	public Date getDate() {
		return orderDate;
	}

	public void setDate() {
		this.orderDate = new java.sql.Date(new Date().getTime());
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}

	public OrderType getType() {
		return type;
	}

	public void setType(OrderType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", totalValue=" + totalValue + ", orderDate=" + orderDate + ", company=" + company + ", items="
				+ items + ", type=" + type + "]";
	}
	
}
