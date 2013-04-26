$(function() {
	initTree(root + "/appFileManager/loadDir");
	init();
});
function init() {
	//_AF.nav.init();// 初始化导航菜单
	_AF.tabs.init();// 绑定右键菜单事件
	_AF.theme.init();// 初始化切换主题
}
// 创建目录树
function initTree(loadNodeUrl) {
	$('#folder').tree(
			{
				animate:true,
				url : loadNodeUrl, // 'tree_data.json',
				onClick : function(node) {
					if (node.text.indexOf('.json') > -1) {
						_AF.tabs.add1(node.text,root+ "/coder/genCode?filePath=" + node.id);
					}
				},
				onBeforeExpand : function(node, param) {
					$('#folder').tree('options').url = loadNodeUrl + "?node="+ node.id;
				}
			});
}