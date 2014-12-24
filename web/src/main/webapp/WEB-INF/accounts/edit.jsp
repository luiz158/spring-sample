<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1>
    <fmt:message key="accounts.edit.title"/>
</h1>

<div id="accountDetails">
    <spring:url value="/accounts/{number}" var="accountUrl">
        <spring:param name="number" value="${account.id}"/>
    </spring:url>
    <form:form modelAttribute="account" action="${accountUrl}" method="POST">
        <fieldset>
            <legend>
                <fmt:message key="accounts.edit.legend"/>
            </legend>
            <ul>
                <li>
                    <form:label path="name">
                        <fmt:message key="label.Account.name"/>
                    </form:label>

                    <div class="control">
                        <form:input path="name"/>
                        <fmt:message key="NotEmpty.account.name" var="errorMessage"/>
                        <form:errors cssClass="error" path="name"/>
                    </div>
                </li>
                <li>
                    <form:label path="dateOfBirth">
                        <fmt:message key="label.Account.dateOfBirth"/>
                    </form:label>

                    <div class="control">
                        <form:input path="dateOfBirth" placeholder="YYYY-MM-DD"/>
                        <fmt:message key="NotNull.account.dateOfBirth" var="errorMessage"/>
                        <form:errors cssClass="error" path="dateOfBirth"/>
                    </div>
                </li>
                <li>
                    <form:label path="email">
                        <fmt:message key="label.Account.email"/>
                    </form:label>

                    <div class="control">
                        <form:input path="email"/>
                        <fmt:message var="errorMessage" key="Pattern.account.email"/>
                        <form:errors cssClass="error" path="email"/>
                    </div>
                </li>
            </ul>

            <button id="saveButton" type="submit">
                <fmt:message key="command.save"/>
            </button>

            <a href="${accountUrl}">
                <fmt:message key="command.cancel"/>
            </a>

        </fieldset>
    </form:form>
</div>