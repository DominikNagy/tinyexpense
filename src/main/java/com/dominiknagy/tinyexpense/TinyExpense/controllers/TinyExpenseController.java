package com.dominiknagy.tinyexpense.TinyExpense.controllers;

import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateCategoryRequest;
import com.dominiknagy.tinyexpense.TinyExpense.requests.CreateExpenseRequest;
import com.dominiknagy.tinyexpense.TinyExpense.responses.GenericResponse;
import com.dominiknagy.tinyexpense.TinyExpense.services.CategoryService;
import com.dominiknagy.tinyexpense.TinyExpense.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TinyExpenseController {

    private final ExpenseService expenseService;
    private final CategoryService categoryService;


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
            @RequestBody CreateCategoryRequest createCategoryRequest,
            @RequestParam String accountId) {
        return ResponseEntity
                .status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
                .body(categoryService.createExpenseCategory(createCategoryRequest));
    }

    @GetMapping("/categories")
    public ResponseEntity<?> retrieveExpenseCategories(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userEmail = userDetails.getUsername(); // Assuming email is stored as username in UserDetails
            System.out.println(userEmail);
            // Use userEmail to retrieve categories from the database
            categoryService.retrieveCategories(); // Adjust this according to your service method
        }

        return ResponseEntity.ok(null);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<?> retrieveExpenseCategory(@PathVariable long categoryId) {
        return ResponseEntity.ok(categoryService.retrieveCategory(categoryId));
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
