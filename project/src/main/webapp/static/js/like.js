/**
 * like.js
 * 
 * 좋아요 
 * 좋아요 취소
 * 싫어요 
 * 싫어요 취소
 * 다른 사람이 누른 좋아요, 싫어요를 취소하지 못하게 한다.
 * 좋아요, 싫어요를 중복으로 하지 못하게 한다. 
 */

 document.addEventListener('DOMContentLoaded', () => {
    
    // 페이지가 로드되면 바로 좋아요 싫어요 를 보여준다.
    likeCount();
    // 페이지가 로드되면 바로 좋아요, 싫어요를 체크하는 테이블에 row를 추가한다. 
    // 좋, 실을 체크하는 row가 이미 있으면 check 값에 따라 btn을 변경한다. 
    postLikeCheck();    
    
    // like 버튼을 등록
    const likeBtn = document.querySelector('button#likeBtn');
    const likeCancelBtn = document.querySelector('button#likeCancelBtn');
    
    // dislike 버튼을 등록
    const dislikeBtn = document.querySelector('button#dislikeBtn');
    const dislikeCancelBtn = document.querySelector('button#dislikeCancelBtn');
    
    // 클릭 이벤트 리스너 등록
    likeBtn.addEventListener('click', postLike);
    
    // 콜백
    function postLike() {
        
        // post_id 등록
        const post_id = document.querySelector('input#post_id').value;
        // 게시글을 보고 있는 사용자의 user_id 등록
        const user_id = document.querySelector('input#writer').value;
        
        // user_id 와 post_id를 json ?(맞는지는 모름) 으로 만듬
        
        const data = {post_id, user_id};
        
        if (user_id === '') {
            alert('좋아요는 로그인 이후에 이용하실 수 있습니다.');
            return;
        }
        
        // POST 방식의 Ajax 요청을 보냄. 응답/실패 콜백을 등록.
        axios.post('../api/like/increase', data) // post 방식의 Ajax 요청으로 data를 보냄.
            .then((response) => {
                if (response.data === 1) {
                // 성공 응답이 왔을 때 실행할 콜백 등록.
                likeCount();
                switchLikeBtn();
                }
            }) 
            .catch((error) => {
                // 실패 응답일 때 실행할 콜백 등록.
            }); 
    } // postLikeCancel end
    
    function postLikeCancel() {
        
        // post_id 등록
        const post_id = document.querySelector('input#post_id').value;
        // 게시글을 보고 있는 사용자의 user_id 등록
        const user_id = document.querySelector('input#writer').value;
        
        // user_id 와 post_id를 json ?(맞는지는 모름) 으로 만듬
        
        const data = {post_id, user_id};
        
        // POST 방식의 Ajax 요청을 보냄. 응답/실패 콜백을 등록.
        axios.post('../api/like/like/cancel', data) // post 방식의 Ajax 요청으로 data를 보냄.
            .then((response) => {
                if (response.data === 1) {
                // 성공 응답이 왔을 때 실행할 콜백 등록.
                likeCount();
                returnLikeBtn();
                }
            }) 
            .catch((error) => {
                // 실패 응답일 때 실행할 콜백 등록.
            }); 
    } // postLike end
    
    dislikeBtn.addEventListener('click', postDislike);
    
    function postDislike() {
        
        // post_id 등록
        const post_id = document.querySelector('input#post_id').value;
        // 게시글을 보고 있는 사용자의 user_id 등록
        const user_id = document.querySelector('input#writer').value;
        
        // user_id 와 post_id를 json ?(맞는지는 모름) 으로 만듬
        
        const data = {post_id, user_id};
        
        if (user_id === '') {
            alert('싫어요는 로그인 이후에 이용하실 수 있습니다.');
            return;
        }
        
        // POST 방식의 Ajax 요청을 보냄. 응답/실패 콜백을 등록.
        axios.post('../api/like/decrease', data) // post 방식의 Ajax 요청으로 data를 보냄.
            .then((response) => {
                if (response.data === 1) {
                // 성공 응답이 왔을 때 실행할 콜백 등록.
                likeCount();
                switchDislikeBtn();
                }
            }) 
            .catch((error) => {
                // 실패 응답일 때 실행할 콜백 등록.
            }); 
    } // postDislike end
    
    function postDislikeCancel() {
        
        // post_id 등록
        const post_id = document.querySelector('input#post_id').value;
        // 게시글을 보고 있는 사용자의 user_id 등록
        const user_id = document.querySelector('input#writer').value;
        
        // user_id 와 post_id를 json ?(맞는지는 모름) 으로 만듬
        
        const data = {post_id, user_id};
        
        // POST 방식의 Ajax 요청을 보냄. 응답/실패 콜백을 등록.
        axios.post('../api/like/dislike/cancel', data) // post 방식의 Ajax 요청으로 data를 보냄.
            .then((response) => {
                if (response.data === 1) {
                // 성공 응답이 왔을 때 실행할 콜백 등록.
                likeCount();
                returnDislikeBtn();
                }
            }) 
            .catch((error) => {
                // 실패 응답일 때 실행할 콜백 등록.
            }); 
    } // postDislikeCancel end
    
    // 좋아요 싫어요 숫자 가져오기
    function likeCount() {
        
        const post_id = document.querySelector('input#post_id').value;
        
        const uri = `../api/like/get/${post_id}`;
         
        axios.get(uri) // GET 방식의 Ajax 요청으 보냄.
            .then((response) => {
                //-> response 객체의 data 속성(property): 서버에서 응답으로 보낸 결과(객체)
                
                // 좋아요 HTML을 작성 -> like count를 보여줌
                makeLikeElement(response.data);
                
            }) // 성공 응답이 왔을 때 실행할 콜백 등록
            .catch((error) => {
            }); // 요청 실패일 때 실행할 콜백 등록
    } // likeCount end
    
    // 좋아요 HTML을 만드는 함수
 function makeLikeElement(data) {
    const likeDiv = document.querySelector('div#like');
    
    const htmlStr = `
        <div style="display: flex; justify-content: center; align-items: center; margin-top: 0px;">
            <div style="margin-right: 80px;">
                <span style="margin-left: 10px;">${data.like_point}     </span>
            </div>
            <div>
                <span style="margin-right: 10px;">     ${data.dislike_point}</span>
            </div>
        </div>`;
        
    likeDiv.innerHTML = htmlStr;

    const like = data.like_point; // 좋아요 수
    const dislike = data.dislike_point; // 싫어요 수

    const existingChart = Chart.getChart("donutChart"); // 이전 차트 가져오기
    if (existingChart) {
        existingChart.destroy(); // 이전 차트 제거
    }

    const dataToChart = {
        labels: ['좋아요', '싫어요'],
        datasets: [
            {
                data: [like, dislike],
                backgroundColor: ['#F0D9FF', '#BFA2DB'] // 파란색 - 좋아요, 빨간색 - 싫어요
            }
        ]
    };

    const options = {
        responsive: true,
        cutout: '70%',
        maintainAspectRatio: false,
        legend: {
            display: false 
        },
        title: {
            display: true,
            text: '좋아요 vs 싫어요',
            position: 'top' 
        }
        
    };

    const donutChart = new Chart(document.getElementById('donutChart'), {
        type: 'doughnut',
        data: dataToChart,
        options: options
    });
}


    
    // POST_ID와 USER_ID를 보내서 GET방식으로 POST_LIKE_CHECK의 데이터를 가져오는 함수
    function postLikeCheck() {
        
        // post_id 등록
        const post_id = document.querySelector('input#post_id').value;
        // 게시글을 보고 있는 사용자의 user_id 등록
        const user_id = document.querySelector('input#writer').value;

        
        // user_id 와 post_id를 json ?(맞는지는 모름) 으로 만듬
        const data = {post_id, user_id};
        
        if (user_id === '') {
            return;
        }
        
        // get 방식의 Ajax 요청을 보냄. 응답/실패 콜백을 등록.
        axios.get('../api/like/check', { params: {post_id: post_id, user_id: user_id} }) // get 방식의 Ajax 요청으로 data를 보냄.
            .then((response) => {
                if (response.data === 0) {
                    makeLikeCheck();
                } else if (response.data === 1) {
                    postLikeDetailCheck();
                }
            }) 
            .catch((error) => {
                // 실패 응답일 때 실행할 콜백 등록.
            });
        } //end postLikeCheck
            
    function makeLikeCheck() {
        
        // post_id 등록
        const post_id = document.querySelector('input#post_id').value;
        // 게시글을 보고 있는 사용자의 user_id 등록
        const user_id = document.querySelector('input#writer').value;
        
        // user_id 와 post_id를 json ?(맞는지는 모름) 으로 만듬
        const data = {post_id: post_id, user_id: user_id};
        
        axios.post('../api/like/make/check', data) // post 방식의 Ajax 요청으로 data를 보냄.
            .then((response) => {
                
            }) 
            .catch((error) => {
                // 실패 응답일 때 실행할 콜백 등록.
            });  
    } // end makeLikeCheck

    function postLikeDetailCheck() {
        
        // post_id 등록
        const post_id = document.querySelector('input#post_id').value;
        // 게시글을 보고 있는 사용자의 user_id 등록
        const user_id = document.querySelector('input#writer').value;
        
        axios.get('../api/like/detailcheck', { params: {post_id: post_id, user_id: user_id} }) // get 방식의 Ajax 요청으로 data를 보냄.
            .then((response) => {
                if (response.data.like_check === 1) {
                    switchLikeBtn();
                }
                if (response.data.dislike_check === 1) {
                    switchDislikeBtn();
                }
                
            }) 
            .catch((error) => {
            });
        
        
    }
// 좋아요 버튼을 활성화하고 싫어요 버튼을 비활성화하는 함수
function switchLikeBtn() {
    const likeBtn = document.querySelector('button#likeBtn'); // 좋아요 버튼 요소 가져오기
    const dislikeBtn = document.querySelector('button#dislikeBtn'); // 싫어요 버튼 요소 가져오기


    // 좋아요 버튼이 이미 활성화되어 있지 않다면
    if (!likeBtn.classList.contains('btn-secondary')) {
        likeBtn.classList.add('btn-secondary'); // 버튼 스타일 변경
        likeBtn.style.backgroundColor = '#F0D9FF'; // 좋아요 버튼 배경색 변경
        likeBtn.style.border = 'none';
        dislikeBtn.style.border = 'none';
        
        likeBtn.innerHTML = '<img src="https://cdn-icons-png.flaticon.com/512/1933/1933691.png" width="30" height="30" alt="" title="" class="img-small"> 좋아요'; // 아이콘과 텍스트 변경
        likeBtn.style.color = '#7F7C82';
        likeBtn.removeEventListener('click', postLike); // 기존 이벤트 제거
        likeBtn.addEventListener('click', postLikeCancel); // 새로운 이벤트 추가

        dislikeBtn.disabled = true; // 싫어요 버튼 비활성화
    }
}

// 싫어요 버튼을 활성화하고 좋아요 버튼을 비활성화하는 함수
function switchDislikeBtn() {
    const dislikeBtn = document.querySelector('button#dislikeBtn'); // 싫어요 버튼 요소 가져오기
    const likeBtn = document.querySelector('button#likeBtn'); // 좋아요 버튼 요소 가져오기

    // 싫어요 버튼이 이미 활성화되어 있지 않다면
    if (!dislikeBtn.classList.contains('btn-secondary')) {
        dislikeBtn.classList.add('btn-secondary'); // 버튼 스타일 변경
        dislikeBtn.style.backgroundColor = '#BFA2DB'; // 싫어요 버튼 배경색 변경
        dislikeBtn.style.border = 'none';
        likeBtn.style.border = 'none';
        dislikeBtn.innerHTML = '<img src="https://cdn-icons-png.flaticon.com/512/1933/1933788.png" width="30" height="30" alt="" title="" class="img-small"> 싫어요'; // 아이콘과 텍스트 변경
        dislikeBtn.removeEventListener('click', postDislike); // 기존 이벤트 제거
        dislikeBtn.addEventListener('click', postDislikeCancel); // 새로운 이벤트 추가

        likeBtn.disabled = true; // 좋아요 버튼 비활성화
    }
}

// 좋아요 버튼을 비활성화하고 원래 상태로 복원하는 함수
function returnLikeBtn() {
    const likeBtn = document.querySelector('button#likeBtn'); // 좋아요 버튼 요소 가져오기
    const dislikeBtn = document.querySelector('button#dislikeBtn'); // 싫어요 버튼 요소 가져오기

    likeBtn.classList.remove('btn-secondary'); // 버튼 스타일 원래대로 변경
    likeBtn.style.backgroundColor = ''; // 원래 버튼 배경색으로
    likeBtn.style.color = 'black';
    likeBtn.innerHTML = '<img src="https://cdn-icons-png.flaticon.com/512/1933/1933511.png" width="30" height="30" alt="" title="" class="img-small"> 좋아요'; // 아이콘과 텍스트 변경
    likeBtn.removeEventListener('click', postLikeCancel); // 기존 이벤트 제거
    likeBtn.addEventListener('click', postLike); // 새로운 이벤트 추가

    dislikeBtn.disabled = false; // 싫어요 버튼 활성화
}

// 싫어요 버튼을 비활성화하고 원래 상태로 복원하는 함수
function returnDislikeBtn() {
    const dislikeBtn = document.querySelector('button#dislikeBtn'); // 싫어요 버튼 요소 가져오기
    const likeBtn = document.querySelector('button#likeBtn'); // 좋아요 버튼 요소 가져오기

    dislikeBtn.classList.remove('btn-secondary'); // 버튼 스타일 원래대로 변경
    dislikeBtn.style.backgroundColor = ''; // 원래 버튼 배경색으로
    dislikeBtn.innerHTML = '<img src="https://cdn-icons-png.flaticon.com/512/1933/1933511.png" width="30" height="30" alt="" title="" class="img-small"> 싫어요'; // 아이콘과 텍스트 변경
    dislikeBtn.removeEventListener('click', postDislikeCancel); // 기존 이벤트 제거
    dislikeBtn.addEventListener('click', postDislike); // 새로운 이벤트 추가

    likeBtn.disabled = false; // 좋아요 버튼 활성화
}

 });