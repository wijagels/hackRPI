scriptId = 'com.thalmic.examples.outputeverything'
isChrome = false

function onPoseEdge(pose, edge)
	if isChrome then
		myo.debug("onPoseEdge: " .. pose .. ", " .. edge)
		if pose == "fist" and edge == "on" and myo.getPitch() < -0.6 then
			myo.keyboard("pageup", "press")
			myo.debug(myo.getPitch())
		elseif pose == "fist" and edge == "on" and myo.getPitch() > 0.6 then
			myo.keyboard("pagedown", "press")
			myo.debug(myo.getPitch())
		end
	end
end

function onPeriodic()
end

function onForegroundWindowChange(app, title)
    myo.debug("onForegroundWindowChange: " .. app .. ", " .. title)
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