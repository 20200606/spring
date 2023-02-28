package com.zhangjiang.base.lock;

/**
 * @className LockTaskRunnable
 * @Author zhangjiang
 * @Description: 同步锁任务运行器
 * @Date 2023/2/10 14:35:56
 */
@FunctionalInterface
public interface LockTaskRunnable {

    /**
     * 运行方法
     * @return
     */
    public abstract String run();
}
