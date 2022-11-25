package application;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre os dados do contrato:");
        System.out.print("Número: ");
        int number = sc.nextInt();
        sc.nextLine();
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.nextLine(), fmt);
        System.out.print("Valor do contrato: ");
        double totalValue = sc.nextDouble();

        Contract contract = new Contract(number,date,totalValue);

        System.out.print("Entre com o número de parcelas: ");
        int months = sc.nextInt();

        ContractService service = new ContractService(new PaypalService());
        service.processContract(contract,months);

        System.out.println();
        System.out.println("Parcelas:");
        for (int i=0; i<service.list().size(); i++) {
            System.out.println(service.list().get(i));
        }

    }
}
