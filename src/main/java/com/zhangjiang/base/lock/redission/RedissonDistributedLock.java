package com.zhangjiang.base.lock.redission;

import com.zhangjiang.base.lock.DistributeLock;
import com.zhangjiang.base.lock.LockKeyRunnable;
import com.zhangjiang.base.lock.LockResultDTO;
import com.zhangjiang.base.lock.LockTaskRunnable;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * @className RedissonDistributedLock
 * @Author zhangjiang
 * @Description:
 * @Date 2023/2/10 14:41:07
 */
@Component
public class RedissonDistributedLock implements DistributeLock {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String PREFIX = "DistributedLock::";

    @Autowired(required = false)
    RedissonClient redissonClient;

    /**
     *  获取可重入锁
     * @param lockKey
     * @return
     */
     public RLock getLock(String lockKey){
         return redissonClient.getLock(PREFIX + lockKey);
     }

    /**
     * 获取公平锁
     * @param lockKey
     * @return
     */
     public RLock getFairLock(String lockKey){
         return redissonClient.getFairLock(PREFIX + lockKey);
     }

    /**
     * md5加密，减少锁Key的长度
     * @param str
     * @return
     */
     public String md5_Digest(String str){
         logger.info("加密摘要数据：" + str);
         MessageDigest messageDigest = null;
         try {
             messageDigest = MessageDigest.getInstance("MD5");
             messageDigest.update(str.getBytes());
         } catch (NoSuchAlgorithmException e) {
             logger.error("加密摘要数据出错：" + str, e);
             e.printStackTrace();
         }
         byte[] digest = messageDigest.digest();
         StringBuffer stringBuffer = new StringBuffer();
         for (int i = 0; i < digest.length; i++) {
             String temStr = Integer.toHexString(digest[i] & 0xff);
             if (temStr.length() == 1) {
                 stringBuffer.append("0");
             }
             stringBuffer.append(temStr);
         }
         return stringBuffer.toString();
     }


    @Override
    public LockResultDTO consumerByLock(LockKeyRunnable lockKeyRunnable, LockTaskRunnable lockTaskRunnable) {
        RLock rLock = null;
        try {
            String key = lockKeyRunnable.run();
            logger.info("原始Key：" + key);
            String md5Key = md5_Digest(key);
            logger.info("获取MD5加密后的Key" + md5Key);
            rLock = getFairLock(md5Key);
            boolean lockResult = rLock.tryLock(1, 300, TimeUnit.MILLISECONDS);
            logger.info("尝试获取加密锁Key:" + md5Key + "的结果为：" + lockResult);
            if(lockResult){
                return LockResultDTO.success(lockTaskRunnable.run());
            }else {
                return LockResultDTO.process(null);
            }
        } catch (Exception e) {
            logger.error("redission加锁失败");
            return LockResultDTO.fail(null);
        }finally {
            if (rLock != null && rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
    }
}
