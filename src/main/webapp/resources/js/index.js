$(document).ready(function(){
	$("html").mask({maskMsg:'页面正在加载中,请稍候...'});
});
$(function() {
	init();
	$("html").mask("hide");
});
function init() {
	_AF.nav.init();// 初始化导航菜单
	_AF.tabs.init();// 绑定右键菜单事件
	_AF.theme.init();// 初始化切换主题
}