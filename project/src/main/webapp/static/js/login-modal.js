document.addEventListener('DOMContentLoaded', function() {
    // 게시글 작성, 댓글 작성, 북마크 버튼 등에 이벤트 리스너 추가
    let createButton = document.getElementById('create');
    createButton.addEventListener('click', function(event) {
        event.preventDefault(); // 기본 이벤트 동작을 막습니다.
        let subCategoryId = getParameterByName('sub_category_id'); // URL에서 sub_category_id 추출
        if (!subCategoryId) {
            alert('서브 카테고리 ID가 URL에 없습니다.');
            return;
        }
        checkLoginStatus(subCategoryId); // 로그인 상태 확인
    });

    // 로그인 폼에 preLoginUrl 값을 설정
    let preLoginUrl = sessionStorage.getItem('preLoginUrl') || '';
    let preLoginUrlInput = document.getElementById('preLoginUrl');
    if (preLoginUrlInput) {
        preLoginUrlInput.value = preLoginUrl;
    } else {
        console.error("preLoginUrl input field not found");
    }
    preLoginUrlInput.value = preLoginUrl;

    console.log("Setting preLoginUrl hidden field to:", preLoginUrl); // 로그 추가

    let loginForm = document.querySelector('form');
    loginForm.addEventListener('submit', function() {
        console.log("Submitting form with preLoginUrl:", preLoginUrlInput.value); // 로그 추가
    });

    // 모달창 닫기 버튼 이벤트 리스너
    let closeButton = document.querySelector('.close-button');
    if (closeButton) {
        closeButton.addEventListener('click', function() {
            let modal = document.getElementById('loginModal');
            modal.style.display = 'none';
        });
    } else {
        console.error('Close button not found');
    }

    // 로그인 페이지로 리다이렉트 버튼 이벤트 리스너
    let loginRedirectButton = document.getElementById('loginRedirectButton');
    if (loginRedirectButton) {
        loginRedirectButton.addEventListener('click', function() {
            sessionStorage.setItem('preLoginUrl', window.location.href);
            window.location.href = '/project/user/signin';
        });
    } else {
        console.error('Login redirect button not found');
    }

    function checkLoginStatus(subCategoryId) { // 함수 이름을 수정합니다.
        fetch('/project/user/checkLoginStatus', {
            method: 'GET',
            headers: {
                'X-Requested-With': 'XMLHttpRequest'
            }
        }).then(response => {
            if (response.status === 200) { // 200 OK 상태 코드를 확인합니다.
                window.location.href = `/project/post/create?sub_category_id=${subCategoryId}`; // URL에 sub_category_id를 포함합니다.
            } else {
                showModal();
            }
        }).catch(error => {
            console.error('Error:', error);
        });
    }

    function showModal() {
        var modal = document.getElementById('loginModal');
        modal.style.display = 'block';
    }

    // URL에서 주어진 이름의 쿼리 파라미터 값을 찾는 함수
    function getParameterByName(name, url = window.location.href) {
        name = name.replace(/[\[\]]/g, '\\$&');
        let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }


});