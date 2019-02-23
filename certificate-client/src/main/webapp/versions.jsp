<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OFI Auditdatei Upload Ergebnis</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="navigation.jsp" />

	<div class="table-responsive">
	<table class="table table-striped"">
		<tr>
			<th>Erstellungsdatum</th>
			<th>Ersteller</th>
		</tr>
	
		<c:forEach var="entry" items="${versions}">
		<tr>
			<td>${entry.imported}</td>
			<td>${entry.importer}</td>
		</tr>
		</c:forEach>

	</table>
	</div>
</body>
</html>