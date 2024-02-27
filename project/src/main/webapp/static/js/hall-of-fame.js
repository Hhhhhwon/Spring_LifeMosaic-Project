/**
 * hall of fame에 연결됨
 * 멋있는 카드효과를 구현하기 위해서 만들어짐
 */

 document.addEventListener('DOMContentLoaded', function(){
     
        const container = document.querySelectorAll('.cardContainer');
        
        container.forEach((card) => {
            
            card.addEventListener('mousemove', function(e) {
            let x = e.offsetX;
            let y = e.offsetY;
            console.log(x, y);
            let rotateY = -4/30 * x + 20;
            let rotateX = 1/5 * y - 20;
    
            card.style = `
            border: 5px solid; border-radius: 20px; border-color: #ffd700;
            transform :
            perspective(350px)
            rotateY(${rotateY}deg)
            rotateX(${rotateX}deg)`
            
            card.addEventListener('mouseout', function() {
                card.style = 'border: 5px solid; border-radius: 20px; border-color: #ffd700; transition-duration: 1.5s'
            })
        })    
    });
    
const textContainer = document.querySelector('.shine-text');
const shine = document.querySelector('.shine');

textContainer.addEventListener('mousemove', function(e) {
  const x = e.offsetX;
  const y = e.offsetY;
  
  shine.style.backgroundPosition = `${x}px ${y}px`;
});
 })