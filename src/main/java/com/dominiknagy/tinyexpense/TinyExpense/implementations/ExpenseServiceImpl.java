package com.dominiknagy.tinyexpense.TinyExpense.implementations;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Expense;
import com.dominiknagy.tinyexpense.TinyExpense.repositories.ExpenseRepository;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateExpenseRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.ExpenseResponse;
import com.dominiknagy.tinyexpense.TinyExpense.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserServiceImpl accountService;
    private final ExpenseCategoryServiceImpl expenseCategoryService;

    @Override
    public Expense createExpense(CreateExpenseRequest createExpenseRequest, String accountId) {
        Expense expense = new Expense();
        expense.setExpenseCategory(expenseCategoryService.retrieveExpenseCategory(createExpenseRequest.getExpenseCategoryId()));
        expense.setUser(accountService.retrieveUser(accountId));
        expense.setExpenseDescription(createExpenseRequest.getExpenseDescription());
        expense.setAmount(createExpenseRequest.getAmount());
        expense.setColor(createExpenseRequest.getColor());
        expense.setCurrency(createExpenseRequest.getCurrency());
        expense.setDate(createExpenseRequest.getDate());

        return expenseRepository.save(expense);
    }

    @Override
    public Expense retrieveExpense(long expenseId) {
        return expenseRepository.findExpenseById(expenseId).orElse(null);
    }

    @Override
    public List<ExpenseResponse> retrieveExpenses(String accountId) {
        List<Expense> expenses = expenseRepository.findExpensesByUser(accountService.retrieveUser(accountId));
        List<ExpenseResponse> expenseResponses = new ArrayList<>();

        for (Expense expense : expenses) {
            ExpenseResponse expenseResponse = new ExpenseResponse();
            expenseResponse.setId(expense.getId());
            expenseResponse.setExpenseDescription(expense.getExpenseDescription());
            expenseResponse.setDate(expense.getDate());
            expenseResponse.setCurrency(expense.getCurrency());
            expenseResponse.setAmount(expense.getAmount());
            expenseResponse.setColor(expense.getColor());
            expenseResponse.setCategoryName(expense.getExpenseCategory().getCategoryName());
            expenseResponse.setCategoryColor(expense.getExpenseCategory().getColor());

            expenseResponses.add(expenseResponse);
        }

        return expenseResponses;
    }

    @Override
    public List<Expense> retrieveExpensesInCategory(long categoryId) {
        return expenseRepository.findExpensesByExpenseCategory(expenseCategoryService.retrieveExpenseCategory(categoryId));
    }

    @Override
    public void deleteExpense(long expenseId) {
        expenseRepository.deleteById(expenseId);
    }
}
