/**
 * hashtag.js
 * 생각을 해보니 내가 만든 쿼리에서 해시태그를 불러 올 수 없음 
 * 자바스크립트 하나 더 만들어야 함
 */

document.addEventListener('DOMContentLoaded', () => {
    
     getAllHashtag();
     
     function getAllHashtag() {
         
         const all_post_id = document.querySelectorAll('input#post_id');
         
         all_post_id.forEach(element => {
            
            const post_id = element.value;
            console.log(post_id);
            const uri = `../api/hashtag/${post_id}`
                         
            axios.get(uri)
            .then((response) => {
                console.log(response);
                const datas = response.data
                const tagDiv = document.querySelector(`div.tagDiv[data-id="${post_id}"]`)
                html = '';
                 
                datas.forEach(data => {
                    html += `<span> <span style="background-color: #F3F1F5; border-radius: 5px; padding: 2px 5px;">#${data.hashTag}</span>&ensp; </span>`
                })
                
                tagDiv.innerHTML = html;
            }) 
            .catch((error) => {
                console.log(error);
            }); 
         });
         
         
         
     }
});