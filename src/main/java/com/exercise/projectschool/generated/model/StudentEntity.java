package com.exercise.projectschool.generated.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * StudentEntity
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class StudentEntity   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("city")
  private String city;

  @JsonProperty("age")
  private String age;

  @JsonProperty("school")
  private String school;

  @JsonProperty("serialNumber")
  private String serialNumber;

  public StudentEntity id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(example = "1", value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public StudentEntity name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(example = "John Doe", value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public StudentEntity city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Get city
   * @return city
  */
  @ApiModelProperty(example = "New York", value = "")


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public StudentEntity age(String age) {
    this.age = age;
    return this;
  }

  /**
   * Get age
   * @return age
  */
  @ApiModelProperty(example = "25", value = "")


  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public StudentEntity school(String school) {
    this.school = school;
    return this;
  }

  /**
   * Get school
   * @return school
  */
  @ApiModelProperty(example = "XYZ High School", value = "")


  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public StudentEntity serialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
    return this;
  }

  /**
   * Get serialNumber
   * @return serialNumber
  */
  @ApiModelProperty(example = "ABC123", value = "")


  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentEntity studentEntity = (StudentEntity) o;
    return Objects.equals(this.id, studentEntity.id) &&
        Objects.equals(this.name, studentEntity.name) &&
        Objects.equals(this.city, studentEntity.city) &&
        Objects.equals(this.age, studentEntity.age) &&
        Objects.equals(this.school, studentEntity.school) &&
        Objects.equals(this.serialNumber, studentEntity.serialNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, city, age, school, serialNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StudentEntity {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    school: ").append(toIndentedString(school)).append("\n");
    sb.append("    serialNumber: ").append(toIndentedString(serialNumber)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

