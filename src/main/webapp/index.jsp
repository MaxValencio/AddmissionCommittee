<html>
<body>
<h2>Control Panel</h2>

 <form action="${pageContext.request.contextPath}/registrate" method="post">
 	<button type="submit">Add new Account</button>
 </form>

 <form action="${pageContext.request.contextPath}/show-accounts-all" method="post">
 	<button type="submit">Get all Accounts</button>
 </form>

 <form action="${pageContext.request.contextPath}/show-accounts-by-id" method="post">
 	<button type="submit">Get Accounts by Id (1)</button>
 </form>

 <form action="${pageContext.request.contextPath}/add-faculty" method="post">
 	<button type="submit">Add new Faculty</button>
 </form>

 <form action="${pageContext.request.contextPath}/show-faculties-all" method="post">
 	<button type="submit">Get All Faculties</button>
 </form>

 <form action="${pageContext.request.contextPath}/add-faculty-by-id" method="post">
 	<button type="submit">Get Faculty by Id (1)</button>
 </form>

</body>
</html>
