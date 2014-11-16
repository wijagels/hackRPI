
import java.text.DecimalFormat;

public class DataCollector implements DeviceListener {
    private static final int SCALE = 18;
    private double rollW;
    private double pitchW;
    private double yawW;
    private double maxForce;
    private double maxAccel;
    private final double poundToNewton = 4.44822162;
    private Pose currentPose;
    private Arm whichArm;
    private final double massSmallForearm = 1.1;
    private final double massAvgForearm = 1.4;
    private final double massLargeForearm = 1.6;
    private double forearm;

    public DataCollector() 
    {
        rollW = 0;
        pitchW = 0;
        yawW = 0;
        currentPose = new Pose();
    }

    @Override
    public void onAccelerometerData(Myo myo, long timestamp, Vector3 accelVector) 
    {
        //         double accelX = accelVector.getX();
        //         double accelY = accelVector.getY();
        //         double accelZ = accelVector.getZ();

        int type = 0;
        double trueAccel = accelVector.magnitude()*9.81;

        

        int weight=0;
        if(weight>=215)
        {
            type = 3;
        }
        else if(weight >= 180)
        {
            type = 2;
        }
        else
            type = 1;

        double newForce = getForce(trueAccel,type);

        if(maxForce<newForce)
        {
            maxForce = newForce;
            maxAccel = trueAccel;
        }
        /**
         * Boxers can hit with an average force of 765 lbs
        so, let us take that as an example and see what the bosy can with stand:
        Biomechanical injury tolerance levels:
        Throat- 300 lbs of force
        Frontal bone ( forehaed)- 1900 lbs
        Back of head ( occiptal)- 2100 lbs
        Temporal - 1400 lbs
        Zygomatic-800 lbs
        mandible - 800 lbs
        maxilla - 500 lbs
        Lat. Maxilla - 700 lbs
        "nasal bone"- 200 lbs
        Cervical vertebra - 500 lbs
        Crown of head - 1350 lbs
        area above the ear - 650 lbs
        sternum with 4" defelction ( penetration) - 960 lbs
        ribs - 400 lbs ( 1-3 ribs are the hardest, 4-9 the most common to fracture)
        Draw you own conclusions :)
         */
        if(newForce>=2100*poundToNewton)
        {
            System.out.print("By goly you broke the back of that mans skull!, you can't be human!");
        }
        else if(newForce>=1900*poundToNewton)
        {
            System.out.print("Holy cucumbers you broke the guys forehead! that is something you want to write home about");
        }
        else if(newForce>=1400*poundToNewton)
        {
            System.out.print("Thats enought to crack the mans temporals!");
        }
        else if(newForce>=1350*poundToNewton)
        {
            System.out.print("YouJustBrokeHisDome.jpg");
        }
        else if(newForce>=960*poundToNewton)
        {
            System.out.print("Breaking a Sternum aint that bad!");
        }
        else if(newForce>=800*poundToNewton)
        {
            System.out.print("You either A) broke his jaw, or B) his cheekbone");
        }
        else if(newForce>=500*poundToNewton)
        {
            System.out.print("You broke his upper jaw, just below the nose");
        }
        else if(newForce>=400*poundToNewton)
        {
            System.out.print("Managed to break a few ribs!");
        }
        else
        {
            System.out.print("WeakSauce.bmp");
        }
        
        //         Quaternion normalized = rotation.normalized();
        // 
        //         double roll = Math.atan2(2.0f * (normalized.getW() * normalized.getX() + normalized.getY() * normalized.getZ()), 1.0f - 2.0f * (normalized.getX() * normalized.getX() + normalized.getY() * normalized.getY()));
        //         double pitch = Math.asin(2.0f * (normalized.getW() * normalized.getY() - normalized.getZ() * normalized.getX()));
        //         double yaw = Math.atan2(2.0f * (normalized.getW() * normalized.getZ() + normalized.getX() * normalized.getY()), 1.0f - 2.0f * (normalized.getY() * normalized.getY() + normalized.getZ() * normalized.getZ()));
        // 
        //         rollW = ((roll + Math.PI) / (Math.PI * 2.0) * SCALE);
        //         pitchW = ((pitch + Math.PI / 2.0) / Math.PI * SCALE);
        //         yawW = ((yaw + Math.PI) / (Math.PI * 2.0) * SCALE);
    }

    @Override
    public void onPose(Myo myo, long timestamp, Pose pose) 
    {
        currentPose = pose;
        if (currentPose.getType() == PoseType.FIST) {
            myo.vibrate(VibrationType.VIBRATION_MEDIUM);
        }
    }

    @Override
    public void onArmRecognized(Myo myo, long timestamp, Arm arm, XDirection xDirection)
    {
        whichArm = arm;
    }

    @Override
    public void onArmLost(Myo myo, long timestamp) 
    {
        whichArm = null;
    }

    @Override
    public void onOrientationData(Myo myo, long timestamp, Quaternion bullshit) {
    }

    @Override
    public void onConnect(Myo myo, long timestamp, FirmwareVersion firmwareVersion) {
    }

    @Override
    public void onDisconnect(Myo myo, long timestamp) {
    }

    @Override
    public void onPair(Myo myo, long timestamp, FirmwareVersion firmwareVersion) {
    }

    @Override
    public void onUnpair(Myo myo, long timestamp) {
    }

    @Override
    public void onGyroscopeData(Myo myo, long timestamp, Vector3 gyro) {
    }

    @Override
    public void onRssi(Myo myo, long timestamp, int rssi) {
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\r");

        //String xDisplay = String.format("[%s%s]", repeatCharacter('*', (int) rollW), repeatCharacter(' ', (int) (SCALE - rollW)));
        //String yDisplay = String.format("[%s%s]", repeatCharacter('*', (int) pitchW), repeatCharacter(' ', (int) (SCALE - pitchW)));
        //String zDisplay = String.format("[%s%s]", repeatCharacter('*', (int) yawW), repeatCharacter(' ', (int) (SCALE - yawW)));

        DecimalFormat hun = new DecimalFormat("0.00N");
        String fDisplay = "Max Force = " + hun.format(maxForce);

        builder.append(fDisplay);
        //         String poseString = null;
        //         if (whichArm != null) {
        //             String poseTypeString = currentPose.getType()
        //                 .toString();
        //             poseString = String.format("[%s][%s%" + (SCALE - poseTypeString.length()) + "s]", whichArm == Arm.ARM_LEFT ? "L" : "R", poseTypeString, " ");
        //         } else {
        //             poseString = String.format("[?][%14s]", " ");
        //         }
        // 
        //         builder.append(poseString);
        return builder.toString();
    }

    private String repeatCharacter(char character, int numOfTimes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numOfTimes; i++) {
            builder.append(character);
        }
        return builder.toString();
    }

    private double getForce(double trueAcceleration, int type)
    {
        switch(type)
        {
            case 1:
            forearm = massSmallForearm;
            break;
            case 2:
            forearm = massAvgForearm;
            break;
            case 3:
            forearm = massLargeForearm;
            break;
        }

        return forearm*trueAcceleration;
    }
}