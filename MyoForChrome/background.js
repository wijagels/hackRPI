var myMyo = Myo.create();
myMyo.on('fist', function(edge){
	chrome.tabs.executeScript(tab.id, {code: 'window.scroll(0, 0);'});
});