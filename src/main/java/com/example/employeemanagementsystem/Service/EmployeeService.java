package com.example.employeemanagementsystem.Service;

import com.example.employeemanagementsystem.Common.JwtUtils;
import com.example.employeemanagementsystem.Common.PayLoad;
import com.example.employeemanagementsystem.Common.RedisConstants;
import com.example.employeemanagementsystem.Common.ResultCode;
import com.example.employeemanagementsystem.Dao.EmployeeMapper;
import com.example.employeemanagementsystem.Dao.LocationMapper;
import com.example.employeemanagementsystem.Dao.UserMapper;
import com.example.employeemanagementsystem.Excep.UserException;
import com.example.employeemanagementsystem.From.EmployeeForm;
import com.example.employeemanagementsystem.From.UserForm;
import com.example.employeemanagementsystem.Model.Employee;
import com.example.employeemanagementsystem.Model.Location;
import com.example.employeemanagementsystem.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    LocationMapper locationMapper;
    @Autowired
    JwtUtils jwtUtils;


    @Autowired
    private StringRedisTemplate redisTemplate;
    //新增员工
    @Transactional
    public Map userCreate(EmployeeForm employeeForm){
        String value = HandleToken(employeeForm);
        Location location = new Location();
        location.setLocationName(employeeForm.getLocationName());
        location.setLatitude(employeeForm.getLatitude());
        location.setLongitude(employeeForm.getLongitude());
        locationMapper.insertLocation(location);
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeForm,employee);
        employee.setLocationId(location.getLocationId());
        employeeMapper.insertEmployee(employee);
        Map map = new HashMap();
        map.put("xAuthtoken",value);
        return map;
    }
//    //查询员工
//    public Map userselect(UserForm userForm){
//        User mallUser = new User();
//        mallUser.setUserName(userForm.getUserName());
//        User userTemp  = userMapper.selectUser(mallUser.getUserName());
//        String str = "-"+userForm.getPassword()+"-";
//        str = md5(str).toUpperCase();
//        if (ObjectUtils.isEmpty(userTemp)){
//            throw new UserException(new ResultCode(false,-1,"需要登录的账号不存在"));
//        }
//        else if (!str.equals(userTemp.getPassword())) {
//            throw new UserException(new ResultCode(false,-1,"密码错误"));
//        }
//        String xAuthtoken = jwtUtils.createJwt(userTemp);
//        Map map = new HashMap();
//        map.put("xAuthtoken",xAuthtoken);
//        map.put("userName",userTemp.getUserName());
//        return map;
//    }

    public String HandleToken(EmployeeForm employeeForm){
        String value = redisTemplate.opsForValue().get(RedisConstants.JTI_KEY_PREFIX+employeeForm.getUserName());
        if (StringUtils.isEmpty(value)){
            throw new UserException(new ResultCode(false,-1,"当前登录的会话已经超时，请重新登录"));
        }
        if (!employeeForm.getToken().equals(value)){
            throw new UserException(new ResultCode(false,-1,"安全认证失败，请重新登录"));
        }
        return value;
    }

}
