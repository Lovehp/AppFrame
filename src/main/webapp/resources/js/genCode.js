$(document).ready(function(){
	alert(root + "/appFileManager/loadDir");
	initTree(root + "/appFileManager/loadDir");//初始化tree
});
function init() {
	$.ajax({
		url : root + "/javaGenManager/getJavaBeanInfo",
		data : {
			fileName : $('#fileName').val()
		},
		success : function(html) {
			loadBeanInfo(html);
		}
	});
}
//创建目录树
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

function loadBeanInfoFromDB(){
	
}

// 载入bean信息
function loadBeanInfo(obj) {
	if (obj) {
		if (obj.beanType) {
			$('#objSrcSelect').combobox('select', obj.beanType);
		}
		if (obj.pkName) {
			$('#pkName').val(obj.pkName);
		}
		if (obj.tableName) {
			$('#tableSelect').combobox('setValue', obj.tableName);
		}
		if (obj.selectView) {
			$('#selectText').val(obj.selectView);
		}
		if (obj.genProperties) {
			$('#datagrid').datagrid('loadData', obj.genProperties);
		}
	}
}