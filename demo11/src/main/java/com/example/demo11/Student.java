package com.example.demo11;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private Date dob;
  private String section;
  private String gender;
  private int marks1;
  private int marks2;
  private int marks3;
  private int total;
  private float average;
  private String result;
}
