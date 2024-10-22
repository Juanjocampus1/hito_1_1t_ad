package com.empresa.hito_1_ad_juanjose_acebedo.Repository;

import com.empresa.hito_1_ad_juanjose_acebedo.entities.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BankDAO {
    private static final String FILE_NAME = "bank.dat";

    public void saveTransaction(Transaction transaction) {
        List<Transaction> transactions = getAllTransactions();
        transactions.add(transaction);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            transactions = (List<Transaction>) ois.readObject();
        } catch (EOFException e) {
            // File is empty, return empty list
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public double getTotalAmount() {
        List<Transaction> transactions = getAllTransactions();
        double total = 0;
        for (Transaction transaction : transactions) {
            total += transaction.getAmount();
        }
        return total;
    }
}