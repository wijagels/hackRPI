scriptId = 'com.thalmic.examples.outputeverything'

function onPoseEdge(pose, edge)
    myo.debug("onPoseEdge: " .. pose .. ", " .. edge)
end

function onPeriodic()
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