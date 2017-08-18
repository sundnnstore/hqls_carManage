var pros =[];
var cs = [];
function getCitys(){
	$.ajax({
		url:"http://42.159.202.20:9999/location",
		type:"get",
		async:false,
		success:function(data){
			var citys = data.result.data;
			for(i=0;i<citys.length;i++){
				var city = {};
				city.name = citys[i][1];
				city.code = citys[i][2];
				city.id = citys[i][0];//省数据
				pros.push(city);
				var cis = citys[i][3];//市数据集合
				for(j=0;j<cis.length;j++){
					var cy = {};
					cy.name = cis[j][1];
					cy.code = cis[j][2];
					cy.id = cis[j][0];
					cy.pid = city.id;
					cs.push(cy);
					var countys =cis[j][3];
					for(k=0;k<countys.length;k++){
						var county = {};
						county.name = countys[k][1];
						county.code = countys[k][2];
						county.id = countys[k][0];
						county.pid = cy.id;
						cs.push(county);
					}
				}
			}
			localStorage.pros=JSON.stringify(pros);
			localStorage.cs=JSON.stringify(cs);
		}
	})
}

function findAllProvinces(){
	var pros = localStorage.pros;
	if(pros == null || pros == ''){
		getCitys();
		pros = localStorage.pros;
	}
	pros = JSON.parse(pros);
	return pros;
}

function findChildrenByPid(pid){
	var cs = localStorage.cs;
	if(cs == null || cs == ''){
		getCitys();
		cs = localStorage.cs;
	}
	cs = JSON.parse(cs);
	var citys = [];
	for(i=0;i<cs.length;i++){
		var city = cs[i];
		if(city.pid == pid){
			citys.push(city);
		}
	}
	console.log(citys);
	return citys;
}