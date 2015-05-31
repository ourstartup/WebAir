package com.sensorhub.iot.device.rs;

import com.sensorhub.iot.device.domain.DeviceInfo;
import com.sensorhub.iot.device.manager.DeviceManager;
import com.sensorhub.iot.device.support.DeviceSensorWrapper;
import com.sensorhub.iot.user.domain.UserInfo;
import com.sensorhub.iot.user.manager.UserInfoManager;
import com.sensorhub.iot.user.support.UserAccountWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2015/1/15.
 */
@Component
@Path("device")
public class DeviceResource
{
    private static Logger logger = LoggerFactory.getLogger(DeviceResource.class);

    private DeviceManager deviceManager;
    private UserInfoManager userInfoManager;

    @Resource
    public void setDeviceManager(DeviceManager deviceManager)
    {
        this.deviceManager = deviceManager;
    }

    @GET
    @Path("registerWithOutUserName")
    @Produces(MediaType.APPLICATION_JSON)
    public Map register0(@QueryParam("dev_uuid") String dev_uuid,
                          @QueryParam("typeCode") String typeCode,
                          @QueryParam("sensors")List<String> sensors)
    {
        Map map = new HashMap();

        DeviceInfo deviceInfo = deviceManager.registerDeviceWithoutUserName(dev_uuid,
                typeCode, sensors);
        if (deviceInfo == null)
        {
            map.put("success", false);
            map.put("message","设备注册失败");
            return  map;
        }
        DeviceSensorWrapper deviceSensorWrapper = new DeviceSensorWrapper(deviceInfo);
        map.put("success", true);
        map.put("message", deviceSensorWrapper);
        return map;

    }

    @POST
    @Path("regiserWithUserName")
    @Produces(MediaType.APPLICATION_JSON)
    public Map register1(@QueryParam("userName") String userId,
                            @QueryParam("dev_uuids") List<String> uuids) {
        Map map = new HashMap<String, Object>();
       UserInfo userInfo = deviceManager.registerDeviceWithUserName(userId, uuids);
        if (userInfo == null)
        {
            map.put("success", false);
            map.put("message", "注册失败");
            return map;
        }

        map.put("success", true);
        map.put("message", new UserAccountWrapper(userInfo).toMap());
        return map;
    }

    @Resource
    public void setUserInfoManager(UserInfoManager userInfoManager)
    {
        this.userInfoManager = userInfoManager;
    }
}
