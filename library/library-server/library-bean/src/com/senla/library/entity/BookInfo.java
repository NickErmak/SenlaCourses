package com.senla.library.entity;

import static com.senla.library.util.DateConverter.dateToString;
import static com.senla.library.util.DateConverter.stringToDate;

import java.util.Date;

import com.senla.library.api.annotation.dao.ColumnDAO;
import com.senla.library.api.annotation.dao.EntityDAO;
import com.senla.library.api.annotation.dao.Id;
import com.senla.library.util.DateConverter;;

@EntityDAO(table = "book_info", pk = "id")
public class BookInfo extends Entity {

	@Id
	@ColumnDAO(name = "id") 
	private int id;		
	@ColumnDAO(name = "title") 
	private String title;
	@ColumnDAO(name = "publication_date") 
	private Date publicationDate;
	@ColumnDAO(name = "price") 
	private double price;
	@ColumnDAO(name = "description") 
	private String description;
	
	public BookInfo() {}
	
	public BookInfo(String title, Date publicationDate, double price, String description) {		
		this.title = title;
		this.publicationDate = publicationDate;
		this.price = price;
		this.description = description;
	}		

	public int getId() {
		return id;
	}

	public void setId(String id) {
		this.id = Integer.valueOf(id);
	}	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPublicationDate() {
		return DateConverter.dateToString(publicationDate);
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = stringToDate(publicationDate);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setPrice(String price) {
		this.price = Double.valueOf(price);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public BookInfo clone() throws CloneNotSupportedException {
		return (BookInfo) super.clone();		
	}

	@Override
	public String toString() {
		return "BookInfo [id=" + id + ", title=" + title + ", publicationDate=" + dateToString(publicationDate) + ", price=" + price
				+ ", description=" + description + "]";
	}	
	
}
