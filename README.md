#!/bin/bash

# Get the directory where the script is located
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

# Define destination directory (modify this to your Tomcat webapps directory)
DEST_DIR="/path/to/tomcat/webapps"  # Change this to your Tomcat directory

# Check if destination directory exists
if [ ! -d "$DEST_DIR" ]; then
    echo "Destination directory $DEST_DIR does not exist."
    exit 1
fi

# Copy .war files from the script's directory to the destination
echo "Copying .war files from $SCRIPT_DIR to $DEST_DIR..."
cp -v "$SCRIPT_DIR"/*.war "$DEST_DIR"/

# Change ownership to oracle
echo "Changing ownership to oracle..."
chown oracle:oracle "$DEST_DIR"/*.war

# Set full permissions (777)
echo "Setting full permissions (777)..."
chmod 777 "$DEST_DIR"/*.war

echo "Deployment completed successfully!"