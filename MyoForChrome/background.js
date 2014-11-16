var myMyo = Myo.create();
myMyo.on('fist', function(edge){
    //Edge is true if it's the start of the pose, false if it's the end of the pose
    if(edge){
        alert("CRUSHIN'!");
    }
});