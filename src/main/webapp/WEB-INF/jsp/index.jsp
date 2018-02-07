
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<header></header>
<body>

<form action="">
<table width="80%" ><tr><td><input type="text" name="dateStr" value="${dateStr}"/><input type="submit" value="查询"></td><td>${dateStr}</td></tr></table>
</form>
<table width="80%" border="1">
    <tr><td colspan="7" align="center">btc.top</td></tr>
    <tr><td>用户</td><td>昨日BTC收益</td><td>昨日BCH收益</td><td>24小时算力</td><td>昨日BTC收益/P</td><td>昨日BCH收益/P</td><td>记录时间</td></tr>
    <c:forEach items="${top}" var="info">
        <c:if test="${info.prv>0}">
        <tr>
            <td>${info.mbSubUser.name}</td> <td><fmt:formatNumber value="${info.btc}" pattern="#.####"/></td> <td><fmt:formatNumber value="${info.bch}" pattern="#.####"/></td><td><fmt:formatNumber value="${info.prv/1000000000000000}" pattern="#.####"/></td>
            <td><fmt:formatNumber value="${info.btc/(info.prv/1000000000000000)}" pattern="#.####"/></td> <td><fmt:formatNumber value="${info.bch/(info.prv/1000000000000000)}" pattern="#.####"/></td><td><fmt:formatDate value="${info.recTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
        </c:if>
        <c:if test="${info.prv==0}">
            <tr>
                <td>${info.mbSubUser.name}</td> <td><fmt:formatNumber value="${info.btc}" pattern="#.####"/></td> <td><fmt:formatNumber value="${info.bch}" pattern="#.####"/></td><td>0</td>
                <td>0</td> <td>0</td><td><fmt:formatDate value="${info.recTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
        </c:if>
    </c:forEach>
    <tr><td colspan="7" align="center">btc.com</td></tr>
    <tr><td>用户</td><td>昨日BTC收益</td><td>昨日BCH收益</td><td>24小时算力</td><td>昨日BTC收益/P</td><td>昨日BCH收益/P</td><td>记录时间</td></tr>
    <c:forEach items="${com}" var="info">
        <c:if test="${info.prv>0}">
            <tr>
                <td>${info.mbSubUser.name}</td> <td><fmt:formatNumber value="${info.btc}" pattern="#.####"/></td> <td><fmt:formatNumber value="${info.bch}" pattern="#.####"/></td><td><fmt:formatNumber value="${info.prv/1000000000000000}" pattern="#.####"/></td>
                <td><fmt:formatNumber value="${info.btc/(info.prv/1000000000000000)}" pattern="#.####"/></td> <td><fmt:formatNumber value="${info.bch/(info.prv/1000000000000000)}" pattern="#.####"/></td><td><fmt:formatDate value="${info.recTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
        </c:if>
        <c:if test="${info.prv==0}">
            <tr>
                <td>${info.mbSubUser.name}</td> <td><fmt:formatNumber value="${info.btc}" pattern="#.####"/></td> <td><fmt:formatNumber value="${info.bch}" pattern="#.####"/></td><td>0</td>
                <td>0</td> <td>0</td><td><fmt:formatDate value="${info.recTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
        </c:if>
    </c:forEach>
</table>
</body>
</html>