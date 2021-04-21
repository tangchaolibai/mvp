package com.hsb.mvpmsuser.user.service.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceImplTest {
	

//	    @Autowired
//	    private RedisTemplate redisTemplate;
//
//	    @Test
//	    public void testRedis() {
//	        String key = "hello";
//	        redisTemplate.opsForValue().set("hello", "你好");
//
//	        String res = (String) redisTemplate.opsForValue().get(key);
//	        System.out.println(res);
//	    }
//	@Autowired
//    private UserMapper userMapper;
//	
//	@Test
//    public void testSelect() {
//        System.out.println(("----- selectAll method test ------"));
//        List<User> userList = userMapper.selectList(null);
////        Assertions.assertEquals(5, userList.size());
//        userList.forEach(e -> System.out.println(e.toString()));
//    }
//	
//	@Test
//	public void testInsert() {
//		User user = new User();
//		user.setUserName("as").setUserRole("N").setMobilePhone("12345");
//		userMapper.insert(user);
//		List<User> userList = userMapper.selectList(null);
////		Assertions.assertEquals(1, userList.size());
//		System.out.println(userList.get(0).getCreateTime());
//		System.out.println(userList.get(0).getLastupdateTime());
//	}
//	
//	@Test
//    public void testSelectXml() {
//        System.out.println(("----- selectAll method test ------"));
//        List<User> userList = userMapper.findAll();
//        userList.forEach(e -> System.out.println(e.toString()));
//    }
}
