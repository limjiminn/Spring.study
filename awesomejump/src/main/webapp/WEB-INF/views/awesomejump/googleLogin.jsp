<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <s:csrfMetaTags/>
<title>Insert title here</title>
 <meta name="google-signin-scope" content="profile email">
 <meta name="google-signin-client_id" content="54924421221-tv5t5ilotp2e66qmhun67cqq8g1mlhmo.apps.googleusercontent.com">
 <script src="https://apis.google.com/js/platform.js" async defer></script>
 <script type="text/javascript">
 function onSignIn(googleUser) {
     // Useful data for your client-side scripts:
     var profile = googleUser.getBasicProfile();
     console.log("ID: " + profile.getId()); // Don't send this directly to your server!
     console.log('Full Name: ' + profile.getName());
     console.log('Given Name: ' + profile.getGivenName());
     console.log('Family Name: ' + profile.getFamilyName());
     console.log("Image URL: " + profile.getImageUrl());
     console.log("Email: " + profile.getEmail());

     // The ID token you need to pass to your backend:
     var id_token = googleUser.getAuthResponse().id_token;
     console.log("ID Token: " + id_token);
 	
   //document.google_login.email.value = profile.getEmail();
      var email = profile.getEmail();
      var nickName = profile.getName();
      
    	var newForm = document.createElement('form');
		newForm.setAttribute('method','post');
		newForm.setAttribute('action','/loginAdd');
		
		var CSRFtoken = $("meta[name='_csrf']").attr('content');
		
		var input1 = document.createElement('input');
		var input2 = document.createElement('input');
		var input3 = document.createElement('input');
		
		input1.setAttribute("type","hidden");
		input1.setAttribute("name","email");
		input1.setAttribute("value",email);
		input2.setAttribute("type","hidden");
		input2.setAttribute("name","nickName");
		input2.setAttribute("value",nickName);
		input3.setAttribute("type","hidden");
		input3.setAttribute("name","_csrf");
		input3.setAttribute("value",CSRFtoken);
		
		newForm.appendChild(input1);
		newForm.appendChild(input2);
		newForm.appendChild(input3);
		
		document.body.appendChild(newForm);
		
		newForm.submit();
		
   }
 </script>
</head>
<body>


<div class="g-signin2" data-onsuccess="onSignIn" ></div>
</body>
</html>