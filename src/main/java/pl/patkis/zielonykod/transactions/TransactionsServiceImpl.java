package pl.patkis.zielonykod.transactions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Override
    public List<Account> calculateReport(List<Transaction> transactions) {
        Map<String, Account> map = new HashMap<>();
        for (Transaction t : transactions) {
            Account creditAccount = getAccount(map, t.creditAccount);
            creditAccount.balance = creditAccount.balance.add(t.amount);
            creditAccount.creditCount += 1;
            Account debitAccount = getAccount(map, t.debitAccount);
            debitAccount.balance = debitAccount.balance.subtract(t.amount);
            debitAccount.debitCount += 1;
        }
        List<Account> result = new ArrayList<>(map.values());
        result.sort((a1, a2) -> a1.account.compareTo(a2.account));
        return result;
    }

    private Account getAccount(Map<String, Account> map, String account) {
        if (map.containsKey(account)) {
            return map.get(account);
        }
        Account result = new Account();
        result.account = account;
        result.creditCount = 0;
        result.debitCount = 0;
        result.balance = BigDecimal.ZERO;
        map.put(account, result);
        return result;
    }
    
}
