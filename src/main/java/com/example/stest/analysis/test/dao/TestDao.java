package com.example.stest.analysis.test.dao;

import com.example.stest.analysis.test.domain.User;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TestDao")
@Mapper
public interface TestDao {
    List<String> getauthmethod(@Param("userid") String userid);
    User login(@Param("account")String account, @Param("password") String password);
}
