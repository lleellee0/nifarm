<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <!-- Site made with Mobirise Website Builder v3.8.5, https://mobirise.com -->
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="generator" content="Mobirise v3.8.5, mobirise.com">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="shortcut icon" href="assets/images/logo.png" type="image/x-icon">
  <meta name="description" content="">
  
  <%@include file="includes/links.jsp" %>
<style>
@font-face {
  font-family: 'Glyphicons Halflings';
  src: url('${path}assets/fonts/glyphicons-halflings-regular.eot');
  src: url('${path}assets/fonts/glyphicons-halflings-regular.eot?#iefix') format('embedded-opentype'), url('${path}assets/fonts/glyphicons-halflings-regular.woff2') format('woff2'), url('${path}assets/fonts/glyphicons-halflings-regular.woff') format('woff'), url('${path}assets/fonts/glyphicons-halflings-regular.ttf') format('truetype'), url('${path}assets/fonts/glyphicons-halflings-regular.svg#glyphicons_halflingsregular') format('svg');
}

.glyphicon {
  position: relative;
  top: 1px;
  display: inline-block;
  font-family: 'Glyphicons Halflings';
  font-style: normal;
  font-weight: normal;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
.glyphicon-info-sign:before {
  content: "\e086";
}
.glyphicon-ok:before {
  content: "\e013";
}


  @media screen and (min-width: 641px) and (max-width: 980px) {
  html {
    font-size: 9px;
  }
}
@media screen and (max-width: 640px) {
  html {
    font-size: 8px;
  }
}
body {
  background-color: #eee;
  font-family: "Roboto", sans-serif;
}
.container {
  max-width: 1200px;
  margin-left: auto;
  margin-right: auto;
  padding-left: 2rem;
  padding-right: 2rem;
}
.title {
  font-size: 3.5rem;
  font-weight: 400;
  text-align: center;
}
p {
  line-height: 1.8;
  margin-bottom: 2rem;
}
p:last-child {
  margin-bottom: 0;
}
/*
 * Core for cards
 */
.cards {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-wrap: wrap;
      -ms-flex-wrap: wrap;
          flex-wrap: wrap;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
      -ms-flex-pack: justify;
          justify-content: space-between;
  font-family: "Roboto", sans-serif;
}
.card {
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);
  margin-bottom: 2rem;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  -webkit-flex-direction: column;
      -ms-flex-direction: column;
          flex-direction: column;
}
.card_main {
  width: 100%;
}
@media screen and (min-width: 801px) {
  .card_main .card__title {
    font-size: 180%;
  }
  .card_main .card__main-action {
    width: 4.48em;
    height: 4.48em;
  }
}
.card_size-2xl {
  width: 66%;
}
@media screen and (min-width: 801px) {
  .card_size-2xl .card__title {
    font-size: 170%;
  }
}
.card_size-xl {
  width: 49%;
}
@media screen and (min-width: 801px) {
  .card_size-xl .card__title {
    font-size: 160%;
  }
}
.card_size-m {
  width: 32%;
}
@media screen and (min-width: 481px) and (max-width: 800px) {
  .card_size-m,
  .card_size-2xl {
    width: 49%;
  }
}
@media screen and (max-width: 480px) {
  .card_size-m,
  .card_size-xl,
  .card_size-2xl {
    width: 100%;
  }
}
.card__header {
  position: relative;
  line-height: 0;
}
*::-ms-backdrop,
.card__header {
  display: -ms-flexbox;
  display: flex;
}
.card__preview {
  max-width: 100%;
  height: auto;
}
*::-ms-backdrop,
.card__preview {
  -ms-flex: 0 0 auto;
      flex: 0 0 auto;
}
.card__main-action {
  font-size: 100%;
  text-decoration: none;
  text-indent: -9999px;
  cursor: pointer;
  border: none;
  border-radius: 50%;
  padding: 0;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12), 0 3px 1px -2px rgba(0, 0, 0, 0.2);
  position: absolute;
  right: 5%;
  bottom: 0;
  -webkit-transform: translateY(50%);
          transform: translateY(50%);
  width: 4em;
  height: 4em;
}
.card__main-action:before {
  content: "";
  display: block;
  width: 60%;
  height: 60%;
  box-sizing: border-box;
  background-position: 50% 50%;
  background-repeat: no-repeat;
  background-size: contain;
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
          transform: translate(-50%, -50%);
}
.card__main-action:focus {
  outline: none;
}
.card__body {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  -webkit-flex-direction: column;
      -ms-flex-direction: column;
          flex-direction: column;
  -webkit-justify-content: space-around;
      -ms-flex-pack: distribute;
          justify-content: space-around;
  -webkit-box-flex: 2;
  -webkit-flex-grow: 2;
      -ms-flex-positive: 2;
          flex-grow: 2;
}
.card__title {
  font-size: 150%;
  font-weight: 400;
  line-height: 1.5;
  margin-top: 0;
  margin-bottom: .8em;
}
.card__showmore {
  text-decoration: none;
}
.card__content {
  padding: 2.5em 4% 1.5em;
  -webkit-box-flex: 2;
  -webkit-flex-grow: 2;
      -ms-flex-positive: 2;
          flex-grow: 2;
}
.card__footer {
  padding: 1.5em 4%;
  border-top-width: 1px;
  border-top-style: solid;
  font-size: 80%;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
      -ms-flex-pack: justify;
          justify-content: space-between;
}
.card__meta-item {
  display: inline-block;
  vertical-align: middle;
  margin-left: .8em;
}
.card__meta-icon {
  display: inline-block;
  vertical-align: middle;
  text-align: right;
  width: 1.5em;
  height: 1.5em;
  margin-right: .2em;
  background-position: 50% 50%;
  background-repeat: no-repeat;
  background-size: contain;
}
/*
 * Skin for cards
 */
.card {
  background-color: #fff;
  color: #212121;
  font-size: 1.4rem;
}
@media screen and (min-width: 801px) {
  .card_main,
  .card_size-2xl,
  .card_size-xl {
    font-size: 1.6rem;
  }
}
.card__main-action {
  background-color: #3f51b5;
}
.card__main-action:before {
  background-image: url("https://stas-melnikov.ru/cssgrid/bookmark.svg");
}
.card__main-action:hover,
.card__main-action:focus {
  background-color: #303f9f;
}
.card__footer {
  border-top-color: #bdbdbd;
}
.card__showmore {
  color: #303f9f;
  -webkit-transition: color 0.3s ease-out;
  transition: color 0.3s ease-out;
}
.card__showmore:hover,
.card__showmore:focus {
  color: #3f51b5;
}
.card__meta-comments {
  background-image: url("https://stas-melnikov.ru/cssgrid/comment.svg");
}
.card__meta-likes {
  background-image: url("https://stas-melnikov.ru/cssgrid/favorite.svg");
}


/* 툴팁 */
		[data-tooltip] {
			position: relative;
			z-index: 2;
			cursor: pointer;
		}
		
		
		[data-tooltip]:before,
		[data-tooltip]:after {
		  visibility: hidden;
			-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
			filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=0);
			opacity: 0;
			pointer-events: none;
		}
		
		
		[data-tooltip]:before {
			position: absolute;
			bottom: 150%;
			left: 50%;
			margin-bottom: 5px;
			margin-left: -80px;
			padding: 7px;
			width: 160px;
			-webkit-border-radius: 3px;
			-moz-border-radius:    3px;
			border-radius:         3px;
			background-color: #000;
			background-color: hsla(0, 0%, 20%, 1);
			color: #fff;
			content: attr(data-tooltip);
			text-align: center;
			font-size: 14px;
			line-height: 1.2;
		}
		
		
		[data-tooltip]:after {
			position: absolute;
			bottom: 150%;
			left: 50%;
			margin-left: -5px;
			width: 0;
			border-top: 5px solid #000;
			border-top: 5px solid hsla(0, 0%, 20%, 1);
			border-right: 5px solid transparent;
			border-left: 5px solid transparent;
			content: " ";
			font-size: 0;
			line-height: 0;
		}
		
		
		[data-tooltip]:hover:before,
		[data-tooltip]:hover:after {
			visibility: visible;
			-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=100)";
			filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=100);
			opacity: 1;
		}
  
  
  
  /*차트*/
  #radarChart {
  margin-top: 100px;
  margin-bottom: 100px;
}
#canvas-holder {
  width:100%;
  height:100%;
  background:#333;
}
</style>
  
  
</head>
<body>
<%@include file="includes/navbar.jsp" %>

	<div class="container">
		<div class="cards" style="margin-top:100px;">
			<article class="card card_size-m">
				<header class="card__header">
					<img class="card__preview" src="https://stas-melnikov.ru/cliparts/phone2_480x320.jpg" alt="Preview img">
				</header>
				<div class="card__body">
					<div class="card__content">
						<h3 class="card__title"><a href="#0" class="card__showmore">Farm Information</a></h3>
						<div class="card__description">
							<p>농장명 : ${fiVo.farm_name}</p>
		            		<p>위치 : ${fiVo.location}</p>
	   		         		<p>규모 : ${scale}</p>
	       		     		<p>마지막 점검일 : ${fiVo.last_check_date}</p>
						</div>
					</div>
				</div>
			</article>		
			<article class="card card_size-2xl">
				<header class="card__header">
					<img class="card__preview" src="https://stas-melnikov.ru/cliparts/snowboarding2_1200x675.jpg" alt="Preview img">
				</header>
				<div class="card__body">
					<div class="card__content">
						<h3 class="card__title"><a href="#0" class="card__showmore">Check Result(${cdVo.check_date})</a></h3>
						<div class="card__description">
							<p id="check_result">
								<table id="result-target" style="width:100%; font-size:15px;">
                                    		
                                </table>
							
							</p>
						</div>
					</div>
				</div>
			</article>	
			<article class="card card_size-xl">
				<header class="card__header">
					<img class="card__preview" src="https://stas-melnikov.ru/cliparts/skills_1000x667.jpg" alt="Preview img">
				</header>
				<div class="card__body">
					<div class="card__content">
						<h3 class="card__title"><a href="#0" class="card__showmore">Chart</a></h3>
						<div class="card__description">
							<p>
								<div class="section" id="canvas-holder">
								  <canvas id="radarChart" />
								</div>
							</p>
						</div>
					</div>
				</div>
			</article>	
			<article class="card card_size-xl">
				<header class="card__header">
					<img class="card__preview" src="https://stas-melnikov.ru/cliparts/work_1200x700.jpg" alt="Preview img">
				</header>
				<div class="card__body">
					<div class="card__content">
						<h3 class="card__title"><a href="#0" class="card__showmore">총평</a></h3>
						<div class="card__description">
							<p>존나 취약함.</p>
						</div>
					</div>
				</div>
			</article>
							
		</div>
	</div>

  <%@include file="includes/scripts.jsp" %>
  
  <script src="//cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.js"></script>
  
  <script>
  $(document).ready(function() {
		var request_scale = ${fiVo.scale};
			
			$.ajax({
			    url : "${path}inner/admin/api/result/${fiVo.scale}/${farm_info_index}/${form_count}",
			    dataType : "json",
			    type : "post",
			    success: function(data) {
			        $('#result-target').empty();
			        var code = "";
			        var category = new Array(13);
			        var all_count = new Array(13);
			        var y_count = new Array(13);
			        for(var i = 0; i < 13; i++) {
			        	all_count[i] = 0;
			        	y_count[i] = 0;
			        }
			        	
			        var table_header = "<tr><th style='width:40%;'>세부내용</th><th style='width:5%;'></th><th style='width:40%;'>점검기준</th><th style='width:5%;'>YPN</th><th style='width:10%;'>File</th></tr>";
			        category[0] += "<tr><td style='font-size:21px;padding-top:20px;'>1. 보안정책<td></tr>" + table_header;
			        category[1] += "<tr><td style='font-size:21px;padding-top:20px;'>2. 정보보호조직<td></tr>" + table_header;
			        category[2] += "<tr><td style='font-size:21px;padding-top:20px;'>3. 자산관리<td></tr>" + table_header;
			        category[3] += "<tr><td style='font-size:21px;padding-top:20px;'>4. 인력보안<td></tr>" + table_header;
			        category[4] += "<tr><td style='font-size:21px;padding-top:20px;'>5. 물리환경적보안<td></tr>" + table_header;
			        category[5] += "<tr><td style='font-size:21px;padding-top:20px;'>6. 통신운영관리<td></tr>" + table_header;
			        category[6] += "<tr><td style='font-size:21px;padding-top:20px;'>7. 접근통제<td></tr>" + table_header;
			        category[7] += "<tr><td style='font-size:21px;padding-top:20px;'>8. 정보시스템도입 및 개발과 유지보수<td></tr>" + table_header;
			        category[8] += "<tr><td style='font-size:21px;padding-top:20px;'>9. 정보보안 사고관리<td></tr>" + table_header;
			        category[9] += "<tr><td style='font-size:21px;padding-top:20px;'>10. 업무연속성관리<td></tr>" + table_header;
			        category[10] += "<tr><td style='font-size:21px;padding-top:20px;'>11. 암호통제<td></tr>" + table_header;
			        category[11] += "<tr><td style='font-size:21px;padding-top:20px;'>12. 인증<td></tr>" + table_header;
			        category[12] += "<tr><td style='font-size:21px;padding-top:20px;'>13. 시큐어 코딩<td></tr>" + table_header;
			        console.log(Object.keys(data).length);
			        for(var i = 0, len = Object.keys(data).length; i < len;i++) {
			        	console.log(data[i][1]);
			        	category[data[i][1]-1] += "<tr><td>" + data[i][2] + 
			        	"</td><td><a href='#' data-tooltip='" + data[i][5] + "'><span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span></a></td><td>" + data[i][4] + "</td>" +
			        		"<td>" + data[i][6] +"</td>" +
			        		"<td><a href='${path}inner/api/file/" + data[i][8] + "'>" + data[i][7] + "</td>" + 
			        		"</tr>";
			        		
			        	all_count[data[i][1]-1]++;
			        	if(data[i][6] === "Y")
			        		y_count[data[i][1]-1]++;
			        }
			        for(var i = 0; i < 13; i++) {
			        	code += category[i];
			        }
					$('#result-target').html(code);
					console.log(data);
					// Radar Data
					
					var datasets_data = new Array(13);
					for(i = 0; i < 13; i++) {
						datasets_data[i] = (y_count[i]/all_count[i]*100).toPrecision(2);
					}
					
					var radarData = {
						labels : [
					      "보안정책", 
					      "정보보호조직", 
					      "자산관리",
					      "인력보안",
					      "물리환경적보안",
					      "통신운영관리",
					      "접근통제",
					      "정보시스템도입 및 개발과 유지보수",
					      "정보보안 사고관리",
					      "업무연속성관리",
					      "암호통제",
					      "인증",
					      "시큐어 코딩"
					  ],
						datasets : [
							{
								fillColor : "rgba(60, 175, 226, .6)",
								strokeColor : "rgba(60, 175, 226, .8)",
								data : datasets_data
							}
						]
					}

					//Get the context of the Radar Chart canvas element we want to select
					var ctx3 = document.getElementById("radarChart").getContext("2d");

					// Create the Radar Chart
					var myRadarChart = new Chart(ctx3).Radar(radarData, radarOptions);
			    },
			    error:function(request,status,error){
			        alert("code:"+request.status+"\n"+"error:"+error);
			    }
			}); 
  });
	  
  
  // 차트 시작
  // Radar Chart Options
var radarOptions = {
				
	//Boolean - If we show the scale above the chart data			
	scaleOverlay : false,
	
	//Boolean - If we want to override with a hard coded scale
	scaleOverride : false,
	
	//Boolean - Whether to show lines for each scale point
	scaleShowLine : true,

	//String - Colour of the scale line	
	scaleLineColor : "#999",
	
	//Number - Pixel width of the scale line	
	scaleLineWidth : 1,

	//Boolean - Whether to show labels on the scale	
	scaleShowLabels : false,
	

	
	//String - Scale label font declaration for the scale label
	scaleFontFamily : "'Arial'",
	
	//Number - Scale label font size in pixels	
	scaleFontSize : 12,
	
	//String - Scale label font weight style	
	scaleFontStyle : "normal",
	
	//String - Scale label font colour	
	scaleFontColor : "#666",
	
	//Boolean - Show a backdrop to the scale label
	scaleShowLabelBackdrop : true,
	
	//String - The colour of the label backdrop	
	scaleBackdropColor : "rgba(255,255,255,0.75)",
	
	//Number - The backdrop padding above & below the label in pixels
	scaleBackdropPaddingY : 2,
	
	//Number - The backdrop padding to the side of the label in pixels	
	scaleBackdropPaddingX : 2,
	
	//Boolean - Whether we show the angle lines out of the radar
	angleShowLineOut : true,
	
	//String - Colour of the angle line
	angleLineColor : "rgba(255,255,255,0.3)",
	
	//Number - Pixel width of the angle line
	angleLineWidth : 1,			
	
	//String - Point label font declaration
	pointLabelFontFamily : "'Arial'",
	
	//String - Point label font weight
	pointLabelFontStyle : "normal",
	
	//Number - Point label font size in pixels	
	pointLabelFontSize : 12,
	
	//String - Point label font colour	
	pointLabelFontColor : "#EFEFEF",
	
	//Boolean - Whether to show a dot for each point
	pointDot : true,
	
	//Number - Radius of each point dot in pixels
	pointDotRadius : 3,
	
	//Number - Pixel width of point dot stroke
	pointDotStrokeWidth : 1,
	
	//Boolean - Whether to show a stroke for datasets
	datasetStroke : true,
	
	//Number - Pixel width of dataset stroke
	datasetStrokeWidth : 1,
	
	//Boolean - Whether to fill the dataset with a colour
	datasetFill : true,
	
	//Boolean - Whether to animate the chart
	animation : true,

	//Number - Number of animation steps
	animationSteps : 60,
	
	//String - Animation easing effect
	animationEasing : "easeOutQuart",

	//Function - Fires when the animation is complete
	onAnimationComplete : null,
  
  //make it respond damnit!
  responsive: true
	
}



// 차트 끝
  
	  
  </script>
  
  
  <input name="animation" type="hidden">
  </body>
</html>