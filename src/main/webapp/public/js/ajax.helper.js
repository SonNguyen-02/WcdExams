﻿// noinspection JSUnresolvedVariable,JSUnresolvedFunction
// noinspection JSUnresolvedFunction

/**
 * @param {String} selector
 * @param {String} into
 * @param {String} rootPath
 * @param {Function} callback
 * @param {String} action
 */
function prepareMouseAction(selector, into = null, rootPath = null, callback = null, action = "click") {
    $(selector).off(action).on(action, function (e) {
        e.preventDefault();
        let url;
        if ($(this).get(0).tagName.toUpperCase() === "A") {
            url = $(this).attr("href");
        } else {
            url = $(this).data("url");
        }
        load(url, into, rootPath, callback);
    });
}

/**
 *
 * @param {String} selector
 * @param {String} into
 * @param {String} rootPath
 * @param {Function} callback
 * @param {String} type
 * @param {String} action
 */
function prepareKeyboardAction(selector, into = null, rootPath = null, callback = null, type = "GET", action = "keyup") {
    $(selector).off(action).on(action, function () {
        let url = $(this).data("url") + removeUrlParam(location.search, "p")
        let val = $(this).val()
        let paramKey = $(this).data("param-key");

        if (val !== undefined && val.length > 0) {
            url = replaceUrlParam(url, paramKey, val)
        } else {
            url = removeUrlParam(url, paramKey)
        }

        load(url, into, rootPath, callback, type);
    });
}

function refreshTableItemSort(tableId) {
    let tbl = $(tableId);
    let currentSort = tbl.data("sort");
    let currentOrder = tbl.data("order");

    tbl.find("thead th.sortable").each(function () {
        $(this).removeClass("asc desc");
        if ($(this).data("sort") === currentSort) {
            $(this).addClass(currentOrder);
            return false;
        }
    })
}

/**
 *
 * @param {String} searchId
 */
function refreshSearchBar(searchId) {
    let paramKey = $(searchId).data("param-key");
    $(searchId).val(getUrlParam(paramKey));
}

function pendingFocus(modal, ele) {
    modal.on('shown.bs.modal', function () {
        ele.focus();
    });
}

function clearFormElements(ele) {
    ele.find(":input").removeClass("valid error");
    ele[0].reset();
}

/**
 *
 * @param {String} $msg
 * @param {String} $type
 * @param {String} $title
 * @param {Number} $duration
 */
function showToast($msg, $type, $title = "", $duration = 3000) {
    let $toast = {
        "title": $title !== "" ? $title : ucfirst($type) + "!",
        "message": $msg,
        "type": $type,
        "duration": $duration
    };
    toast($toast);
}

/**
 *
 * @param {String} $title
 * @param {String} $msg
 * @param {String} $btnType
 * @param {String} $icon
 * @param {Function} callback
 * @param {Boolean} autoHide
 */
function showConfirm($title, $msg, $btnType = "danger", $icon = null, callback = null, autoHide = true) {
    let mModal = $("#confirmDialog");
    let mIcon = mModal.find(".modal-header i")
    let mSubmit = mModal.find("button[submit]")
    mModal.find(".modal-title").text($title)
    mModal.find(".modal-body").text($msg)

    mSubmit.removeClass();
    mSubmit.addClass("btn btn-" + $btnType)

    mIcon.removeClass();
    if ($icon != null) {
        mIcon.addClass("mdi mdi-" + $icon)
    }

    mSubmit.off("click").on("click", () => {
        if (autoHide) {
            mModal.modal("hide")
        }
        if (callback != null) {
            callback()
        }
    })
    mModal.modal("show")
}

function hideConfirm() {
    $("#confirmDialog").modal("hide");
}

// ============================= Utils ================================

function adapter_ajax($param) {
    $.ajax({
        url: $param.url,
        type: $param.type,
        data: $param.data,
        async: true,
        success: $param.success,
        error: $param.error
    });
}

function adapter_ajax_with_file($param) {
    $.ajax({
        url: $param.url,
        type: $param.type,
        data: $param.data,
        encType: "multipart/form-data",
        cache: false,
        processData: false,
        contentType: false,
        success: $param.success,
        error: $param.error
    });
}

/**
 *
 * @param {String} url
 * @param {String} into
 * @param {String} rootPath
 * @param {Function, String} callback function name with empty param
 * @param {String} type POST | GET
 * @param {Object} data
 */
function load(url, into, rootPath, callback = null, type = "GET", data = null) {
    if (rootPath != null) {
        let realUrl = rootPath + getTailUrl(url)
        let pushData = {
            into: into,
            loadUrl: url,
            realUrl: realUrl,
            type: type,
        }
        if (typeof callback == "string") {
            pushData.callback = callback;
        }
        if (typeof callback == "function" && callback.name !== "") {
            pushData.callback = callback.name;
        }
        if (history.state == null || history.state.realUrl !== realUrl) {
            history.pushState(pushData, null, realUrl);
        }
    }
    loadUrl(replaceUrlParam(url, "ajax", "true"), function (data) {
        if (into != null) {
            if (typeof into == 'string') {
                $(into).html(data);
            }
            if (typeof into == 'object') {
                into.html(data);
            }
        }
        if (typeof callback == "function") {
            callback(data);
        }
    }, null, type, data);
}

function loadUrl(url, success = null, error = null, type = "GET", data = null) {
    const $param = {
        type: type,
        url: url,
        data: data,
        success: success,
        error: error,
    };
    adapter_ajax($param);
}

function loadScripts(src, raw = false, head = false) {
    let container = head ? "head" : "body"
    let contentScriptSelector = container + " .contentScript"
    let contentScript = $(contentScriptSelector);
    if (contentScript.length === 0) {
        $(container).append("<div class='contentScript'></div>")
        contentScript = $(contentScriptSelector);
    }
    let content = raw ?
        "<script>" + src + "</script>" :
        "<script src='" + src + "'></script>";
    contentScript.html(content);
}

function getTailUrl(url) {
    let lastIndex = url.lastIndexOf("?");
    return lastIndex !== -1 ? url.substring(lastIndex) : "";
}

function getTailUrlWithoutQuestionMark(url) {
    let lastIndex = url.lastIndexOf("?");
    return lastIndex !== -1 ? url.substring(lastIndex + 1) : "";
}

function getUrlParam(param) {
    let searchParams = new URLSearchParams(window.location.search)
    if (searchParams.has(param)) {
        return searchParams.get(param);
    }
    return null;
}

function replaceUrlParam(url, paramName, paramValue) {
    if (paramValue == null) {
        paramValue = '';
    }
    const pattern = new RegExp('\\b(' + paramName + '=).*?(&|#|$)');
    if (url.search(pattern) >= 0) {
        return url.replace(pattern, '$1' + paramValue + '$2');
    }
    url = url.replace(/[?#]$/, '');
    return url + (url.indexOf('?') > 0 ? '&' : '?') + paramName + '=' + paramValue;
}

function removeUrlParam(url, paramName) {
    let rtn = url.split("?")[0],
        param,
        params_arr = [],
        queryString = (url.indexOf("?") !== -1) ? url.split("?")[1] : "";
    if (queryString !== "") {
        params_arr = queryString.split("&");
        for (let i = params_arr.length - 1; i >= 0; i -= 1) {
            param = params_arr[i].split("=")[0];
            if (param === paramName) {
                params_arr.splice(i, 1);
            }
        }
        if (params_arr.length) rtn = rtn + "?" + params_arr.join("&");
    }
    return rtn;
}

function objectifyForm(formElement) {
    let formArray = formElement.serializeArray();
    //serialize data function
    const returnArray = {};
    for (let i = 0; i < formArray.length; i++) {
        returnArray[formArray[i]['name']] = formArray[i]['value'];
    }
    return returnArray;
}

function ucfirst(string) {
    return string.charAt(0).toUpperCase() + string.slice(1)
}

function generatePassword(length = 20) {
    let charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",
        retVal = "";
    for (let i = 0, n = charset.length; i < length; ++i) {
        retVal += charset.charAt(Math.floor(Math.random() * n));
    }
    return retVal;
}

function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        // Generate random number
        let j = Math.floor(Math.random() * (i + 1));
        const temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    return array;
}

function savePdf(ele, fileName = generatePassword(16)) {
    if (typeof ele == 'string') {
        ele = $(ele)[0]
    }
    let opt = {
        margin: 0.5,
        filename: fileName,
        image: {type: 'jpeg', quality: 0.98},
        html2canvas: {scale: 2},
        jsPDF: {unit: 'in', format: 'letter', orientation: 'portrait'}
    };
    if (!window.html2pdf) {
        loadScripts("https://raw.githack.com/eKoopmans/html2pdf/master/dist/html2pdf.bundle.js", false, true);
    }
    let count = 0;
    let timeOut = 250;
    let run = () => {
        if (count++ > 20) return;
        window.html2pdf ? html2pdf(ele, opt) : setTimeout(run, timeOut);
    };
    run();
}

function previewPdf(ele) {
    if (typeof ele == 'string') {
        ele = $(ele)
    }
    const width = 800, height = 450;
    let left = (screen.width - width) / 2;
    let top = screen.height / 6;
    let params = 'width=' + width + ',height=' + height + ',left=' + left + ',top=' + top;
    let printWindow = window.open('', '', params);
    // noinspection HtmlRequiredTitleElement,HtmlRequiredLangAttribute
    printWindow.document.write('<html><head>' + $("head").html() + '</head>');
    printWindow.document.write('<body class="container-fluid p-0" onafterprint="close()">');
    printWindow.document.write(ele.parent().html());
    printWindow.document.write('<script>window.print();</script>');
    printWindow.document.write('</body></html>');
    printWindow.document.close();
}
