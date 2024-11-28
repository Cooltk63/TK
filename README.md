Executing task: javac -d out -classpath WebContent\WEB-INF\lib\* D:\Dashboard_local\sources.txt 

error: invalid flag: D:\Dashboard_local\sources.txt
Usage: javac <options> <source files>
use --help for a list of possible options


This is my tasks.json file
{
    "version": "2.0.0",
    "tasks": [
        {
            "label": "Compile Java",
            "type": "shell",
            "command": "javac",
            "args": [
                "-d",
                "out",
                "-classpath",
                "WebContent/WEB-INF/lib/*",
                "${fileDirname}/**/*.java"
            ],
            "windows": {
                "args": [
                    "-d",
                    "out",
                    "-classpath",
                    "WebContent\\WEB-INF\\lib\\*",
                    "${workspaceFolder}\\sources.txt"
                ]
            },
            "problemMatcher": [],
            "group": "build"
        },
        {
            "label": "Build WAR",
            "type": "shell",
            "command": "jar",
            "args": [
                "-cvf",
                "out/Dashboard.war",
                "-C",
                "out",
                ".",
                "-C",
                "WebContent",
                "."
            ],
            "group": {
                "kind": "build",
                "isDefault": true
            },
            "dependsOn": "Compile Java",
            "problemMatcher": []
        }
    ]
}
