<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>life mosaic</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
          rel="stylesheet" 
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" 
          crossorigin="anonymous">
          
  <style>
	.ck-editor__editable {
	    min-height: 400px;
        }
    #create.container {
        display: grid;
    }
    #btnCreate:hover {
        background-color: #BFA2DB;
    }
    #btnCancel:hover {
        background-color: #7F7C82;
        color: white;
    }
    
	</style>
    <link rel="stylesheet" href="../css/main_navigation.css" />
</head>

<body>

<!-- 메뉴바 -->
			<c:url var="imgPath" value="../img/logo.png" />
			<%@ include file="../fragments/navigation.jspf" %>
            
            <!-- 메뉴바랑 겹치기 않기 위해 빈 공간 생성 -->
            <div style="margin:150px"></div>
<div id="create" class=container>
<div class="container-fluid my-3">
    <div class="row justify-content-center">
        <div class="">
            <form id="createForm" action="${postCreatePage}" method="post" class="border border-2 border-dark-subtle p-4 rounded">
              <div class="mb-3">
			    <label for="postTitle" class="form-label">제목</label>
			    <input id="postTitle" class="form-control" type="text" name="title" placeholder="제목을 입력해주세요." autofocus required>
			    <div id="titleError"></div>
			</div>
			<div class="mb-3">
			    <label for="postContent" class="form-label">본문</label>
			    <textarea id="editor" class="form-control" name="content" rows="5" placeholder="내용을 입력해주세요." required></textarea>
			    <div id="contentError"></div>
			</div>
                <div class="mb-3">
                    <label for="inputHashTag" class="form-label">태그 - 내용을 대표하는 태그 5개 정도 입력해주세요.</label>
                    <input id="inputHashTag" class="form-control" type="text" placeholder="#특수문자 제외" list="searchOptions">
<!-- 자동완성 기능 ------------------------------------------------------------------------ -->                    	
                    <datalist id="searchOptions">
                    	<option value="키워드 준비 중 ..." />
                    </datalist>
<!-- ------------------------------------------------------------------------------------------ -->

                </div>
                <div class="d-none">
                    <input class="form-control" type="text" name="user_id" value="${signedInUser}" readonly>
                </div>
                <div class="d-none">
                    <input class="form-control" type="number" name="sub_category_id" value="${cId}" readonly>
                </div>
                <div id="hashtagList" class="d-flex gap-2 justify-content-start"></div>
                <div class="mb-3">
                </div>
                <div style="display: flex; justify-content: center;">
                    <button type="button" id="btnCreate" class="btn me-2" style="font-size: 120%; padding: 10px 25px 8px 25px; border-color: #BFA2DB">등록</button>
                    <button type="button" id="btnCancel" class="btn" style="font-size: 120%; padding: 10px 25px 8px 25px; border-color: #7F7C82">취소</button>
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
    <script src="https://kit.fontawesome.com/1306328925.js" crossorigin="anonymous"></script>
    <script src="../js/post-create.js"></script>
    <script src="https://cdn.ckeditor.com/ckeditor5/40.2.0/classic/ckeditor.js"></script>
	<script src="https://cdn.ckeditor.com/ckeditor5/40.2.0/classic/translations/ko.js"></script>
    
    
    <script>
    document.addEventListener('DOMContentLoaded', function () {
        ClassicEditor
            .create(document.querySelector('#editor'))
            .then(editor => {
                editor.model.document.on('change:data', () => {
                    // 에디터 값이 변경될 때마다 textarea에 값을 할당.
                    const editorValue = editor.getData();
                    document.querySelector('#editor').value = editorValue;

                    // 로그에 에디터 값과 textarea 값 모두 출력.
                    console.log('에디터 값:', editorValue);
                    console.log('Textarea 값:', document.querySelector('#editor').value);
                });
            })
            .catch(error => {
                console.error(error);
            });
    });

</script>
    

</body>
</html>
