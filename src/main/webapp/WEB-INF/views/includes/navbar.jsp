<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section id="menu-6">

    <nav class="navbar navbar-dropdown bg-color transparent navbar-fixed-top">
        <div class="container">

            <div class="mbr-table">
                <div class="mbr-table-cell">

                    <div class="navbar-brand">
                        <a href="${path}inner/list" class="navbar-logo"><img src="${path}assets/images/logo.png" alt="Mobirise"></a>
                        <a class="navbar-caption" href="${path}inner/list">니팜내팜</a>
                    </div>

                </div>
                <div class="mbr-table-cell">

                    <button class="navbar-toggler pull-xs-right hidden-md-up" type="button" data-toggle="collapse" data-target="#exCollapsingNavbar">
                        <div class="hamburger-icon"></div>
                    </button>

                    <ul class="nav-dropdown collapse pull-xs-right nav navbar-nav navbar-toggleable-sm" id="exCollapsingNavbar">
                    	<c:choose>
                    		<c:when test="${memberVo eq null}">
	                   			<li class="nav-item nav-btn"><a class="nav-link btn btn-white btn-white-outline" href="${path}">Login</a></li>
                    		</c:when>
                    		<c:otherwise>
                    			<li class="nav-item nav-link link">${memberVo.name}(${memberVo.id})님 접속중</li>
                    			<c:choose>
                    				<c:when test="${memberVo.admin eq true}">
                    					<c:choose>
                    						<c:when test="${state eq 'farm'}">
                    							<li class="nav-item nav-btn"><a class="nav-link btn btn-white btn-white-outline" href="${path}inner/company/list">Company</a></li>
                    						</c:when>
                    						<c:otherwise>
                    							<li class="nav-item nav-btn"><a class="nav-link btn btn-white btn-white-outline" href="${path}inner/list">Farm</a></li>
                    						</c:otherwise>
                    					</c:choose>
                    					<li class="nav-item nav-btn"><a class="nav-link btn btn-white btn-white-outline" href="${path}admin/setting">Admin</a></li>
                    				</c:when>
                    			</c:choose>
	                   			<li class="nav-item nav-btn"><a class="nav-link btn btn-white btn-white-outline" href="${path}logout">Logout</a></li>
                    		</c:otherwise>
                    	</c:choose>
	                    
                    </ul>
                    <button hidden="" class="navbar-toggler navbar-close" type="button" data-toggle="collapse" data-target="#exCollapsingNavbar">
                        <div class="close-icon"></div>
                    </button>
                </div>
            </div>

        </div>
    </nav>

</section>