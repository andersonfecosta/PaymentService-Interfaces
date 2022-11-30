package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContractService {

    private OnlinePaymentService service;

    public ContractService(OnlinePaymentService service) {
        this.service = service;
    }

    List<Installment> installments = new ArrayList<>();
    public void processContract(Contract contract, int months) {

        double amount = contract.getTotalValue() / months;

        for (int i=1; i<=months; i++) {
            double a = amount + service.interest(amount,i);
            a = a + service.paymentFee(a);
            LocalDate date = LocalDate.from(contract.getDate().plusMonths(i));
            contract.getInstallment().add(new Installment(date,a));
        }
    }

    public List<Installment> list() {
        return installments;
    }

    @Override
    public String toString() {
        return installments.toString();
    }
}
