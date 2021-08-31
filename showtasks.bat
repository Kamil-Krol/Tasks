call runcrud.bat
if "%ERRORLEVEL%" == "0" goto opensite

:fail
echo.
echo There were errors
goto end

:opensite
explorer "https://www.google.com/"
explorer "http://localhost:8080/crud/v1/task/getTasks"

:end
echo Program end