<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发表微博</title>
</head>
<body>
	<form action="weiboPost" method="post" enctype="multipart/form-data">
		<input id="text" name="weibo.text" type="text" />
		<input id="imageName" name="imageName" type="text" hidden="hidden" />
		<s:file id="image" name="image"
			onChange="if(this.value) insertImageName(this.value);"></s:file>
		<input type="submit" />
	</form>

	<script>
		function insertImageName(tValue) {
			var t1 = tValue.lastIndexOf("\\");
			if (t1 >= 0 && t1 < tValue.length) {
				document.getElementById("imageName").value = tValue.substring(
						t1 + 1, tValue.length);
			}
		}
	</script>
</body>
</html>
