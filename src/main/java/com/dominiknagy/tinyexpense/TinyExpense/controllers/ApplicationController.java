package com.dominiknagy.tinyexpense.TinyExpense.controllers;

import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateCategoryRequest;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateExpenseRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.GenericResponse;
import com.dominiknagy.tinyexpense.TinyExpense.services.CategoryService;
import com.dominiknagy.tinyexpense.TinyExpense.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ApplicationController {

    private final ExpenseService expenseService;
    private final CategoryService categoryService;

    @PostMapping("/expenses")
    public ResponseEntity<?> createExpense(@RequestBody CreateExpenseRequest createExpenseRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.createExpense(createExpenseRequest));
    }

    @GetMapping("/expenses")
    public ResponseEntity<?> retrieveExpenses() {
        return ResponseEntity.ok(expenseService.retrieveExpenses());
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
    public ResponseEntity<?> createExpenseCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(createCategoryRequest));
    }

    @GetMapping("/categories")
    public ResponseEntity<?> retrieveExpenseCategories() {
        return ResponseEntity.ok(categoryService.retrieveCategories());
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<?> retrieveExpenseCategory(@PathVariable long categoryId) {
        return ResponseEntity.ok(categoryService.retrieveCategoryAsResponse(categoryId));
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<?> deleteExpenseCategory(@PathVariable long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(new GenericResponse("Category deleted."));
    }

    @GetMapping("/categories/{categoryId}/expenses")
    public ResponseEntity<?> retrieveExpensesInCategory(@PathVariable long categoryId) {
        return ResponseEntity.ok(expenseService.retrieveExpensesInCategory(categoryId));
    }
}
