package com.fxpcxt.context;

import java.io.Serializable;

import com.fxpcxt.common.UserStatus;
import com.fxpcxt.entity.User;

public interface CoreContext extends Serializable {
	/**
     * 获取JWT User对象
     * @return
     */
    User getUser();
    
    /**
     * 获取当前token信息
     * @return
     */
    String getToken();
    
    /**
     * 获取登录状态信息
     * @return
     */
    UserStatus getStatus();
}
