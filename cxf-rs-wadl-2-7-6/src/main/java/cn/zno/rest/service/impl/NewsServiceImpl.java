package cn.zno.rest.service.impl;

import cn.zno.pojo.News;
import cn.zno.pojo.Param;
import cn.zno.rest.service.NewsService;

public class NewsServiceImpl implements NewsService {

	@Override
	public News requestNews(Param param) {
		
		String title = "天津爆炸事件";
		String content = "新京报快讯(记者程媛媛 王大鹏 林野 杨锋)天津滨海爆炸事故发生已超过84小时。据最新新闻发布会消息，目前发现遗体112具。今天上午11时许，新京报记者在距离爆炸核心点不到2公里处的北海路和东海路口发现，昨日被戒严的多个区域已解除警戒线，多辆武警车辆往外撤离。现场消防员称目前正在进行扫尾作业。昨日，距离爆炸点2公里附近，全都拉起了警戒线。但今日上午11时许，新京报记者发现，第八大街警戒线都已解除;而第九大街东边戒严，武警战士称，他们即将对路面进行消毒和清洗，目前工作已进入清理和收尾阶段。记者在第八大街附近看见，不少环卫车辆正在进行道路清理作业。环卫工人表示已进入扫尾工作阶段。目前，八大街、北海路、东海路口交通已恢复正常。道路上可见有不少武警车辆往外撤离。昨日，现场至少有5处志愿者服务站，目前仅剩下1处。与此同时，在北海路与八大街交叉口的指挥部后备区，今天也已全部撤离。上午11时左右，两三辆黑色殡仪专用车从爆炸点北侧驶离。救援人员透露，这些殡仪车早上驶入现场，但早上并未发现幸存者和遇难者遗体。";
		
		News news = new News();
		news.setTitle(title);
		news.setContent(content.substring(0, param.getWordNumber()));
		return news;
	}

}
