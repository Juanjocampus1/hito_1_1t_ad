package com.empresa.hito_1_ad_juanjose_acebedo.controller;

import com.empresa.hito_1_ad_juanjose_acebedo.Repository.BankDAO;
import com.empresa.hito_1_ad_juanjose_acebedo.Repository.Persistence;
import com.empresa.hito_1_ad_juanjose_acebedo.entities.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class MainController {
    @FXML
    private Label usernameLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private ListView<Transaction> transactionHistory;
    @FXML
    private TextField amountField;
    @FXML
    private Button depositButton;
    @FXML
    private Button withdrawButton;

    private BankDAO bankDAO = new BankDAO();

    @FXML
    public void initialize() {
        usernameLabel.setText("Welcome, " + Persistence.loggedInUsername + "!");
        updateBalance();
        updateTransactionHistory();
    }

    @FXML
    protected void onDepositButtonClick() {
        double amount = Double.parseDouble(amountField.getText());
        Transaction transaction = new Transaction("deposit", amount);
        bankDAO.saveTransaction(transaction);
        updateBalance();
        updateTransactionHistory();
    }

    @FXML
    protected void onWithdrawButtonClick() {
        double amount = Double.parseDouble(amountField.getText());
        Transaction transaction = new Transaction("withdraw", -amount);
        bankDAO.saveTransaction(transaction);
        updateBalance();
        updateTransactionHistory();
    }

    private void updateBalance() {
        double totalAmount = bankDAO.getTotalAmount();
        balanceLabel.setText(String.format("%.2f €", totalAmount));
        if (totalAmount < 0) {
            balanceLabel.setTextFill(Color.RED);
        } else {
            balanceLabel.setTextFill(Color.GREEN);
        }
    }

    private void updateTransactionHistory() {
        transactionHistory.getItems().clear();
        transactionHistory.getItems().addAll(bankDAO.getAllTransactions());
        transactionHistory.setCellFactory(new Callback<ListView<Transaction>, ListCell<Transaction>>() {
            @Override
            public ListCell<Transaction> call(ListView<Transaction> listView) {
                return new ListCell<Transaction>() {
                    @Override
                    protected void updateItem(Transaction transaction, boolean empty) {
                        super.updateItem(transaction, empty);
                        if (transaction != null) {
                            setText(String.format("%s: %.2f €", transaction.getType(), transaction.getAmount()));
                            if (transaction.getType().equals("deposit")) {
                                setTextFill(Color.GREEN);
                            } else {
                                setTextFill(Color.RED);
                            }
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }
}