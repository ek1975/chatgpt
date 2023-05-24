package aj.sbcrud.chatgpt.controller;

import aj.sbcrud.chatgpt.dao.EmployeeRepository;
import aj.sbcrud.chatgpt.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employee-list";
    }

    @GetMapping("/add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @PostMapping("/add")
    public String addEmployeeSubmit(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employees/";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee id: " + id));
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @PostMapping("/edit/{id}")
    public String editEmployeeSubmit(@PathVariable("id") Long id, @ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employees/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/employees/";
    }
}
