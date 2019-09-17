call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runbrowser
echo.
echo There were errors witch running runcrud.bat - breaking work
goto fail

:runbrowser
start chrome "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
echo There were errors with running the browser - breaking work
goto fail

:fail
echo.
echo There were errors.

:end
echo.
echo Work is finished.