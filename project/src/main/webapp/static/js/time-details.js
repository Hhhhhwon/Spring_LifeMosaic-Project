/**
 *  time-details 상세보기 페이지 작성시간 변경
 * 
 */

 function calculateTimeSincePost(postTime) {
  const now = new Date();
  const postDate = new Date(postTime);
  const seconds = Math.floor((now - postDate) / 1000);

  let interval = Math.floor(seconds / 31536000);
  if (interval >= 1) {
    return `${interval}년 전`;
  }

  interval = Math.floor(seconds / 2592000);
  if (interval >= 1) {
    return `${interval}개월 전`;
  }

  interval = Math.floor(seconds / 86400);
  if (interval >= 1) {
    return `${interval}일 전`;
  }

  interval = Math.floor(seconds / 3600);
  if (interval >= 1) {
    return `${interval}시간 전`;
  }

  interval = Math.floor(seconds / 60);
  if (interval >= 1) {
    return `${interval}분 전`;
  }

  return `${Math.floor(seconds)}초 전`;
}

const timeElement = document.querySelector('#time');
const postTime = timeElement.textContent;
timeElement.textContent = calculateTimeSincePost(postTime);