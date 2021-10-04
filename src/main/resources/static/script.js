$(function () {
  $.extend(true, $.fn.datetimepicker.defaults, {
    icons: {
      time: 'far fa-clock',
      date: 'far fa-calendar',
      up: 'fas fa-arrow-up',
      down: 'fas fa-arrow-down',
      previous: 'fas fa-chevron-left',
      next: 'fas fa-chevron-right',
      today: 'far fa-calendar-check-o',
      clear: 'far fa-trash',
      close: 'far fa-times'
    }
  });
});

$(function () {
  $('.datetimepicker').datetimepicker();
});

function displayStars(){
  let output = "";
  output = renderStars(5);
  document.querySelector(".stars").innerHTML = output;
}

function renderStars(number) {
  let output = '';
  for (let index = 1; index <= 5; index++) {
    let starClass = index <= number ? 'checked' : '';
    output += `<i class="fas fa-star ${starClass}"></i>`
  }
  return output;
}

function handleErrors(error) {
}

