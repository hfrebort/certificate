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

	<div class="jumbotron">
		<h1 class="display-4">OFI Auditdatei Upload</h1>
		<p class="lead">Applikaton zur Verwaltung von Zertifikaten</p>
		<hr class="my-4">
		<h2>Status: ${result.status}</h2>
		<p>${result.message}</p>
		<p>Um die aktuellen Ergebnisse zu sehen, klicken sie <a href="find">hier</a></p>
	</div>

</body>
</html>