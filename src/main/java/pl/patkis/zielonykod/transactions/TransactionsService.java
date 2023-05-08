package pl.patkis.zielonykod.transactions;

import java.util.List;

public interface TransactionsService {
    public List<Account> calculateReport(List<Transaction> transactions);
}
