# 煎蛋网更新了
* 现在图片url已经改变，需要js动态加载出来，原来方法可能已经失效了。最近比较忙，过几天更新一下
# Java爬虫 Java Spider爬取煎蛋网妹子图
* 不使用爬虫框架，只需会用HttpClient和正则表达式即可爬取
* 可自定义开始页面以及爬取页数，以及存放地址（妹子图的网址：http://jandan.net/ooxx/page-1 )
* 得到图片存放在D:/meizi文件夹下，每页单独一个文件夹，路径可在类MeiZi.java中修改
* 练手项目，仅供娱乐，多线程，代理Ip等暂时不会T_T
* 运行主类后，调用run方法抓取，如不指定页数则默认抓取10页,详细如下：

+	run()抓前10页
+	run(x)抓取前x页
+	run(x,y)抓取从x到y页，终止页码1500

+ 有问题Issue,相互学习~
# Git clone 以后，用maven导入
* eclipse导入maven项目方法：
* http://blog.csdn.net/baidu_32262373/article/details/52344316

# Java Crawler ,spide girls' pictures from internet, very simple
* Without any crawler frame, friendly to newbie
* Could set your own crawler choice
* Use function run()to cra 10 pages
* Use run(i) to craw top i pages
* And run(i,j) to craw from page i to page j
* Good Luck! Study hard and make progress everyday!
