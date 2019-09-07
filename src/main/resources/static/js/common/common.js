Array.prototype.contains = function (obj) {
    var contains = false;
    if (!this) {
        return contains;
    }
    for (var i = 0; i < this.length; i++) {
        if (this[i] == obj) {
            contains = true;
            break;
        }
    }
    return contains;
}

var PageUtil = function () {
    var isChildPage = false;       //是否是子页面
    if (window.location.href != window.top) {
        isChildPage = true;
    }
    /**
     * 重置滚动条位置（默认置顶）
     */
    var scrollReset = function () {
        if (isChildPage) {
            parent.$("body,html").scrollTop(0);
        } else {
            $("body,html").scrollTop(0);
        }
    }
    return {
        goto: function (url, isMenu) {
            window.location.href = url;
            if (isMenu && isChildPage) {

            }
            scrollReset();
        },
        goBack: function () {
            window.history.back();
            scrollReset();
        },
        getUrlParam: function () {
            var url = location.search; //获取url中"?"符后的字串
            var theRequest = new Object();
            if (url.indexOf("?") != -1) {
                var str = url.substr(1);
                strs = str.split("&");
                for (var i = 0; i < strs.length; i++) {
                    theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
                }
            }
            return theRequest;
        },
        menuGoTo: function (url) {
            $("#mainContent").attr('src', url);
        }
    }
}();