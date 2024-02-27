package com.itwill.project.dto.comment;

import com.itwill.project.domain.Recomment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //@Data = @Getter + @Setter + @ToString + @EqualsAndHashCode + @RequireArgsConstructor
@NoArgsConstructor // 아규먼트를 갖지않는 생성자. 기본 생성자.
@AllArgsConstructor // 모든 필드를 초기화 할 수 있는 아규먼트들을 갖는 생성자
@Builder // -> 빌더 패턴의 build() 메서드에서 AllArgsConstructor를 사용하기 때문에
public class RecommentRegisterDto {
    
    private Long comment_id;
    private String rtext;
    private String writer;
    
    public Recomment toEntity() {
        return Recomment.builder()
                .comment_id(comment_id)
                .recomment_content(rtext)
                .user_id(writer)
                .build();
    }
}
