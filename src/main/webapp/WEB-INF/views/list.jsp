<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>

<!DOCTYPE html>
<html>
<head>
  <!-- Site made with Mobirise Website Builder v3.8.5, https://mobirise.com -->
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="generator" content="Mobirise v3.8.5, mobirise.com">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="shortcut icon" href="assets/images/kakaotalk-20161105-173132001-256x128-53.png" type="image/x-icon">
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
  
		  .filebox label {
		  display: inline-block;
		  padding: .5em .75em;
		  color: #999;
		  font-size: inherit;
		  line-height: normal;
		  vertical-align: middle;
		  background-color: #fdfdfd;
		  cursor: pointer;
		  border: 1px solid #ebebeb;
		  border-bottom-color: #e2e2e2;
		  border-radius: .25em;
		}
		
		.filebox input[type="file"] {  /* 파일 필드 숨기기 */
		  position: absolute;
		  width: 1px;
		  height: 1px;
		  padding: 0;
		  margin: -1px;
		  overflow: hidden;
		  clip:rect(0,0,0,0);
		  border: 0;
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
		
		
		
		
	#scaleChartdiv {
		width		: 100%;
		height		: 500px;
		font-size	: 14px;
	}
	#locationChartdiv {
		width		: 100%;
		height		: 500px;
		font-size	: 14px;
	}				
  </style>
  
  
  
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<section class="engine"><a rel="external" href="https://mobirise.com">easy html web site building software download</a></section><section class="mbr-section extTable1 mbr-after-navbar" id="extTable1-m" style="background-color: rgb(150, 207, 167); padding-top: 120px; padding-bottom: 120px;">

    

    <div class="container">
        <h3 class="mbr-section-title display-2">TABLE</h3>
    </div>

    <div class="container table-wrapper" data-search="true" search-text="Search:" info1-text="Showing" info2-text="entries" info3-text="(filtered from" info4-text="total entries)">
        
        <table class="table" cellspacing="0" width="100%">
        <thead>
        	<tr><th>번호</th><th>농장명</th><th>위치</th><th>규모</th><th>날짜</th><th>점검결과</th></tr>
        </thead>
	        <tbody>

        	</tbody>
        </table>
    </div>
    <div class="container">
    	<div class="col-md-10"></div>
    	<div class="col-md-2"><input type="button" class="nav-link btn btn-white btn-white-outline" value="add new" data-toggle="modal" data-target="#farmModal"></div>
    </div>
</section>

<section class="mbr-section article mbr-section__container" id="content6-d" style="background-color: rgb(255, 255, 255); padding-top: 20px; padding-bottom: 20px;">

    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-md-6 lead"><div id="scaleChartdiv"></div></div>
            <div class="col-xs-12 col-md-6 lead"><div id="locationChartdiv"></div></div>
        </div>
    </div>

</section>

<!-- 농장모달 -->
<div class="modal fade" id="farmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Add Farm to Check List</h4>
      </div>
        <div class="form-table mbr-valign-top col-md-12  formblock" data-form-type="formoid" style="padding-top: 30px;padding-bottom: 20px;">
                        	<div data-form-alert="true">
                                <div hidden="" data-form-alert-success="true" class="alert alert-form alert-success text-xs-center">Thanks for filling out form!</div>
                            </div>
                            <form id="submitForm" action="submit-checklist" method="post" enctype="multipart/form-data" data-form-title="INTRO WITH FORM">
                                    <div class="col-xs-12" style="padding-bottom: 5px;">
                                         <input type="text" class="form-control" name="farmName" required="" data-form-field="farmName" placeholder="농장명">
                                    </div>
                                    <div class="col-xs-12" style="padding-bottom: 5px;">
                                         <input type="text" class="form-control" name="farmerId" required="" data-form-field="farmerId" placeholder="농장주 ID">
                                    </div>

                                    <div class="col-xs-12" style="padding-bottom: 5px;"> 
                                         <input type="text" id="datepicker" class="form-control" name="checkDate" required="" data-form-field="checkDate" placeholder="점검일">    
                                    </div>

                                    <div class="col-xs-12" style="padding-bottom: 5px;">
                                         <select class="form-control" name="location">
                                          <option>지역</option>
										  <option>서울</option>
										  <option>경기</option>
										  <option>강원</option>
										  <option>충남</option>
										  <option>충북</option>
										  <option>경북</option>
										  <option>경남</option>
										  <option>전북</option>
										  <option>전남</option>
										  <option>제주</option>
										</select>
                                    </div>

                                    <div class="col-xs-12" style="padding-bottom: 5px;">
                                         <select class="form-control" name="scale" id="scale">
                                          <option>규모</option>
										  <option>대규모</option>
										  <option>중규모</option>
										  <option>소규모</option>
										</select>
                                    </div>
                                    <!-- 규모별 질문 -->
                                    <div class="col-xs-12" style="margin-top: 20px;">
                                    	<table id="scale-target" style="width:100%;">
                                    		
                                    	</table>
                                    </div>
                                    <input id="form-submit-button" type="submit" value="Submit" style="float:right;" class="btn btn-primary"
                                    >
                            </form>
                        </div>
      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>

<div class="modal fade bs-example-modal-sm" id="loadingModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm" style="width: 100%; height:90%; backgound:rgba(0, 0, 0, 0.4);">
    <div class="modal-content" style="border: 0px; background: rgba(0, 0, 0, 0);">
      <div class="loading-bro">
		 <h1>Sending..</h1>
		  <svg id="load" x="0px" y="0px" viewBox="0 0 150 150">
		    <circle id="loading-inner" cx="75" cy="75" r="60"/>
		 </svg>
		</div>
    </div>
  </div>
</div>

<%@include file="includes/scripts.jsp" %>
  
  
  <script>
  $(document).ready(function() {
	  $.ajax({
		    url : "${path}inner/admin/api/dataTable",
		    dataType : "json",
		    type : "post",
		    success: function(data) {
		        var t = $('.dataTable').DataTable();
		        
		        console.log(Object.keys(data).length);
		        for(var i = 0, len = Object.keys(data).length; i < len;i++) {
		        	t.row.add( [
		        	            data[i][0],
		        	            data[i][1],
		        	            data[i][2],
		        	            data[i][3],
		        	            data[i][4],
		        	            "<a href='${path}inner/result/" + data[i][0] + "/" + data[i][5] +  "'>" + "<span class='glyphicon glyphicon-ok' aria-hidden='true'></span></a>"
		        	]).draw(false);
		        }
		    },
		    error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"error:"+error);
		    }
		});
	} );
  
  // Ajax 폼 전송
 $(function() {
		$('#submitForm').ajaxForm({
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			processData: false,
			dataType: 'json',
			beforeSend: function() {
				console.log('성공했을때..');
				$('#loadingModal').modal('show');
			},
			complete: function(data) {
				$('#loadingModal').modal('hide');
				$('#farmModal').modal('hide');
				console.log('실패했을때.....');
			}
		});
	});

  
  $(function() {
	  $( "#datepicker" ).datepicker({
	    dateFormat: 'yy-mm-dd'
	  });
	});
  
  $("#scale").change(function(){
      console.log($(this).val());
      makeForm($(this).val());
});
  
function makeForm(scale) {
	var request_scale = 0;
	if(scale !== "규모") {
		if(scale === "대규모")
			request_scale = 3;
		else if(scale === "중규모")
			request_scale = 2;
		else if(scale === "소규모")
			request_scale = 1;
		
		$.ajax({
		    url : "${path}inner/admin/api/check-form-info/" + request_scale,
		    dataType : "json",
		    type : "post",
		    success: function(data) {
		        $('#scale-target').empty();
		        var code = "";
		        var category = new Array(13);
		        var table_header = "<tr><th style='width:40%;'>세부내용</th><th style='width:5%;'></th><th style='width:30%;'>점검기준</th><th style='width:5%;'>Y</th><th style='width:5%;'>P</th><th style='width:5%;'>N</th><th style='width:10%;'></th></tr>";
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
		        		"<td><input type='radio' name='ypn_" + data[i][0] +"' value='Y'></td>" +
		        		"<td><input type='radio' name='ypn_" + data[i][0] +"' value='P'></td>" +
		        		"<td><input type='radio' name='ypn_" + data[i][0] +"' value='N'></td>" +
		        		"<td><div class='filebox'><label for='file_" + data[i][0] + "'>업로드</label><input type='file' id='file_" + data[i][0] + "' name='file_" + data[i][0] + "'></td>" + 
		        		"</tr>";
		        }
		        for(var i = 0; i < 13; i++) {
		        	code += category[i];
		        }
				$('#scale-target').html(code);
				console.log(data);
		    },
		    error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"error:"+error);
		    }
		}); 
	}
	else {
		$('#scale-target').empty();
	}
	
}
  
  </script>
  
  
  <script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
<script src="https://www.amcharts.com/lib/3/pie.js"></script>
<script src="https://www.amcharts.com/lib/3/themes/light.js"></script>
  <script>
  var scaleChart = AmCharts.makeChart( "scaleChartdiv", {
	  "type": "pie",
	  "theme": "light",
	  "dataProvider": [ {
	    "country": "대규모",
	    "litres": 1
	  }, {
	    "country": "중규모",
	    "litres": 4
	  }, {
	    "country": "소규모",
	    "litres": 1
	  } ],
	  "valueField": "litres",
	  "titleField": "country",
	   "balloon":{
	   "fixedPosition":true
	  },
	  "export": {
	    "enabled": true
	  }
	} );
  var locationChart = AmCharts.makeChart( "locationChartdiv", {
	  "type": "pie",
	  "theme": "light",
	  "dataProvider": [ {
	    "country": "서울",
	    "litres": 1
	  }, {
	    "country": "강원",
	    "litres": 7
	  }, {
	    "country": "경기",
	    "litres": 6
	  }, {
		"country": "충남",
	 	"litres": 3
	  }, {
		"country": "충북",
		"litres": 10
	  }, {
		"country": "경북",
		"litres": 14
	  }, {
		"country": "경남",
		"litres": 21
	  }, {
		"country": "전북",
		"litres": 11
	  }, {
		"country": "전남",
		"litres": 90
	  }, {
		"country": "제주",
		"litres": 60
	  } ],
	  
	  "valueField": "litres",
	  "titleField": "country",
	   "balloon":{
	   "fixedPosition":true
	  },
	  "export": {
	    "enabled": true
	  }
	} );
  </script>
  
		  
  
  <input name="animation" type="hidden">
  </body>
</html>