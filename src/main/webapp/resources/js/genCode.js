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