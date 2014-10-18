package se.exsolvi.robot.body;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RobotBody {

  private static final int FREQ = 60;
  private static final int LEFT_ENGINE_IO_PORT = 0;
  private static final int RIGHT_ENGINE_IO_PORT = 1;

  // These values are determined by adjusting the value until the servos are
  // standing still.
  private float LEFT_ZERO = 1.52f;
  private float RIGHT_ZERO = 1.55f;
  private float leftPulseOffset = -0.75f;
  private float rightPulseOffset = 0.75f;
  
  private float currentLeftWheelSpeed = LEFT_ZERO;
  private float currentRightWheelSpeed = RIGHT_ZERO;

  // The singleton instance
  private static RobotBody instance;

  private AdafruitPCA9685 servoBoard;
  private static Logger log;

  // Singleton pattern
  public static RobotBody getInstance() {
    if (instance == null) {
      instance = new RobotBody();
    }
    return instance;
  }

  private RobotBody() {
    log = LoggerFactory.getLogger(this.getClass());
    servoBoard = new AdafruitPCA9685();
    servoBoard.setPWMFreq(FREQ); 
    turnOff();
  }

  public void startForward() {
    log.debug("Starting forward movement");
    currentLeftWheelSpeed += leftPulseOffset;
    currentRightWheelSpeed += rightPulseOffset;
    setSpeed();
  }

  public void stopForward() {
    log.debug("Stopping forward movement");
    currentLeftWheelSpeed -= leftPulseOffset;
    currentRightWheelSpeed -= rightPulseOffset;
    setSpeed();
  }

  public void startReverse() {
    log.debug("Reversing");
    currentLeftWheelSpeed -= leftPulseOffset;
    currentRightWheelSpeed -= rightPulseOffset;
    setSpeed();
  }

  public void stopReverse() {
    log.debug("Stopping reverse");
    currentLeftWheelSpeed += leftPulseOffset;
    currentRightWheelSpeed += rightPulseOffset;
    turnOff();
  }

  public void startTurningRight() {
    log.debug("Turning right");
    currentRightWheelSpeed += rightPulseOffset;
    setSpeed();
  }
  
  public void stopTurningRight() {
    log.debug("Stop turning right");
    currentRightWheelSpeed -= rightPulseOffset;
    setSpeed();
  }

  public void startTurningLeft() {
    log.debug("Turning left");
    currentLeftWheelSpeed += leftPulseOffset;
    setSpeed();
  }

  public void stopTurningLeft() {
    log.debug("Stop turning left");
    currentLeftWheelSpeed -= leftPulseOffset;
    setSpeed();
  }

  public void turnOff() {
    currentLeftWheelSpeed = LEFT_ZERO;
    currentRightWheelSpeed = RIGHT_ZERO;
    servoBoard.setPWM(RIGHT_ENGINE_IO_PORT, 0, 0);
    servoBoard.setPWM(LEFT_ENGINE_IO_PORT, 0, 0);
  }

  private void setSpeed() {
    servoBoard.setServoPulse(LEFT_ENGINE_IO_PORT, currentLeftWheelSpeed);
    servoBoard.setServoPulse(RIGHT_ENGINE_IO_PORT, currentRightWheelSpeed);
  }

  public void setFrequency(int frequency) {
    servoBoard.setPWMFreq(frequency);
  }

  public void setRightIdleSpeed(float idleSpeed) {
    this.RIGHT_ZERO = idleSpeed;
    currentRightWheelSpeed = idleSpeed;
    setSpeed();
  }

  public void setLeftIdleSpeed(float idleSpeed) {
    this.LEFT_ZERO = idleSpeed;
    currentLeftWheelSpeed = idleSpeed;
    setSpeed();
  }

}
