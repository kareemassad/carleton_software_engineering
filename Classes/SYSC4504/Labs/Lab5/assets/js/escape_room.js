// add an event listener for the whole doc
document.addEventListener('DOMContentLoaded', function () {

    var image1Control = document.getElementById('image1Control');
    var image2Control = document.getElementById('image2Control');
    var image1Opacity = document.getElementById('image1Opacity');
    var image2Opacity = document.getElementById('image2Opacity');

    image1Control.addEventListener('input', function () {
        var first_image = document.getElementById('first-image');
        first_image.style.opacity = image1Control.value;
        image1Opacity.value = image1Control.value;
    });

    image2Control.addEventListener('input', function () {
        var second_image = document.getElementById('second-image');
        second_image.style.opacity = image2Control.value;
        image2Opacity.value = image2Control.value;
    });

    image1Opacity.addEventListener('input', function () {
        image1Control.value = image1Opacity.value;
        var first_image = document.getElementById('first-image');
        first_image.style.opacity = image1Opacity.value;
    });
    image2Opacity.addEventListener('input', function () {
        image2Control.value = image2Opacity.value;
        var second_image = document.getElementById('second-image');
        second_image.style.opacity = image2Opacity.value;
    });
});