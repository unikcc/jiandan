import requests as rq
import re
import os
import os.path
import time

indexURL = 'http://www.meizitu.com/a/'
header = {'User-Agent':'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36'}

def run(url,page=1):
    url = url + str(page) + '.html'
    html = rq.get(url,headers=header)
    document = html.text
    results = re.findall(r'scrollLoading.+?src=\"(.+?)\"',document)
    for i in range(len(results)):
        html = rq.get(results[i], headers=header)
        content = html.content
        path = 'meizi/'+str(page)+'/'
        if not os.path.exists(path):
            os.makedirs(path)
        with open(path+str(i)+".jpg",'wb') as f:
            f.write(content)
            f.flush()
            print('page=%4i, jpg=%2i has down'%(page,i))
            time.sleep(2)

def manager(start=1,end=50):
    start = max(1,start)
    end = min(1000, end)
    for i in range(start,end):
        run(indexURL,i)


if __name__ == '__main__':
    manager()
