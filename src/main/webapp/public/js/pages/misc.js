// load if can or reload when state change
$(window).off('popstate').on('popstate', function (e) {
    const state = e.originalEvent.state;
    //console.log(state)
    if (state !== null) {
        let url = state.loadUrl;
        let into = $(state.into);
        let callback = state.callback;
        // console.log(into)
        if (into.length === 0) {
            // try to find content body
            into = $(ContentBody);
            if (into.length === 0) {
                window.location.reload();
                return;
            }
            url = state.realUrl;
        }

        load(url, null, null, (data) => {
            $(".modal").modal("hide");
            into.html(data);
            if (typeof callback == "string") {
                window[callback]();
            } else if (typeof callback == "function") {
                callback();
            }
        }, state.type);
    }
});
