$(function(){
	$(".sidebar_tree li a").bind("click",function(){
		var title = $(this).text();
		var iconCls = $(this).attr("data-icon");
		var url = $(this).attr("data-link");
		var iframe = $(this).attr("iframe")==1?true:false;
		addTab(title,url,iconCls,iframe);
	});
});

function addTab(title,url,iconCls,iframe){
	var tabPanel = $("#sams_tabs");
	if(!tabPanel.tabs("exists",title)){
		var content = "<iframe scrolling='auto' frameborder='0' src='"+url+"' style='width:100%;height:100%'></iframe>";
		if(iframe){//<iframe>标签内嵌一个网页，灵活，里面的网页和外面的网页相互独立，缺点：js、css资源需重新加载
			tabPanel.tabs("add",{
				title:title,
				content:content,
				iconCls:iconCls,
				fit:true,
				cls:'pd3',
				closable:true
			});
		}else{
			tabPanel.tabs("add",{
			title:title,
			href:url,//会将<body></body>外的内容全部去掉，本质URL指向的页面<body></body>,好处不用重复加载js、css文件，缺点：里面的js、css必须写在<body></body>里面
			iconCls:iconCls,
			fit:true,
			cls:'pd3',
			closable:true
		});
			
		}
	}else{
		tabPanel.tabs("select",title)
	}
}