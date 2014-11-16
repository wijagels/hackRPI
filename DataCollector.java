
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.FirmwareVersion;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.Vector3;
import com.thalmic.myo.enums.Arm;
import com.thalmic.myo.enums.PoseType;
import com.thalmic.myo.enums.VibrationType;
import com.thalmic.myo.enums.XDirection;

public class DataCollector implements DeviceListener {
    private static final int SCALE = 18;
    private double rollW;
    private double pitchW;
    private double yawW;
    private double maxForce;
    private double maxAccel
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

        int type = 1;
        double trueAccel = accelVector.magnitude();
        double newForce = getForce(trueAccel,type);

        if(maxForce<newForce)
        {
            maxForce = newForce;
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
    public void onAccelerometerData(Myo myo, long timestamp, Vector3 accel) {
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
        
        String poseString = null;
        if (whichArm != null) {
            String poseTypeString = currentPose.getType()
                .toString();
            poseString = String.format("[%s][%s%" + (SCALE - poseTypeString.length()) + "s]", whichArm == Arm.ARM_LEFT ? "L" : "R", poseTypeString, " ");
        } else {
            poseString = String.format("[?][%14s]", " ");
        }

        builder.append(xDisplay);
        builder.append(yDisplay);
        builder.append(zDisplay);
        builder.append(poseString);
        return builder.toString();
    }

    private String repeatCharacter(char character, int numOfTimes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numOfTimes; i++) {
            builder.append(character);
        }
        return builder.toString();
    }

    private getForce(double trueAcceleration, int type)
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
    }
}