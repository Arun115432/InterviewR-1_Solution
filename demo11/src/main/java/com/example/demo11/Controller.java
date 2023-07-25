package com.example.demo11;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class Controller {

  @Autowired
  private Service service;

  @GetMapping(value = "/hi")
  public String sayHi(){
    return "Hello";
  }

  @PostMapping(value ="/student", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> addStudent(@RequestBody StudentRequestPojo studentRequestPojo){
    String response = "";
    try {
      response = service.addStudent(studentRequestPojo);
    }
    catch (Exception ex){
      ex.printStackTrace();
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(response, HttpStatus.OK);

  }

  @GetMapping("")
  public List<Student> getStudents(){
    return service.getAllStudents();
  }

  @GetMapping("/{id}")
  public Optional<Student> getStudentById(long id){
    return service.getStudenById(id);
  }

  @PutMapping("/update")
  public String updateStudent(@RequestParam long id, @RequestParam int mark1,@RequestParam int mark2,@RequestParam int mark3){
    try{
      service.getStudenById(id,mark1,mark2,mark3);
    }catch (Exception ex){
      ex.printStackTrace();
    }
    return "Updated successfully!...";
  }
}
