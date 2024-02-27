package com.itwill.project.web;

import java.util.List;

import org.springframework.stereotype.Controller;
///src/main/java/com/itwill/project/web/UserController.java
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.project.domain.MyBookmarkListItemByPaging;
import com.itwill.project.domain.MyPagePost;
import com.itwill.project.domain.Post;
import com.itwill.project.service.MyPagePostService;
import com.itwill.project.service.PostService;
//기존 코드 생략...
import com.itwill.project.service.SettingService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/setting")
public class MyPageUserController {
    private final MyPagePostService myPagePostService;
    private final SettingService settingService;

    @GetMapping("/myPostList")
    public String myPosts(HttpSession session, Model model) {
        String user_id = (String) session.getAttribute("signedInUser");
        if (user_id != null) {
            List<MyPagePost> posts = myPagePostService.getPostsWithCommentCount(user_id);
            
            
        	//게시물들에 있는 해시태그값들 가져오기(for문을 통해서 있는 리스트에만 해시태그값을 넣어준다..
			for(MyPagePost post : posts) {
				
				Long post_id =  post.getPost_id();
				
				List<String> hashtags = settingService.selectPostHashtag(post_id);
				
				if(hashtags != null) {
					post.setHashTag(hashtags);
				}
			}
            model.addAttribute("posts", posts);
        } else {
            // 로그인하지 않은 사용자 처리. 예: 로그인 페이지로 리다이렉트
            return "redirect:/user/signin";
        }
        return "setting/myPostList"; // /WEB-INF/views/setting/myPostList.jsp
    }
}