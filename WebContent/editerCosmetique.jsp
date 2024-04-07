<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%@include file="header.jsp"%>
	<p></p>
	<div class="container">
		<div class="card">
			<div class="card-header">Modification des Cosmetiques</div>
			<div class="card-body">
				<form action="update.do" method="post">
					<div hidden class="form-group">
						<label class="control-label">ID Cosmetique :</label> <input
							type="text" name="id" class="form-control"
							value="${Cosmetique.idCosmetique}" />
					</div>
					<div class="form-group">
						<label class="control-label">Nom Cosmetique :</label> <input
							type="text" name="nom" class="form-control"
							value="${Cosmetique.nomCosmetique}" />
					</div>
					<div class="form-group">
						<label class="control-label">Prix :</label> <input type="text"
							name="prix" class="form-control" value="${Cosmetique.prix}" />
					</div>
					<div class="form-group">
						<select name="classification" class="form-control">
							<option value="${Cosmetique.classification.idClas}" selected>${Cosmetique.classification.nomClas}</option>
							<c:forEach items="${catModel.classifications}" var="cat">
								<c:if test="${cat.idClas != Cosmetique.classification.idClas}">
									<option value="${cat.idClas}">${cat.nomClas}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<div>
						<button type="submit" class="btn btn-primary">Modifier</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>