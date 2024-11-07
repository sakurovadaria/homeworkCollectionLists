package com.homework.collec.homework_collec;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private static final int MAX_EMPLOYEES = 100; // Максимальное количество сотрудников

    private final List<Employee> employees = new ArrayList<>(); // Коллекция сотрудников

    // Метод для добавления сотрудника
    public void addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Достигнуто максимальное количество сотрудников: " + MAX_EMPLOYEES);
        }
        // Проверка на существование сотрудника
        if (employees.stream().anyMatch(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))) {
            throw new EmployeeAlreadyAddedException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " уже добавлен.");
        }
        employees.add(new Employee(firstName, lastName)); // Добавляем нового сотрудника
    }

    // Метод для удаления сотрудника
    public void removeEmployee(String firstName, String lastName) {
        Optional<Employee> employeeToRemove = employees.stream()
                .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))
                .findFirst();

        if (employeeToRemove.isPresent()) {
            employees.remove(employeeToRemove.get()); // Удаляем сотрудника
        } else {
            throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " не найден."); // Выбрасываем исключение, если не найден
        }
    }

    // Метод для поиска сотрудника
    public Employee findEmployee(String firstName, String lastName) {
        return employees.stream()
                .filter(e -> e.getFirstName().equals(firstName) && e.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " не найден.")); // Выбрасываем исключение, если не найден
    }

    // Метод для получения списка всех сотрудников
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees); // Возвращаем копию списка сотрудников
    }
}