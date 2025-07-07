var carbSwiper = new Swiper('.swiper-container', {
    // Set autoplay
    autoplay: {
        delay: 3000,
        disableOnInteraction: false
    },
    // Set infinite loop
    loop: true,
    // Set pagination indicator
    pagination: {
        el: '.swiper-pagination',
    },
    // Set navigation buttons
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    }
});
