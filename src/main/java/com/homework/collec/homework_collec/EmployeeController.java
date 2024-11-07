package com.homework.collec.homework_collec;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    // Конструктор для внедрения зависимости
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Метод для добавления сотрудника
    @PostMapping("/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        employeeService.addEmployee(firstName, lastName);
        return new Employee(firstName, lastName); // Возвращаем добавленного сотрудника в формате JSON
    }

    // Метод для удаления сотрудника
    @DeleteMapping("/remove")
    public String removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        employeeService.removeEmployee(firstName, lastName);
        return "Сотрудник " + firstName + " " + lastName + " успешно удален."; // Возвращаем сообщение об успешном удалении
    }

    // Метод для поиска сотрудника
    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName); // Возвращаем найденного сотрудника в формате JSON
    }

    // Метод для получения списка всех сотрудников в формате JSON
    @GetMapping("/list")
    public List<Employee> listEmployees() {
        return employeeService.getEmployees(); // Возвращаем список сотрудников в формате JSON
    }
}
