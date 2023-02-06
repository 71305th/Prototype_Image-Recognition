# Apriltag

# v2.1.2 Adjust Github Settings

Commiter : **Moyu**

## Features 

1. Repo Name : `Apriltag` -> `Imagine`
2. Branch Name : `ap1` -> `apriltag`

# v2.1.1 Adjust & Optimize Code

# v2.1 ApriltagCommand & ApriltagSubsystem 

Commiter : **AngelShih**

## ApriltagCommand
using getBestCameraToTarget() to know the 3d transform of the camera to target and using PID to define the speed of the robot.
 > <font style="color: blue">**return definition of the getBestCameraToTarget() **</font>
        > (x,y,z) = (forward, left , up)

## ApriltagSubsystem
method added:
- snapshot()
- getCameratoTaget()
- getTargetID()
- getYaw()
- getSkew()
- getPitch()
- hasTarget()
- getPoseAmbiguity()

# v1.2.3 ~ v1.2.4 Integration

Commiter : **Moyu**

# v1.2.1 Add Useful Thing In README.md

Commiter : **FUNDAI**

## Features

1. Tuning Config files for photon vision
    - The data is the best for my computer , you can have some small adjust according to your computer , but still can use it as the example
    - How to use this file
        1. Download the tuning example.zip [tuning example.zip](https://github.com/FutureShock-2022-23-5th/Apriltag/files/10564513/tuning.example.zip)
        2. Unzip it and drag the json file to the ...\photonvision_config\cameras\WEB_CAM\pipelines
            - Please note that you need to run the photon vision jar file and plug in the webcam we use , so the config file can be generate .
        3. Then you can see the tunung example in the pipeline
2. Calibrations image for the cam
    - These config is only for the resolution of 320 * 240
    - How to use this 
        1. Download the zip [calibImgs.zip](https://github.com/FutureShock-2022-23-5th/Apriltag/files/10564604/calibImgs.zip)
        2. Unzip it and drag the whole folder to the ...\photonvision_config
        3. Open the ...\photonvision_config\cameras\WEB_CAM\config.json
        4. Copy this to the "calibrations" :[ **here** ] in the json file
        ```json
            {
                "resolution" : {
                "width" : 320.0,
                "height" : 240.0
            },
            "cameraIntrinsics" : {
                "rows" : 3,
                "cols" : 3,
                "type" : 6,
                "data" : [ 325.4782289990506, 0.0, 161.16227748156365, 0.0, 324.88650396794645, 126.84535212023526, 0.0, 0.0, 1.0 ]
            },
            "cameraExtrinsics" : {
                "rows" : 1,
                "cols" : 5,
                "type" : 6,
                "data" : [ -0.42743921214881714, 0.18577548823718337, -0.0019772787780521733, -6.147030353048858E-4, 0.15962711883283534 ]
            },
            "perViewErrors" : [ 0.16507820914949986, 0.1791804853539527, 0.3501084526252324, 0.060278947060275916, 0.11327293587756991, 0.10125361580613283, 0.10907778732478748, 0.08805826464716052, 0.11764671960915368, 0.15328027228464564, 0.15372513382862976, 0.0958581539721597, 0.09129136311272125, 0.056747500430105235, 0.2922282467441044, 0.18689829311976291 ],
            "standardDeviation" : 0.07733126413720712
            } 
        ```
3. Quick way to open photon vision
    - How to use it
        1. Create a txt file in the folder where your photon jar located
            - Please note that you need to see the filename extension (like .zip .txt .jpg...) , if not please open it.
        2. Rename the file to -> the_name_you_want.bat
            - Please note that you need to deleate the .txt when you are renaming , and the picture of the file change to the gear or check if it change the file to "window 批次檔"
        3. Right click the file , click edit and paste the code you start the photon vision (java - jar C:\.....\"".jar)
        4. Then create a shortcut to desktop and click the shortcut you can see the terminal running

# v1.2.0 Add Variables

# v1.1 Add Function Remarks

Commiter : **Moyu**

## Features

1. Remark Details
    - Most of the Funciton and Something have their comments
        1. Changed data includes
            - ApriltagSubsystem.java
            - Consiants.java
        2. Every comment contains 
            - `Since`
            - `Returns`
            - Some comments have `ApiNote`

# v1.0 Complete Most Function in ApriltagSubsystem

Commiter : **Moyu**

## Features

### Getting Target Data

1. Declare Webcam

```java
// Change this to match the name of your camera
PhotonCamera camera = new PhotonCamera("photonvision");
```

**PhotonCamera** is a class in PhotonLib that **allows a user to interact with one camera** that is **connected to hardware** that is **running Photon Vision**. Through this class, **users can retrieve yaw, pitch, roll, robot-relative pose, latency, and a wealth of other information**.

The **PhotonCamera** class has **two constructors**: one that takes a **NetworkTable** and another that **takes in the name of the network table that PhotonVision is broadcasting information over**. For ease of use, it is **recommended to use the latter**. **The name of the NetworkTable** (for the string constructor) **should be the same as the camera’s nickname** (from the PhotonVision UI).

> **Notice**
>  In theory, I should name the camera a special name, but since I'm just starting to learn it, I'll use the `webcam` name first.

2. Getting the Pipeline Result

```java
// Query the latest result from PhotonVision
PhotonPipelineResult wemcam_result = webcam.getLatestResult();
```

A **PhotonPipelineResult** is a container that contains all information about currently detected targets from a PhotonCamera. You can retrieve the latest pipeline result using the PhotonCamera instance.

Use the **```getLatestResult()/GetLatestResult()```** (Java and C++ respectively) to **obtain the latest pipeline result**. An advantage of using this method is that **it returns a container with information that is guaranteed to be from the same timestamp**. This is important if you are using this data for latency compensation or in an estimator.

### Function in ApriltagSubsystem <font style="color: red">**(VERY IMPORTANT)**</font>
    
- `boolean hasTargets()`

    **RETURN** if the result contains any targets.

    > <font style="color: red">**Warning**</font>
    > You must **always check if the result has a target** via `hasTargets()` before getting targets or else you may get a null pointer exception. Further, you must use the same result in every subsequent call in that loop.

- `List getApriltagTargeList()`

    **RETURN** a List includes **yaw**, **pitch**, **area**, and **robot relative pose**.

- `PhotonTrackedTarget getBestApriltahTarget()`

    **RETURN** the best target. Here is the [declaration](https://docs.photonvision.org/en/latest/docs/getting-started/pipeline-tuning/reflectiveAndShape/contour-filtering.html#contour-grouping-and-sorting) of bestTarget from PhotonVision

- Getting Data From A Target

    - `List<PhotonTrackedTarget> getApriltagTargeList( String best )`

        **RETURN** A **tracked target** contains information about each target from a pipeline result. This information includes **yaw, pitch, area, and robot relative pose**.

        > <font style="color: blue">**Notice**</font>
        > You can put an string while you called it. This string used to distinguish what kind of result you want. If you want the BEST target, put `"True"` into funtion. Otherwise, put any string which dosen't match `"True"`, here recommended to use `"False"`. Once you put "True" into the function, it will return **BEST** Target to you. Otherwise, it will return the first Target <font style="color: orange">( Because I haven't figured out how to handle detecting many Apriltag at the same time) <- **This problem needs to be resolved as soon as possible**</font>

    - `double getTargetYaw( String best )`

        **RETURN** The yaw of the target in degrees ( positive right ).

        > <font style="color: blue">**Notice**</font>
        > You can put an string while you called it. This string used to distinguish what kind of result you want. If you want the BEST target, put `"True"` into funtion. Otherwise, put any string which dosen't match `"True"`, here recommended to use `"False"`.

    - `double getTargetPitch( String best )`
        
        **RETURN** The pitch of the target in degrees ( positive up ).

        > <font style="color: blue">**Notice**</font>
        > You can put an string while you called it. This string used to distinguish what kind of result you want. If you want the BEST target, put `"True"` into funtion. Otherwise, put any string which dosen't match `"True"`, here recommended to use `"False"`.

    - `double getTargetArea( String best )`
        
        **RETURN** The area ( how much of the camera feed the bounding box takes up ) as a percent ( 0 - 100 ).

        > <font style="color: blue">**Notice**</font>
        > You can put an string while you called it. This string used to distinguish what kind of result you want. If you want the BEST target, put `"True"` into funtion. Otherwise, put any string which dosen't match `"True"`, here recommended to use `"False"`

    - `double getTargetSkew( String best )`
        
        **RETURN** The skew of the target in degrees (counter-clockwise positive).

        > <font style="color: blue">**Notice**</font>
        > You can put an string while you called it. This string used to distinguish what kind of result you want. If you want the BEST target, put `"True"` into funtion. Otherwise, put any string which dosen't match `"True"`, here recommended to use `"False"`

    - `List<TargetCorner> getTargetCorner( String best )`

        **RETURN** the 4 corners of the minimum bounding box rectangle.

        > <font style="color: blue">**Notice**</font>
        > You can put an string while you called it. This string used to distinguish what kind of result you want. If you want the BEST target, put `"True"` into funtion. Otherwise, put any string which dosen't match `"True"`, here recommended to use `"False"`

    - `Transform3d getCameraToTarget( String best )`

        **RETURN** The camera to target transform.

        > <font style="color: blue">**Notice**</font>
        > You can put an string while you called it. This string used to distinguish what kind of result you want. If you want the BEST target, put `"True"` into funtion. Otherwise, put any string which dosen't match `"True"`, here recommended to use `"False"`

# 2023.01.26 Initialize

Commiter : **Moyu**

## Features

### Setup 

- Finish DriveSubsystem.java

### Constants

- Use `public static final class DriveConstants` to classify constants
- Classification
    - DriveConstants
        - Four Direction `MotorID`
    - JoystickConstants
        - All of the `Button`, `Trigger`, and `Axis`


