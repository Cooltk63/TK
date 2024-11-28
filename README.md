javac -d ./build -classpath "./libs/*" ./src/main/java/com/example/controller/*.java



mkdir -p ./dist/YourApp/WEB-INF/classes
cp -r ./build/* ./dist/YourApp/WEB-INF/classes/
cp -r ./src/main/webapp/* ./dist/YourApp/
cp -r ./libs ./dist/YourApp/WEB-INF/lib/



{
  "version": "2.0.0",
  "tasks": [
    {
      "label": "Compile Java",
      "type": "shell",
      "command": "javac",
      "args": [
        "-d",
        "build",
        "-classpath",
        "libs/*",
        "src/main/java/com/example/controller/*.java"
      ],
      "group": {
        "kind": "build",
        "isDefault": true
      },
      "problemMatcher": "$javac"
    },
    {
      "label": "Build WAR",
      "type": "shell",
      "command": "jar",
      "args": [
        "-cvf",
        "dist/YourApp.war",
        "-C",
        "build",
        ".",
        "-C",
        "src/main/webapp",
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

