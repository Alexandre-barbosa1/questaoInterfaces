package model.service;

import java.time.Duration;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {
	private double pricePerDay;
	private double pricePerHour;
	
	private TaxService taxService;

	public RentalService(Double pricePerDay, Double pricePerHour, TaxService taxService) {
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
		this.taxService = taxService;
	}

	public void processInvoide(CarRental cr) {
		double minutes =  Duration.between(cr.getStart(),cr.getFinish()).toMinutes();
		double hours = minutes / 60;
		
		double basicPayment;
		if (hours <= 12.0) {
			basicPayment = pricePerHour * Math.ceil(hours);
		}
		else {
			basicPayment = pricePerDay * Math.ceil(hours / 24);
		}

		double tax = taxService.tax(basicPayment);

		cr.setInvoice(new Invoice(basicPayment, tax));
	}
		
	}
	
	
	


