<jsp:directive.include file="/templates/includes.jsp"/>
<jsp:directive.include file="/templates/header.jsp"/>

<link rel="stylesheet" type="text/css" href="styles.css" />

<h1>Webex</h1>
<div class="conference">
    <form:form method="POST" commandName="conferenceForm">
        <form:hidden path="siteId" />
        <div class="field">
            <form:label path="confName">Conference Name</form:label><form:input path="confName" />
        </div>
        <div class="field">
            <form:label path="meetingType">Meeting Type</form:label><form:input path="meetingType" />
        </div>
        <div class="field">
            <form:label path="agenda">Agenda</form:label><form:input path="agenda" />
        </div>
        <div class="field">
            <form:label path="hostUserId">Host User ID</form:label><form:input path="hostUserId" />
        </div>
        <div class="field">
            <form:label path="maxUserNumber">Max User Number</form:label>
            <form:input path="maxUserNumber" />
        </div>
        <div class="field">
            <form:checkboxes path="attendeeIds" items="${siteUsers}" itemLabel="displayName" itemValue="id" />
        </div>
        <div class="field">
            <form:label path="chatEnabled">Chat Enabled</form:label><form:checkbox path="chatEnabled" />
        </div>
        <div class="field">
            <form:label path="pollEnabled">Poll Enabled</form:label><form:checkbox path="pollEnabled" />
        </div>
        <div class="field">
            <form:label path="audioVideoEnabled">Audio/Video Enabled</form:label>
            <form:checkbox path="audioVideoEnabled" />
        </div>
        <div class="field">
            <form:label path="date">Date</form:label><form:select path="date" items="${dates}" />
            <form:label path="month">Month</form:label><form:select path="month" items="${months}" />
            <form:label path="year">Year</form:label><form:select path="year" items="${years}" />
        </div>
        <div class="field">
            <form:label path="hour">Hour</form:label><form:select path="hour" items="${hours}"/>
            <form:label path="minute">Minute</form:label><form:select path="minute" items="${minutes}"  />
            <form:label path="ampm">AM/PM</form:label>
            <form:select path="ampm">
                <form:option value="AM" />
                <form:option value="PM" />
            </form:select>
        </div>
        <div class="field">
            <form:label path="duration">Duration (in minutes)</form:label><form:input path="duration" />
        </div>
        <div class="field">
            <form:label path="timeZoneID">Time Zone (e.g. 1)</form:label>
            <form:input path="timeZoneID" />
        </div>
        <div class="field">
            <form:label path="telephonySupport">Telephony Support</form:label>
            <form:input path="telephonySupport" />
        </div>
        <div class="field">
            <form:label path="extTelephonyDescription">Extended Telephony Description</form:label>
            <form:input path="extTelephonyDescription" />
        </div>
        <div class="field"><input type="submit" value="Edit"/></div>
    </form:form>
</div>
<jsp:directive.include file="/templates/footer.jsp"/>
