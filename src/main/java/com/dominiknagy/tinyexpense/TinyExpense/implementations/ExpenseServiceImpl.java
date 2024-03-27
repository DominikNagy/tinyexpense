package com.dominiknagy.tinyexpense.TinyExpense.implementations;

import com.dominiknagy.tinyexpense.TinyExpense.entities.Expense;
import com.dominiknagy.tinyexpense.TinyExpense.exception.ResourceNotFoundException;
import com.dominiknagy.tinyexpense.TinyExpense.repositories.ExpenseRepository;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateExpenseRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.ExpenseResponse;
import com.dominiknagy.tinyexpense.TinyExpense.services.ExpenseService;
import com.dominiknagy.tinyexpense.TinyExpense.utility.Mapper;
import com.dominiknagy.tinyexpense.TinyExpense.utility.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryServiceImpl categoryService;

    @Override
    public ExpenseResponse createExpense(CreateExpenseRequest createExpenseRequest) {
        Expense expense = Mapper.mapExpenseRequest(
                createExpenseRequest, categoryService.retrieveCategory(createExpenseRequest.getCategoryId()));

        return Mapper.mapExpenseResponse(expenseRepository.save(expense));
    }

    @Override
    public ExpenseResponse retrieveExpense(long expenseId) {
        Expense expense = expenseRepository.findExpenseByIdAndUser(expenseId, UserUtils.authedUser())
                .orElseThrow(() -> new ResourceNotFoundException("Expense could not be found"));
        return Mapper.mapExpenseResponse(expense);
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
    public List<ExpenseResponse> retrieveExpensesInCategory(long categoryId) {
        List<Expense> expenses = expenseRepository.findExpensesByCategoryAndUser(
                categoryService.retrieveCategory(categoryId), UserUtils.authedUser());

        return expenses.stream().map(Mapper::mapExpenseResponse).toList();
    }

    @Override
    public void deleteExpense(long expenseId) {
        expenseRepository.deleteById(expenseId);
    }
}
