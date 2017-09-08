package com.sinoauto.util;

import java.util.HashMap;
import java.util.Map;

import com.gexin.fastjson.JSONObject;
import com.sinoauto.entity.RespEntity;

public class OperDataUtil {
	private static String firstName = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏" + "陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕"
			+ "郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪" + "舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫"
			+ "经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴" + "糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶"
			+ "郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩" + "桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘"
			+ "匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后" + "江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙"
			+ "太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕" + "乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴"
			+ "梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓续";
	private static String second = "伟刚勇毅俊峰强军平保秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真"
			+ "环雪荣爱妹霞香月东文辉力明永健世广志义兴良海山仁波宁莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡"
			+ "婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚贵福安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠"
			+ "柔竹霭凝晓欢霄枫芸菲寒伊亚浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘宜可姬舒影荔枝思丽 ";

	private static String nums = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static String mobiles = "1300905,1301914,1302905,1303905,1303935,1304033,1304034,1304438,1306905,1306906,"
			+ "1306925,1306926,1308007,1308008,1308009,1308925,1308926,1308935,1310438,1312438,1312578,1313438,1314438,"
			+ "1314767,1314768,1314769,1314772,1315438,1315958,1315967,1315971,1317905,1317920,1318077,1318078,1319611,"
			+ "1319612,1319613,1320438,1320860,1320861,1320862,1320863,1320864,1321438,1322438,1322448,1323438,1324428,"
			+ "1324429,1324438,1324448,1324449,1325178,1325179,1325188,1325250,1325251,1327438,1327830,1327831,1327832,"
			+ "1327858,1327859,1329438,1329882,1329883,1330438,1332148,1332152,1332438,1333152,1333178,1334155,1335155,"
			+ "1335317,1335321,1335438,1336438,1336453,1336467,1338438,1339438,1390438,1394330,1394333,1394380,1394381,"
			+ "1394382,1394383,1394384,1394385,1394386,1394387,1394388,1394389,1504385,1504386,1504387,1504388,1504389,"
			+ "1510438,1513430,1513431,1513432,1513433,1513434,1513435,1513436,1513437,1513438,1513439,1514380,1514381,"
			+ "1514382,1514383,1514384,1514385,1520438,1524300,1524301,1524302,1524303,1524304,1524305,1524306,1524307,"
			+ "1524308,1524309,1530438,1532637,1532638,1532639,1532648,1532649,1533062,1535468,1535469,1550606,1550607,"
			+ "1550608,1550609,1552697,1552698,1552699,1554306,1554307,1554308,1554326,1554329,1554330,1554331,1554332,"
			+ "1554333,1554334,1554335,1554336,1554337,1554338,1554339,1554348,1554380,1554381,1554382,1554383,1554384,1554385"
			+ ",1554386,1554387,1554388,1554389,1556726,1556727,1556728,1560438,1564380,1564381,1564382,1564383,1564384,"
			+ "1564385,1564386,1564387,1564388,1566238,1566239,1566250,1566251,1566252,1566253,1584384,1584385,1584386,"
			+ "1584387,1584388,1584389,1584480,1584481,1584482,1584483,1584484,1584485,1584486,1584487,1594385,1594386,"
			+ "1594387,1594388,1594389,1594480,1594481,1594482,1594483,1594484,1594485,1594486,1594487,1594488,1594489,"
			+ "1594880,1594881,1594882,1594883,1594884,1594885,1594886,"
			+ "1860438,1864308,1864309,1864380,1864381,1864382,1864383,1864384,1864385,1864386,1864387,1864388,1864389,"
			+ "1880438,1884380,1884381,1884382,1884383,1884384,1884385,1884386,1884387,1884388,1884389";

	public static int getNum(int start, int end) {
		return (int) (Math.random() * (end - start + 1) + start);
	}

	public static String getUserName() {
		int index = getNum(0, firstName.length() - 1);
		int sec = getNum(0, second.length() - 1);
		int isth = getNum(0, 1);
		String th = "";
		if (isth == 1) {
			int thnum = getNum(0, second.length() - 1);
			th = second.substring(thnum, thnum + 1);
		}
		return firstName.substring(index, index + 1) + second.substring(sec, sec + 1) + th;
	}

	public static String getCarNo() {
		StringBuffer carNo = new StringBuffer("吉J");
		int count = 0;
		for (int i = 0; i < 5; i++) {
			if (count == 2) {
				int num = getNum(0, 9);
				carNo.append(num);
			} else {
				int isNum = getNum(0, 1);
				if (isNum == 0) {
					int index = getNum(0, nums.length() - 1);
					String zm = nums.substring(index, index + 1);
					carNo.append(zm);
					count++;
				} else {
					int num = getNum(0, 9);
					carNo.append(num);
				}
			}

		}
		return carNo.toString();
	}

	public static String getMobile() {
		String[] mos = mobiles.split(",");
		int index = getNum(0, mos.length - 1);
		StringBuffer mobile = new StringBuffer(mos[index]);
		int len = mobile.length();
		for (int i = 11; i > len; i--) {
			int n = getNum(0, 9);
			mobile.append(n);
		}
		return mobile.toString();
	}

	public static String[] getLocation(String xq) {
		String[] geo = new String[3];
		Map<String, Object> params = new HashMap<>();
		params.put("address", xq);
		params.put("key", "f66d4d2a8397352b3da15c7015fd327e");
		RespEntity result = HttpUtil.request("GET", "https://restapi.amap.com/v3/geocode/geo", null, params, null);
		JSONObject jo = JSONObject.parseObject(result.getResult());
		geo[0] = JSONObject.parseObject(jo.getJSONArray("geocodes").get(0).toString()).getString("location");
		geo[1] = JSONObject.parseObject(jo.getJSONArray("geocodes").get(0).toString()).getString("district");
		geo[2] = JSONObject.parseObject(jo.getJSONArray("geocodes").get(0).toString()).getString("adcode");
		return geo;
	}
	
	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<>();
		params.put("address", "吉林省松原市电业小区(富强街199号)");
		params.put("key", "f66d4d2a8397352b3da15c7015fd327e");
		RespEntity result = HttpUtil.request("GET", "https://restapi.amap.com/v3/geocode/geo", null, params, null);
		JSONObject jo = JSONObject.parseObject(result.getResult());
		System.err.println(jo.toString());
	}
	
	public static String[] getPosition(String xq){
		Map<String, Object> params = new HashMap<>();
		params.put("address", xq);
		params.put("ak", "PAuf1KwK4q4mR3TT2VKk6Kx56sk39Opp");
		params.put("output", "json");
		RespEntity result = HttpUtil.request("GET", "https://api.map.baidu.com/geocoder/v2/", null, params, null);
		JSONObject jo = JSONObject.parseObject(result.getResult());
		String lat = jo.getJSONObject("result").getJSONObject("location").getString("lat");
		String lng = jo.getJSONObject("result").getJSONObject("location").getString("lng");
		String[] geo = new String[2];
		geo[0] = lat;
		geo[1] = lng;
		return geo;
	}

}
