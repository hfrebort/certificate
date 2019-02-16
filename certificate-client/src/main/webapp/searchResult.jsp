<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OFI Auditdatei Upload</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
  <h2>Ergebnisse</h2>
	<div class="table-responsive">
	<table class="table">
		<tr>
			<th>Zertifikatsnummer</th>
			<th>Hersteller</th>
			<th>Ansprechpartner</th>
			<th>Produktbeschreibung</th>
			<th>Auditor</th>
			<th>Normen</th>
			<th>Branche</th>
		</tr>
	
		<c:forEach var="entry" items="${certificateEntries}">
		<tr>
			<td>${entry.certNumber}</td>
			<td>${entry.manufacturer}</td>
			<td>${entry.manufacturerContact}</td>
			<td>${entry.productDescription}</td>
			<td>${entry.auditor}</td>
			<td>${entry.norms}</td>
			<td>${entry.industry}</td>
		</tr>
		</c:forEach>

	</table>
	</div>
  </body>
</html>