package com.zhangjiang.base.lock;

/**
 * @className LockKeyRunnable
 * @Author zhangjiang
 * @Description: 同步锁主键生成器
 * @Date 2023/2/10 14:35:02
 */
@FunctionalInterface
public interface LockKeyRunnable {

    /**
     * 运行方法
     * @return
     */
    public abstract String run();
}
