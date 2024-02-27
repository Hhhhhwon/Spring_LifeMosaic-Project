<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>life mosaic</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
            rel="stylesheet" 
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" 
            crossorigin="anonymous">
    </head>
    
      <style>
	   .ck-editor__editable {
	        min-height: 450px;
        }
        #modify.container {
            display: grid;
        }
        #btnUpdate:hover {
            background-color: #F0D9FF;
        }
        #btnDelete:hover {
            background-color: #7F7C82;
            color: white;
        }
    
	</style>
    <link rel="stylesheet" href="../css/main_navigation.css" />
    <body>
    
    <c:url var="imgPath" value="../img/logo.png" />
			<%@ include file="../fragments/navigation.jspf" %>
            
            <!-- 메뉴바랑 겹치기 않기 위해 빈 공간 생성 -->
            <div style="margin:150px"></div>
            
<div id=modify class="container">
<div class="container-fluid my-3">
    <div class="row justify-content-center">
        <div class="col-lg-10">
            <form id="postModifyform" class="border border-2 border-dark-subtle p-4 rounded">
             <div style="display: none;">
                <input class="d-none" id="sub_category_id" name="sub_category_id" value="${post.sub_category_id}" />
                <label for="post_id" class="form-label d-none">번호</label> 
                <input class="form-control" id="post_id" name="post_id" type="number" value="${post.post_id}" readonly />
            </div>
                <div class="mb-3">
                    <label for="title" class="form-label">제목</label>
                    <input id="title" class="form-control" type="text" name="title" value="${post.title}">
                    <div id="titleError"></div>
                </div>
                
                <div class="mb-3">
                    <label for="content" class="form-label">본문</label>
                    <textarea id="editor" class="form-control" name="content" rows="5">${post.content}</textarea>
                    <div id="contentError"></div>
                </div>
                
                <div class="mb-3">
                    <input id="inputHashTag" class="form-control" type="text" placeholder="#특수문자 제외" list="searchOptions">
                    <!-- 자동완성 기능 -->
                    <datalist id="searchOptions">
                        <option value="키워드 준비 중 ..." />
                    </datalist>
                    <!-- -->
                </div>
                
                <!-- 해시태그 보여주는 창 -->
                <div class="my-2">
                    <div id="hashtagList" class="d-flex gap-2 justify-content-start"></div>
                </div>
                <div style="display: flex; justify-content: center;">
                    <c:if test="${post.user_id eq signedInUser}">
						<button type="button" class="btn me-2" id="btnUpdate" type="submit" style="font-size: 120%; padding: 10px 25px 8px 25px; border-color: #F0D9FF">수정</button>
                        <button type="button" class="btn" id="btnDelete" style="font-size: 120%; padding: 10px 25px 8px 25px; border-color: #7F7C82">삭제</button>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
</div>
</div>
                
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" 
                crossorigin="anonymous"></script>
       <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
       <script src="https://cdn.ckeditor.com/ckeditor5/40.2.0/classic/ckeditor.js"></script>
	<script src="https://cdn.ckeditor.com/ckeditor5/40.2.0/classic/translations/ko.js"></script>
    
    
 <script>
    document.addEventListener('DOMContentLoaded', function () {
        // 에디터 값이 변경될 때마다 에디터 값 가져오기
        ClassicEditor
            .create(document.querySelector('#editor'))
            .then(editor => {
                editor.model.document.on('change:data', () => {
                    // 에디터 값이 변경될 때마다 에디터 값 가져오기
                    const editorValue = editor.getData();
                    console.log('에디터 값:', editorValue);

                    // 'editor' ID를 가진 textarea에 에디터 값 할당
                    document.querySelector('#editor').value = editorValue;
                });
            })
            .catch(error => {
                console.error(error);
            });
    });
</script>
		<script>let a=[];</script>
			<c:forEach items="${tags }" var="tag">
				<script>a.push('${tag}');</script>
			</c:forEach>
		<script>const postid = ${post.post_id}</script>              
        <script src="../js/post-modify.js"></script>
    </body>
</html>