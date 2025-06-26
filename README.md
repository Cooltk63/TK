@echo off
setlocal EnableDelayedExpansion
set /a total=0
set "targetFolder=C:\Path\To\Your\Folder"

for /r "%targetFolder%" %%f in (*.js *.jsx) do (
    for /f %%l in ('find /c /v "" "%%f"') do (
        set /a total+=%%l
    )
)

echo Total lines in .js and .jsx files: !total!