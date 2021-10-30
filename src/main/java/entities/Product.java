package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Product {
	
	private int id, stock;
	private String name, description;
	private double price;
	private boolean shippingIncluded;
	private LocalDateTime disabledOn;
	private LocalDate disabledDate;
	private LocalTime disabledTime;
	
	private static Scanner lector = new Scanner(System.in);
	static String dateFormat = "dd/MM/yyyy";
	static String timeFormat = "HH:mm:ss";
	static String dateTimeFormat = dateFormat + " " + timeFormat;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isShippingIncluded() {
		return shippingIncluded;
	}
	public void setShippingIncluded(boolean shippingIncluded) {
		this.shippingIncluded = shippingIncluded;
	}
	public LocalDateTime getDisabledOn() {
		return disabledOn;
	}
	public void setDisabledOn(LocalDateTime disabledOn) {
		this.disabledOn = disabledOn;
	}
	public LocalDate getDisabledDate() {
		return disabledDate;
	}
	public void setDisabledDate(LocalDate disabledDate) {
		this.disabledDate = disabledDate;
	}
	public LocalTime getDisabledTime() {
		return disabledTime;
	}
	public void setDisabledTime(LocalTime disabledTime) {
		this.disabledTime = disabledTime;
	}
	
	@Override
	public String toString() {
		
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern(dateTimeFormat);
		DateTimeFormatter dFormat = DateTimeFormatter.ofPattern(dateFormat);
		DateTimeFormatter tFormat = DateTimeFormatter.ofPattern(timeFormat);
		
		return "Product [id=" + id + ", stock=" + stock + ", name=" + name + ", description=" + description + ", price="
				+ price + ", shippingIncluded=" + shippingIncluded + ", disabledOn=" +
				disabledOn == null ? null : disabledOn.format(dtFormat) + ", disabledDate="
				+ disabledDate == null ? null : disabledDate.format(dFormat) + ", disabledTime=" +
				disabledTime == null ? null : disabledTime.format(tFormat) + "]\n";
	}
	
}
