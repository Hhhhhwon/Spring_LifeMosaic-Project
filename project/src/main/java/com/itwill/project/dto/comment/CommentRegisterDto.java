package com.itwill.project.dto.comment;

import com.itwill.project.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //@Data = @Getter + @Setter + @ToString + @EqualsAndHashCode + @RequireArgsConstructor
@NoArgsConstructor // 아규먼트를 갖지않는 생성자. 기본 생성자.
@AllArgsConstructor // 모든 필드를 초기화 할 수 있는 아규먼트들을 갖는 생성자
@Builder // -> 빌더 패턴의 build() 메서드에서 AllArgsConstructor를 사용하기 때문에
public class CommentRegisterDto {
    
    private Long post_id;
    private String ctext;
    private String writer;
    
    public Comment toEntiy() {
        return Comment.builder()
                .post_id(post_id)
                .comment_content(ctext)
                .user_id(writer)
                .build();
    }
}
