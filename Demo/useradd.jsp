<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
       
    </tr>
    <c:forEach items="${list}" var="item">
        <tr>
            <td>${item.crscode}</td>
            <td><c:out value="${item.crsname}" /></td>
            <td>${item.deptid}</td>
            <td><c:out value="${item.name}" /></td>
            
           
        </tr>
    </c:forEach>
</table>
User Added!
<a href="index.htm"><font color="Blue">Home</font></a>


