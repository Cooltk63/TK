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
            "problemMatcher": "$javac",
            "group": {
                "kind": "build",
                "isDefault": true
            }
        },
        {
            "label": "Build WAR",
            "type": "shell",
            "command": "jar",
            "args": [
                "-cvf",
                "dist/Dashboard.war",
                "-C",
                "out",
                ".",
                "-C",
                "WebContent",
                "."
            ],
            "group": {
                "kind": "build",
                "isDefault": false
            },
            "dependsOn": "Compile Java"
        }
    ]
}

Above is my tasks.json file 

below error i am getting on running the 
javac -d out -classpath WebContent\WEB-INF\lib\* -encoding ISO-8859-1  @D:\Dashboard_local\sources.txt  this command inside "cmd" as per your instruction

