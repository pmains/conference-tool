<jsp:directive.include file="/templates/includes.jsp"/>
<jsp:directive.include file="/templates/header.jsp"/>

<link rel="stylesheet" type="text/css" href="styles.css" />

<h1>Webex</h1>
<div class="conference">
    <div class="alert">Are you sure you want to delete this conference?</div>
    <div class="summary">
        <h2 class="title">${conference.confName}</h2>
        <div class="date">${conference.startDate}</div>
        <div class="date">Duration: ${conference.duration} minutes</div>
        <div class="description">${conference.agenda}</div>
    </div>
    <div class="participants">
        <h2>Host</h2>
        <div class="host">James Wilson</div>
        <div class="attendee">
        <c:forEach var="attendeeId" items="${conference.attendeeIds}">
            <div class="attendee">Bill Smith</div>
        </c:forEach>
        </div>
    </div>
    <div class="buttons">
        <form action="delete.htm">
            <input type="hidden" name="id" value="${conference.id}" />
            <button type="submit" value="delete">DELETE</button>
        </form>
    </div>
</div>
<jsp:directive.include file="/templates/footer.jsp"/>
