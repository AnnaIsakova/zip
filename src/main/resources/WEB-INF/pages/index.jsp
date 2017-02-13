<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ZIP</title>
  </head>
  <body>
      <form id="form" method="POST" action="uploadMultipleFile" enctype="multipart/form-data">
          <div id="dynamicInput"><input type="file" name="file" id="input_file"/></div>
          <p><input type="submit" value="Submit" /></p>
      </form>
      <p><button id="ifile" onclick="inputBtn('dynamicInput')">Add file</button></p>

      <script>
          function inputBtn(divName){
              var newdiv = document.createElement('div');
              newdiv.innerHTML = "<br><input type='file' name='file'>";
              document.getElementById(divName).appendChild(newdiv);
          }
      </script>
  </body>
</html>
