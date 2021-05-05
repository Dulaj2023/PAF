<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="model.Funding"%>


<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"
	integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js"
	integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
	crossorigin="anonymous"></script>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<%
	Funding fundObj = new Funding();
String stsMsg = "";

// insert 
if (request.getParameter("funderName") != null) {

	stsMsg = fundObj.insertFund(request.getParameter("funderName"), request.getParameter("fundDate"),
	request.getParameter("fundPrice"), request.getParameter("fundCate"), request.getParameter("fundDesc"));

	session.setAttribute("statusMsh", stsMsg);

}
%>


<%
	if(request.getParameter("fundId")!= null){
		
		Funding fundObj2 = new Funding();
		
		String stsMsg1 = fundObj2.deleteFund(request.getParameter("fundId"));
		
		session.setAttribute("statusMsg", stsMsg1);
		
	}

%>

<body>

	<div class="container">
		<div class="row">
			<div class="col">

				<form id="formFund" name="formFund" method="post"
					action="funding.jsp">
					<div class="form-row">

						<div class="form-group col-md-6">
							<label for="inputName">Funder Name</label> <input type="text"
								class="form-control" id="funderName" name="funderName"
								placeholder="">
						</div>

						<div class="form-group col-md-6">
							<label for="example-date-input" class="col-2 col-form-label">Date</label>
							<div class="col-10">
								<input class="form-control" type="date" value="2011-08-19"
									id="fundDate" name="fundDate">
							</div>
						</div>

						<div class="form-group">
							<label for="inputPrice">Fund Price</label> <input type="text"
								class="form-control" id="fundPrice" name="fundPrice"
								placeholder="">
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="inputCategory">Fund category</label> <input
									type="text" class="form-control" id="fundCate" name="fundCate">
							</div>
						</div>
						<div class="form-group">
							<label for="exampleFormControlTextarea1">Description</label>
							<textarea class="form-control" id="fundDesc" name="fundDesc" rows="3"></textarea>
						</div>


						<button type="submit" name="btnSav" class="btn btn-primary" value="save">ADD</button>

				</form>


			</div>

			<%
				Funding fundObj1 = new Funding();

			out.print(fundObj1.readFund());
			%>


		</div>
	</div>
	</div>



</body>
</html>