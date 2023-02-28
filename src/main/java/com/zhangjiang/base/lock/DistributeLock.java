package com.zhangjiang.base.lock;

/**
 * @className DistributeLock
 * @Author zhangjiang
 * @Description: 分布式锁接口
 * @Date 2023/2/10 14:27:44
 */
public interface DistributeLock {

    /**
     * 锁的实现方式
     * @param lockKeyRunnable
     * @param lockTaskRunnable
     * @return
     */
    LockResultDTO consumerByLock(LockKeyRunnable lockKeyRunnable, LockTaskRunnable lockTaskRunnable);
}
