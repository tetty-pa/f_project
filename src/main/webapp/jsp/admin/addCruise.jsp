
<html>
<head>
    <title>Add Cruise Page</title>
</head>
<body>
<b>Add Cruise Page</b>
<div class="form">
    <form id="comm1" method="POST" action="${pageContext.request.contextPath}/controller/">
        <input type="hidden" name="command" value="addCruise"/>
        <div class="field"><%--
            cruise.setName(name);
            cruise.setDescription(description);
            cruise.setNumberOfPorts(numberOfPorts);
            cruise.setPrice(price);
            cruise.setRoute(route);
            cruise.setStartDate(startDate);
            cruise.setEndDate(endDate);
            cruise.setLinerId(linerId);--%>
            <input type="text" name="name" value="" required/></label>name<br>
        </div>
        <div class="field">
            <input type="text" name="description" value="" required/></label>description<br>
        </div>
        <div class="field">
            <input type="text" name="numberOfPorts" value="" required/></label>numberOfPorts<br>
        </div>
        <div class="field">
            <input type="text" name="price" value="" required/></label>price<br>
        </div>
        <div class="field">
            <input type="text" name="route" value="" required/></label>route<br>
        </div>
        <div class="field">
            <input type="text" name="startDate" value="" required/></label>startDate<br>
        </div>
        <div class="field">
            <input type="text" name="endDate" value="" required/></label>endDate<br>
        </div>
        <div class="field">
            <input type="text" name="linerId" value="" required/></label>linerId<br>
        </div>
        <br>
        <div class="field">
            <input type="reset"/>
        </div>
        <br>
        <div class="field">
            <input type="submit" value="submit" name="registration"/>
        </div>

    </form>


</div>
</body>
</html>
