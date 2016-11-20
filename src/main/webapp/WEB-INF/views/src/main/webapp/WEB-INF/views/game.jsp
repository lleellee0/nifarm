<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Game</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.0.3/sockjs.min.js"></script>
	<script src="http://GOESSNER.net/articles/JsonPath/"></script>
	<script type="text/javascript">
		var sock = null;
		var message = {};
		
		$(document).ready(function(){
			chatSock = new SockJS("/sketchbook/echo");
			chatSock.open = function(){
				message = {};
				message.message = "연결성공";
				message.type = "all";
				message.to = "all";
				chatSock.send(JSON.stringify(message));
			}
			chatSock.onmessage = function(evt){		//서버로부터 메시지가 도착?
				$("#messageWindow").append(evt.data);
				$("#messageWindow").append("<br>");
				$("#messageWindow").scrollToop(99999999);
			}
			chatSock.onclose = function(){
				$("#messageWindow").append("onClose");
				//sock.send("퇴장");
			}
			
			$("#inputMessage").keydown(function (key) {
				if (key.keyCode == 13) {
					$("#sendMessage").click();
				}
			});

			$("#sendMessage").click(function() {
				if( $("#inputMessage").val() != "") {
					message={};
					message.message = $("#inputMessage").val();
					message.type = "all";
					message.to = "all";

					chatSock.send(JSON.stringify(message));
					$("#messageWindow").append("나 ->  " + $("#inputMessage").val() + "<br/>");
					$("#messageWindow").scrollTop(99999999);

					$("#inputMessage").val("");
				}
			});

<%--			$(form).submit(function(){	//폼이 submit되면 실행
				if($("#inputMessage").val() != ""){*/
					message={};
					message.message = $("#inputMessage").val();
					message.type = "all";
					messasge.to = "all";
					
					chatSock.send(JSON.stringify(message));
					$("messageWindow").append("나: "+$("#inputMessage").val()+"<br>");
					$("messageWindow").scrollTop(99999999);
					
					$("#inputMessage").val("");
				}
			});--%>
		}); 
	</script>
</head>
<body>
	<h2>캐치마인드</h2>
<!--<textarea id="messageWindow" rows="10" cols="50" readonly="readonly"></textarea>-->
	<div id="messageWindow"></div>
	<form>
		<input type="text" id="inputMessage" size="50"/>
		<input type="button" id="sendMessage" value="send">
	</form>
</body>
</html>
