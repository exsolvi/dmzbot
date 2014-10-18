package se.exsolvi.robot.brain.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.exsolvi.robot.body.RobotBody;

import com.codahale.metrics.annotation.Timed;

@Path("/robot")
@Produces(MediaType.APPLICATION_JSON)
public class RobotBrainInterface {

  private static final Logger log = LoggerFactory.getLogger(RobotBrainInterface.class);
  private RobotBody body;

  public RobotBrainInterface() {
    body = RobotBody.getInstance();
    log.info("Bootstrapped RobotBrainInterface");
  }

  @POST
  @Timed
  @Path("direction/forward/start")
  public void startForward() {
    log.debug("Starting forward movement");
    body.startForward();
  }

  @POST
  @Timed
  @Path("direction/forward/stop")
  public void stopForward() {
    log.debug("Stopping forward movement");
    body.stopForward();
  }

  @POST
  @Timed
  @Path("direction/right/start")
  public void startRight() {
    log.debug("Starting right turn");
    body.startTurningRight();
  }

  @POST
  @Timed
  @Path("direction/right/stop")
  public void stopRight() {
    log.debug("Stopping right turn");
    body.stopTurningRight();
  }

  @POST
  @Timed
  @Path("direction/left/start")
  public void startLeft() {
    log.debug("Starting left turn");
    body.startTurningLeft();
  }

  @POST
  @Timed
  @Path("direction/left/stop")
  public void stopLeft() {
    log.debug("Stopping left turn");
    body.stopTurningLeft();
  }

  @POST
  @Timed
  @Path("direction/back/start")
  public void startBack() {
    log.debug("Starting backward movement");
    body.startReverse();
  }

  @POST
  @Timed
  @Path("direction/back/stop")
  public void stopBack() {
    log.debug("Stopping backward movement");
    body.stopReverse();
  }

  @POST
  @Timed
  @Path("stop")
  public void stop() {
    body.turnOff();
    log.debug("Stopping all movement");
  }

  @GET
  @Timed
  @Path("leftidlespeed/{speed}")
  public void setLeftIdleSpeed(@PathParam("speed") float leftSpeed) {
    log.debug("Setting left idle speed to {}", leftSpeed);
    body.setLeftIdleSpeed(leftSpeed);
  }

  @GET
  @Timed
  @Path("rightidlespeed/{speed}")
  public void setRightIdleSpeed(@PathParam("speed") float rightSpeed) {
    log.debug("Setting right idle speed to {}", rightSpeed);
    body.setRightIdleSpeed(rightSpeed);
  }

}