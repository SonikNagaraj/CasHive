<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.BankApplication.model.AccountsModel"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>

	<%
		ArrayList list = (ArrayList) session.getAttribute("list");
	%>

	<table class="table table-striped table-dark">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">Txn_ref_number</th>
				<th scope="col">Account_number</th>
				<th scope="col">Date</th>
				<th scope="col">Time</th>
				<th scope="col">Description</th>
				<th scope="col">Withdrawals</th>
				<th scope="col">Credit</th>
				<th scope="col">Running balance</th>
			</tr>
		</thead>
		<tbody>
			<%
				int index = 1;
			for (int i = 0; i < list.size(); i++) {
			%>
			<tr>
				<th scope="row"><%=index%></th>
				<%
					AccountsModel obj = (AccountsModel) list.get(i);
				%>
				<td><%=obj.getTxn_ref_number()%></td>
				<td><%=obj.getAccount_number()%></td>
				<td><%=obj.getDate_()%></td>
				<td><%=obj.getTime_()%></td>
				<td><%=obj.getDesciption_()%></td>
				<td><%=obj.getWithdrawals()%></td>
				<td><%=obj.getCredit()%></td>
				<td><%=obj.getRunning_Balance()%></td>
			</tr>
			<%
				index++;
			%>
			<%
				}
			;
			%>
		</tbody>
	</table>

</body>
</html>