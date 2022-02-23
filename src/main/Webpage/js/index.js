import {TextPlugin} from "./gsap/TextPlugin.js";

function destroyBody() {
    $("#header").off("click");

    let t1 = gsap.timeline();
    let text1 = $("#header h1");
    t1.flickerTo(text1, {duration: 5,
            textAlign: "center"})
        .flickerTo(text1, {text: "Leave",
            duration: 0.002,
            textAlign: "center"});


    let t2 = gsap.timeline();
    let text2 = $("#textbody");
    t2.flickerTo(text2, {text: "<p></p>", duration: 0.0002})
        .flickerTo(text2, {text: "<p></p>", duration: 10})
        .flickerTo(text2, {text: "<p>Come back in 5 minutes. Give us some time to fix what <b style='color: rgb(255, 0, 0)'><i>you</i></b> have broken.</p>",
            duration: 0,
            textAlign: "center"});

    let t3 = gsap.timeline();
    t3.flickerTo($("title"), {duration: 5})
        .flickerTo($("title"), {text: "Leave",
            duration: 0.0002})
}

function isVisible(querySelector) {
    const box = document.querySelector('#header');
    const rect = box.getBoundingClientRect();

    const isInViewport = rect.top >= 0 &&
        rect.left >= 0 &&
        rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&
        rect.right <= (window.innerWidth || document.documentElement.clientWidth);

    return isInViewport;
}

function addEventListeners() {
    $("#header").on("click", destroyBody);
}

// Start script
window.onload = function () {
    gsap.registerPlugin(TextPlugin);
    gsap.registerEffect({
        name: "flickerTo",
        effect: (targets, config) => {
            return gsap.to(targets, {duration: config.duration,
                text: config.text,
                textAlign: config.textAlign,
                ease: "none"});
        },
        defaults: {duration: 2, text: "Welcome", textAlign: "left"}, //defaults get applied to any "config" object passed to the effect
        extendTimeline: true, //now you can call the effect directly on any GSAP timeline to have the result immediately inserted in the position you define (default is sequenced at the end)
    });
    addEventListeners();
}