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
	<jsp:include page="navigation.jsp" />
	<div class="jumbotron">
		<h1 class="display-4">OFI Auditdatei Upload</h1>
		<p class="lead">Applikaton zur Verwaltung von Zertifikaten</p>
		<hr class="my-4">
	
		<form action="upload" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="uploadFile">Wählen Sie die Auditdatei (*.xlsx, *.csv) von Ihrem Rechner aus.</label>
				<input id="uploadFile" class="form-control-file" name="uploadFile" type="file" size="50" accept="*/*">
			</div>
			<input type="submit" class="btn btn-primary" />
		</form>
	</div>

</body>
</html>