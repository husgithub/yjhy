/**
 * Created by husong on 2018/5/7.
 */

angular.module('pagingModule', []).directive('paging', function () {
    return {
        restrict: 'E',
        replace: true,
        scope:{
            option : "=option"
        },
        templateUrl : '/template/paging.html',
        link : function (scope,element,attrs) {
            console.log(attrs.option)
            scope.$watch("option",function(newVal,oldVal){
                if(!newVal){
                    return ;
                }
                scope.option = newVal;
                //容错处理
                if (!scope.option.curr || isNaN(scope.option.curr) || scope.option.curr < 1) scope.option.curr = 1;
                if (!scope.option.total || isNaN(scope.option.total) || scope.option.total < 1) scope.option.total = 1;
                if (scope.option.curr > scope.option.total) scope.option.curr = scope.option.total;
                if (!scope.option.limit || isNaN(scope.option.limit) || scope.option.limit < 1) scope.option.limit = 10;
                scope.option.totalPage = Math.ceil(scope.option.total/scope.option.limit);
                scope.option.pageSelect = scope.initSelect(scope.option.pageSelect);
                scope.option.pageList = scope.initPages(scope.option.totalPage);
            });
            scope.initSelect = function (_pageSelect) {
                var defaultSelect = [10,20,30,40,50];
                if(_pageSelect && _pageSelect.length>0){
                    return _pageSelect;
                }
                return defaultSelect;
            }
            scope.initPages = function (_totalPage) {
                var _pageList = [];
                if(_totalPage <=9){
                    for(var i=1;i<=_totalPage;i++){
                        _pageList.push(i);
                    }
                }
                return _pageList;
            }
            scope.goTo = function (_curr,_limit) {
                console.log(_curr+"   "+_limit)
                if(!_limit && scope.option.curr == _curr){      //传入页码与当前页一致，不做任何操作
                    return;
                }
                _curr = _curr < 1 ? 1:_curr;
                _curr = _curr >scope.option.totalPage  ? scope.option.totalPage :_curr;
                scope.option.curr = _curr;
                if(scope.option.click && typeof scope.option.click === 'function'){
                    scope.option.click(_curr);
                }
            }
        },
        controller :function (scope,element,attrs){
            
        }
    }
});
