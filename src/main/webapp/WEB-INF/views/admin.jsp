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
		  transition: .3s;
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
	
	
	.display-none {
		display: none;
	}
	
	
	
.ui-autocomplete {
	z-index: 9999;
}	
	
	
  </style>
  
  
  
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<section class="engine"><a rel="external" href="https://mobirise.com">easy html web site building software download</a></section><section class="mbr-section extTable1 mbr-after-navbar" id="extTable1-m" style="background-color: rgb(242, 130, 129); padding-top: 120px; padding-bottom: 120px;">

    

    <div class="container">
        <h3 class="mbr-section-title display-2">ADMIN PAGE</h3>
        <div class="row">
        	<h4 class="mbr-section-title display-2" style="text-size:2rem;">Add Farm</h4>
        </div>
        <div class="row">
        	<div class="form-table mbr-valign-top col-md-12  formblock" data-form-type="formoid" style="padding-top: 30px;padding-bottom: 20px;">
                        	<div data-form-alert="true">
                                <div hidden="" data-form-alert-success="true" class="alert alert-form alert-success text-xs-center">Thanks for filling out form!</div>
                            </div>
                            <form id="submitForm" action="${path}admin/setting/add-farm" method="post" data-form-title="INTRO WITH FORM">
                            		<div class="col-xs-12" style="padding-bottom: 5px;">
                                         <input type="text" class="form-control" id="memberId" name="memberId" required="" data-form-field="memberId" placeholder="회원 ID">
                                    </div>
                                    <div class="col-xs-12" style="padding-bottom: 5px;">
                                         <input type="text" class="form-control" id="farmName" name="farmName" required="" data-form-field="farmName" placeholder="농장명">
                                    </div>

                                    <div class="col-xs-12" style="padding-bottom: 5px;">
                                         <select class="form-control" id="location" name="location">
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
                                    
                                    <div class="col-xs-12" style="padding-bottom: 5px;">
                                         <input type="text" class="form-control" id="farmId" name="farmId" required="" data-form-field="farmId" placeholder="농장 ID (ex. PF_0000001)">
                                    </div>
                                    
                                    
                                    <input id="form-submit-button" type="submit" value="Submit" style="float:right;" class="btn btn-primary"
                                    >
                            </form>
                        </div>
        </div>
    </div>

    
</section>

<section class="mbr-section article mbr-section__container" id="content6-d" style="background-color: rgb(255, 255, 255); padding-top: 100px; padding-bottom: 100px;">

    <div class="container">
    	<div class="row" style="text-align:center;">
        	<h4 class="mbr-section-title display-2" style="text-size:2rem;">Add Company</h4>
        </div>
        <div class="row">
            <div class="form-table mbr-valign-top col-md-12  formblock" data-form-type="formoid" style="padding-top: 30px;padding-bottom: 20px;">
                        	<div data-form-alert="true">
                                <div hidden="" data-form-alert-success="true" class="alert alert-form alert-success text-xs-center">Thanks for filling out form!</div>
                            </div>
                            <form id="submitForm" action="${path}admin/setting/add-company" method="post" data-form-title="INTRO WITH FORM">
                            		<div class="col-xs-12" style="padding-bottom: 5px;">
                                         <input type="text" class="form-control" id="memberId" name="memberId" required="" data-form-field="memberId" placeholder="회원 ID">
                                    </div>
                                    <div class="col-xs-12" style="padding-bottom: 5px;">
                                         <input type="text" class="form-control" id="companyName" name="companyName" required="" data-form-field="companyName" placeholder="기업명">
                                    </div>

                                    <div class="col-xs-12" style="padding-bottom: 5px;">
                                         <select class="form-control" id="location" name="location">
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
                                         <input type="text" class="form-control" id="companyId" name="companyId" required="" data-form-field="companyId" placeholder="기업 ID (ex. EN_0000001)">
                                    </div>
                                    
                                    
                                    <input id="form-submit-button" type="submit" value="Submit" style="float:right;" class="btn btn-primary"
                                    >
                            </form>
                        </div>
        </div>
    </div>

</section>

<section class="mbr-section article mbr-section__container" id="content6-d" style="background-color: rgb(2, 117, 216); padding-top: 100px; padding-bottom: 100px;">

    <div class="container">
    	<div class="row" style="text-align:center;">
        	<h4 class="mbr-section-title display-2" style="text-size:2rem;">Add Member</h4>
        </div>
        <div class="row">
            <div class="form-table mbr-valign-top col-md-12  formblock" data-form-type="formoid" style="padding-top: 30px;padding-bottom: 20px;">
                        	<div data-form-alert="true">
                                <div hidden="" data-form-alert-success="true" class="alert alert-form alert-success text-xs-center">Thanks for filling out form!</div>
                            </div>
                            <form id="submitForm" action="${path}admin/setting/add-member" method="post" data-form-title="INTRO WITH FORM">
                            		<div class="col-xs-12" style="padding-bottom: 5px;">
                                         <input type="text" class="form-control" id="memberId" name="memberId" required="" data-form-field="memberId" placeholder="회원 ID">
                                    </div>
                                    <div class="col-xs-12" style="padding-bottom: 5px;">
                                         <input type="password" class="form-control" id="memberPassword" name="memberPassword" required="" data-form-field="memberPassword" placeholder="비밀번호">
                                    </div>

                                    <div class="col-xs-12" style="padding-bottom: 5px;">
                                         <input type="text" class="form-control" id="memberName" name="memberName" required="" data-form-field="memberName" placeholder="회원 이름">
                                    </div>
                                    
                                    
                                    <input id="form-submit-button" type="submit" value="Submit" style="float:right;" class="btn btn-primary"
                                    >
                            </form>
                        </div>
        </div>
    </div>

</section>

<%@include file="includes/footer.jsp" %>

<%@include file="includes/scripts.jsp" %>
  
  
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.js"></script>
  
<!-- id 자동완성 -->
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script>
var availableTags = new Array();
  $( function() {
    $('#farmId').autocomplete({
      source: availableTags
    });
  } );
  
  $(document).ready(function() {
		$.ajax({
			url : "${path}inner/admin/api/farm-id-list",
			type : "post",
			success : function(data) {
				for(var i = 0, len = Object.keys(data).length; i < len;i++) {
		        	availableTags.push(data[i][1]);
		        }
			}
		});
	});

  $('#farmId').on("focusout", function(event) {
	  $.ajax({
			url : "${path}inner/admin/api/farm-info-by-farm-id/" + $(event.target).val(),
			type : "post",
			success : function(data) {
				$('#farmName').val(data[0][1]);
				$('#location').val(data[0][2]);
				$('#scale').val(data[0][3]);
				
				makeForm(data[0][3]);
			}
		});
  });
  
</script>
  
  <input name="animation" type="hidden">
  </body>
</html>