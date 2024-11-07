package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.service.BrazilTaxService;
import model.service.RentalService;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		try {
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro:");
		String carModel = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy HH:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), dtf);
		System.out.print("Retorno (dd/MM/yyyy HH:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), dtf);
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		System.out.print("Entre com o preço por hora: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		double pricePerDay = sc.nextDouble();
		RentalService rs = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		rs.processInvoide(cr);
		System.out.println("FATURA:");
		System.out.println("Pagamento basico: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Imposto: " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Pagamento total: " + String.format("%.2f", cr.getInvoice().totalPayment()));
					
		}
		catch (DateTimeParseException e ) {
			System.out.println(e.getMessage());
		}
		
		sc.close();
	}

}
