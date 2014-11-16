
var myMyo = Myo.create();
myMyo.on('gyroscope', function(data){
    document.getElementById("roll").value = data.z;
    document.getElementById("pitch").value = data.y;
    document.getElementById("yaw").value = data.x;
});