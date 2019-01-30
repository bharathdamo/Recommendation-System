<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<style type="text/css">
    .fieldset-auto-width {
         display: inline-block;
    }
</style>

<!DOCTYPE html>
<html lang="en">
<head>
<style>
ul {
    background: #ff9999;
    padding: 20px;
}

ol {
    background: #3399ff;
    padding: 20px;
}

ul li {
    background:  #ffe5e5;
    padding: 5px;
    margin-left: 35px;
}

ol li {
    background: #cce5ff;
    margin: 5px;
}

#post1,#code1_label{
    background-color: 	#ff9999; 
} 
</style>

<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}

input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

.form-heading{
 text-align: center;
}


pre {
    white-space: pre-wrap;       /* Since CSS 2.1 */
    white-space: -moz-pre-wrap;  /* Mozilla, since 1999 */
    white-space: -pre-wrap;      /* Opera 4-6 */
    white-space: -o-pre-wrap;    /* Opera 7 */
    word-wrap: break-word;       /* Internet Explorer 5.5+ */
}


button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

button:hover {
    opacity: 0.8;
}

.cancelbtn {
    width: auto;
    padding: 10px 18px;
    background-color: #f44336;
}

.imgcontainer {
    text-align: center;
    margin: 24px 0 12px 0;
}

img.avatar {
    width: 40%;
    border-radius: 50%;
}

.container {
    padding: 16px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

.userform {
     margin: 0 auto;
     width: 500px; 
     height: 400px;
}
.button3{
     margin: 0 auto;
     width: 500px; 
     height: 50px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
    span.psw {
       display: block;
       float: none;
    }
    .cancelbtn {
       width: 100%;
    }
}
</style>
<script>
function validate(){
	var b = document.myform.username.value;
	var res=parseInt(b, 10);
	if(res>10 || res<1)   
	  {
	   alert("Incorrect Input: "+b); return false;
	  }
	return true;
}

</script>
</head>

<body>

<div id="container" class="userform center-div"> 
<h1 class="form-heading">Java Wikibooks Recommendations</h1>

<form name="myform" onsubmit="return validate();" method="POST" action="recommendations" class="form-signin">



  <div class="container">
    <label for="post"><b>Post Number</b></label>
    <input type="text" maxlength="10" size="12" placeholder="Enter Post Number (1-10)" name="username" required>


        
    <button type="submit">Fetch Recommendations</button>
    <br/>
    <br/>

  </div>
  
  

  
</form>




</div>  
</form>

<div class="container">
 <h2>Post:</h2>
   <pre id="post1" >
<b>Answer:</b>  
${question} 
 
<b>Sample code:</b>
${code} 
</pre>

<h2>Recommendations:</h2>


<ul>
  <li>  <pre width="30"> ${rec1} </pre> </li>
  <li>  <pre width="30"> ${rec2} </pre> </li>
  <li>  <pre width="30"> ${rec3} </pre> </li>
  <li>  <pre width="30"> ${rec4} </pre> </li>
  <li>  <pre width="30"> ${rec5} </pre> </li>
  <li>  <pre width="30"> ${rec6} </pre> </li>
  <li>  <pre width="30"> ${rec7} </pre> </li>
  <li>  <pre width="30"> ${rec8} </pre> </li>
  <li>  <pre width="30"> ${rec9} </pre> </li>
  <li>  <pre > ${rec10} </pre> </li>
</ul>


<pre>
<span class="inner-pre" style="font-size: 23px">
<b>Indexing the contents:</b>
</span>
<span class="inner-pre" style="font-size: 18px">
I have written crawling-Javawikibooks.py script to fetch all the links from https://en.wikibooks.org/wiki/Java_Programming.
To fetch the links related to Java related concepts, I have used regular expression which will filter around 100 links. 
After obtaining the links, I am going through the contents of each link and fetching the contents between 2 headers as they contain relevant information i.e. code and explanation.
Once I extract content between 2 headers, I have added them to separate files.
Indexing in this way, help to give suggestions with sufficient useful contents.
</span>
</pre>
   
  </div>
  

   
</body>
</html>
