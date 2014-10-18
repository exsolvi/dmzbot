package se.exsolvi.robot.brain;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class RobotBrainconfiguration extends Configuration {

  @NotEmpty
  private String robotName = "Brainiac";



  @JsonProperty
  public String getRobotName() {
    return robotName;
  }

  @JsonProperty
  public void setRobotName(String name) {
    this.robotName = name;
  }
}