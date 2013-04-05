<jsp:directive.include file="/templates/includes.jsp"/>
<jsp:directive.include file="/templates/header.jsp"/>

<link rel="stylesheet" type="text/css" href="styles.css" />

<h1>Webex</h1>
<div class="conference">
    <form method="POST">
        <div class="field"><label>Title:</label><input type="text" name="title" /></div>
        <div class="field"><label>Start Date:</label><input type="text" name="startDate" /></div>
        <div class="field"><label>Start Date:</label><input type="text" name="endDate" /></div>
        <div class="field"><label>Description:</label><input type="textarea" name="description" /></div>
        <div class="field"><label>URL:</label><input type="text" name="url" /></div>
        <div class="buttons"><button value="save">Save</button></div>
    </form>
</div>
<jsp:directive.include file="/templates/footer.jsp"/>
