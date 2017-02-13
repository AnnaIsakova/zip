<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>ZIP</title>
    </head>
    <body>
        <div>
            <p><a href="/download/${name}">${name}</a></p>
        </div>
        <form action="/all_files" method="POST">
            <input type="submit" value="Show all files" />
        </form>
    </body>
</html>
