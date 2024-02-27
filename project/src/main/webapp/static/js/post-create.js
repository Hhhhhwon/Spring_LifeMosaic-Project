/**
 * create.jsp 작성
 * 폼 제출 버튼 이벤트 작성
 * 해시태그 엔터 이벤트 작성
 */

document.addEventListener('DOMContentLoaded', function() {
	const formCreate = document.querySelector('form#createForm');
	const btnFormCreate = document.querySelector('button#btnCreate');

	const inputHash = document.querySelector('input#inputHashTag');


	// 해시태그 입력창의 글자가 들어간 키워드 가져오기 ---------------------------
	const taglistOption = document.querySelector('datalist#searchOptions');

	inputHash.addEventListener('input', function() {
		const val = (inputHash.value).trim();
		if (val !== '') {
			optionTagList(val);
		}
	});

	
	//비동기 방식으로 데이터 가져와 html 출력
	async function optionTagList(value) {
		let htmlStr = '';
		const response = await axios.get(`../post/readtaglist?value=${value}`);
		console.log(response.data);
		if (response.data.length === 1 && value === response.data[0]) {
			taglistOption.innerHTML = '';
		} else {
			for (let a of response.data) {
				htmlStr += `<option value="${a}" />`;
			}
			taglistOption.innerHTML = htmlStr;
		}
	}

	//---------------------------------------------------------------------------	

	//엔터 및 스페이스가 눌렸을 때 해시태그를 html 출력
	inputHash.addEventListener('keydown', function(e) {
		//엔터키가 눌렸을 때
		if (e.keyCode === 13 || e.keyCode === 32) {

			// 특수문자를 허용하지 않을 정규식
			const regex = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gim;

			let hashValue = (inputHash.value).trim();	//앞 뒤 공백 제거
			hashValue = hashValue.replace(regex, '');  //특수문자 삭제

			console.log("1. input 글자 : ", hashValue);

			const liValue = document.querySelectorAll('span.tagValue');
			
			if(hashValue.length >=11){
				alert("10글자 이내로 작성 가능합니다.");
				return;
			}
			
			if (hashValue !== '' && liValue.length < 5) {
				//입력된 값 중복 확인하기
				//이미 입력된 태그를 다시 입력하면 input창을 초기화하고 함수 종료
				for (let item of liValue) {
					if (hashValue === item.innerHTML) {
						console.log("2. 똑같은 글자 발견 : ", inputHash.value);
						alert("동일한 태그가 존재합니다.");
						inputHash.value = '';
						return;
					}
				}
				//html에 태그 출력
				htmlTagList(hashValue);
			} else if (hashValue === '') {
				alert('태그 내용을 확인해주세요.');
			} else {
				//5개 초과 시 입력할 수 없음.
				alert("태그는 5개만 입력 가능합니다.");
			}
			inputHash.value = '';
			taglistOption.innerHTML = '';
		}
	});

	btnFormCreate.addEventListener('click', function() {
		let hasError = false;

		// 제목과 내용 유효성 검사
		const titleValue = document.querySelector('input[name="title"]').value.trim();
		const contentValue = document.querySelector('textarea[name="content"]').value.trim();
		const titleError = document.querySelector('div#titleError');
		const contentError = document.querySelector('div#contentError');

		titleError.innerHTML = '';
		contentError.innerHTML = '';

		if (titleValue === '' || titleValue.length < 5) {
			titleError.innerHTML = `
        <span class="flex items-center space-x-1 text-xs text-red-500">
            <i class="fa-solid fa-triangle-exclamation fa-beat-fade" style="color: #ef0b0b;"></i>
            <span style="color: #ef0b0b;">제목은 최소 5글자 이상 입력하세요.</span>
        </span>
    `;
			titleError.style.textAlign = 'left';
			hasError = true;
		} else {
			titleError.innerHTML = '';
		}

		if (contentValue === '' || contentValue.length < 5) {
			contentError.innerHTML = `
        <span class="flex items-center space-x-1 text-xs text-red-500">
            <i class="fa-solid fa-triangle-exclamation fa-beat-fade" style="color: #ef0b0b;"></i>
            <span style="color: #ef0b0b;">내용은 최소 5글자 이상 입력하세요.</span>
        </span>
    `;
			contentError.style.textAlign = 'left';
			hasError = true;
		} else {
			contentError.innerHTML = '';
		}


		// 오류가 없으면 폼 제출
		if (!hasError) {
			formCreate.action = 'create';
			formCreate.method = 'post';
			formCreate.submit();
		}
	});


	//---------------------------------------------------------------------------------------------	 
	//html에 태그 출력
	function htmlTagList(tag) {
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
		for (let btnD of btnDelete) {
			btnD.addEventListener('click', function(e) {
				const aTag = 'span#' + e.target.getAttribute('data-id');
				const cTag = document.querySelector(aTag);
				cTag.remove();
			})
		}
		//input 초기화
		inputHash.value = '';
	};


	const cancelButton = document.querySelector('button#btnCancel');

	cancelButton.addEventListener('click', function() {
		if (confirm('정말로 취소하시겠습니까?')) {
			window.history.back();
		}
	});
});