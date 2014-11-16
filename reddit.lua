scriptId = 'com.thalmic.examples.outputeverything'
isChrome = false

function onPoseEdge(pose, edge)
	if isChrome then
		myo.debug("onPoseEdge: " .. pose .. ", " .. edge)
		if pose == "fist" and edge == "on" and myo.getPitch() < -0.6 then
			myo.keyboard("k", "press")
		elseif pose == "fist" and edge == "on" and myo.getPitch() > 0.6 then
			myo.keyboard("j", "press")
		elseif pose == "waveIn" and edge == "on" then
			if myo.getPitch() > 0.6 then
				myo.keyboard("j", "press")
			elseif myo.getPitch() < -0.6 then
				myo.keyboard("k", "press")
			else
				myo.keyboard("x", "press")
			end
		elseif pose == "fingersSpread" and edge == "on" then
			if myo.getPitch() < -0.6 then
				myo.keyboard("a", "press")
			elseif myo.getPitch() > 0.6 then
				myo.keyboard("z", "press")
			else
				myo.keyboard("return", "press", "shift")
			end
		end
	end
end

function onPeriodic()
end

function onForegroundWindowChange(app, title)
    local x = 0
	if(title:match("Google Chrome")) then
		myo.vibrate("short") x = x + 1  
		isChrome = true
		myo.debug("it's chrome!")
	else
		isChrome = false
	end
	return true
end

function activeAppName()
    return "Output Everything"
end

function onActiveChange(isActive)
    myo.debug("onActiveChange")
end