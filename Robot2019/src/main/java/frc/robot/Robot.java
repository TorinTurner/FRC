/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.networktables.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   * 
   */


  Victor driveMotorLeftOne, driveMotorLeftTwo, driveMotorRightOne, driveMotorRightTwo, led;
  Joystick astronautOne, astronautTwo;
  NetworkTable table;
  NetworkTableEntry direction;

  //joystick constants
  int leftXAxis = 0;
  int leftYAxis = 1;
  int leftTrigger = 2;
  int rightTrigger = 3;
  int rightXAxis = 4;
  int rightYAxis = 5; 
  double deadzone = 0.05;
  

  @Override
  public void robotInit() {
    driveMotorLeftOne = new Victor(7);
    driveMotorLeftTwo = new Victor(8);
    driveMotorRightOne = new Victor(0);
    driveMotorRightTwo = new Victor(1);
    led = new Victor(9);
    astronautOne = new Joystick(0);
    astronautTwo = new Joystick(1);
    //table = NetworkTableInstance.getDefault().getTable("Smart Dashboard");
    table = NetworkTableInstance.getDefault().getTable("FMSInfo");
    direction = table.getEntry("dir");
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    direction = table.getEntry("dir");

    if(direction.equals("l"))
    {
      motorSet(0.25, 0.4);
    }
    else if(direction.getValue().equals("r"))
    {
      motorSet(0.4, 0.25);
    }
    else if(direction.getValue().equals("f"))
    {
      motorSet(0.4, 0.4);
    }

    //some real sound like shit down here

  }

  @Override
  public void teleopInit() {
   
  }

  @Override
  public void teleopPeriodic() {
    led.set(1);
    double leftMotorMove = astronautOne.getRawAxis(leftYAxis);
    double rightMotorMove = astronautOne.getRawAxis(rightYAxis);

    if(Math.abs(leftMotorMove) <= deadzone)
    {
      leftMotorMove = 0D;
    }

    if(Math.abs(rightMotorMove) <= deadzone)
    {
      rightMotorMove = 0D;
    }

    motorSet(leftMotorMove, rightMotorMove);

  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  private void motorSet(double leftVal, double rightVal)
  {
    driveMotorLeftOne.set(leftVal * -1);
    driveMotorLeftTwo.set(leftVal * -1);
    driveMotorRightOne.set(rightVal);
    driveMotorRightTwo.set(rightVal);
  }

}
