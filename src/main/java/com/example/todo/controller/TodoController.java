package com.example.todo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todo.entity.TodoData;
import com.example.todo.service.TodoService;

@Controller
public class TodoController {
	@Autowired
	TodoService service = new TodoService();
	
	@GetMapping("/")
	public String index(Model model) {
		List<TodoData> list = this.service.list();
		model.addAttribute("size", list.size());
		model.addAttribute("todolist", list);
		return "todo";
	}
	
	@PostMapping("/entry")
	public String entry(@Validated @ModelAttribute TodoData data, BindingResult result, Model model) {
		if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            List<TodoData> list = this.service.list();
    		model.addAttribute("todolist", list);
            return "todo";
        }
		this.service.entry(data);
		return "redirect:/";
	}
	
	@PostMapping("/update")
	public String update(TodoData data, Model model) {
		this.service.update(data);
		return "redirect:/";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(name = "id", defaultValue = "0", required = false)long id) {
		this.service.deleteById(id);
		return "redirect:/";
	}
}
