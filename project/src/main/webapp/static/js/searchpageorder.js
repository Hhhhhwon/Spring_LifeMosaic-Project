/**
 * searchlist.jsp 사용
 */

document.addEventListener('DOMContentLoaded', function() {

	//기본 정렬 조건 설정
	//정렬 방법
	let orderStatus = 'upto';  // upto 면 최신순, good 면 좋아요 순
	//서브 카테고리 구분
	let subcategory = 0;

	//검색 키워드와 검색 카테고리 가져오기
	const keyword = document.querySelector('strong#keyword').innerHTML;
	const search_category = document.querySelector('div#search_category').innerHTML;

	const count_per_page = 10;
	let currentPage = 1;
	let searchData = '';

	//맨 처음 검색될 시 화면에 띄워줌
	workPost();



	// 정렬 버튼 이벤트 리스너 등록하기
	const btnOrder = document.querySelector('span#orderSearchList');
	btnOrder.addEventListener('click', function() {
		if (btnOrder.innerHTML === '좋아요순') {

			btnOrder.innerHTML = "최신순"
			orderStatus = 'good';
		} else {
			btnOrder.innerHTML = "좋아요순"
			orderStatus = 'upto';
		}

		workPost();
	});



	//카테고리 버튼 이벤트 리스너 등록하기
	const ulSubCatogoryList = document.querySelector('ul#sub_category');
	ulSubCatogoryList.addEventListener('click', selectByCategory);
	function selectByCategory(e) {
		subcategory = e.target.name;

		let btnSelectCategory = document.querySelector('button#btnSelectCategory');

		btnSelectCategory.innerHTML = e.target.innerHTML;

		currentPage = 1;

		workPost();
	};




	// 비동기 데이터 전송 함수 ---------------------------------------------------------------------------
	// 검색된 키워드와 기본 정렬 방식을 가지고 모든 데이터 가져옴
	async function workPost() {
		try {
			const uri = `../api/sort/category`;
			const data = { orderStatus, subcategory, keyword, search_category };
			const response = await axios.post(uri, data);

			searchData = response.data;

			makePostHtmlTemplet(currentPage);
			makePageHtmlTemplet();
		} catch (error) {
			console.log(error);
		}
	}



	// html 템플릿 만들기 -------------------------------------------------------------------------------
	function makePageHtmlTemplet() {
		//페이지 템플릿
		const divPage = document.querySelector('div#pageTemplate');

		divPage.innerHTML = '';
		let htmlStrPage = '';

		//한 페이지 당 보여줄 포스트 개수
		const totalPageCount = Math.ceil(searchData.length / count_per_page);

		//페이지 번호 동적으로 생성
		htmlStrPage += `<nav><ul class="pagination pagination-sm">`;
		for (let num = 1; num <= totalPageCount; num++) {
			if (num === parseInt(currentPage)) {
				htmlStrPage +=
					`<li class="page-item"  >
			    	<span class="liPageSelect page-link text-white" style="background-color: #BFA2DB;" data-id="${num}">${num}</span>
				</li>`;
			} else {
				htmlStrPage +=
					`<li class="page-item"  >
			    	<span class="liPageSelect page-link" style="background-color: #F3F1F5;color:#7F7C82;" data-id="${num}">${num}</span>
				</li>`;
			}
		}

		divPage.innerHTML = htmlStrPage;

		//페이지에 클릭 이벤트 리스너 등록
		const selectPage = document.querySelectorAll('span.liPageSelect');
		for (let pageSelect of selectPage) {
			pageSelect.addEventListener('click', changecurrentPage);
		}
	}

	function changecurrentPage(e) {
		const aPage = e.target.getAttribute('data-id');
		currentPage = aPage
		makePostHtmlTemplet(aPage);
		makePageHtmlTemplet();
	}


	function makePostHtmlTemplet(pageNumber) {
		//검색 결과 템플릿
		const divPost = document.querySelector('div#postTemplate')

		divPost.innerHTML = '';

		let htmlStr = '';

		if (searchData.length == 0) {
			htmlStr += `
			<div class="my-3 ms-2">
			<div class="my-2">
			<h4>검색 결과가 없습니다.</h4>
			</div>
			</div>
			<div style="margin-top : 500px"></div>
			`;
		} else {
			for (
				let i = count_per_page * (pageNumber - 1) + 1;
				i <= count_per_page * (pageNumber - 1) + count_per_page && i <= searchData.length;
				i++
			) {

				htmlStr += `
                <div class="border-bottom px-2 pb-3 my-2">
                    <div class="text-body-secondary d-flex mb-1">
                        
                        <div class="d-flex flex-fill align-items-center">
                            <img src="../setting/settingImg?fileName=${searchData[i - 1].profile }" alt="profile" class="me-2 rounded-circle" style="width: 20px; height: 20px; border: 1px solid lightgray;"/>

                            <div class="fw-semibold" style="font-size:12px;">
                                <a href="../member/memberPostList?nickname=${searchData[i - 1].nickname }" style="text-decoration:none" class="text-body-secondary" ><span>${searchData[i - 1].nickname }</span></a>
                                
                                <span class="mx-1"> · </span>
                                <span class="time">${searchData[i - 1].created_time }</span>
                            </div>
                            
                        </div>
                        
                        <div class="text-secondary" style="font-size: 12px;">

                            <span class="me-2"> 
                            		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="16" height="16">
	                                    <g id="_01_align_center" data-name="01 align center">
		                                    <path d="M23.821,11.181v0C22.943,9.261,19.5,3,12,3S1.057,9.261.179,11.181a1.969,1.969,0,0,0,0,1.64C1.057,14.739,4.5,21,12,21s10.943-6.261,11.821-8.181A1.968,1.968,0,0,0,23.821,11.181ZM12,19c-6.307,0-9.25-5.366-10-6.989C2.75,10.366,5.693,5,12,5c6.292,0,9.236,5.343,10,7C21.236,13.657,18.292,19,12,19Z" />
		                                    <path d="M12,7a5,5,0,1,0,5,5A5.006,5.006,0,0,0,12,7Zm0,8a3,3,0,1,1,3-3A3,3,0,0,1,12,15Z" />
	                                    </g>
                                    </svg>
                                ${searchData[i - 1].view_count } views
                            </span>
    
                            <!-- 좋아요수 -->
                            <span class="me-2"> <svg xmlns="http://www.w3.org/2000/svg"
                                    id="Outline" viewBox="0 0 24 24" width="16" height="16">
                                        <path
                                        d="M22.773,7.721A4.994,4.994,0,0,0,19,6H15.011l.336-2.041A3.037,3.037,0,0,0,9.626,2.122L7.712,6H5a5.006,5.006,0,0,0-5,5v5a5.006,5.006,0,0,0,5,5H18.3a5.024,5.024,0,0,0,4.951-4.3l.705-5A5,5,0,0,0,22.773,7.721ZM2,16V11A3,3,0,0,1,5,8H7V19H5A3,3,0,0,1,2,16Zm19.971-4.581-.706,5A3.012,3.012,0,0,1,18.3,19H9V7.734a1,1,0,0,0,.23-.292l2.189-4.435A1.07,1.07,0,0,1,13.141,2.8a1.024,1.024,0,0,1,.233.84l-.528,3.2A1,1,0,0,0,13.833,8H19a3,3,0,0,1,2.971,3.419Z" /></svg>
                                ${searchData[i - 1].like_point } likes
                            </span>
    
    
                            <!-- 댓글수 -->
                            <span class="me-2"> <svg xmlns="http://www.w3.org/2000/svg"
                                    id="Layer_1" data-name="Layer 1" viewBox="0 0 24 24" width="16" height="16">
                                        <path
                                        d="M12.009,23.665c-.476,0-.955-.168-1.337-.507l-3.748-3.157h-2.923c-2.206,0-4-1.794-4-4V4C0,1.794,1.794,0,4,0H20c2.206,0,4,1.794,4,4v12c0,2.206-1.794,4-4,4h-2.852l-3.847,3.18c-.362,.322-.825,.484-1.293,.484ZM4,2c-1.103,0-2,.897-2,2v12c0,1.103,.897,2,2,2h3.289c.236,0,.464,.083,.645,.235l4.047,3.41,4.17-3.416c.18-.148,.405-.229,.638-.229h3.212c1.103,0,2-.897,2-2V4c0-1.103-.897-2-2-2H4Z" /></svg>
                                ${searchData[i - 1].comment_cnt } comments
                            </span>
                        </div>
                    </div>
    
    
                    <!-- 두 번째 줄/ 게시글 제목, 내용 -->
                    <div class="my-2">
                        <a class="fw-bold nav-link text-truncate"  href="detail?post_id=${searchData[i - 1].post_id}">${searchData[i - 1].title }</a>
                    </div>
                    
                    <div class="my-2"">
                     <span style="overflow: hidden;  text-overflow: ellipsis;  white-space: nowrap; word-break:break-all;max-width:580px;height: 20px;font-size:14px;" class=" my-1 d-inline-block text-truncate">${searchData[i - 1].content }</span>
                    </div>
                 
                 
                 	<!-- 서브 카테고리 -->
					 <div class="mt-1" style="font-size:12px;">
					 <a class="rounded fw-semibold text-decoration-none px-2" style="font-size:12px; color : #AB49A0; background-color: #F3F1F5;" href="../post/list/?sub_category_id=${searchData[i - 1].sub_category_id }">${searchData[i - 1].sub_category_name }</a>
					`
			
					for(let h of searchData[i - 1].hashTag){
        					htmlStr +=`<span class="m-2 fw-medium" style="colot:#7F7C82;"># ${h }</span>`
					}
			
			htmlStr +=`</div>
                </div>
                <!-- 게시글 1개 종료 태그 -->`;
			}
			
			if(searchData.length <3){
				htmlStr += '<div style="margin-top : 350px;"></div>';
			}
		}

		divPost.innerHTML = htmlStr;
		
		// 모든 td 요소를 선택
		//자바스크립트에서 이 함수 못 불러와서 searchpageorder.js에서 한 번 더 불러줌
		const timeElements = document.querySelectorAll('.time');
		
		timeElements.forEach((timeElement) => {
		  const createdTime = timeElement.textContent;
		  timeElement.textContent = timeAgo(createdTime);
		});


	};
});
