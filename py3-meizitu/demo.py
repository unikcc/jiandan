import requests as rq
import re
import os
import os.path
import time

indexURL = 'http://www.meizitu.com/a/1.html'
header = {'User-Agent':'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36'}

def run(url):
    html = rq.get(url,headers=header)
    document = html.text
    results = re.findall(r'scrollLoading.+?src=\"(.+?)\"',document)
    for i in range(len(results)):
        html = rq.get(results[i], headers=header)
        content = html.content
        if not os.path.exists('meizi/1/'):
            os.makedirs('meizi/1/')
        with open('meizi/1/'+str(i)+".jpg",'wb') as f:
            f.write(content)
            f.flush()
            print('jpg '+ str(i) +' has downloaded')
            time.sleep(2)


if __name__ == '__main__':
    run(indexURL)
