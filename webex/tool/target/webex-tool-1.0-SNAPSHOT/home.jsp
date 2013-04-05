<jsp:directive.include file="/templates/includes.jsp"/>
<jsp:directive.include file="/templates/header.jsp"/>

<link rel="stylesheet" type="text/css" href="styles.css" />

<h1>Webex</h1>
<div id="addConference"><a href="add.htm">Add New Conference</a></div>
<div class="conferences">
<c:forEach items="${conferences}" var="conference">
    <c:set value="${conferences[i]}" var="conf" />
    <div class="conference">
        <div class="summary">
            <h2 class="title"><a href="view.htm">${conference.confName}</a></h2>
            <div class="date">${conference.startDate}</div>
            <div class="date">${conference.duration}</div>
            <div class="description">${conference.agenda}</div>
            <div class="url"><a href="http://www.webex.com">Go to conference</a></div>
        </div>
        <div class="participants">
            <h2>Host</h2>
            <div class="host">James Wilson</div>
            <div class="invitees">
                ${fn:length(conference.attendeeIds)} Invitees
            </div>
        </div>
        <div class="buttons">
            <button value="edit">EDIT</button> <button value="delete">DELETE</button>
        </div>
    </div>
</c:forEach>
</div>
<jsp:directive.include file="/templates/footer.jsp"/>
