<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form id="registerForm" method="Post" action="register">
		<p id="nameField">
			<label for="name">Name:</label>
			<input type = "text" name = "name" required="true"/>
		</p>
		
		<p id="age">
			<label for="bornYear">Birth Year:</label>
			<input type="number" name="year" min="1990" max="2000">
		</p>
		
		<p id="phoneField">
			<label for="telephone">Phone:</label>
			<input type = "tel" name = "telephone" required="true"/>
		</p>
		
		<p id="emailField">
			<label for="email">Email:</label>
			<input type = "email" name = "email" required="true"/>
		</p>
		
		<p id="passwordField">
			<label for="password">Password:</label>
			<input type = "password" name = "password" required="true"/>
		</p>
		
		<p id="rePasswordField">
			<label for="password">Retype Password:</label>
			<input type = "password" name = "rePassword" required="true"/>
		</p>
		
		<p id="hasDriverLicense">
			<label for="driverOption">Do you have a driving license?</label>
			<input type="radio" name="driver license" value="Yes"/>Yes
			<input type="radio" name="driver license" value="No" checked="checked" />No
		</p>
		
		<input type="submit" name="registerSubmit" value="submit" />
	</form>
		
	<!-- 
	Register Page:
	Менютата си остават като в home page
	
	Register Form – да има username и email (потребителят си ги въвежда), password, repeat password, име, телефон, възраст, submit button. Да има въпрос ИМАШ ЛИ КНИЖКА и съответно Радио бутони Да / Не за отговор.
			
	Ако няма книжка, се създава инстанция на пътник и препраща към ChooseRole Page. По подразбиране рейтингът му е 0.
	Ако потребителят има книжка, се отварят още опции за попълване стаж(години, откакто е взел книжка), каква музика слуша, пуши ли в колата.  След попълването и събмитването се създава инстанция на шофьор – по подразбиране рейтингът му е 0 и броят пътувания също е 0.
	
	Когато потребителят натисне submit, данните се ВАЛИДИРАТ – Проверява се дали паролата е силна, дали имейлът е валиден,  дали вече не съществува, дали телефонът е валиден, дали въведеният стаж е валиден, възрастта също.
	Какво означава стажът да е валиден?
	да е валиден значи, че той нали въвежда от колко години има книжка => трябва да е по-голямо число от 0. Тва е. Просто всичките данни, които е въвел при регистрация, тр да се валидират.
	При невалидни данни, извежда съобщение за грешка.
	При валидни създава профила (записва даните в базите данни) и отива към ChooseRole Page
	
	
	 -->
	
</form>
</body>
</html>