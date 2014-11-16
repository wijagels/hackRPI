var myMyo = Myo.create();
var box;
myMyo.on('fist', function(edge){
	box = document.getElementById("box");
	//box.innerHTML= "Hello" ;
});
var time = setInterval(function() {})

myMyo.on('gyroscope', function(data){
	box = document.getElementById("box");
	box.innerHTML = data.x;
	sleep(1000);
 });	

function sleep(ms)
	{
		var dt = new Date();
		dt.setTime(dt.getTime() + ms);
		while (new Date().getTime() < dt.getTime());
	}