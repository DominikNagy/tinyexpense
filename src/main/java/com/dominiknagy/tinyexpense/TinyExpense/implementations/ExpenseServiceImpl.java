package com.dominiknagy.tinyexpense.TinyExpense.implementations;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Expense;
import com.dominiknagy.tinyexpense.TinyExpense.repositories.ExpenseRepository;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateExpenseRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.ExpenseResponse;
import com.dominiknagy.tinyexpense.TinyExpense.services.ExpenseService;
import com.dominiknagy.tinyexpense.TinyExpense.utility.Mapper;
import com.dominiknagy.tinyexpense.TinyExpense.utility.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryServiceImpl expenseCategoryService;

    @Override
    public Expense createExpense(CreateExpenseRequest createExpenseRequest) {
        Expense expense = new Expense();
        expense.setCategory(expenseCategoryService.retrieveCategory(createExpenseRequest.getCategoryId()));
        expense.setUser(UserUtils.authedUser());
        expense.setExpenseDescription(createExpenseRequest.getExpenseDescription());
        expense.setAmount(createExpenseRequest.getAmount());
        expense.setColor(createExpenseRequest.getColor());
        expense.setCurrency(createExpenseRequest.getCurrency());
        expense.setDateTime(createExpenseRequest.getDateTime());

        return expenseRepository.save(expense);
    }

    @Override
    public Expense retrieveExpense(long expenseId) {
        return expenseRepository.findExpenseById(expenseId).orElse(null);
    }

    @Override
    public List<ExpenseResponse> retrieveExpenses() {
        List<Expense> expenses = expenseRepository.findExpensesByUser(UserUtils.authedUser());
        List<ExpenseResponse> expenseResponses = new ArrayList<>();

        for (Expense expense : expenses) {
            expenseResponses.add(Mapper.mapExpenseResponse(expense));
        }

        return expenseResponses;
    }

    @Override
    public List<Expense> retrieveExpensesInCategory(long categoryId) {
        return expenseRepository.findExpensesByCategory(expenseCategoryService.retrieveCategory(categoryId));
    }

    @Override
    public void deleteExpense(long expenseId) {
        expenseRepository.deleteById(expenseId);
    }
}
