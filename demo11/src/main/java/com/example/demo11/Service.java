package com.example.demo11;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.stereotype.Service
public class Service {

  @Autowired
  private StudentRepository customerRepository;

  public String addStudent(@RequestBody StudentRequestPojo studentRequestPojo) throws MyException {
    Student student = new Student();

    String firstName = studentRequestPojo.getFirstName();
    String lastName = studentRequestPojo.getLastName();
    Date dob = studentRequestPojo.getDob();
    String section = studentRequestPojo.getSection();
    String gender = studentRequestPojo.getGender();
    int marks1 = studentRequestPojo.getMarks1();
    int marks2 = studentRequestPojo.getMarks2();
    int marks3 = studentRequestPojo.getMarks3();

    //   Field Validation
    if (firstName.length() < 3 || lastName.length() < 3) {
      throw new MyException("minimum length of firstName and lastName should be 3 characters");
    }
    if (dob.toString().isEmpty()) {
      throw new MyException("DOB field can't be null!..");
    }

    LocalDate curDate = LocalDate.now();
    LocalDate tempDob = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int age = Period.between(tempDob, curDate).getYears();
    if (age < 15 || age > 20) {
      throw new MyException(
          "Age should be greater than 15 year and less than or equal to 20 years.");
    }
    if ((marks1 < 0 || marks1 > 100) || (marks2 < 0 || marks2 > 100) || (marks3 < 0
        || marks3 > 100)) {
      throw new MyException(
          "Marks range should be 0 to 100.");
    }
    if (!section.equals("A") && !section.equals("B") && !section.equals("C")) {
      throw new MyException(
          "Invalid Section!..");
    }
    if (!gender.equals("M") && !gender.equals("F")) {
      throw new MyException(
          "Invalid Gender !..");
    }

    int total = marks1 + marks3 + marks2;
    float avg = total / 3;
    String result = "";
    if (avg < 35.0) {
      result = "Fail";
    }
    else {
      result = "Pass";
    }

    student.setFirstName(firstName);
    student.setLastName(lastName);
    student.setDob(dob);
    student.setSection(section);
    student.setGender(gender);
    student.setMarks1(marks1);
    student.setMarks2(marks2);
    student.setMarks3(marks3);
    student.setTotal(total);
    student.setAverage(avg);
    student.setResult(result);
    customerRepository.save(student);
    return "Student details has been added successfully!...";
  }

  public Optional<Student> getStudenById(Long cid){
    return customerRepository.findById(cid);
  }

  public List<Student> getAllStudents() {
    return customerRepository.findAll();
  }

  public void getStudenById(long id, int marks1, int marks2, int marks3) throws MyException {
    if ((marks1 < 0 || marks1 > 100) || (marks2 < 0 || marks2 > 100) || (marks3 < 0
        || marks3 > 100)) {
      throw new MyException(
          "Marks range should be 0 to 100.");
    }
    int total = marks1 + marks3 + marks2;
    float avg = total / 3;
    String result = "";
    if (avg < 35.0) {
      result = "Fail";
    }
    else {
      result = "Pass";
    }
    Student student = customerRepository.findById(id).orElse(null);
    student.setMarks3(marks3);
    student.setMarks2(marks2);
    student.setMarks1(marks1);
    student.setTotal(total);
    student.setAverage(avg);
    student.setResult(result);
    customerRepository.save(student);

  }
}
