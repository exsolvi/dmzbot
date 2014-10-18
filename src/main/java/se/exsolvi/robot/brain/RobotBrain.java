package se.exsolvi.robot.brain;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.exsolvi.robot.body.RobotBody;
import se.exsolvi.robot.brain.api.RobotBrainInterface;

public class RobotBrain extends Application<RobotBrainconfiguration> {

  private Logger log = LoggerFactory.getLogger(RobotBrain.class);
  
  public static void main(String[] args) throws Exception {
    new RobotBrain().run(args);
  }
 
  @Override
  public void initialize(Bootstrap<RobotBrainconfiguration> bootstrap) {
    bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
    bootstrap.addBundle(new AssetsBundle("/assets/html", "/gui", "index.html", "html"));
    RobotBody body = RobotBody.getInstance();
    body.turnOff();
    log.info("Robotbrain initialized");
  }
  
  @Override
  public void run(RobotBrainconfiguration configuration, Environment environment) {
    RobotBrainInterface restApi = new RobotBrainInterface();
    environment.jersey().register(restApi);
  }

}