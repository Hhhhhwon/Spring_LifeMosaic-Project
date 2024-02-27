window.addEventListener('load', function() {
    var verifyButton = document.getElementById('verifyCurrentPasswordButton');
    var changeButton = document.getElementById('changePasswordButton');
    var messageBox1 = document.getElementById('messageBox1');
    var messageBox2 = document.getElementById('messageBox2');
    var currentPassword; // 'currentPassword'를 두 버튼의 공통 스코프로 이동

    // 현재 비밀번호 확인 버튼의 이벤트 리스너
    verifyButton.addEventListener('click', function() {
        currentPassword = document.getElementById('currentPassword').value; // 'var' 키워드 제거

        // 현재 비밀번호를 서버로 전송하여 확인
        axios.post('/project/setting/verifyPassword', { currentPassword: currentPassword })
            .then(function(response) {
                messageBox1.textContent = '비밀번호가 확인되었습니다.';
                messageBox1.style.color = 'green';
            })
            .catch(function(error) {
                messageBox1.textContent = '비밀번호가 일치하지 않습니다.';
                messageBox1.style.color = 'red';
            });
    });

    // 새 비밀번호 변경 버튼의 이벤트 리스너
    changeButton.addEventListener('click', function() {
        var newPassword = document.getElementById('newPassword').value;
        var confirmPassword = document.getElementById('confirmPassword').value;

        // 새 비밀번호 유효성 검사
        var passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~`!@#$%\^&*()-+=]).{8,16}$/;
        if (!passwordRegex.test(newPassword)) {
            messageBox2.textContent = '비밀번호는 8~16자의 영문 대/소문자, 숫자, 특수문자를 포함해야 합니다.';
            messageBox2.style.color = 'red';
            return;
        }

        // 새 비밀번호와 비밀번호 확인이 일치하는지 검사
        if (newPassword !== confirmPassword) {
            messageBox2.textContent = '새 비밀번호와 비밀번호 확인이 일치하지 않습니다.';
            messageBox2.style.color = 'red';
            return;
        }

        // 새 비밀번호를 서버로 전송하여 변경
        axios.post('/project/setting/changePassword', {
            currentPassword: currentPassword, // 여기에서 'currentPassword'를 사용
            newPassword: newPassword
        })
            .then(function(response) {
                messageBox2.textContent = '비밀번호가 변경되었습니다.';
                messageBox2.style.color = 'green';
            })
            .catch(function(error) {
                messageBox2.textContent = '비밀번호 변경에 실패했습니다. 오류 메시지: ' + (error.response && error.response.data ? error.response.data : error.message);
                messageBox2.style.color = 'red';
            });
    });
});