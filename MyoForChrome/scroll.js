var myMyo = Myo.create();
myMyo.on('fist', function(edge){
	console.log('Turning ' + tab.url + ' red!');
	alert("scrollin'!");
	chrome.tabs.executeScript({
		code: 'window.scrollBy(0,50)'
	});
	
});