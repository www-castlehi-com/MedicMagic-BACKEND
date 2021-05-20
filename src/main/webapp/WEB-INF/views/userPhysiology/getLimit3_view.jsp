<%--
  Created by IntelliJ IDEA.
  User: 박수민
  Date: 2021-05-20
  Time: 오전 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%=request.getAttribute("size")%>
<%= request.getAttribute("startPhysiology1") %> <%= request.getAttribute("endPhysiology1") %> <%= request.getAttribute("expectedOvulationDate1")%> <%=request.getAttribute("expectedPhysiologyDate1")%>
<%= request.getAttribute("startPhysiology2") %> <%= request.getAttribute("endPhysiology2") %> <%= request.getAttribute("expectedOvulationDate2")%> <%=request.getAttribute("expectedPhysiologyDate2")%>
<%= request.getAttribute("startPhysiology3") %> <%= request.getAttribute("endPhysiology3") %> <%= request.getAttribute("expectedOvulationDate3")%> <%=request.getAttribute("expectedPhysiologyDate3")%>