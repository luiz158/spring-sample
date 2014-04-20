<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1>
    <fmt:message key="accounts.show.title"/>
</h1>

<ul class="controlLinks">
    <li>
        <c:url var="newSearchUrl" value="search"/>
        <a href="${newSearchUrl}">
            <fmt:message key="command.newSearch"/>
        </a>
    </li>
</ul>

<div id="entityDetails">
    <div id="accountDetails">
        <ul>
            <li>
                <fmt:message key="label.Account.number"/>: ${account.id}
                <spring:url var="editUrl" value="{number}/edit">
                    <spring:param name="number" value="${account.id}"/>
                </spring:url>
                (<a id="editUrl" href="${editUrl}"><fmt:message key="command.edit"/></a>)
            </li>
            <li>
                <fmt:message key="label.Account.name"/>: ${account.name}
            </li>
            <li>
                <fmt:message key="label.Account.dateOfBirth"/>: <fmt:formatDate value="${account.dateOfBirth}"/>
            </li>
            <li>
                <fmt:message key="label.Account.email"/>: ${account.email}
            </li>
        </ul>
    </div>
</div>