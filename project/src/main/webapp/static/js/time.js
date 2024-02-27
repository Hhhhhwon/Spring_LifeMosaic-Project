/**
 *  작성 시간 바꾸기 
 */

function timeAgo(date) {
  const now = new Date();
  const created = new Date(date);

  const seconds = Math.floor((now - created) / 1000);
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

// 모든 td 요소를 선택
const timeElements = document.querySelectorAll('.time');

timeElements.forEach((timeElement) => {
  const createdTime = timeElement.textContent;
  timeElement.textContent = timeAgo(createdTime);
});
