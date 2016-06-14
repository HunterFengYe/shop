package shop.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import shop.entity.User;

public class RedisDaoImpl extends AbstractBaseRedisDao implements RedisDao {

	 /**  
     * ���� 
     *<br>------------------------------<br> 
     * @param user 
     * @return 
     */  
    @SuppressWarnings("unchecked")
	public boolean add(final User user) {  
        boolean result = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key  = serializer.serialize(user.getUserId());  
                byte[] name = serializer.serialize(user.getAcount());  
                return connection.setNX(key, name);  
            }  
        });  
        return result;  
    }  
      
    /** 
     * �������� ʹ��pipeline��ʽ   
     *<br>------------------------------<br> 
     *@param list 
     *@return 
     */  
    @SuppressWarnings("unchecked")
	public boolean add(final List<User> list) {  
        Assert.notEmpty(list);  
        boolean result = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                for (User user : list) {  
                    byte[] key  = serializer.serialize(user.getUserId());  
                    byte[] name = serializer.serialize(user.getAcount());  
                    connection.setNX(key, name);  
                }  
                return true;  
            }  
        }, false, true);  
        return result;  
    }  
      
    /**  
     * ɾ�� 
     * <br>------------------------------<br> 
     * @param key 
     */  
    public void delete(String key) {  
        List<String> list = new ArrayList<String>();  
        list.add(key);  
        delete(list);  
    }  
  
    /** 
     * ɾ����� 
     * <br>------------------------------<br> 
     * @param keys 
     */  
    @SuppressWarnings("unchecked")
	public void delete(List<String> keys) {  
        redisTemplate.delete(keys);  
    }  
  
    /** 
     * �޸�  
     * <br>------------------------------<br> 
     * @param user 
     * @return  
     */  
    @SuppressWarnings("unchecked")
	public boolean update(final User user) {  
        String key = user.getUserId();  
        if (get(key) == null) {  
            throw new NullPointerException("�����в�����, key = " + key);  
        }  
		boolean result =(Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key  = serializer.serialize(user.getUserId());  
                byte[] name = serializer.serialize(user.getAcount());  
                connection.set(key, name);  
                return true;  
            }  
        });  
        return result;  
    }  
  
    /**  
     * ͨ��key��ȡ 
     * <br>------------------------------<br> 
     * @param keyId 
     * @return 
     */  
    @SuppressWarnings("unchecked")
	public User get(final String keyId) {  
        User result = (User) redisTemplate.execute(new RedisCallback<User>() {  
            public User doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key = serializer.serialize(keyId);  
                byte[] value = connection.get(key);  
                if (value == null) {  
                    return null;  
                }  
                String acount = serializer.deserialize(value);  
                return new User(keyId, acount);  
            }  
        });  
        return result;  
    }  

}
