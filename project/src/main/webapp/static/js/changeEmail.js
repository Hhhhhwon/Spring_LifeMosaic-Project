document.getElementById("changeEmailButton").addEventListener("click", function() {
    var newEmail = document.getElementById("newEmail").value;

    axios.post('/project/setting/changeEmail', { email: newEmail })
        .then(function (response) {
            document.getElementById("emailChangeMessage").innerText = response.data;
        })
        .catch(function (error) {
            document.getElementById("emailChangeMessage").innerText = '오류: ' + error.response.data;
        });
});