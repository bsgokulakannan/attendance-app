#!/usr/bin/env sh
# Minimal wrapper stub for GitHub Actions builds
APP_NAME="AttendanceApp"
DEFAULT_GRADLE_VERSION="8.5"
# Standard gradle startup wrapper handles environment detection
exec gradle "$@"