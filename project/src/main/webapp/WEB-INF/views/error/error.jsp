<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
   <style>
  
    
    * {
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
    }
    
    body {
        padding: 0;
        margin: 0;
        font-family: 'Roboto', sans-serif;
        background-color: #f0f7ff;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    
    .notfound {
        text-align: center;
        background-color: #fff;
        padding: 40px;
        border-radius: 10px;
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        position: relative;
        margin: 40px;
    }
    
    .notfound img {
        max-width: 250px;
        position: absolute;
        top: -120px;
        left: 50%;
        transform: translateX(-50%);
    }
    
    .notfound-err {
        font-size: 48px;
        font-weight: 700;
        color: #333;
        margin-bottom: 10px;
        text-transform: uppercase;
    }
    
    .notfound-sorry {
        font-size: 60px;
        font-weight: 700;
        color: #555;
        margin: 0;
        text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
        animation: shake 0.5s infinite;
    }

    @keyframes shake {
        0% { transform: rotate(0); }
        25% { transform: rotate(5deg); }
        50% { transform: rotate(-5deg); }
        75% { transform: rotate(3deg); }
        100% { transform: rotate(0); }
    }
    
    .notfound a {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        margin-top: 20px;
        padding: 15px 40px;
        background-color: #333;
        color: #fff;
        text-decoration: none;
        border-radius: 50px;
        transition: background-color 0.3s ease-in-out;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        font-size: 24px;
        position: relative;
    }
    
    .notfound a:hover {
        background-color: #555;
    }

    /* Emoticons */
    .emoticon {
        font-size: 28px;
        margin-right: 8px;
        transform: scale(1);
        transition: transform 0.3s ease-in-out;
    }

    .emoticon:hover {
        transform: scale(1.2);
    }

    .notfound a:hover .emoticon {
        transform: scale(1.2);
    }

    .notfound a:hover:nth-of-type(1) .emoticon {
        animation: moveToLeft 0.5s forwards;
    }

    .notfound a:hover:nth-of-type(2) .emoticon {
        animation: moveToRight 0.5s forwards;
    }

    @keyframes moveToLeft {
        0% { transform: scale(1); }
        100% { transform: scale(1.2) translateX(-20px); }
    }

    @keyframes moveToRight {
        0% { transform: scale(1); }
        100% { transform: scale(1.2) translateX(20px); }
    }

    .notfound a:hover .emoticon {
        transform: scale(1.2);
        animation: moveToButton 0.5s forwards, changeEmoji 0.5s forwards;
    }

    @keyframes moveToButton {
        0% { transform: scale(1); }
        100% { transform: scale(1.2) translate(10px, 0); }
    }

    @keyframes changeEmoji {
        0% { content: "\1F634"; } /* 시작 이모티콘 */
        50% { content: "\1F60A"; } /* 중간 이모티콘 */
        100% { content: "\1F603"; } /* 최종 이모티콘 */
    }
    
    .middle-emoticon {
        font-size: 60px;
        transition: transform 0.5s ease-in-out;
    }

    .middle-emoticon:hover {
        transform: scale(1.2);
    }

    .notfound-sorry {
        font-size: 60px;
        font-weight: 700;
        color: #555;
        margin: 0;
    }
   
    /* 수정한 부분 */
    .notfound-404 {
        margin-top: 20px;
        text-align: center;
    }

    .notfound-404 h1 {
        font-size: 36px;
        margin-bottom: 10px;
    }

    .notfound-err {
        font-size: 18px;
        color: #888;
    }
     .notfound img {
        max-width: 250px;
        position: absolute;
        top: -180px; /* 이미지를 더 위로 이동 */
        left: 50%;
        transform: translateX(-50%);
    }
</style>

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&display=swap" rel="stylesheet">
</head>
<body>
   <div class="notfound">
    <a href="/project">
        <img src="../img/logo.png" alt="로고 이미지"/>
    </a>
    <div class="notfound-404">
        <h1>404. That’s an error.</h1>
        <h3 class="notfound-err">The requested URL /asd was not found on this server. That’s all we know.</h3>
    </div>
    <p class="notfound-sorry">&#129300;</p>
    <!-- 이전 페이지로 이동 버튼 -->
    <a href="javascript:history.go(-1)" onmouseover="addEffects(this, event)" onmouseout="removeEffects()">
        <div class="emoticon" id="emoji1">&#128073;</div>
        <span style="letter-spacing: 2px;">이전 페이지로 이동</span>
    </a>
    <!-- 게시판으로 이동 버튼 -->
    <a href="/project" onmouseover="addEffects(this, event)" onmouseout="removeEffects()">
        <span style="letter-spacing: 2px;">게시판으로 이동</span>
        <div class="emoticon" id="emoji2">&#128073;</div>
    </a>
</div>
    <script>
        const sorryEmoji = document.querySelector('.notfound-sorry');
        sorryEmoji.addEventListener('mouseover', addEffects);
        sorryEmoji.addEventListener('mouseout', removeEffects);

        function addEffects(event) {
            // 이모티콘 효과: 웃는 얼굴 이모티콘으로 변경
            sorryEmoji.innerHTML = '&#128512;';

            // 꽃가루 효과
            const flower = document.createElement('span');
            flower.classList.add('glow');
            sorryEmoji.appendChild(flower);
            setTimeout(() => {
                flower.style.opacity = '1';
            }, 10);

            // 움직임 효과
            sorryEmoji.addEventListener('mousemove', moveFlower);
        }

        function removeEffects() {
            // 이모티콘 원래대로 변경
            sorryEmoji.innerHTML = '&#129300;';

            // 효과 제거
            const flower = document.querySelector('.glow');
            if (flower) {
                flower.style.opacity = '0';
                setTimeout(() => {
                    flower.remove();
                }, 500);
            }

            sorryEmoji.removeEventListener('mousemove', moveFlower);
        }

        function moveFlower(event) {
            const flower = document.querySelector('.glow');
            flower.style.left = event.clientX - 100 + 'px';
            flower.style.top = event.clientY - 100 + 'px';
        }
    </script>
    
</body>
</html>