package com.dominiknagy.tinyexpense.TinyExpense.controllers;

import com.dominiknagy.tinyexpense.TinyExpense.implementations.ExpenseCategoryServiceImpl;
import com.dominiknagy.tinyexpense.TinyExpense.implementations.ExpenseServiceImpl;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateExpenseCategoryRequest;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateExpenseRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TinyExpenseController {

    private final ExpenseServiceImpl expenseService;
    private final ExpenseCategoryServiceImpl expenseCategoryService;


    @PostMapping("/expenses")
    public ResponseEntity<?> createExpense(
            @RequestBody CreateExpenseRequest createExpenseRequest, @RequestParam String accountId) {
        return ResponseEntity
                .status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
                .body(expenseService.createExpense(createExpenseRequest, accountId));
    }

    @GetMapping("/expenses")
    public ResponseEntity<?> retrieveExpenses(@RequestParam String accountId) {
        return ResponseEntity.ok(expenseService.retrieveExpenses(accountId));
    }

    @GetMapping("/expenses/{expenseId}")
    public ResponseEntity<?> retrieveExpense(@PathVariable long expenseId) {
        return ResponseEntity.ok(expenseService.retrieveExpense(expenseId));
    }

    @DeleteMapping("/expenses/{expenseId}")
    public ResponseEntity<?> deleteExpense(@PathVariable long expenseId) {
        expenseService.deleteExpense(expenseId);
        return ResponseEntity.ok(new GenericResponse("Expense deleted"));
    }

    @PostMapping("/categories")
    public ResponseEntity<?> createExpenseCategory(
            @RequestBody CreateExpenseCategoryRequest createExpenseCategoryRequest,
            @RequestParam String accountId) {
        return ResponseEntity
                .status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
                .body(expenseCategoryService.createExpenseCategory(createExpenseCategoryRequest, accountId));
    }

    @GetMapping("/categories")
    public ResponseEntity<?> retrieveExpenseCategories(@RequestParam String accountId) {
        return ResponseEntity.ok(expenseCategoryService.retrieveExpenseCategories(accountId));
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<?> retrieveExpenseCategory(@PathVariable long categoryId) {
        return ResponseEntity.ok(expenseCategoryService.retrieveExpenseCategory(categoryId));
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<?> deleteExpenseCategory(@PathVariable long categoryId) {
        expenseCategoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(new GenericResponse("Category deleted."));
    }

    @GetMapping("/categories/{categoryId}/expenses")
    public ResponseEntity<?> retrieveExpensesInCategory(@PathVariable long categoryId) {
        return ResponseEntity.ok(expenseService.retrieveExpensesInCategory(categoryId));
    }
}
