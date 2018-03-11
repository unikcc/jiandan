# 爬虫练手小程序
Java爬虫 / Python爬虫

## jiandan-xxoo 
+ 现在煎蛋网已经更新了url获取方式，原方法(或许)已经失效，推荐py3-mezitu，使用 Python, 更为方便
+ java编写，除Httpclient外不使用第三方包. 爬取[煎蛋网](http://jandan.net/ooxx)妹子图，没有多线程，代理IP等，简单易用，适合新手

## py3-meizitu
+ 采用`Python3`编写，抓取妹子图([点我看](http://www.meizitu.com/))
+ 简单无脑易用,核心代码不超过二十行,是~~宅男居家必备良品~~`学习编程的好帮手`
+ 请确保你使用了虚拟环境管理器，可用virtualenv或者`Conda`(强烈推荐! [光速入门](https://zhuanlan.zhihu.com/p/22678445))
+ git clone 之后,激活虚拟环境
+ 使用`pip install requests` 或者 `conda install requests`安装本程序使用的包
+ demo.py是对一个网页的爬取得,spider.py可以自定义爬取起始页 
+ 然后即可运行，在当前目录下meizitu/下查看详细结果
