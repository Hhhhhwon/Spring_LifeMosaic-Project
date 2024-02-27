/**
 * post-modify.js
 * modify.jsp에 포함됨
 * 포스트 삭제, 업데이트 기능
 */

  document.addEventListener('DOMContentLoaded', () => {
    //form 요소를 찾음.
    const form = document.querySelector('form#postModifyform');
    //포스트 번호(id)를 가지고 있는 요소를 찾음.
    const inputId = document.querySelector('input#post_id');
    
    const cId = document.querySelector('input#sub_category_id');
    
    //포스트 제목(title)을 가지고 있는 요소를 찾음
    const inputTitle = document.querySelector('input#title');
    //포스트 내용(content)를 가지고 있는 요소를 찾음.
    const textareaContent = document.querySelector('textarea#editor');
    //삭제 버튼 찾기
    const btnDelete = document.querySelector('button#btnDelete');
    //수정 버튼 찾기
    const btnUpdate = document.querySelector('button#btnUpdate');
    
    //해시관련
    const inputHash = document.querySelector('input#inputHashTag');
    
	// 검색된 값과 관련된 키워드 가져오기 ---------------------------
	const taglistOption = document.querySelector('datalist#searchOptions');

	inputHash.addEventListener('input', function() {
		const val = (inputHash.value).trim();
		if (val !== '') {
			optionTagList(val);
		}
	});

	async function optionTagList(value) {
		let htmlStr = '';
		const response = await axios.get(`../post/readtaglist?value=${value}`);
console.log(response.data);
		if(response.data.length === 1 && value === response.data[0]){
			taglistOption.innerHTML = '';
		}else{
			for (let a of response.data) {
				htmlStr += `<option value="${a}" />`;
			}
			taglistOption.innerHTML = htmlStr;			
		}
	}

	//---------------------------------------------------------------------------	
    
    for(let b of a){
		htmlTagList(b);
	}
    
    //삭제 버튼에 클릭 이벤트 핸들러(리스너)를 등록
    btnDelete.addEventListener('click', () => {
       const result = confirm('정말 삭제할까요?');
       // console.log(`confirm result = ${result}`); // -> true/false
       if (result) { // result === true: 사용자가 [확인(yes)]을 선택하면
           location.href = `delete?sub_category_id=${cId.value}&post_id=${inputId.value}`; //delete 요청을 보냄
           
       } 
    });
    
    btnUpdate.addEventListener('click', function() {
    // 제목, 내용이 비어있는 지 체크:
    if (inputTitle.value === '' || textareaContent.value === '') {
        alert('제목과 내용을 반드시 입력해야 합니다.');
        return; // 함수를 종료한다.
    }

    // 폼 제출을 위해 폼 속성을 설정하고 제출한다.
    form.action = 'update'; // 폼(양식)을 제출(submit)할 요청 주소. 기본값은 현재 URL.
    form.method = 'post'; // 폼 요청 방식(get/post). 기본값은 'get'.
    form.submit(); // 폼 제출(서버로 요청 보냄).
});
    
    

	//엔터 및 스페이스가 눌렸을 때 해시태그를 html 출력
	 inputHash.addEventListener('keydown', function(e){
		 //엔터키가 눌렸을 때
		 if(e.keyCode === 13 || e.keyCode === 32){
			 
			// 특수문자를 허용하지 않을 정규식
    		const regex = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gim;
			 
			 let hashValue = (inputHash.value).trim();	//앞 뒤 공백 제거
		     hashValue = hashValue.replace(regex, '');  //특수문자 삭제
			 
			 console.log("1. input 글자 : ", hashValue);

			 const liValue = document.querySelectorAll('span.tagValue');
			 if(hashValue !== '' && liValue.length<5){
					 //입력된 값 중복 확인하기
					 //이미 입력된 태그를 다시 입력하면 input창을 초기화하고 함수 종료
					 for(let item of liValue){
						 if(hashValue === item.innerHTML	){
							 console.log("2. 똑같은 글자 발견 : ", inputHash.value);
							 alert("동일한 태그가 존재합니다.");
					 		 inputHash.value = '';
							 return;
						 }
					 }
					 //html에 태그 출력
					 htmlTagList(hashValue); 
			 }else if(hashValue === ''){
				 alert('태그 내용을 확인해주세요.');
			 }else{
				 //5개 초과 시 입력할 수 없음.
				 alert("태그는 5개만 입력 가능합니다.");
			 }
		 		inputHash.value = '';
		 }
	 });
	 
//---------------------------------------------------------------------------------------------	 
 //html에 태그 출력
 function htmlTagList(tag){
	 	 let hashtagList = document.querySelector('div#hashtagList');
	 	 
	 	 //해시태그 <ul>에 출력함
	 	  hashtagList.innerHTML += 
	 	  `<span id=${tag} class="badge d-flex p-2 align-items-center text-primary-emphasis rounded-pill" style="background-color : #F3F1F5">
		    <span class="px-1 tagValue">${tag}</span>
		    <span class="btn-close btnDel"  data-id="${tag}"></span>
            <input class="d-none" name="hashTag" value="${tag}" />
		    </span>`
	 	  
		  //x에 클릭이벤트 리스너 등록하기
	 	  const btnDelete = document.querySelectorAll('span.btnDel');
	 	  for(let btnD of btnDelete){
			   btnD.addEventListener('click', function(e){
				   const aTag = 'span#'+ e.target.getAttribute('data-id');
				   const cTag = document.querySelector(aTag);
				   cTag.remove();
			   });
		   }
		 //input 초기화
		 inputHash.value = '';
 };
    
 });