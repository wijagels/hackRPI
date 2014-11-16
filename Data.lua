

scriptId = 'com.thalmic.examples.outputeverything'

avgYaw = 0;

function onPoseEdge(pose, edge)
    myo.debug("onPoseEdge: " .. pose .. ", " .. edge)
    if (pose == "fist" and edge == "on")
    	then getAvgYaw(2)

    end
end

function onPeriodic()
	-- local now = myo.getTimeMilliseconds();
	-- myo.debug(myo.getTimeMilliseconds() % 1000)
	--  --if (myo.getTimeMilliseconds() % 1000) == (now % 1000) then myo.debug(myo.getYaw()) end
end

function onForegroundWindowChange(app, title)
    myo.debug("onForegroundWindowChange: " .. app .. ", " .. title)
    local x = 0
  if(app == "com.google.Chrome") then while x < 3 do myo.vibrate("short") x = x + 1  end  end
    return true
end

function activeAppName()
    return "Output Everything"
end

function onActiveChange(isActive)
    myo.debug("onActiveChange")
end


function getAvgYaw(seconds) 
	--Declare 
	local i = 0;
	local yawData = {}
	for i = 1, 10000 do
		yawData[i] = 0
	end

	local time = seconds
	local now = myo.getTimeMilliseconds()
	local times = 0
	while 
		now + (seconds * 1000) >= myo.getTimeMilliseconds()
	do
		times = times + 1
		yawData[times] = myo.getYaw()
	end
		--calculate avg yaw
	myo.debug(yawData)
	-- for i=1, times do avgYaw = yawData[i]
	-- end

	-- avgYaw = avgYaw/times
	-- myo.debug(avgYaw)

end

function sleep(n)
  local t = os.clock()
  while os.clock() - t <= n do
    -- nothing
  end
end