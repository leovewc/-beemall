var carbSwiper = new Swiper('.swiper-container', {
    loop: true,
    autoplay: false, // 我们自己控制 autoplay
    pagination: {
        el: '.swiper-pagination',
    },
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    }
});

const videos = document.querySelectorAll('.swiper-slide video');
let slideTimer = null;

// 播放当前 slide 的视频，并在 10 秒后切换
function playAndScheduleNext() {
    videos.forEach(v => {
        v.pause();
        v.currentTime = 0;
    });

    const current = document.querySelector('.swiper-slide-active video');
    if (current) {
        current.play();

        if (slideTimer) clearTimeout(slideTimer); // 清除旧计时器

        slideTimer = setTimeout(() => {
            carbSwiper.slideNext();
        }, 10000); // 10秒
    }
}

// 初始化时播放第一个
playAndScheduleNext();

// 每次滑动结束时播放当前
carbSwiper.on('slideChangeTransitionEnd', () => {
    playAndScheduleNext();
});


$('.all-sort-list > .item').hover(function () {
    var eq = $('.all-sort-list > .item').index(this),
        h = $('.all-sort-list').offset().top,
        s = $(window).scrollTop(),
        i = $(this).offset().top,
        item = $(this).children('.item-list').height(),
        sort = $('.all-sort-list').height();

    if (item < sort) {
        if (eq == 0) {
            $(this).children('.item-list').css('top', (i - h));
        } else {
            $(this).children('.item-list').css('top', (i - h) + 1);
        }
    } else {
        if (s > h) {
            if (i - s > 0) {
                $(this).children('.item-list').css('top', (s - h) + 2);
            } else {
                $(this).children('.item-list').css('top', (s - h) - (-(i - s)) + 2);
            }
        } else {
            $(this).children('.item-list').css('top', 3);
        }
    }

    $(this).addClass('hover');
    $(this).children('.item-list').css('display', 'block');
}, function () {
    $(this).removeClass('hover');
    $(this).children('.item-list').css('display', 'none');
});
