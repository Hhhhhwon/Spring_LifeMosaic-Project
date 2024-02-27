package com.itwill.project.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itwill.project.domain.UserManagement;

@Mapper
public interface UserManagementDao {



	// 사용자의 이메일을 통해 user_id 조회
	String findUserIdByEmail(@Param("email") String email);

	// 특정 사용자의 비밀번호를 조회
	String getPasswordByUserId(@Param("user_id") String user_id);

	// 사용자의 비밀번호 변경
	int updatePasswordByUserId(@Param("user_id") String user_id,
		@Param("newPassword") String newPassword);

	// 비밀번호 검증을 위한 사용자 조회
	UserManagement findByUserIdAndPassword(@Param("user_id") String user_id,
		@Param("password") String password);

	int checkEmailExists(String email);

	int updateEmailByUserId(@Param("user_id") String user_id, @Param("newEmail") String newEmail);

	int updateEmail(String user_id, String newEmail);


}
