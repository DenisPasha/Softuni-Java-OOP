package P09TestDrivenDevelopment.Exercise;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChainblockImpl implements Chainblock{

   private Map<Integer ,Transaction> transactionMap;

    public ChainblockImpl() {
        this.transactionMap = new HashMap<>();
    }

    public Map<Integer, Transaction> getTransactionMap() {
        return transactionMap;
    }

    public void add(Transaction transaction) {
        transactionMap.putIfAbsent(transaction.getId(),transaction);
    }

    public boolean contains(Transaction transaction) {
        Transaction transaction1 = transaction;
        contains(transaction1.getId());
       return transactionMap.containsKey(transaction1.getId());
    }

    public boolean contains(int id) {
        return transactionMap.containsKey(id);
    }


    public int getCount() {
        return transactionMap.size();
    }



    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (transactionMap.containsKey(id)){
            Transaction transaction = transactionMap.get(id);
            transaction.setStatus(newStatus);
            transactionMap.put(id,transaction);
        }else {
            throw new IllegalArgumentException();
        }
    }

    public void removeTransactionById(int id) {
        if (transactionMap.containsKey(id)){
            transactionMap.remove(id);
        }else {
            throw new IllegalArgumentException();
        }
    }

    public Transaction getById(int id) {
        if (transactionMap.containsKey(id)){
            return transactionMap.get(id);
        }else {
            throw new IllegalArgumentException();
        }
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> transactionList = new ArrayList<>();

        for (Map.Entry<Integer, Transaction> entry : transactionMap.entrySet()) {
            Integer key = entry.getKey();
            Transaction currentTransaction = entry.getValue();
            if (currentTransaction.getStatus().equals(status)) {
                transactionList.add(currentTransaction);
            }
        }
        if (transactionList.size() == 0){
            throw new IllegalArgumentException();
        }else {
            transactionList.sort(Comparator.comparing(Transaction::getAmount).reversed());
            return transactionList;
        }

    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<Transaction>transactionsList = new ArrayList<>();
        List<String>personsList = new ArrayList<>();
        for (Map.Entry<Integer, Transaction> entry : transactionMap.entrySet()) {
            Transaction value = entry.getValue();
            if (value.getStatus().equals(status)){
               transactionsList.add(value);
           }
        }

        transactionsList.sort(Comparator.comparing(Transaction::getAmount));
        transactionsList.forEach(transaction -> personsList.add(transaction.getFrom()));
        if (personsList.isEmpty()){
            throw new IllegalArgumentException();
        }
        return personsList;

    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<Transaction>transactionList = new ArrayList<>();
        List<String>namesList = new ArrayList<>();
        for (Map.Entry<Integer, Transaction> entry : transactionMap.entrySet()) {
            Transaction currentTransaction = entry.getValue();
            if (currentTransaction.getStatus().equals(status)){
                transactionList.add(currentTransaction);
            }
        }

        transactionList.sort(Comparator.comparing(Transaction::getAmount));
        transactionList.forEach(transaction -> namesList.add(transaction.getTo()));

        if (namesList.isEmpty()){
            throw new IllegalArgumentException();
        }
        return namesList;

    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        List<Transaction> collect = transactionMap.values().stream()
                .sorted(Comparator.comparing(Transaction::getAmount)
                        .thenComparing(Transaction::getId)).collect(Collectors.toList());
        System.out.println();
        return collect;
    }



    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> collect = transactionMap.values().stream().
                filter(transaction -> transaction.getFrom().equals(sender))
                .collect(Collectors.toList());
        collect.stream().sorted(Comparator.comparing(Transaction::getAmount).reversed());
        if (!collect.isEmpty()){
            return collect;
        }else {
            throw new IllegalArgumentException();
        }

    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> collect = transactionMap.values().stream().filter(transaction -> transaction.getTo().equals(receiver))
                .sorted(Comparator.comparing(Transaction::getAmount).
                        thenComparing(Transaction::getId)).collect(Collectors.toList());
        if (!collect.isEmpty()){
            return collect;
        }else {
            throw new IllegalArgumentException();
        }
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        List<Transaction> collect = transactionMap.values().stream().filter(transaction -> transaction.getStatus().equals(status))
                .filter(transaction -> transaction.getAmount() <= amount)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .collect(Collectors.toList());
        if (!collect.isEmpty()){
            return collect;
        }else {
            throw new IllegalArgumentException();
        }
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> collect = transactionMap.values().stream().filter(transaction -> transaction.getFrom().equals(sender))
                .filter(transaction -> transaction.getAmount() > amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        if (!collect.isEmpty()){
            return collect;
        }else {
            throw new IllegalArgumentException();
        }
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        List<Transaction> collect = transactionMap.values().stream().filter(transaction -> transaction.getTo().equals(receiver))
                .filter(transaction -> transaction.getAmount() >= lo && transaction.getAmount() < hi)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());
        if (!collect.isEmpty()){
            return collect;
        }else {
            throw new IllegalArgumentException();
        }
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        List<Transaction> collect = transactionMap.values().stream().filter(transaction -> transaction.getAmount() >= lo && transaction.getAmount() <= hi)
                .collect(Collectors.toList());
        if (!collect.isEmpty()){
            return collect;
        }else {
            throw new IllegalArgumentException();
        }
    }

    public Iterator<Transaction> iterator() {
        return null;
    }
}
