  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>Your Course Information -- Student Registration System</title>

</head>
<body style="text-align: center" bgcolor="#ffff00">
    <span style="font-size: 14pt; font-family: Arial"><strong>Hello, Sir. Your ID is
        <%=session.getValue("login")%>. Here is Your Course Information.<br />
        <br />
        <table border="0" cellpadding="0" cellspacing="0" style="width: 100%; height: 100%">
            <tr>
                <td style="vertical-align: top; width: 11237px; text-align: left; height: 454px;">
                    <span style="font-size: 10pt">
                    This is the table for the courses in which you enrolled.<br />
                    You can delete the ones which do not have a grade.<br />
                        And you can search new courses by click the "Search" button below.<br />
                        You may engage in at most 10 courses.<br />
                    </span><br />
                    <table border="8" id="TABLE1" onclick="return TABLE1_onclick()">
                    <tr>
                      <td style="width: 84px">
                          <span style="font-size: 10pt">
                          Course Code</span></td>
                      <td style="width: 187px">
                          <span style="font-size: 10pt">Course Name</span></td>
                        <td style="width: 74px">
                            <span style="font-size: 10pt">Department</span></td>
                        <td>
                            <span style="font-size: 10pt">Professor</span></td>
                        <td style="width: 7px">
                            <span style="font-size: 10pt">Grade</span></td>
                        <td style="width: 7px">
                            <span style="font-size: 10pt">Oper</span></td>
                    </tr>   





<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${list2}" var="item">
 
 	
      <% String stuId = "" + session.getValue("login"); %>
     
     
        
         <tr>
                      <td style="width: 84px">
                          <span style="font-size: 10pt">${item.item1}</span></td>
                      <td style="width: 187px">
                          <span style="font-size: 10pt">${item.item2}</span></td>
                        <td style="width: 74px">
                            <span style="font-size: 10pt">${item.item3}</span></td>
                        <td>
                            <span style="font-size: 10pt">${item.item4}</span></td>
                        <td style="width: 7px">
                            <span style="font-size: 10pt">${item.item5}</span></td>
                        <td>
                        
                        	
                        	<form name="myForm" action="delcourse" method="post">
    <input type="hidden" name="userid" value=<%=stuId%>>
    <input type="hidden" name="crscode" value=${item.item1} >  
   <input id="Button2" type="submit" value="Delete" />

</form>
                        
                        </td>
                        		
                    </tr>
        
        
        
        
        
        
        
        
    </c:forEach>



  					</table>
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <input id="Button1" type="button" value="Search Course" onclick="window.open('SearchCourse.htm','_self');" />
                    <input id="Button1" type="button" value="Logout" onclick="window.open('index.htm','_self');" /><br />
                    <span style="font-size: 8pt">
                        <br />
                        Shang Yang, 10/19/2006, Demo Version<br />
                        Department of Computer Science,<br />
                        SUNY at Stony Brook</span></td>
                <td style="width: 292px; height: 454px; vertical-align: top; text-align: left;">
                    <img src="Hospital.JPG" /></td>
            </tr>
        </table>
    </strong></span>

</body>
</html>
