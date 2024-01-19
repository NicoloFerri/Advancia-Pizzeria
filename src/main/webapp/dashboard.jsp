<%@page import="java.util.List"%>
<%@page import="model.Ingrediente"%>
<%@page import="model.Pizza"%>
<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="model.User"%>
<%@page import="DAO.DAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Dashboard</title>
<style>
.table-container {
	float: left;
	/* Imposta il float per posizionare le tabelle affiancate */
	margin-right: 20px; /* Aggiungi margini a tua discrezione */
}
</style>
</head>
<body>
<body>


<form action="DashboardServlet" method="post">
	<div class="table-container">
		<table border="1">
			<thead>
				<tr>
					<th></th>
					<th><h1>Impasto</h1></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="radio" name="dough" value="Classico"></td>
					<td>Classico</td>
				</tr>
				<tr>
					<td><input type="radio" name="dough" value="Integrale"></td>
					<td>Integrale</td>
				</tr>
				<tr>
					<td><input type="radio" name="dough" value="Sottile"></td>
					<td>Sottile e Croccante</td>

				</tr>
				<tr>
					<td><input type="radio" name="dough" value="Piatto"></td>
					<td>Piatto</td>

				</tr>
				<tr>
					<td><input type="radio" name="dough" value="Pugliese"></td>
					<td>Pugliese</td>

				</tr>
			</tbody>
		</table>
	</div>

	<div class="table-container">
		<table border="1">
			<thead>
				<tr>
					<th></th>
					<th><h1>Ingredienti</h1></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="checkbox" name="ingredient" value="Pomodoro"></td>
					<td>Pomodoro</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="ingredient" value="Mozzarella"></td>
					<td>Mozzarella</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="ingredient" value="Funghi"></td>
					<td>Funghi</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="ingredient" value="Peperoni"></td>
					<td>Peperoni</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="ingredient" value="Oliva"></td>
					<td>Oliva</td>
				</tr>
			</tbody>
		</table>
	</div>

	<%
	Object userId = request.getAttribute("userObj");
	%>
	

	<br style="clear: both;">
    <input type="hidden" name="userObj" value="<%= userId %>">
	<input type="text" id="pizzaName" name="pizzaName" required>
	<br>
	<input type="submit" value="Insert Pizza">
	</form>

	<h3>Lista Pizze</h3>
	<table border=\ "1\" cellspacing=\ "10\" cellpadding=\ "5\" style="""text-align:center;\">
		<tr>
			<th>Nome Pizza</th>
			<th>Impasto</th>
			<th>Ingredienti</th>
		</tr>
 	
 	<%
 	List<String> pizzas = (List<String>) request.getAttribute("listOfPizza");
 	List<List<String>> ingredients = (List<List<String>>) request.getAttribute("listOfIngredienti");
 	List<String> impasti = (List<String>) request.getAttribute("listOfImpasti");
 	%>
	<tr>
	 	<%
	 	if(pizzas!=null){
	 		for( int i=0 ; i<pizzas.size() ; i++){
	 			
	 	%>    
	       <tr><th><%= pizzas.get(i) %></th>
	       <th><%= impasti.get(i) %></th>
	       <th>
                        <% List<String> ingredientList = ingredients.get(i);
                               for (String ingredient : ingredientList) { 
                               %>
                                   <%= ingredient + ", "%>
                        <%
                        }
                           } %>
                    </th>
     <%
     }
     %>
	</tr>

	
		
	</table>

</body>
</html>
