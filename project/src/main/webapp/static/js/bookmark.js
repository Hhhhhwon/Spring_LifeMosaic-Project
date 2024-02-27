/**
 * bookmark.js
 * like.js에 같이 만들어도 되지만 코드가 너무 어지러워서 따로 뺌
 */

 document.addEventListener('DOMContentLoaded', () => {
     
     const bookmark = document.querySelector('a#bookmark');
     
     bookmarkCheck();
     
     bookmark.addEventListener('click', bookmarkAdd);
     
     function bookmarkAdd() {
         
         // post_id 등록
        const post_id = document.querySelector('input#post_id').value;
        // 게시글을 보고 있는 사용자의 user_id 등록
        const user_id = document.querySelector('input#writer').value;
        
        // user_id 와 post_id를 json ?(맞는지는 모름) 으로 만듬
        
        const data = {post_id, user_id};
        console.log(data);
        
        if (user_id === '') {
            alert('북마크는 로그인 이후에 이용하실 수 있습니다.');
            return;
        }
        
        // POST 방식의 Ajax 요청을 보냄. 응답/실패 콜백을 등록.
        axios.post('../api/bookmark', data) // post 방식의 Ajax 요청으로 data를 보냄.
            .then((response) => {
                console.log(response);
                if (response.data === 1) {
                // 성공 응답이 왔을 때 실행할 콜백 등록.
                switchBookmarkBtn();
                }
            }) 
            .catch((error) => {
                console.log(error);
                // 실패 응답일 때 실행할 콜백 등록.
            }); 
    } // bookmarkAdd end
    
    function CancelBookmark() {
         
         // post_id 등록
        const post_id = document.querySelector('input#post_id').value;
        // 게시글을 보고 있는 사용자의 user_id 등록
        const user_id = document.querySelector('input#writer').value;
        
        // user_id 와 post_id를 json ?(맞는지는 모름) 으로 만듬
        
        const data = {post_id, user_id};
        console.log(data);
        
        // POST 방식의 Ajax 요청을 보냄. 응답/실패 콜백을 등록.
        axios.delete('../api/bookmark/cancel', {data: data}) // delete 방식의 Ajax 요청으로 data를 보냄.
            .then((response) => {
                console.log(response);
                if (response.data === 1) {
                // 성공 응답이 왔을 때 실행할 콜백 등록.
                returnBookmarkBtn();
                }
            }) 
            .catch((error) => {
                console.log(error);
                // 실패 응답일 때 실행할 콜백 등록.
            }); 
    } // bookmarkCancel end
    
    function bookmarkCheck() {
        
        // post_id 등록
        const post_id = document.querySelector('input#post_id').value;
        // 게시글을 보고 있는 사용자의 user_id 등록
        const user_id = document.querySelector('input#writer').value;

        
        // user_id 와 post_id를 json ?(맞는지는 모름) 으로 만듬
        const data = {post_id, user_id};
        console.log(data);
        
        if (user_id === '') {
            return;
        }
        
        // get 방식의 Ajax 요청을 보냄. 응답/실패 콜백을 등록.
        axios.get('../api/bookmark/check', { params: {post_id: post_id, user_id: user_id} }) // get 방식의 Ajax 요청으로 data를 보냄.
            .then((response) => {
                console.log(response);
                if (response.data === 0) {
                    return;
                } else if (response.data === 1) {
                    switchBookmarkBtn();
                }
            }) 
            .catch((error) => {
                console.log(error);
                // 실패 응답일 때 실행할 콜백 등록.
            });
    } //end bookmarkCheck
     
    function switchBookmarkBtn() {
        
        // 좋아요 버튼을 등록
        const bookmark = document.querySelector('a#bookmark');
        
        // 좋아요 버튼의 아이디를 변경
        bookmark.setAttribute('id', 'bookmarkCancel');
        
        // 변경된 아이디로 좋아요 취소 버튼을 등록
        const bookmarkCancel = document.querySelector('a#bookmarkCancel');
        
        // 좋아요 취소 버튼의 모양을 바꿈
        bookmarkCancel.setAttribute('class','btn fs-5 fw-bold');
        bookmarkCancel.setAttribute('style','border-color: #F0D9FF; background-color: #F0D9FF; border-width: 3px; padding: 3px 10px 3px 12px; float: right;')
        
        // 좋아요 버튼의 이벤트 리스너를 삭제
        bookmark.removeEventListener('click', bookmarkAdd);
        
        // 좋아요 취소 버튼의 이벤트 리스너를 등록
        bookmarkCancel.addEventListener('click', CancelBookmark);
    }
    
    function returnBookmarkBtn() {
        
        // 좋아요 버튼을 등록
        const bookmarkCancel = document.querySelector('a#bookmarkCancel');
        
        // 좋아요 버튼의 아이디를 변경
        bookmarkCancel.setAttribute('id', 'bookmark');
        
        // 변경된 아이디로 좋아요 취소 버튼을 등록
        const bookmark = document.querySelector('a#bookmark');
        
        // 좋아요 취소 버튼의 모양을 바꿈
        bookmark.setAttribute('class','btn fs-5 fw-bold');
        bookmark.setAttribute('style','border-color: #F0D9FF; border-width: 3px; padding: 3px 10px 3px 12px; float: right;')
        
        // 좋아요 버튼의 이벤트 리스너를 삭제
        bookmarkCancel.removeEventListener('click', CancelBookmark);
        
        // 좋아요 취소 버튼의 이벤트 리스너를 등록
        bookmark.addEventListener('click', bookmarkAdd);
    }
     
 });