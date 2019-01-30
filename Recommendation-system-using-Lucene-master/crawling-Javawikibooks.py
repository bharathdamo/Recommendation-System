
# coding: utf-8

# In[21]:


import urllib.request
import re
from bs4 import BeautifulSoup
response=urllib.request.urlopen("https://en.wikibooks.org/wiki/Java_Programming").read()
#print(response) 
soup = BeautifulSoup(response, "lxml") 


# In[22]:


urls=[]
for a in soup.find_all('a', href=True):
    url='https://en.wikibooks.org/'+a['href']
    temp=a['href']
    reg="https://en.wikibooks.org//wiki/Java_Programming\W*"
    if(re.match(reg,url) and temp not in ["/wiki/Java_Programming","/wiki/Java_Programming/Q%26A","/wiki/Java_Programming/Todo","/wiki/Java_Programming/Conventions","/wiki/Java_Programming/Contributors","/wiki/Java_Programming/Print_version","/wiki/Java_Programming/Tutorials","/wiki/Java_Programming/Preface","/wiki/Java_Programming/About_This_Book","/wiki/Java_Programming/History","/wiki/Java_Programming/Index","/wiki/Java_Programming/Glossary","/wiki/Java_Programming/Links"]):
                                          #print(line)
        print(url)
        count=0
        file_name=a['href']
        new_file_name = re.sub('[^a-zA-Z0-9 \n\.]', '_', file_name)
        #print(new_file_name)
        soup = BeautifulSoup(urllib.request.urlopen(url).read(),"lxml")
        #print(soup.find('title').getText())  # Get the title
        #file.write(soup.find('title').getText()+"\n")
        
        #final_file_name=new_file_name+"ul"+str(count)
        file = open("C:\\Users\\Abhinethri\\Desktop\\Assignment2\\Content\\"+final_file_name+".txt","w", encoding="utf-8") 
        for ul in soup.find_all('ul'): # Get the contents which are in list  <li>  element
            file.write(ul.getText())
        file.close()
        count+=1;
        #print(final_file_name)
        
        final_file_name=new_file_name+"ol"+str(count)
        file = open("C:\\Users\\Abhinethri\\Desktop\\Assignment2\\Content\\"+final_file_name+".txt","w", encoding="utf-8") 
        for ol in soup.find_all('ol'): # Get the contents which are in list  <li>  element
            file.write(ol.getText()) 
        file.close()
        count+=1;   
        
        final_file_name=new_file_name+"dl"+str(count)
        file = open("C:\\Users\\Abhinethri\\Desktop\\Assignment2\\Content\\"+final_file_name+".txt","w", encoding="utf-8") 
        for dl in soup.find_all('dl'): # Get the contents which are in  <dl> element
            file.write(dl.getText()) 
        file.close()
        count+=1; 
            #print(dd.getText())
        final_file_name=new_file_name+"pre"+str(count)
        file = open("C:\\Users\\Abhinethri\\Desktop\\Assignment2\\Content\\"+final_file_name+".txt","w", encoding="utf-8") 
        for pre in soup.find_all('pre'): # Get the contents which are in  <pre> element
            file.write(pre.getText()) 
        file.close()
        count+=1; 
            #print(pre.getText())
        final_file_name=new_file_name+"p"+str(count)
        file = open("C:\\Users\\Abhinethri\\Desktop\\Assignment2\\Content\\"+final_file_name+".txt","w", encoding="utf-8") 
        for p in soup.find_all('p'): # Get the contents which are in paragraph <p> element
            file.write(p.getText()) 
        file.close()
        count+=1; 
            #print(p.getText())
            
        final_file_name=new_file_name+"table"+str(count)
        file = open("C:\\Users\\Abhinethri\\Desktop\\Assignment2\\Content\\"+final_file_name+".txt","w", encoding="utf-8") 
        for table in soup.find_all('table'): # Get the contents which are in paragraph <p> element
            file.write(p.getText()) 
        file.close()
        count+=1;

