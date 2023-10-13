console.log("Hello World");

document.addEventListener("DOMContentLoaded", function () {
    console.log("DOM fully loaded and parsed");

    // get slider
    const slider = document.querySelector("#image_size");
    console.log(slider);
    // get img
    const img = document.querySelector(".image");
    console.log(img);

    slider.addEventListener("change", function () {
        var imageSize = slider.value;
        img.style.width = `${imageSize}px`;
        img.style.height = `${imageSize}px`;
    });

});