<jsp:directive.include file="/templates/includes.jsp"/>
<jsp:directive.include file="/templates/header.jsp"/>

<link rel="stylesheet" type="text/css" href="styles.css" />

<h1>Webex</h1>
<h2>Conference Added</h2>
<div class="conference">
    <div class="summary">
        <h2 class="title">${conference.confName}</h2>
        <div class="date start">${conference.startDate.toString()}</div>
        <div class="date end">Duration: ${conference.duration} minutes.</div>
        <div class="agenda">${conference.agenda}</div>
        <div class="url"><a href="http://www.webex.com" target="_new">Go to conference</a></div>
    </div>
    <div class="participants">
        <h2>Host</h2>
        <div class="host">James Wilson</div>
        <div class="attendees">
        <c:forEach var="attendee" items="${conference.attendeeIds}">
            <div class="attendee">Bill Smith</div>
        </c:forEach>
        </div>
    </div>
    <div class="buttons"><button value="edit">Edit</button> <button value="delete">Delete</button></div>
</div>
<jsp:directive.include file="/templates/footer.jsp"/>
