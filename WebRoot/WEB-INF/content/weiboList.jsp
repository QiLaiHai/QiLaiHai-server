<%@page import="qilaihai.domain.Weibo"%>
<%@page import="java.util.List"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>

<%
	@SuppressWarnings("unchecked")
	List<Weibo> weiboList = (List<Weibo>) ActionContext.getContext()
	        .get("weiboList");
%>

<json:object>
	<json:property name="status" value="success" />
	<json:array name="weibos" var="weibo" items="${weiboList}">
		<json:object>
			<json:property name="text" value="${weibo.getText()}" />
			<json:property name="id" value="${weibo.getId()}" />
			<json:property name="picture" value="${weibo.getPicture()}" />
			<json:property name="agreement" value="${weibo.getAgreement()}" />
			<json:property name="disagreement" value="${weibo.getDisagreement()}" />
			<json:property name="postTime" value="${weibo.getPostTime()}" />
			<json:object name="poster">
				<json:property name="id" value="${weibo.getPoster().getId()}" />
				<json:property name="nickName" value="${weibo.getPoster().getNickName()}" />
			</json:object>
		</json:object>
	</json:array>
</json:object>
