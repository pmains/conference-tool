<jsp:directive.include file="/templates/includes.jsp"/>
<jsp:directive.include file="/templates/header.jsp"/>

<link rel="stylesheet" type="text/css" href="styles.css" />

<h1>Webex</h1>
<div class="conference">
    <div class="alert">Are you sure you want to delete this conference?</div>
    <div class="summary">
        <h2 class="title">${conference.title}</h2>
        <div class="date">${conference.startDate}</div>
        <div class="date">${conference.endDate}</div>
        <div class="description">${conference.description}</div>
        <div class="url closed">${conference.url}</div>
    </div>
    <div class="participants">
        <h2>Host</h2>
        <div class="host">James Wilson</div>
        <div class="invitees">
        <c:forEach var="inviteeId" items="${conference.inviteeIds}">
            <div class="invitee">Bill Smith</div>
        </c:forEach>
        </div>
    </div>
    <div class="buttons"><button value="delete">DELETE</button></div>
</div>
<jsp:directive.include file="/templates/footer.jsp"/>
