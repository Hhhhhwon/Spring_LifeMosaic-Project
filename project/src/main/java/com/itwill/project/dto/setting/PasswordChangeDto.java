package com.itwill.project.dto.setting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordChangeDto {
    private String currentPassword; // 현재 비밀번호
    private String newPassword;     // 새로운 비밀번호
}